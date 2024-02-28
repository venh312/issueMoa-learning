package com.issuemoa.learning.infrastructure.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@Component
public class UsersRestApi {
    @Value("${endpoint.users.info}")
    private String endpointUserInfo;

    public HashMap<String, Object> getUserInfo(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("==> [getUserInfo] bearerToken: {}", bearerToken);

        if (bearerToken == null || bearerToken.isEmpty() || bearerToken.contains("undefined")) return null;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", bearerToken);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return ConvertUtil.toUserInfoMap(new RestTemplate().exchange(endpointUserInfo, HttpMethod.GET, entity, String.class).getBody());
    }

}
