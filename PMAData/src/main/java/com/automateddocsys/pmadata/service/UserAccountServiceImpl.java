package com.automateddocsys.pmadata.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pmadata.bo.ClientName;
import com.automateddocsys.pmadata.bo.Message;
import com.automateddocsys.pmadata.bo.Permission;
import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.bo.UpdateDate;
import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;
import com.automateddocsys.pmadata.repository.ClientNameRepository;
import com.automateddocsys.pmadata.repository.MessageRepository;
import com.automateddocsys.pmadata.repository.PositionTotalRepository;
import com.automateddocsys.pmadata.repository.UpdateDateRepository;
import com.automateddocsys.pmadata.repository.UserRepository;

@Component
public class UserAccountServiceImpl implements UserAccountService {

	private static final Logger log = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	private static final BigDecimal BD100 = new BigDecimal(100);

	@Autowired
	UserRepository userRepository;

	@Autowired
	PositionTotalRepository positionTotalRepository;

	@Autowired
	UpdateDateRepository updateDateRepository;

	@Autowired
	ClientNameRepository clientNameRepository;
	
	@Autowired
	MessageRepository messageRepository;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.automateddocsys.PMADataTransfer.service.UserAccountService#findAll()
	 */
	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public List<UserAccount> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	@Deprecated
	public List<AccountTotal> getTotalForUserAccount(Integer pClientNo) {
		UserAccount ua = userRepository.findOne(pClientNo);
		List<AccountTotal> result = new ArrayList<AccountTotal>();
		List<Permission> permissions = ua.getPermissions();
		for (Permission permission : permissions) {
			List<PositionTotal> position = positionTotalRepository.findByClientNo(permission.getValidAccount());
			if (position.size() > 0) {
				AccountTotal at = new AccountTotal();
				for (PositionTotal positionTotal : position) {
					at.setClientNumber(positionTotal.getClientNo());
					at.setAccountName(positionTotal.getName1());
					at.addValue(positionTotal.getMarketValue());
				}
				result.add(at);
			} else {
				log.warn(String.format("No Positions for Account %s which is related to Client Number %s ",
						permission.getValidAccount(), pClientNo));
			}
		}
		return result;
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public List<AccountTotal> getTotalForUserAccount(String pUserName) {
		UserAccount ua = userRepository.findByUsername(pUserName);
		List<AccountTotal> result = new ArrayList<AccountTotal>();
		List<Permission> permissions = ua.getPermissions();
		for (Permission permission : permissions) {
			List<PositionTotal> position = positionTotalRepository.findByClientNo(permission.getValidAccount());
			if (position.size() > 0) {
				AccountTotal at = new AccountTotal();
				for (PositionTotal positionTotal : position) {
					at.setClientNumber(positionTotal.getClientNo());
					at.setAccountName(positionTotal.getName1());
					at.addValue(positionTotal.getMarketValue());
				}
				result.add(at);
			} else {
				log.warn(String.format("No Positions for Account %s which is related to Client Name %s ",
						permission.getValidAccount(), pUserName));
			}
		}
		return result;
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	@Deprecated
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
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public boolean hasRightsToThisAccount(String pUserName, Integer pAcctNumber) {
		UserAccount ua = userRepository.findByUsername(pUserName);
		List<Permission> permissions = ua.getPermissions();
		for (Permission permission : permissions) {
			if (permission.getValidAccount().equals(pAcctNumber)) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
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
			at.setPercentOfTotal(
					at.getTotalValue().divide(accountTotal, 3, BigDecimal.ROUND_HALF_EVEN).multiply(BD100));
		}
		return result;
	}

	public List<PositionTotal> getPositionTotals(Integer pAcctNumber) {
		List<PositionTotal> positions = positionTotalRepository.findByClientNo(pAcctNumber);
		return positions;
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public String getUpdateDate() {
		List<UpdateDate> lst = updateDateRepository.findAll();
		if (lst.size() != 1) {
			throw new RuntimeException("Either no or too many Last Update Dates");
		}
		Date update = lst.get(0).getLastDateUpdated();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");
		return formatter.format(update);
	}

	@Override
	public void setPercentOfTotal(List<PositionTotal> positions, BigDecimal grandTotalPos) {
		for (PositionTotal positionTotal : positions) {
			positionTotal.setPercentOfTotal(positionTotal.getMarketValue()
					.divide(grandTotalPos, 3, BigDecimal.ROUND_HALF_EVEN).multiply(BD100));
		}
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public String getClientName(int pClientNo) {
		ClientName clientName = clientNameRepository.findByClientNo(pClientNo);
		if (clientName == null) {
			return "No Such Client as " + Integer.toString(pClientNo);
		} else {
			return clientName.getFullName();
		}
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	@Deprecated
	/**
	 * Invalid now that we no longer have a master client number
	 */
	public boolean hasMoreThanOneAccount(Integer pClientNo) {
		UserAccount ua = userRepository.findOne(pClientNo);
		List<Permission> permissions = ua.getPermissions();
		return permissions.size() > 1;
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public boolean hasMoreThanOneAccount(String pUserName) {
		UserAccount ua = userRepository.findByUsername(pUserName);
		List<Permission> permissions = ua.getPermissions();
		return permissions.size() > 1;
	}
	
	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public String getUpfrontMessage() {
		List<Message> lst = messageRepository.findAll();
		if ((lst.size() == 0) || (lst.get(0) == null) || lst.get(0).getMessage().trim().length() == 0) {
			return null;
		} else {
			return lst.get(0).getMessage();
		}
	}

	@Override
	@Transactional(value = "transactionManagerPMA", readOnly = true)
	public Integer getOnlyAccountForThisUser(String pUserName) {
		UserAccount ua = userRepository.findByUsername(pUserName);
		List<Permission> permissions = ua.getPermissions();
		if (permissions.size() > 1) {
			throw new RuntimeException("Tried to get only account for User that has more than one account for " + pUserName);
		} else if (permissions.size() == 0) {
			throw new RuntimeException(pUserName + " has no permissions to any accounts");
		} else {
			return permissions.get(0).getValidAccount();
		}
	}

}
