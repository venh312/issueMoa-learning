package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.presentation.dto.VocaResponse;
import com.issuemoa.learning.application.VocaService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Tag(name = "Voca", description = "Voca API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaController {
    private final VocaService vocaService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공",
            content = @Content(schema = @Schema(implementation = VocaResponse.class)))})
    @Operation(summary = "Voca 목록", description = "Voca 목록을 불러온다.")
    @GetMapping("/voca")
    public ResponseEntity<HashMap<String, Object>> findAll(
        @RequestHeader("Authorization") String token,
        @RequestParam(required = false, defaultValue = "0") Integer offset,
        @RequestParam(required = false, defaultValue = "20") Integer limit){

        return ResponseEntity.ok(vocaService.findAll(token, offset, limit));
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공",
            content = @Content(schema = @Schema(implementation = VocaResponse.class)))})
    @Operation(summary = "단어 다시보기 목록", description = "단어 다시보기 목록을 불러온다.")
    @GetMapping("/voca/retry")
    public ResponseEntity<HashMap<String, Object>> findByVocaRetry(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit){
        return ResponseEntity.ok(vocaService.findByVocaRetry(token, offset, limit));
    }
}
