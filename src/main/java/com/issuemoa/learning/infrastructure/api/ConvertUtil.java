package com.issuemoa.learning.infrastructure.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import java.util.HashMap;

@Slf4j
public class ConvertUtil {
    public static HashMap<String, Object> toUserInfo(String strData) throws JsonProcessingException {
        HashMap<String, Object> resultMap = null;
        if (!StringUtils.isBlank(strData)) {
            // ObjectMapper 생성
            ObjectMapper objectMapper = new ObjectMapper();

            // Long으로 매핑하도록 설정
            objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);

            // JSON 문자열을 해시맵으로 변환
            resultMap = objectMapper.readValue(strData, HashMap.class);
        }
        return resultMap;
    }
}
