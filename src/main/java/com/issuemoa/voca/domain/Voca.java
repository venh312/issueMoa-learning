package com.issuemoa.voca.domain;

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

        public Request(Long id, String word, String mean, Long registerId, Long modifyId) {
            this.id = id;
            this.word = word;
            this.mean = mean;
            this.registerId = registerId;
            this.modifyId = modifyId;
        }
    }

    @Getter
    public static class Response {
        private Long id;
        private String word;
        private String mean;
        private Long registerId;
        private Long modifyId;

        public String getRegisterTime(LocalDateTime registerName) {
            return toStringDateTime(registerName);
        }

        public String getModifyName(LocalDateTime modifyName) {
            return toStringDateTime(modifyName);
        }

        public Response(Long id, String word, String mean, Long registerId, Long modifyId) {
            this.id = id;
            this.word = word;
            this.mean = mean;
            this.registerId = registerId;
            this.modifyId = modifyId;
        }
    }
}
