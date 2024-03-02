package com.issuemoa.learning.infrastructure.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

@Slf4j
public class ConvertUtil {
    public static HashMap<String, Object> toUserInfo(String strData) throws JsonProcessingException {
        HashMap<String, Object> result = new HashMap<>();;

        if (StringUtils.isBlank(strData)) {
            JsonNode jsonNode = new ObjectMapper().readTree(strData);

            JsonNode idNode = jsonNode.get("id");
            if (idNode != null)
                result.put("id", idNode.asLong());
            else
                log.info("[ConvertUtil] ID 이(가) 존재하지 않습니다.");

            JsonNode emailNode = jsonNode.get("email");
            if (emailNode != null)
                result.put("email", emailNode.toString());
            else
                log.info("[ConvertUtil] Email 이(가) 존재하지 않습니다.");
        }

        return result;
    }
}
