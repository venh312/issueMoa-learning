package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.voca.learn.VocaLearn;
import com.issuemoa.learning.domain.voca.learn.VocaLearnRepository;
import com.issuemoa.learning.presentation.dto.VocaLearnRequest;
import com.issuemoa.learning.presentation.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VocaLearnService {
    private final VocaLearnRepository vocaLearnRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long save(VocaLearnRequest request, String token){
        Long userId = tokenProvider.getUserId(token);
        if (userId == null) return 0L;

        Optional<VocaLearn> findLearn = vocaLearnRepository.findByUserIdAndVocaId(userId, request.vocaId());

        // 학습 기록이 있다면 learnYn 변경
        if (findLearn.isPresent()) {
            VocaLearn learn = findLearn.get();
            learn.updateLearnYn(request.learnYn());
            return learn.getId();
        }

        return vocaLearnRepository.save(request.toEntity(userId)).getId();
    }

    public int countByUserIdAndLearnYn(String token){
        Long userId = tokenProvider.getUserId(token);
        if (userId == null) return 0;
        return vocaLearnRepository.countByUserIdAndLearnYn(userId, "Y");
    }
}
