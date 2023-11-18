package com.issuemoa.voca.service;

import com.issuemoa.voca.domain.QVoca;
import com.issuemoa.voca.domain.Voca;
import com.issuemoa.voca.domain.VocaRepository;
import com.querydsl.core.types.Projections;
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

    public HashMap<String, Object> findAll(Voca.Request request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<Voca.Response> list = jpaQueryFactory
            .select(Projections.constructor(Voca.Response.class,
                voca.id,
                voca.word,
                voca.mean
            ))
            .from(voca)
            .offset(page)
            .limit(pageSize)
            .orderBy(voca.id.desc())
            .fetch();

        Long totalCnt = (long) jpaQueryFactory.select(voca.count()).from(voca).fetchOne();

        int totalPage = (int) Math.ceil((float) totalCnt / pageSize);
        totalPage = totalPage == 0 ? 1 : totalPage;

        resultMap.put("list", list);
        resultMap.put("request", request);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);
        resultMap.put("totalCnt", totalCnt);
        resultMap.put("totalPage", totalPage);

        return resultMap;
    }
}
