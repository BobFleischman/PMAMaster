/**
 * 
 */
package com.automateddocsys.pma.repository.service;

/**
 * @author Robert
 *
 */
public interface WebClientService {
	void clearClientQuestions(Long accountId);
	void clearClientQuestions(String userName);
	void changePassword(Long accountId, String oldPassword, String newPassword);
}
