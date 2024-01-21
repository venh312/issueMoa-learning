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

    public HashMap<String, Object> findAll(String category) throws JsonProcessingException {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<interviewResponse> list = jpaQueryFactory
            .select(Projections.constructor(interviewResponse.class,
                interview.id,
                interview.category,
                interview.question,
                interview.answer
            ))
            .from(interview)
            .where(interview.category.eq(category))
            .orderBy(interview.id.asc())
            .fetch();

        resultMap.put("list", list);

        return resultMap;
    }
}
