package com.automateddocsys.pmadata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automateddocsys.pmadata.bo.ClientName;
import com.automateddocsys.pmadata.bo.UserAccount;

public interface ClientNameRepository extends JpaRepository<ClientName, Integer> {
	
	ClientName findByClientNo(int pClientNo);

}

