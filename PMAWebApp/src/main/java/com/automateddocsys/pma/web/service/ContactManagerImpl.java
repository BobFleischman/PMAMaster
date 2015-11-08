package com.automateddocsys.pma.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pmadata.bo.Contact;
import com.automateddocsys.pmadata.repository.ContactRepository;

@Component
public class ContactManagerImpl implements ContactManager {
	
	@Autowired
	ContactRepository contactRepository;

	@Override
	@Transactional(value="transactionManagerPMA")
	public void addMessage(Contact pContact) {
		contactRepository.save(pContact);
	}

}
