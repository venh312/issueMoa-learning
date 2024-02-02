package com.issuemoa.learning.service.vocalearn;

import com.issuemoa.learning.domain.vocalearn.VocaLearn;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="Voca Learn Response")
public record VocaLearnRequest(
        @Schema(description = "IDX") Long id,
        @Schema(description = "사용자 ID") Long userId,
        @Schema(description = "Voca ID") Long vocaId,
        @Schema(description = "학습 여부 (Y/N)") String learnYn) {
    public VocaLearn toEntity(Long userId) {
        return VocaLearn.builder()
                    .userId(userId)
                    .vocaId(this.vocaId)
                    .learnYn(this.learnYn)
                    .registerId(userId)
                    .modifyId(userId)
                    .build();
    }
}
