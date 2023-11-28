package com.issuemoa.voca.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.voca.domain.learn.VocaLearn;
import com.issuemoa.voca.message.RestMessage;
import com.issuemoa.voca.service.VocaLearnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaLearnController {
    private final VocaLearnService vocaLearnService;

    @PostMapping("/learn")
    public ResponseEntity<RestMessage> save(@RequestBody VocaLearn.Request request, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, vocaLearnService.save(request, httpServletRequest)));
    }
}
