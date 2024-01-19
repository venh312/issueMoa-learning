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
}
