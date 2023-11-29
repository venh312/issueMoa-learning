package com.issuemoa.voca.service;

import com.issuemoa.voca.domain.learn.QVocaLearn;
import com.issuemoa.voca.domain.voca.QVoca;
import com.issuemoa.voca.domain.voca.Voca;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VocaService {
    private final JPAQueryFactory jpaQueryFactory;

    QVoca voca = QVoca.voca;
    QVocaLearn vocaLearn = QVocaLearn.vocaLearn;

    public BooleanExpression eqId(Long id) {
        if (id == null) id = 0L;
        return vocaLearn.userId.eq(id);
    }

    public HashMap<String, Object> findAll(Voca.Request request, Integer offset, Integer limit) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<Voca.Response> list = jpaQueryFactory
            .select(Projections.constructor(Voca.Response.class,
                voca.id,
                voca.word,
                voca.mean
            ))
            .from(voca)
            .where(
                JPAExpressions.selectFrom(vocaLearn)
                    .where(vocaLearn.vocaId.eq(voca.id)
                    .and(vocaLearn.learnYn.eq("Y")
                    .and(eqId(request.getUserId()))))
                    .notExists()
            )
            .offset(offset)
            .limit(limit)
            .orderBy(voca.id.asc())
            .fetch();

        long totalCnt = (long) jpaQueryFactory
            .select(voca.count())
            .from(voca)
            .where(
                JPAExpressions.selectFrom(vocaLearn)
                    .where(vocaLearn.vocaId.eq(voca.id)
                    .and(vocaLearn.learnYn.eq("Y")
                    .and(eqId(request.getUserId()))))
                    .notExists()
            )
            .fetchOne();

        int totalPage = (int) Math.ceil((float) totalCnt / limit);
        totalPage = totalPage == 0 ? 1 : totalPage;

        resultMap.put("list", list);
        resultMap.put("request", request);
        resultMap.put("offset", offset);
        resultMap.put("limit", limit);
        resultMap.put("totalCnt", totalCnt);
        resultMap.put("totalPage", totalPage);

        return resultMap;
    }
}
