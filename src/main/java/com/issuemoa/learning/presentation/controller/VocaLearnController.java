package com.issuemoa.learning.presentation.controller;

import com.issuemoa.learning.presentation.dto.VocaLearnRequest;
import com.issuemoa.learning.application.VocaLearnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Voca Learn", description = "Voca 학습 API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaLearnController {
    private final VocaLearnService vocaLearnService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "등록 성공")})
    @Operation(summary = "Voca 알고 있어요", description = "학습한 단어의 학습 여부를 등록한다.")
    @PostMapping("/voca-learn")
    public ResponseEntity<Long> save(@RequestBody VocaLearnRequest request, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(vocaLearnService.save(request, token));
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공")})
    @Operation(summary = "Voca 학습한 단어 개수", description = "학습한 단어 개수를 가져온다.")
    @GetMapping("/voca-learn/count")
    public ResponseEntity<Long> countByLearn(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(vocaLearnService.countByLearn(token));
    }
}
