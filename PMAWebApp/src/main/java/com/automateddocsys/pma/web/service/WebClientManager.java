package com.automateddocsys.pma.web.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.automateddocsys.pma.webdata.bo.PotentialQuestion;
import com.automateddocsys.pma.webdata.bo.WebClient;

@Component
public interface WebClientManager {
		 
	    void addWebClient(WebClient p);
	    void updateWebClient(WebClient p);
	    List<WebClient> listWebClients();
	    WebClient getWebClientByUsername(String username);
	    void removeWebClient(String username);
		Page<WebClient> pageWebClients(int page, int size);
		//void addReportForClient(PMAReport r, WebClient client);
		
		Set<PotentialQuestion> getRandom(int pCt);
		
		Collection<PotentialQuestion> getQuestions();
		Map<String, String> getQuestionsAsMap();

}
