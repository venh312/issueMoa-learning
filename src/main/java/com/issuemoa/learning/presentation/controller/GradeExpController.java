package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.application.GradeExpService;
import com.issuemoa.learning.presentation.dto.GradeExpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Grade Exp", description = "등급 API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class GradeExpController {
    private final GradeExpService gradeExpService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공",
            content = @Content(schema = @Schema(implementation = GradeExpResponse.class)))})
    @Operation(summary = "Grade Exp 목록", description = "등급 달성 목록을 불러온다.")
    @GetMapping("/grade-exp")
    public ResponseEntity<List<GradeExpResponse>> findAll(){
        return ResponseEntity.ok(gradeExpService.findAll());
    }
}
