package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.exception.InterviewFavoritesExistsException;
import com.issuemoa.learning.domain.interview.favorites.InterviewFavorites;
import com.issuemoa.learning.domain.interview.favorites.InterviewFavoritesRepository;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesRequest;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesResponse;
import com.issuemoa.learning.presentation.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
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

    @Transactional
    public Long save(String token, InterviewFavoritesRequest request) {
        Long userId = tokenProvider.getUserId(token);
        Optional<InterviewFavorites> interviewFavorites = interviewFavoritesRepository.findByInterviewIdAndRegisterId(request.interviewId(), userId);

        if (interviewFavorites.isPresent())
            throw new InterviewFavoritesExistsException("이미 등록된 인터뷰 ID 입니다.");

        return interviewFavoritesRepository.save(request.toEntity(userId)).getId();
    }
}
