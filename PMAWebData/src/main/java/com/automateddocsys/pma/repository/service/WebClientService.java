/**
 * 
 */
package com.automateddocsys.pma.repository.service;

import com.automateddocsys.pma.webdata.bo.WebClient;

/**
 * @author Robert
 *
 */
public interface WebClientService {
	void clearClientQuestions(Long accountId);
	void clearClientQuestions(String userName);
	void changePassword(Long accountId, String oldPassword, String newPassword);
	WebClient findByUsername(String pUsername);
	void save(WebClient wc);
}
