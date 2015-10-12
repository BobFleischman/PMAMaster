package com.automateddocsys.PMADataTransfer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.automateddocsys.pma.repository.service.WebClientService;
import com.automateddocsys.pma.webdata.bo.WebClient;
import com.automateddocsys.pmadata.bo.UserAccount;
import com.automateddocsys.pmadata.service.UserAccountService;

@Component
public class CopyUsers {

	@Autowired
	private UserAccountService userRepo;
	
	@Autowired
	private WebClientService webClientService;
	
	private Logger log = LoggerFactory.getLogger(CopyUsers.class);
	
	public static void main(String[] args) {
		   ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(DataTransferConfig.class);
		   
		   CopyUsers copyUsers = ctx.getBean(CopyUsers.class);
		   copyUsers.copy();
	}

	private void copy() {
		int newUsers = 0;
		int updatedUsers = 0;
		List<UserAccount> lstUses = userRepo.findAll();
		for (UserAccount userAccount : lstUses) {
			WebClient client = webClientService.findByUsername(userAccount.getUsername());
			if (client == null) {
				client = new WebClient();
				newUsers++;
				log.info("Creating a new user for " + userAccount.getUsername());
			} else {
				updatedUsers++;
			}
			client.setClientNumber(userAccount.getClientNo().toString());
			client.setMasterClientNumber(userAccount.getClientNo().toString());
			client.setMasterClientName(userAccount.getName());
			client.setUsername(userAccount.getUsername());
			//client.setPassword(userAccount.getPassword());
			client.setPassword(userAccount.getUsername()+"1234");
			if (client.getAuthorities().size() == 0) {
				client.addAuthority("ROLE_USER");
			}

			System.out.println(client.getMasterClientName());
			webClientService.save(client);
		}
		log.info(String.format("Created %s new users on %s", newUsers, new Date()));
		log.info(String.format("Updated %s users on %s", updatedUsers, new Date()));		
	}

}
