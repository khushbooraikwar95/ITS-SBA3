package com.wellsfargo.fsd.its.services;



import java.util.List;

import com.wellsfargo.fsd.its.entities.Interview;

public interface InterviewService {
    Interview addInterview(Interview interview);

    void deleteInterview(int interviewId);

    Interview updateInterview(Interview interview);

    List<Interview> getInterviews();

    int getInterviewsCount();

    Interview findByInterviewId(int interviewId);

    List<Interview> findAllByInterviewName(String interviewName);

    List<Interview> findAllByInterviewer(String interviewer);

    List<Interview> findAllById(List<Integer> interviewIds);

    Interview addUsersToInterview(int interviewId, int userId);

    Interview removeUsersFromInterview(int interviewId, int userId);

    Interview updateInterviewStatus(int interviewId, String status);

}
