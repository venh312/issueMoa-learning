package com.issuemoa.learning.domain.vocalearn;

import com.issuemoa.learning.domain.BaseTime;
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
}
