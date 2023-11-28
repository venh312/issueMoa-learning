package com.issuemoa.voca.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;

@Slf4j
public class ConvertUtil {
    public static HashMap<String, Object> toUserInfoMap(String strData) throws JsonProcessingException {
        HashMap<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(strData);

        // "data" 객체에서 "id" 필드 추출
        JsonNode dataNode = jsonNode.get("data");
        if (dataNode != null) {
            JsonNode idNode = dataNode.get("id");
            if (idNode != null) {
                long idValue = idNode.asLong();
                result.put("id", idValue);
                log.info("ID: {}", idValue);
            } else {
                log.info("ID not found in data.");
            }

            JsonNode emailNode = dataNode.get("email");
            if (emailNode != null) {
                result.put("email", emailNode.toString());
            } else {
                log.info("EMAIL not found in data.");
            }
        } else {
            log.info("EMAIL not found in data.");
        }

        return result;
    }

}
