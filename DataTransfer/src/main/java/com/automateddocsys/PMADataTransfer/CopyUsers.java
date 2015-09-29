package com.automateddocsys.PMADataTransfer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.automateddocsys.PMADataTransfer.service.UserAccountService;
import com.automateddocsys.pma.repository.WebClientRepository;
import com.automateddocsys.pma.repository.service.WebClientService;
import com.automateddocsys.pma.webdata.bo.WebClient;
import com.automateddocsys.pmadata.bo.UserAccount;

@Component
public class CopyUsers {

	@Autowired
	private UserAccountService userRepo;
	
	@Autowired
	private WebClientService webClientService;
	
	public static void main(String[] args) {
		   ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(DataTransferConfig.class);
		   
		   CopyUsers copyUsers = ctx.getBean(CopyUsers.class);
		   copyUsers.copy();
	}

	private void copy() {
		List<UserAccount> lstUses = userRepo.findAll();
		for (UserAccount userAccount : lstUses) {
			WebClient client = webClientService.findByUsername(userAccount.getUsername());
			if (client == null) {
				client = new WebClient();
			}
			client.setClientNumber(userAccount.getClientNo().toString());
			client.setMasterClientNumber(userAccount.getClientNo().toString());
			client.setMasterClientName(userAccount.getName());
			client.setUsername(userAccount.getUsername());
			client.setPassword(userAccount.getPassword());
			if (client.getAuthorities().size() == 0) {
				client.addAuthority("ROLE_USER");
			}

			System.out.println(client);
			webClientService.save(client);
		}
		
	}

}
