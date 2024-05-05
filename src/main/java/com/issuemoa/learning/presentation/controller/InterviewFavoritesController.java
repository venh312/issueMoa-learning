package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.application.InterviewFavoritesService;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesRequest;
import com.issuemoa.learning.presentation.dto.InterviewFavoritesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class InterviewFavoritesController {
    private final InterviewFavoritesService interviewFavoritesService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                content = @Content(schema = @Schema(implementation = InterviewFavoritesResponse.class)))})
    @Operation(summary = "인터뷰 관심 목록", description = "인터뷰 관심 목록을 불러온다.")
    @GetMapping("/interview/favorites")
    public ResponseEntity<Map> findByRegisterId(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(interviewFavoritesService.findByRegisterId(httpServletRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공",
                    content = @Content(schema = @Schema(implementation = InterviewFavoritesRequest.class)))})
    @Operation(summary = "인터뷰 관심 등록", description = "인터뷰 관심 등록하기.")
    @PostMapping("/interview/favorites")
    public ResponseEntity<Long> findByRegisterId(HttpServletRequest httpServletRequest, InterviewFavoritesRequest request) {
        return ResponseEntity.ok(interviewFavoritesService.save(httpServletRequest, request));
    }
}
