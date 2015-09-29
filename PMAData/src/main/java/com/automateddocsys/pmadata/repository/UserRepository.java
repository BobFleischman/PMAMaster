package com.automateddocsys.pmadata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automateddocsys.pmadata.bo.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, Integer> {
	
	UserAccount findByUsername(String pUserName);

}

