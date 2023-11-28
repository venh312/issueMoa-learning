package com.issuemoa.voca.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.issuemoa.voca.common.UsersRestApi;
import com.issuemoa.voca.domain.voca.Voca;
import com.issuemoa.voca.message.RestMessage;
import com.issuemoa.voca.service.VocaLearnService;
import com.issuemoa.voca.service.VocaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VocaController {
    private final VocaService vocaService;
    private final VocaLearnService vocaLearnService;
    private final UsersRestApi usersRestApi;

    @GetMapping("/voca/list")
    public ResponseEntity<RestMessage> findAll(
        Voca.Request request,
        HttpServletRequest httpServletRequest,
        @RequestParam(required = false, defaultValue = "0") Integer offset,
        @RequestParam(required = false, defaultValue = "20") Integer limit) throws JsonProcessingException {

        HashMap<String, Object> userInfo = usersRestApi.getUserInfo(httpServletRequest);
        if (userInfo != null)
            request.setUserId((Long) userInfo.get("id"));

        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, vocaService.findAll(request, offset, limit)));
    }
}
