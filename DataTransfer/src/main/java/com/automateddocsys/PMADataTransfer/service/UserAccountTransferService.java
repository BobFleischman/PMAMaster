package com.automateddocsys.PMADataTransfer.service;

import java.util.List;

import com.automateddocsys.pmadata.bo.UserAccount;

public interface UserAccountTransferService {

	List<UserAccount> findAll();

}