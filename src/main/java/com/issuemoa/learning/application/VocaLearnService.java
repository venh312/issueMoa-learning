package com.issuemoa.learning.application;

import com.issuemoa.learning.infrastructure.api.UsersRestApi;
import com.issuemoa.learning.domain.voca.learn.QVocaLearn;
import com.issuemoa.learning.domain.voca.learn.VocaLearnRepository;
import com.issuemoa.learning.presentation.dto.VocaLearnRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VocaLearnService {
    private final VocaLearnRepository vocaLearnRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final UsersRestApi usersRestApi;
    private final QVocaLearn vocaLearn = QVocaLearn.vocaLearn;

    @Transactional
    public Long save(VocaLearnRequest request, HttpServletRequest httpServletRequest){
        Long userId = usersRestApi.getUserId(httpServletRequest);
        long result = jpaQueryFactory.select(vocaLearn.count())
                        .from(vocaLearn)
                        .where(vocaLearn.vocaId.eq(request.vocaId())
                        .and(vocaLearn.userId.eq(userId)))
                        .fetchOne();

        // 이미 등록 했다면 learnYn 업데이트
        if (result > 0) {
            return jpaQueryFactory.update(vocaLearn)
                    .set(vocaLearn.learnYn, request.learnYn())
                    .where(vocaLearn.vocaId.eq(request.vocaId())
                    .and(vocaLearn.userId.eq(userId)))
                    .execute();
        }

        // 최초 등록
        return vocaLearnRepository.save(request.toEntity(userId)).getId();
    }

    public Long countByLearn(HttpServletRequest httpServletRequest){
        Long userId = usersRestApi.getUserId(httpServletRequest);
        return jpaQueryFactory
                .select(vocaLearn.count())
                .from(vocaLearn)
                .where(vocaLearn.userId.eq(userId)
                .and(vocaLearn.learnYn.eq("Y")))
                .fetchOne();
    }
}
