package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.interview.favorites.InterviewFavorites;
import com.issuemoa.learning.domain.interview.favorites.InterviewFavoritesRepository;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesRequest;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesResponse;
import com.issuemoa.learning.presentation.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InterviewFavoritesService {
    private final InterviewFavoritesRepository interviewFavoritesRepository;
    private final TokenProvider tokenProvider;

    public Map<String, Object> findByRegisterId(String token){
        Map<String, Object> resultMap = new HashMap<>();

        Long userId = tokenProvider.getUserId(token);
        resultMap.put("list", interviewFavoritesRepository.findUserInterviewFavorites(userId).stream().map(InterviewFavoritesResponse::toDto));

        return resultMap;
    }

    public List<Long> findInterviewFavoritesIdByRegisterId(String token){
        Long userId = tokenProvider.getUserId(token);
        return interviewFavoritesRepository.findInterviewFavoritesIdByRegisterId(userId);
    }

    @Transactional
    public Long save(String token, InterviewFavoritesRequest request) {
        Long userId = tokenProvider.getUserId(token);
        Optional<InterviewFavorites> interviewFavorites = interviewFavoritesRepository.findByInterviewIdAndRegisterId(request.interviewId(), userId);

        if (interviewFavorites.isPresent()) {
            interviewFavorites.get().updateUseYn(request.useYn());
        } else {
            interviewFavoritesRepository.save(request.toEntity(userId));
        }

        return request.interviewId();
    }
}
