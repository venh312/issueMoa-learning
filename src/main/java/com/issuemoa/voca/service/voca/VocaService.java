package com.issuemoa.voca.service.voca;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.voca.common.UsersRestApi;
import com.issuemoa.voca.domain.learn.QVocaLearn;
import com.issuemoa.voca.domain.voca.QVoca;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VocaService {
    private final JPAQueryFactory jpaQueryFactory;

    QVoca voca = QVoca.voca;
    QVocaLearn vocaLearn = QVocaLearn.vocaLearn;
    private final UsersRestApi usersRestApi;

    public BooleanExpression eqId(Long id) {
        if (id == null) id = 0L;
        return vocaLearn.userId.eq(id);
    }

    public HashMap<String, Object> findAll(HttpServletRequest httpServletRequest, Integer offset, Integer limit) throws JsonProcessingException {
        HashMap<String, Object> resultMap = new HashMap<>();

        Long userId = null;

        // 로그인 상태 라면 본인의 학습 진도를 위해 설정
        HashMap<String, Object> userInfo = usersRestApi.getUserInfo(httpServletRequest);
        if (userInfo != null) userId = ((Long) userInfo.get("id"));

        List<VocaResponse> list = jpaQueryFactory
            .select(Projections.constructor(VocaResponse.class,
                voca.id,
                voca.word,
                voca.mean
            ))
            .from(voca)
            .where(
                JPAExpressions.selectFrom(vocaLearn)
                    .where(vocaLearn.vocaId.eq(voca.id)
                    .and(vocaLearn.learnYn.eq("Y")
                    .and(eqId(userId))))
                    .notExists()
            )
            .offset(offset)
            .limit(limit)
            .orderBy(voca.id.asc())
            .fetch();

        long totalCnt = (long) jpaQueryFactory
            .select(voca.count())
            .from(voca)
            .where(
                JPAExpressions.selectFrom(vocaLearn)
                    .where(vocaLearn.vocaId.eq(voca.id)
                    .and(vocaLearn.learnYn.eq("Y")
                    .and(eqId(userId))))
                    .notExists()
            )
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
