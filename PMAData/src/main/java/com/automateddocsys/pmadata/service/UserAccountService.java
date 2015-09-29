package com.automateddocsys.pmadata.service;

import java.util.List;

import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;

public interface UserAccountService {

	List<UserAccount> findAll();
	
	List<AccountTotal> getTotalForUserAccount(Integer pClientNo);

}