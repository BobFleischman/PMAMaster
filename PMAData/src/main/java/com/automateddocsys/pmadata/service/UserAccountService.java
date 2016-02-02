package com.automateddocsys.pmadata.service;

import java.math.BigDecimal;
import java.util.List;

import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;

public interface UserAccountService {

	List<UserAccount> findAll();
	
	@Deprecated
	List<AccountTotal> getTotalForUserAccount(Integer pClientNo);

	@Deprecated
	boolean hasRightsToThisAccount(Integer integer, Integer pAcctNumber);
	
	@Deprecated
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

	boolean hasMoreThanOneAccount(String pUserName);

	boolean hasRightsToThisAccount(String pUserName, Integer pAcctNumber);
	
	/**
	 * This should only be called if the user only has one account
	 * It will throw an error is called otherwise
	 * @param pUserName
	 * @return AccountNumber (ClientNo)
	 */
	Integer getOnlyAccountForThisUser(String pUserName);

	List<AccountTotal> getTotalForUserAccount(String pUserName);

}