package com.automateddocsys.pmadata.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.or.jms.MessageRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pmadata.bo.ClientName;
import com.automateddocsys.pmadata.bo.Contact;
import com.automateddocsys.pmadata.bo.Message;
import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;
import com.automateddocsys.pmadata.config.PMADataDataConfiguration;
import com.automateddocsys.pmadata.repository.ClientNameRepository;
import com.automateddocsys.pmadata.repository.ContactRepository;
import com.automateddocsys.pmadata.repository.MessageRepository;
import com.automateddocsys.pmadata.repository.PositionTotalRepository;
import com.automateddocsys.pmadata.repository.UserRepository;
import com.automateddocsys.pmadata.service.UserAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={PMADataDataConfiguration.class})
public class TestConnection {
	
	@Autowired
	private PositionTotalRepository positionTotalRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private ClientNameRepository clientNameRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Test
	public void testPositionRepo() {
		System.out.println("Testing");
		Sort sort = new Sort(Direction.ASC,"ClientNo","ObjectName","FundName");
//		List<PositionTotal> lst2 = positionTotalRepository.findAll(sort);
//		showTotals(lst2);
		List<PositionTotal> lst = positionTotalRepository.findByClientNo(355);
		showTotals(lst);
		List<Integer> lstClientNo = new ArrayList<Integer>();
		lstClientNo.add(355);
		lstClientNo.add(1643);
		lst = positionTotalRepository.findByClientNoIn(lstClientNo,sort);
		showTotals(lst);
		
	}

	@Test
	@Transactional
	public void testUserRepo() {
		UserAccount ua = userRepository.findByUsername("fsnitzer");
		System.out.println(ua);		
	}

	private void showTotals(List<PositionTotal> lst) {
		System.out.println();System.out.println();System.out.println();
		for (PositionTotal positionTotal : lst) {
			System.out.println(positionTotal.toString());
		}
	}

	@Test
	public void testTotals() {
		List<AccountTotal> lst = userAccountService.getTotalForUserAccount(2);
		for (AccountTotal accountTotal : lst) {
			System.out.println(accountTotal.toString());
		}
	}
	
	@Test
	public void testPercentage() {
		List<AccountTotal> lst = userAccountService.getAccountDetails(355);
		for (AccountTotal accountTotal : lst) {
			System.out.println(accountTotal.toString());
		}
	}
	
	@Test
	public void testClientName() {
		ClientName cName = clientNameRepository.findByClientNo(1640);
		System.out.println(cName.getFullName());
	}
	
	@Test
	public void testContacts() {
		List<Contact> lst = contactRepository.findAll();
		for (Contact contact : lst) {
			System.out.println(contact.toString());
		}
	}
	
	@Test
	public void testMessages() {
		List<Message> lst = messageRepository.findAll();
		if (lst.size() > 0) {
			System.out.println("Got a message: " + lst.get(0).getMessage());
		} else {
			System.out.println("Nothing to show.");
		}
	}
}
