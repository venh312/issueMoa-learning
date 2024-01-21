package com.issuemoa.learning.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.learning.message.RestMessage;
import com.issuemoa.learning.service.interview.InterviewService;
import com.issuemoa.learning.service.voca.VocaResponse;
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

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Interview", description = "Interview API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class InterviewController {
    private final InterviewService interviewService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = VocaResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")})
    @Operation(summary = "인터뷰 목록", description = "인터뷰 목록을 불러온다.")
    @GetMapping("/interview")
    public ResponseEntity<RestMessage> findAll(
            HttpServletRequest httpServletRequest,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit) throws JsonProcessingException {

        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(new RestMessage(HttpStatus.OK, interviewService.findAll(httpServletRequest, offset, limit)));
    }
}
