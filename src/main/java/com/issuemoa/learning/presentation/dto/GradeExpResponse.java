package com.issuemoa.learning.presentation.dto;

import com.issuemoa.learning.domain.BaseTime;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Schema(name = "Grade Exp Response")
public record GradeExpResponse(
        @Schema(description = "IDX") Long id,
        @Schema(description = "등급 코드") String gradeCode,
        @Schema(description = "등급 달성 목표") String standard,
        @Schema(description = "등록시간") LocalDateTime registerTime,
        @Schema(description = "변경시간") LocalDateTime modifyTime)
{
    public String getRegisterTime() {
        return BaseTime.toStringDateTime(registerTime);
    }

    public String getModifyTime() {
        return BaseTime.toStringDateTime(modifyTime);
    }
}
