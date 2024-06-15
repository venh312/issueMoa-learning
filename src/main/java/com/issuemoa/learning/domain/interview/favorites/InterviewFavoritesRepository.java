package com.issuemoa.learning.domain.interview.favorites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface InterviewFavoritesRepository extends JpaRepository<InterviewFavorites, Long> {
    @Query(value = "select f from interview_favorites f  join fetch f.interview where f.registerId = :registerId and use_yn ='Y'")
    public List<InterviewFavorites> findUserInterviewFavorites(@Param("registerId") Long registerId);

    public Optional<InterviewFavorites> findByInterviewIdAndRegisterId(@Param("interviewId") Long interviewId, @Param("registerId") Long registerId);
}
