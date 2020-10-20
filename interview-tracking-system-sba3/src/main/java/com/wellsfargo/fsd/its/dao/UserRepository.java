package com.wellsfargo.fsd.its.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.its.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
