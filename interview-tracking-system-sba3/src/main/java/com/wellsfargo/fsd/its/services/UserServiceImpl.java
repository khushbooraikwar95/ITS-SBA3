package com.wellsfargo.fsd.its.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.UserRepository;
import com.wellsfargo.fsd.its.entities.Interview;
import com.wellsfargo.fsd.its.entities.User;
import com.wellsfargo.fsd.its.exceptions.InvalidData;
import com.wellsfargo.fsd.its.validator.UserValidator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public abstract class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	InterviewService interviewService;

	@PersistenceContext
	private EntityManager entityManger;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId).orElseThrow(() -> new InvalidData("User Id" + userId + " not found."));
	}

	@Override
	@Transactional
	public User addUser(User user) {
		UserValidator userValidator = new UserValidator();
		if (userValidator.validateUser(user)) {
			if (null == user.getInterviews()) {
				Optional<User> usr = userRepository.findById(user.getUserId());
				if (usr.isPresent()) {
					throw new InvalidData("User Id : " + user.getUserId() + " is already present");
				}
			} else
				throw new InvalidData("Can't select interviews when creating new user.");
		} else
			throw new InvalidData(userValidator.getErrors());
		return user;
	}

	@Transactional
	@Override
	public void deleteUser(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new InvalidData("User Id : " + userId + " not found."));
		Set<Interview> interviews = user.getInterviews();
		interviews
				.forEach((interview) -> interviewService.removeUsersFromInterview(interview.getInterviewId(), userId));
		userRepository.deleteById(userId);
	}

	@Transactional
	@Override
	public User saveUser(User updatedUser) {
		UserValidator userValidator = new UserValidator();
		if (userValidator.validateUser(updatedUser)) {
			User curUserWithThisId = userRepository.findById(updatedUser.getUserId())
					.orElseThrow(() -> new InvalidData("User Id : " + updatedUser.getUserId() + " not found."));
			curUserWithThisId.copyUser(updatedUser);
			if (null != updatedUser.getInterviews()) {
				updatedUser.getInterviews().forEach(interview -> {
					interviewService.addUsersToInterview(interview.getInterviewId(), updatedUser.getUserId());
				});
			}
			userRepository.save(curUserWithThisId);
			return updatedUser;
		} else
			throw new InvalidData(userValidator.getErrors());
	}
}
