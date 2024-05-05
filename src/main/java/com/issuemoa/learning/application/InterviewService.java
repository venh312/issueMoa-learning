package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.interview.Interview;
import com.issuemoa.learning.domain.interview.InterviewRepository;
import com.issuemoa.learning.domain.interview.QInterview;
import com.issuemoa.learning.presentation.dto.InterviewRequest;
import com.issuemoa.learning.presentation.dto.InterviewResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InterviewService {
    private final JPAQueryFactory jpaQueryFactory;
    private final InterviewRepository interviewRepository;
    private final QInterview interview = QInterview.interview;

    @Cacheable(value = "interview", key = "#category", cacheManager = "contentCacheManager", unless = "#result == null")
    public HashMap<String, Object> findAll(String category){
        HashMap<String, Object> resultMap = new HashMap<>();

        List<InterviewResponse> list = jpaQueryFactory
            .select(Projections.constructor(InterviewResponse.class,
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

    @Transactional
    public InterviewResponse update(InterviewRequest request) {
        Interview interview = interviewRepository.findById(request.id())
                                        .orElseThrow(() -> new IllegalArgumentException("[Interview] Not Found ::" + request.id()));

        interview.update(request.category(), request.question(), request.answer(), request.modifyId());

        return new InterviewResponse(interview.getId(), interview.getCategory(), interview.getQuestion(), interview.getAnswer());
    }
}
