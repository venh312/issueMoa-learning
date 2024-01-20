package com.issuemoa.learning.domain.vocalearn;

import com.issuemoa.learning.domain.BaseTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "voca_learn")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocaLearn extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long vocaId;
    private String learnYn;
    private Long registerId;
    private Long modifyId;

    @Builder
    public VocaLearn(Long id, Long userId, Long vocaId, String learnYn, Long registerId, Long modifyId) {
        this.id = id;
        this.userId = userId;
        this.vocaId = vocaId;
        this.learnYn = learnYn;
        this.registerId = registerId;
        this.modifyId = modifyId;
    }

    @Getter
    @Setter
    @ToString
    public static class Request {
        private Long userId;
        private Long vocaId;
        private String learnYn;
        private Long registerId;
        private Long modifyId;

        public VocaLearn toEntity() {
            return VocaLearn.builder()
                .userId(this.userId)
                .vocaId(this.vocaId)
                .learnYn(this.learnYn)
                .registerId(userId)
                .modifyId(userId)
                .build();
        }
    }

    @Schema(name="Voca Learn Response")
    @Getter
    public static class Response {
        @Schema(description = "IDX")
        private Long id;

        @Schema(description = "사용자 ID")
        private Long userId;

        @Schema(description = "Voca ID")
        private Long vocaId;

        @Schema(description = "학습 여부 (Y/N)")
        private String learnYn;

        public Response(Long id, Long userId, Long vocaId, String learnYn) {
            this.id = id;
            this.userId = userId;
            this.vocaId = vocaId;
            this.learnYn = learnYn;
        }
    }
}
