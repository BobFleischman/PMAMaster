package com.automateddocsys.pma.web.service;

import org.springframework.stereotype.Component;

import com.automateddocsys.pmadata.bo.Contact;

@Component
public interface ContactManager {
	
	public void addMessage(Contact pContact);
	
}
