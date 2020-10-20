package com.wellsfargo.fsd.its.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import com.wellsfargo.fsd.its.entities.Interview;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    List<Interview> findByInterviewName(String interviewName);
    List<Interview> findAllByInterviewer(String interviewer);
}
