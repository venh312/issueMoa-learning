package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.exception.InterviewFavoritesExistsException;
import com.issuemoa.learning.domain.exception.UsersNotFoundException;
import com.issuemoa.learning.domain.interview.favorites.InterviewFavoritesRepository;
import com.issuemoa.learning.infrastructure.api.UsersRestApi;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesRequest;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InterviewFavoritesService {
    private final InterviewFavoritesRepository interviewFavoritesRepository;
    private final UsersRestApi usersRestApi;

    public Map<String, Object> findByRegisterId(HttpServletRequest httpServletRequest){
        Map<String, Object> resultMap = new HashMap<>();

        Long userId = usersRestApi.getUserId(httpServletRequest);
        if (userId == null)
            throw new UsersNotFoundException("사용자 정보가 존재하지 않습니다. AccessToken을 확인해 주세요.");

        resultMap.put("list", interviewFavoritesRepository.findUserInterviewFavorites(userId).stream().map(InterviewFavoritesResponse::toDto));

        return resultMap;
    }

    @Transactional
    public Long save(HttpServletRequest httpServletRequest, InterviewFavoritesRequest request) {
        Long userId = usersRestApi.getUserId(httpServletRequest);
        if (userId == null)
            throw new UsersNotFoundException("사용자 정보가 존재하지 않습니다. AccessToken 을 확인해 주세요.");

        Optional interviewFavorites = interviewFavoritesRepository.findById(request.interviewId());
        if (interviewFavorites.isPresent())
            throw new InterviewFavoritesExistsException("이미 등록된 인터뷰 ID 입니다.");

        return interviewFavoritesRepository.save(request.toEntity(userId)).getId();
    }
}
