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

	List<AccountTotal> getAccountDetails(Integer pAcctNumber);
	
	List<PositionTotal> getPositionTotals(Integer pAcctNumber);

	String getUpdateDate();

	void setPercentOfTotal(List<PositionTotal> positions, BigDecimal grandTotalPos);

}