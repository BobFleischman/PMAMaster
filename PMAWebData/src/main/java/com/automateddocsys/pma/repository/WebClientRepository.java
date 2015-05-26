package com.automateddocsys.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.automateddocsys.pma.webdata.bo.WebClient;

@Service
public interface WebClientRepository  extends JpaRepository<WebClient, String> {

	WebClient findByUsername(String pUserName);
	

}
