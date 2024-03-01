package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.grade.QGradeExp;
import com.issuemoa.learning.presentation.dto.GradeExpResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GradeExpService {
    private final JPAQueryFactory jpaQueryFactory;
    private final QGradeExp gradeExp = QGradeExp.gradeExp;

    public List<GradeExpResponse> findAll() {
        return jpaQueryFactory.select(
            Projections.constructor(GradeExpResponse.class,
                gradeExp.id,
                gradeExp.gradeCode,
                gradeExp.standard,
                gradeExp.registerTime,
                gradeExp.modifyTime
            ))
            .from(gradeExp)
            .orderBy(gradeExp.standard.desc())
            .fetch();
    }
}
