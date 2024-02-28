package com.issuemoa.learning.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.learning.infrastructure.api.UsersRestApi;
import com.issuemoa.learning.domain.vocalearn.QVocaLearn;
import com.issuemoa.learning.domain.vocalearn.VocaLearnRepository;
import com.issuemoa.learning.presentation.dto.VocaLearnRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class VocaLearnService {
    private final VocaLearnRepository vocaLearnRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final UsersRestApi usersRestApi;
    private final QVocaLearn vocaLearn = QVocaLearn.vocaLearn;

    @Transactional
    public Long save(VocaLearnRequest request, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        HashMap<String, Object> userMap = usersRestApi.getUserInfo(httpServletRequest);

        Long userId = 0L;
        if (userMap == null) return 0L;

        userId = (Long) userMap.get("id");

        long result = jpaQueryFactory.select(vocaLearn.count())
                            .from(vocaLearn)
                            .where(vocaLearn.vocaId.eq(request.vocaId())
                            .and(vocaLearn.userId.eq(userId)))
                            .fetchOne();

        if (result > 0) {
            return jpaQueryFactory.update(vocaLearn)
                        .set(vocaLearn.learnYn, request.learnYn())
                        .where(vocaLearn.vocaId.eq(request.vocaId()).and(vocaLearn.userId.eq(userId)))
                        .execute();
        }

        return vocaLearnRepository.save(request.toEntity(userId)).getId();
    }

    public Long countByLearn(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        HashMap<String, Object> userMap = usersRestApi.getUserInfo(httpServletRequest);

        if (userMap == null) return 0L;

        return jpaQueryFactory
                    .select(vocaLearn.count())
                    .from(vocaLearn)
                    .where(vocaLearn.userId.eq((Long) userMap.get("id"))
                    .and(vocaLearn.learnYn.eq("Y")))
                    .fetchOne();
    }
}
