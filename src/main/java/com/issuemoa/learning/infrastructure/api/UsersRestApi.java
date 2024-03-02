package com.issuemoa.learning.infrastructure.api;

import com.issuemoa.learning.domain.exception.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

    private HashMap<String, Object> getUserInfo(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("==> [UsersRestApi.getUserInfo] BearerToken :: {}", bearerToken);

        if (StringUtils.isBlank(bearerToken) || bearerToken.contains("undefined")) return null;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", bearerToken);

        try {
            return ConvertUtil.toUserInfo(new RestTemplate().exchange(endpointUserInfo, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody());
        } catch (Exception e) {
            throw new JsonProcessingException(e.getMessage());
        }
    }

    public Long getUserId(HttpServletRequest httpServletRequest) {
        HashMap<String, Object> userInfo = getUserInfo(httpServletRequest);
        if (userInfo == null) return null;
        return (Long) userInfo.get("id");
    }

}
