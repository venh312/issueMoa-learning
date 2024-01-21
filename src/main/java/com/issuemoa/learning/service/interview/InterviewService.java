package com.issuemoa.learning.service.interview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.learning.common.UsersRestApi;
import com.issuemoa.learning.domain.interview.InterviewRepository;
import com.issuemoa.learning.domain.interview.QInterview;
import com.issuemoa.learning.service.interview.interviewResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InterviewService {
    private final UsersRestApi usersRestApi;
    private final JPAQueryFactory jpaQueryFactory;
    private final QInterview interview = QInterview.interview;

    public HashMap<String, Object> findAll(HttpServletRequest httpServletRequest, Integer offset, Integer limit) throws JsonProcessingException {
        HashMap<String, Object> resultMap = new HashMap<>();

        Long userId = null;

        // 로그인 상태 라면 본인의 학습 진도를 위해 설정
        HashMap<String, Object> userInfo = usersRestApi.getUserInfo(httpServletRequest);
        if (userInfo != null) userId = ((Long) userInfo.get("id"));

        List<interviewResponse> list = jpaQueryFactory
            .select(Projections.constructor(interviewResponse.class,
                interview.id,
                interview.category,
                interview.question,
                interview.answer
            ))
            .from(interview)
//                .where(
//                    JPAExpressions.selectFrom(interviewLearn)
//                        .where(interviewLearn.interviewId.eq(interview.id)
//                            .and(interviewLearn.learnYn.eq("Y")
//                            .and(eqId(userId))))
//                        .notExists()
//                )
            .offset(offset)
            .limit(limit)
            .orderBy(interview.id.asc())
            .fetch();

        long totalCnt = (long) jpaQueryFactory
            .select(interview.count())
            .from(interview)
//            .where(
//                JPAExpressions.selectFrom(interviewLearn)
//                    .where(interviewLearn.interviewId.eq(interview.id)
//                    .and(interviewLearn.learnYn.eq("Y")
//                            .and(eqId(userId))))
//                .notExists()
//            )
            .fetchOne();

        int totalPage = (int) Math.ceil((float) totalCnt / limit);
        totalPage = totalPage == 0 ? 1 : totalPage;

        resultMap.put("list", list);
        resultMap.put("offset", offset);
        resultMap.put("limit", limit);
        resultMap.put("totalCnt", totalCnt);
        resultMap.put("totalPage", totalPage);

        return resultMap;
    }
}
