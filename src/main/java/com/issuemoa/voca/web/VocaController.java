package com.issuemoa.voca.web;

import com.issuemoa.voca.domain.Voca;
import com.issuemoa.voca.message.RestMessage;
import com.issuemoa.voca.service.VocaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaController {
    private final VocaService vocaService;

    @GetMapping("/voca/list")
    public ResponseEntity<RestMessage> findAll(
        Voca.Request request,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "24") Integer pageSize) {

        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, vocaService.findAll(request, page, pageSize)));
    }
}
