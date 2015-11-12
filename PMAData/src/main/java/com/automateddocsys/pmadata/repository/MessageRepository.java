package com.automateddocsys.pmadata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automateddocsys.pmadata.bo.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
}

