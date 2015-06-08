package com.automateddocumentsys.pma.webdata.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.automateddocsys.pma.repository.service.WebClientService;
import com.automateddocsys.pma.webdata.config.DataConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfiguration.class})
public class TestService {
	
	@Autowired
	WebClientService clientService;
	
	@Test
	public void testClearAnswers() {
		clientService.clearClientQuestions(15L);
	}

}
