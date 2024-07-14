package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.application.InterviewFavoritesService;
import com.issuemoa.learning.application.InterviewService;
import com.issuemoa.learning.presentation.dto.InterviewRequest;
import com.issuemoa.learning.presentation.dto.InterviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Interview", description = "Interview API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class InterviewController {
    private final InterviewService interviewService;
    private final InterviewFavoritesService interviewFavoritesService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공",
            content = @Content(schema = @Schema(implementation = InterviewResponse.class)))})
    @Operation(summary = "인터뷰 목록", description = "인터뷰 목록을 불러온다.")
    @GetMapping("/interview")
    public ResponseEntity<HashMap<String, Object>> findAll(
            @Schema(description = "NETWORK/DATABASE/BACKEND/SECURITY/AGDS/CRYPTO/OS")
            @RequestParam(value = "category", defaultValue = "BACKEND") String category,
            @RequestHeader(value = "Authorization", required = false) String token){

        HashMap<String, Object> interviewMap = interviewService.findAll(category);
        interviewMap.put("favoritesId", interviewFavoritesService.findInterviewFavoritesIdByRegisterId(token));

        return ResponseEntity.ok(interviewMap);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정 성공",
            content = @Content(schema = @Schema(implementation = InterviewResponse.class)))})
    @Operation(summary = "인터뷰 수정", description = "인터뷰 정보를 수정한다.")
    @PutMapping("/interview")
    public ResponseEntity<InterviewResponse> updateInterview(@RequestBody InterviewRequest request){
        return ResponseEntity.ok(interviewService.update(request));
    }
}
