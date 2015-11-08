/**
 * 
 */
package com.automateddocsys.pma.web.mvc;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.automateddocsys.pma.webdata.bo.WebClient;
import com.automateddocsys.pmadata.bo.Contact;

/**
 * @author Robert
 *
 */
@Controller
public class ContactController extends AbstractBaseController {
	
	@RequestMapping(value="/contact",method=RequestMethod.POST)
	public String displayForm(@RequestParam(value="acctNo") Integer pAcctNo, HttpServletRequest request, Model pModel, HttpServletResponse response) {
		setServers(request,pModel);
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		/*
		 * now check that this account is related to this client and if not
		 * throw an error
		 */
		boolean hasRightsToThisAccount = userAccountService
				.hasRightsToThisAccount(new Integer(client.getClientNumber()), pAcctNo);
		if (!hasRightsToThisAccount) {
			runMerger("errors/illegalAccess.ftl", pModel, response, request);
		} else {
			pModel.addAttribute("acctNo",pAcctNo);
			runMerger("pages/contact.ftl", pModel, response, request);
		}
		return "template/pmabase";		

	}
	
	@RequestMapping(value="/contact/submit",method=RequestMethod.POST)
	public String postComment(@RequestParam(value="comments") String pComment, 
			@RequestParam(value="acctNo") Integer pAcctNo, 
			HttpServletRequest request, Model pModel, HttpServletResponse response) {
		setServers(request,pModel);
		pModel.addAttribute("principal", request.getUserPrincipal());
		pModel.addAttribute("user", request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		boolean hasRightsToThisAccount = userAccountService
				.hasRightsToThisAccount(new Integer(client.getClientNumber()), pAcctNo);
		if (!hasRightsToThisAccount) {
			runMerger("errors/illegalAccess.ftl", pModel, response, request);
		} else {
			Contact newMessage = new Contact();
			newMessage.setComments1(client.getMasterClientName() + " (" + client.getMasterClientNumber() + ") sent: " +pComment);
			newMessage.setEntryDate(new Timestamp(new Date().getTime()));
			newMessage.setLastName(pAcctNo.toString());
			newMessage.setFirstName(client.getMasterClientName());
			contactManager.addMessage(newMessage);
			pModel.addAttribute("acctNo",pAcctNo);
			pModel.addAttribute("client", client);
		    runMerger("pages/thankyou.ftl", pModel, response, request);
		}
		return "template/pmabase";		

	}
	
	
}
