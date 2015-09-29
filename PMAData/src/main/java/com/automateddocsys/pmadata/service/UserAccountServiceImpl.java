package com.automateddocsys.pmadata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pmadata.bo.Permission;
import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;
import com.automateddocsys.pmadata.repository.PositionTotalRepository;
import com.automateddocsys.pmadata.repository.UserRepository;

@Component
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PositionTotalRepository positionTotalRepository;
	/* (non-Javadoc)
	 * @see com.automateddocsys.PMADataTransfer.service.UserAccountService#findAll()
	 */
	@Override
	@Transactional("transactionManagerPMA")
	public List<UserAccount> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(value="transactionManagerPMA",readOnly=true)
	public List<AccountTotal> getTotalForUserAccount(Integer pClientNo) {
		UserAccount ua = userRepository.findOne(pClientNo);
		List<AccountTotal> result = new ArrayList<AccountTotal>();
		List<Permission> permissions = ua.getPermissions();
		for (Permission permission : permissions) {
			List<PositionTotal> position = positionTotalRepository.findByClientNo(permission.getValidAccount());
			AccountTotal at = new AccountTotal();
			double accountTotal = 0; 
			for (PositionTotal positionTotal : position) {
				at.setClientNumber(positionTotal.getClientNo());
				at.setAccountName(positionTotal.getName1());
				at.addValue(positionTotal.getTotal());
			}
			result.add(at);
		}
		return result;
	}
	
	
}
