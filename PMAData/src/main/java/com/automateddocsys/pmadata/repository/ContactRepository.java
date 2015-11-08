package com.automateddocsys.pmadata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automateddocsys.pmadata.bo.ClientName;
import com.automateddocsys.pmadata.bo.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	
}

