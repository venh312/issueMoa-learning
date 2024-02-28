package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.presentation.message.RestMessage;
import com.issuemoa.learning.application.InterviewService;
import com.issuemoa.learning.presentation.dto.interviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Interview", description = "Interview API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class InterviewController {
    private final InterviewService interviewService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = interviewResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재 하지 않는 리소스 접근")})
    @Operation(summary = "인터뷰 목록", description = "인터뷰 목록을 불러온다.")
    @GetMapping("/interview")
    public ResponseEntity<RestMessage> findAll(
            @Schema(description = "NETWORK/DATABASE/BACKEND/SECURITY/AGDS/CRYPTO/OS")
            @RequestParam(value = "category", defaultValue = "BACKEND") String category) {
        return ResponseEntity.ok()
                    .headers(new HttpHeaders())
                    .body(new RestMessage(HttpStatus.OK, interviewService.findAll(category)));
    }
}
