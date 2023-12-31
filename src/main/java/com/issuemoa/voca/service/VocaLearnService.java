package com.issuemoa.voca.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.voca.common.UsersRestApi;
import com.issuemoa.voca.domain.learn.QVocaLearn;
import com.issuemoa.voca.domain.learn.VocaLearn;
import com.issuemoa.voca.domain.learn.VocaLearnRepository;
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
    private QVocaLearn vocaLearn = QVocaLearn.vocaLearn;

    @Transactional
    public Long save(VocaLearn.Request request, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        HashMap<String, Object> userMap = usersRestApi.getUserInfo(httpServletRequest);
        if (userMap == null) return null;
        request.setUserId((Long) userMap.get("id"));

        long result = jpaQueryFactory.select(vocaLearn.count())
                            .from(vocaLearn)
                            .where(vocaLearn.vocaId.eq(request.getVocaId())
                                .and(vocaLearn.userId.eq(request.getUserId()))
                            )
                            .fetchOne();

        if (result > 0) {
            return jpaQueryFactory.update(vocaLearn)
                .set(vocaLearn.learnYn, request.getLearnYn())
                .where(vocaLearn.vocaId.eq(request.getVocaId())
                    .and(vocaLearn.userId.eq(request.getUserId()))
                )
                .execute();
        }

        return vocaLearnRepository.save(request.toEntity()).getId();
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
