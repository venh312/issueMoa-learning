package com.issuemoa.learning.domain.grade;

import com.issuemoa.learning.domain.BaseTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "grade_exp")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GradeExp extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gradeCode;
    private String standard;
    private Long registerId;
    private Long modifyId;

    @Schema(name = "Grade Exp Response")
    @Getter
    public static class Response {
        @Schema(description = "IDX")
        private Long id;

        @Schema(description = "등급 코드")
        private String gradeCode;

        @Schema(description = "등급 달성 목표")
        private String standard;

        @Schema(description = "등록자 ID")
        private Long registerId;

        @Schema(description = "변경자 ID")
        private Long modifyId;

        @Schema(description = "등록시간")
        private String registerTime;

        @Schema(description = "변경시간")
        private String modifyTime;

        public Response(Long id, String gradeCode, String standard) {
            this.id = id;
            this.gradeCode = gradeCode;
            this.standard = standard;
        }

        public Response(Long id, String gradeCode, String standard, Long registerId, Long modifyId, LocalDateTime registerTime, LocalDateTime modifyTime) {
            this.id = id;
            this.gradeCode = gradeCode;
            this.standard = standard;
            this.registerId = registerId;
            this.modifyId = modifyId;
            this.registerTime = toStringDateTime(registerTime);
            this.modifyTime = toStringDateTime(modifyTime);
        }
    }
}
