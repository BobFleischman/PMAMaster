/**
 * 
 */
package com.automateddocsys.pma.web.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pma.repository.QuestionRepository;
import com.automateddocsys.pma.repository.WebClientRepository;
import com.automateddocsys.pma.web.mvc.models.AnswerSet;
import com.automateddocsys.pma.web.mvc.models.VerificationItem;
import com.automateddocsys.pma.webdata.bo.PotentialQuestion;
import com.automateddocsys.pma.webdata.bo.WebClient;

/**
 * @author ads203
 *
 */
@Component
public class WebClientManagerImpl implements WebClientManager {

	@Autowired
	WebClientRepository clientRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	/* (non-Javadoc)
	 * @see com.automateddocsys.pma.web.service.WebClientManager#addWebClient(com.automateddocsys.pma.web.data.WebClient)
	 */
	@Override
	@Transactional
	public void addWebClient(WebClient p) {
		// first see if there is already a client with this username
		WebClient client = clientRepository.findByUsername(p.getUsername());
		if (client != null) {
			client.setMasterClientName(p.getMasterClientName());
			client.setMasterClientNumber(p.getMasterClientNumber());
			client.setPassword(p.getPassword());
			client.setEnabled(p.isEnabled());
			client.setAccountNonExpired(p.isAccountNonExpired());
			client.setAccountNonLocked(p.isAccountNonLocked());
			client.setClientNumber(p.getClientNumber());
			client.setCredentialsNonExpired(p.isCredentialsNonExpired());
			for(GrantedAuthority role : p.getAuthorities()) {
				client.addAuthority(role.getAuthority());				
			}
//			for (WebClientAccount acct : p.getAccounts()) {
//				client.getAccounts().add(acct);
//			}
			clientRepository.save(client);
		} else {
			clientRepository.save(p);
		}
	}

	/* (non-Javadoc)
	 * @see com.automateddocsys.pma.web.service.WebClientManager#updateWebClient(com.automateddocsys.pma.web.data.WebClient)
	 */
	@Override
	@Transactional
	public void updateWebClient(WebClient p) {
		clientRepository.save(p);
	}

	/* (non-Javadoc)
	 * @see com.automateddocsys.pma.web.service.WebClientManager#listWebClients()
	 */
	@Override
	@Transactional
	public List<WebClient> listWebClients() {
		return clientRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.automateddocsys.pma.web.service.WebClientManager#getWebClientById(int)
	 */
	@Override
	@Transactional
	public WebClient getWebClientByUsername(String pUserName) {
		return clientRepository.findByUsername(pUserName);
	}

	/* (non-Javadoc)
	 * @see com.automateddocsys.pma.web.service.WebClientManager#removeWebClient(int)
	 */
	@Override
	@Transactional
	public void removeWebClient(String pUserName) {
		WebClient client = clientRepository.findByUsername(pUserName);
		if (client != null) {
			clientRepository.delete(client);
		}
	}

	/* (non-Javadoc)
	 * @see com.automateddocsys.pma.web.service.WebClientManager#pageWebClients()
	 */
	@Override
	@Transactional
	public Page<WebClient> pageWebClients(int page, int size) {
		Page<WebClient> p = clientRepository.findAll(new PageRequest(page, size));
		return p;
	}

	@Override
	@Transactional
	public Set<PotentialQuestion> getRandom(int pCt) {
		return questionRepository.getRandom(pCt);
	}

	@Override
	public Collection<PotentialQuestion> getQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public SortedMap<String, String> getQuestionsAsMap() {
		SortedMap<String,String> result = new TreeMap<String,String>();
		for (PotentialQuestion pq : getQuestions()) {
			result.put(pq.getQuestionId().toString(), pq.getQuestion());
		}
		return result;
	}

	@Override
	public void addAnswers(String pUserName, AnswerSet answerSet) {
		WebClient client = clientRepository.findByUsername(pUserName);
		client.addAnswer(
				questionRepository.findOne(answerSet.getQuestion1()),
				answerSet.getAnswer1()
				);
		client.addAnswer(
				questionRepository.findOne(answerSet.getQuestion2()),
				answerSet.getAnswer3()
				);
		client.addAnswer(
				questionRepository.findOne(answerSet.getQuestion3()),
				answerSet.getAnswer3()
				);
		clientRepository.save(client);
		
	}

	@Override
	public boolean didSupplyCorrectAnswer(String pUserName, VerificationItem verification) {
		WebClient client = clientRepository.findByUsername(pUserName);
		return client.getAnswerFor(verification.getQuestionNumber()).equalsIgnoreCase(verification.getAnswer());
	}

}
