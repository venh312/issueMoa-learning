package com.issuemoa.learning.web;

import com.issuemoa.learning.message.RestMessage;
import com.issuemoa.learning.service.grade.GradeExpService;
import com.issuemoa.learning.service.grade.GradeExpResponse;
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
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Grade Exp", description = "Grade Exp API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class GradeExpController {
    private final GradeExpService gradeExpService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GradeExpResponse.class))),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")})
    @Operation(summary = "Grade Exp 목록", description = "등급 달성 목록을 불러온다.")
    @GetMapping("/grade-exp")
    public ResponseEntity<RestMessage> findAll() {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, gradeExpService.findAll()));
    }
}
