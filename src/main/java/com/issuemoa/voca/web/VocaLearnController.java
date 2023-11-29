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
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<RestMessage> save(@RequestBody VocaLearn.Request request, HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(new RestMessage(HttpStatus.OK, vocaLearnService.save(request, httpServletRequest)));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

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
