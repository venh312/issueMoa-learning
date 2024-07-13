package com.issuemoa.learning.application;

import com.issuemoa.learning.domain.grade.GradeExpRepository;
import com.issuemoa.learning.presentation.dto.GradeExpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeExpService {
    private final GradeExpRepository gradeExpRepository;

    public List<GradeExpResponse> findAll() {
        return gradeExpRepository.findAll(Sort.by(Sort.Direction.ASC, "standard"))
                .stream()
                .map(GradeExpResponse::toDto)
                .toList();
    }
}
