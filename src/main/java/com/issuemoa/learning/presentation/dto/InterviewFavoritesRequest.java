package com.issuemoa.learning.presentation.dto;

import com.issuemoa.learning.domain.interview.favorites.InterviewFavorites;
import io.swagger.v3.oas.annotations.media.Schema;

public record InterviewFavoritesRequest(
        @Schema(description = "인터뷰 IDX", required = true) Long interviewId,
        @Schema(description = "사용 여부(Y/N)", required = true) String useYn
) {

    public InterviewFavorites toEntity(Long registerId) {
        return InterviewFavorites.builder()
                    .interviewId(interviewId)
                    .useYn(useYn)
                    .registerId(registerId)
                    .build();
    }
}
