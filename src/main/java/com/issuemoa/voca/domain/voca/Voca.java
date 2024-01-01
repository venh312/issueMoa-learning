package com.issuemoa.voca.domain.voca;

import com.issuemoa.voca.domain.BaseTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "voca")
public class Voca extends BaseTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String word;
    private String mean;
    private Long registerId;
    private Long modifyId;

    @Builder
    public Voca(Long id, String word, String mean, Long registerId, Long modifyId) {
        this.id = id;
        this.word = word;
        this.mean = mean;
        this.registerId = registerId;
        this.modifyId = modifyId;
    }

    @Getter
    @Setter
    public static class Request {
        private Long id;
        private String word;
        private String mean;
        private Long registerId;
        private Long modifyId;
        private Long userId;

        public Request(Long id, String word, String mean, Long registerId, Long modifyId, Long userId) {
            this.id = id;
            this.word = word;
            this.mean = mean;
            this.registerId = registerId;
            this.modifyId = modifyId;
            this.userId = userId;
        }
    }

    @Schema(description = "Voca 응답")
    @Getter
    public static class Response {
        @Schema(description = "IDX")
        private Long id;

        @Schema(description = "단어")
        private String word;

        @Schema(description = "의미")
        private String mean;

        @Schema(description = "등록자 ID")
        private Long registerId;

        @Schema(description = "변경자 ID")
        private Long modifyId;

        public Response(Long id, String word, String mean) {
            this.id = id;
            this.word = word;
            this.mean = mean;
        }
    }
}
