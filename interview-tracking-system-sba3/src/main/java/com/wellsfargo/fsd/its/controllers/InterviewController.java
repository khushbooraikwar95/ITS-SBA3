package com.wellsfargo.fsd.its.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wellsfargo.fsd.its.entities.Interview;
import com.wellsfargo.fsd.its.entities.User;
import com.wellsfargo.fsd.its.services.InterviewService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @GetMapping({"", "/"})
    public List<Interview> getInterviews() {
        return interviewService.getInterviews();
    }

    @GetMapping("/{id}")
    public Interview getInterviewById(@PathVariable(value = "id") int id) {
        return interviewService.findByInterviewId(id);
    }

    @GetMapping("/interview/{name}")
    public List<Interview> getInterviewByName(@PathVariable(value = "name") String name) {
        return interviewService.findAllByInterviewName(name);
    }

    @GetMapping("/interviewer/{name}")
    public List<Interview> getInterviewBy(@PathVariable(value = "name") String interviewer) {
        return interviewService.findAllByInterviewer(interviewer);
    }

    @GetMapping("/attendees/{interviewId}")
    public Set<User> getAttendees(@PathVariable(value = "interviewId") int interviewId) {
        return interviewService.findByInterviewId(interviewId).getUsers();
    }

    @PutMapping("/updateStatus/{interviewId}/{interviewStatus}")
    public Interview updateInterviewStatus(@PathVariable(value = "interviewId") int interviewId, @PathVariable(value = "interviewStatus") String interviewStatus) {
        return interviewService.updateInterviewStatus(interviewId,interviewStatus);
    }

    @GetMapping("/count")
    public int getInterviewCount() {

        return interviewService.getInterviewsCount();
    }

    @PostMapping({"", "/"})
    public Interview addInterview(@RequestBody Interview interview) {

        return interviewService.addInterview(interview);
    }

    @PutMapping({"/addUsers/{interviewId}/{userId}"})
    public Interview addUsers(@PathVariable(value = "interviewId") int interviewId, @PathVariable(value = "userId") int userId) {
        return interviewService.addUsersToInterview(interviewId, userId);
    }

    @PutMapping({"/removeUsers/{interviewId}/{userId}"})
    public Interview removeUsers(@PathVariable(value = "interviewId") int interviewId, @PathVariable(value = "userId") int userId) {
        return interviewService.removeUsersFromInterview(interviewId, userId);
    }

    @PutMapping({"", "/"})
    public Interview updateInterview(@RequestBody Interview interview) {
        return interviewService.updateInterview(interview);
    }

    @DeleteMapping({"/{id}"})
    public void deleteInterview(@PathVariable(value = "id") int id) {
        interviewService.deleteInterview(id);
    }

}

