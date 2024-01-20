package com.issuemoa.learning.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.learning.message.RestMessage;
import com.issuemoa.learning.service.voca.VocaResponse;
import com.issuemoa.learning.service.voca.VocaService;
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
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Tag(name = "Voca", description = "Voca API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaController {
    private final VocaService vocaService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = VocaResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근")})
    @Operation(summary = "Voca 목록", description = "Voca 목록을 불러온다.")
    @GetMapping("/voca")
    public ResponseEntity<RestMessage> findAll(
        HttpServletRequest httpServletRequest,
        @RequestParam(required = false, defaultValue = "0") Integer offset,
        @RequestParam(required = false, defaultValue = "20") Integer limit) throws JsonProcessingException {

        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, vocaService.findAll(httpServletRequest, offset, limit)));
    }
}
