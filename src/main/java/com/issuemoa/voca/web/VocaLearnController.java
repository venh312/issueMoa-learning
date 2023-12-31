package com.issuemoa.voca.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.voca.domain.learn.VocaLearn;
import com.issuemoa.voca.message.RestMessage;
import com.issuemoa.voca.service.VocaLearnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Tag(name = "Voca Learn", description = "Voca 학습 API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaLearnController {
    private final VocaLearnService vocaLearnService;

    @Operation(summary = "Voca 알고있어요", description = "학습한 단어를 등록한다.")
    @PostMapping("/learn")
    public ResponseEntity<RestMessage> save(
            @RequestBody VocaLearn.Request request,
            HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(new RestMessage(HttpStatus.OK, vocaLearnService.save(request, httpServletRequest)));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Operation(summary = "Voca 학습한 단어 개수", description = "학습한 단어 개수를 가져온다.")
    @GetMapping("/countLearn")
    public ResponseEntity<RestMessage> countByLearn(HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(new RestMessage(HttpStatus.OK, vocaLearnService.countByLearn(httpServletRequest)));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
