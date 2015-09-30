package com.automateddocsys.pmadata.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pmadata.bo.Permission;
import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.bo.UpdateDate;
import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;
import com.automateddocsys.pmadata.repository.PositionTotalRepository;
import com.automateddocsys.pmadata.repository.UpdateDateRepository;
import com.automateddocsys.pmadata.repository.UserRepository;

@Component
public class UserAccountServiceImpl implements UserAccountService {

	private static final BigDecimal BD100 = new BigDecimal(100);
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PositionTotalRepository positionTotalRepository;
	
	@Autowired
	UpdateDateRepository updateDateRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.automateddocsys.PMADataTransfer.service.UserAccountService#findAll()
	 */
	@Override
	@Transactional("transactionManagerPMA")
	public List<UserAccount> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public List<AccountTotal> getTotalForUserAccount(Integer pClientNo) {
		UserAccount ua = userRepository.findOne(pClientNo);
		List<AccountTotal> result = new ArrayList<AccountTotal>();
		List<Permission> permissions = ua.getPermissions();
		for (Permission permission : permissions) {
			List<PositionTotal> position = positionTotalRepository.findByClientNo(permission.getValidAccount());
			AccountTotal at = new AccountTotal();
			for (PositionTotal positionTotal : position) {
				at.setClientNumber(positionTotal.getClientNo());
				at.setAccountName(positionTotal.getName1());
				at.addValue(positionTotal.getMarketValue());
			}
			result.add(at);
		}
		return result;
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public boolean hasRightsToThisAccount(Integer pClientNo, Integer pAcctNumber) {
		UserAccount ua = userRepository.findOne(pClientNo);
		List<Permission> permissions = ua.getPermissions();
		for (Permission permission : permissions) {
			if (permission.getValidAccount().equals(pAcctNumber)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<AccountTotal> getAccountDetails(Integer pAcctNumber) {
		List<AccountTotal> result = new ArrayList<AccountTotal>();
		List<PositionTotal> position = positionTotalRepository.findByClientNo(pAcctNumber);
		BigDecimal accountTotal = new BigDecimal(0);
		for (PositionTotal positionTotal : position) {
			AccountTotal at = new AccountTotal();
			at.setClientNumber(positionTotal.getClientNo());
			at.setAccountName(positionTotal.getName1());
			at.addValue(positionTotal.getMarketValue());
			result.add(at);
			accountTotal = accountTotal.add(positionTotal.getTotal());
		}
		for (AccountTotal at : result) {
			at.setPercentOfTotal(at.getTotalValue().divide(accountTotal,3,BigDecimal.ROUND_HALF_EVEN).multiply(BD100));
		}
		return result;
	}

	public List<PositionTotal> getPositionTotals(Integer pAcctNumber) {
		List<PositionTotal> positions = positionTotalRepository.findByClientNo(pAcctNumber);
		return positions;
	}

	@Override
	public String getUpdateDate() {
		List<UpdateDate> lst = updateDateRepository.findAll();
		if (lst.size() != 1) {
			throw new RuntimeException("Either no or too many Last Update Dates");
		}
		Date update = lst.get(0).getLastDateUpdated();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		return formatter.format(update);
	}

	@Override
	public void setPercentOfTotal(List<PositionTotal> positions, BigDecimal grandTotalPos) {
		for (PositionTotal positionTotal : positions) {
			positionTotal.setPercentOfTotal(positionTotal.getMarketValue().divide(grandTotalPos,3,BigDecimal.ROUND_HALF_EVEN).multiply(BD100));
		}
	}
	
}
