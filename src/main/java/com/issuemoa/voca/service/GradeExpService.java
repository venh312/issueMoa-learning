package com.issuemoa.voca.service;

import com.issuemoa.voca.domain.grade.GradeExp;
import com.issuemoa.voca.domain.grade.GradeExpRepository;
import com.issuemoa.voca.domain.grade.QGradeExp;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GradeExpService {
    private final GradeExpRepository gradeExpRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private QGradeExp gradeExp = QGradeExp.gradeExp;

    public List<GradeExp.Response> findAll() {
        return jpaQueryFactory.select(
            Projections.constructor(GradeExp.Response.class,
                gradeExp.id,
                gradeExp.gradeCode,
                gradeExp.standard
            ))
            .from(gradeExp)
            .orderBy(gradeExp.standard.desc())
            .fetch();
    }


}
