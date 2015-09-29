/**
 * 
 */
package com.automateddocsys.pma.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automateddocsys.pma.repository.WebClientRepository;
import com.automateddocsys.pma.webdata.bo.WebClient;

/**
 * @author Robert
 *
 */
@Component
public class WebClientServiceImpl implements WebClientService {

	@Autowired
	WebClientRepository webClientRepository;
	
	@Override
	public void clearClientQuestions(Long pAccountId) {
		WebClient client = webClientRepository.findOne(pAccountId);
		client.clearAnswers();
		webClientRepository.save(client);
	}

	@Override
	public void clearClientQuestions(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(Long pAccountId, String pOldPassword, String newPassword) {
		WebClient client = webClientRepository.findOne(pAccountId);
		if (!client.getPassword().equals(pOldPassword)) {
			throw new RuntimeException("Old Password is not a match");
		}
		client.setPassword(newPassword);
		webClientRepository.save(client);		
	}

	@Override
	public WebClient findByUsername(String pUsername) {
		WebClient wc = webClientRepository.findByUsername(pUsername);
		if (wc != null) {
			wc.getAnswers().size();
			wc.getAuthorities();
		}
		return wc;		
	}

	@Override
	public void save(WebClient pWebClient) {
		webClientRepository.save(pWebClient);
	}

}
