package com.automateddocsys.PMADataTransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.repository.UserRepository;

@Component
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.automateddocsys.PMADataTransfer.service.UserAccountService#findAll()
	 */
	@Override
	@Transactional("transactionManagerPMA")
	public List<UserAccount> findAll() {
		return userRepository.findAll();
	}
	
	
}
