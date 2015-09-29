package com.automateddocsys.PMADataTransfer.service;

import java.util.List;

import com.automateddocsys.pmadata.bo.UserAccount;

public interface UserAccountService {

	List<UserAccount> findAll();

}