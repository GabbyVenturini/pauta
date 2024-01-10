package com.schedule.vote.repository;

import com.schedule.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByIdSchedule(Long idShedule);
}