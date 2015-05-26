/**
 * 
 */
package com.automateddocsys.pma.repository;

import java.util.Set;

import com.automateddocsys.pma.webdata.bo.PotentialQuestion;

/**
 * @author Robert
 *
 */
public interface QuestionRepositoryCustom {

	Set<PotentialQuestion> getRandom(int ct);
}
