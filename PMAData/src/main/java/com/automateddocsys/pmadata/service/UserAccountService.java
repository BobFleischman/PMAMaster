package com.automateddocsys.pmadata.service;

import java.math.BigDecimal;
import java.util.List;

import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;

public interface UserAccountService {

	List<UserAccount> findAll();
	
	List<AccountTotal> getTotalForUserAccount(Integer pClientNo);

	boolean hasRightsToThisAccount(Integer integer, Integer pAcctNumber);
	
	boolean hasMoreThanOneAccount(Integer pClientNo);

	List<AccountTotal> getAccountDetails(Integer pAcctNumber);
	
	List<PositionTotal> getPositionTotals(Integer pAcctNumber);

	String getUpdateDate();

	void setPercentOfTotal(List<PositionTotal> positions, BigDecimal grandTotalPos);
	
	String getClientName(int pClientNo);
	
	/**
	 * This is used to show a message on the login page
	 * if there is nothing in the table or the message is null it returns null
	 * else the String contains the message;
	 * @return
	 */
	String getUpfrontMessage();

}