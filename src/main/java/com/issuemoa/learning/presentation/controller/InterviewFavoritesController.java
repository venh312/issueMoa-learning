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
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/interview")
public class InterviewFavoritesController {
    private final InterviewFavoritesService interviewFavoritesService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인터뷰 관심 목록 조회 성공",
                content = @Content(schema = @Schema(implementation = InterviewFavoritesResponse.class)))})
    @Operation(summary = "인터뷰 관심 목록", description = "사용 여부(useYn) 이 Y인 인터뷰 관심 목록을 조회한다.")
    @GetMapping("/favorites")
    public ResponseEntity<Map> findByRegisterId(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(interviewFavoritesService.findByRegisterId(token));
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "인터뷰 관심 등록/해제 성공")})
    @Operation(summary = "인터뷰 관심 등록/해제", description = "인터뷰 관심 등록/해제한다. (이미 등록되어 있을 경우 사용 여부(useYn)를 변경한다.")
    @PostMapping("/favorites")
    public ResponseEntity<Long> findByRegisterId(
                @RequestHeader("Authorization") String token,
                @Valid @RequestBody InterviewFavoritesRequest request) {
        return ResponseEntity.ok(interviewFavoritesService.save(token, request));
    }
}
