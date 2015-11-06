package com.automateddocsys.pma.web.mvc;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.automateddocsys.pma.web.service.WebClientManager;




/**
 * Handles requests for the application home page.
 */
@Controller
public class PMAWebController extends AbstractBaseController {
	//TODO:	implement class


	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private WebClientManager clientManager;

	private static final Logger logger = LoggerFactory.getLogger(PMAWebController.class);


	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = {"/login","/login/login.cfm"}, method = { RequestMethod.GET })
	public String login(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response,		
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		System.out.println("context: " + request.getContextPath());
		System.out.println("pathInfo: " + request.getPathInfo());
		System.out.println("requestURL: " + request.getRequestURL());

		Date date = new Date();
		updateModel(pModel);
		String formattedDate = dateFormat.format(date);
		pModel.addAttribute("serverTime", formattedDate);
		setServers(request,pModel);
		if (error != null) {
			pModel.addAttribute("error", "Invalid: " + error); //  username and password!");
		}
 
		if (logout != null) {
			pModel.addAttribute("msg", "You've been logged out successfully.");
		}
	    runMerger("pages/login_content.ftl", pModel, response, request);
		return "template/pmabase";
	}

	//Spring Security see this :
	@RequestMapping(value={"/"})
	public String startingPlace(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		return "redirect:/level2";		
//		updateModel(pModel);
//		String formattedDate = dateFormat.format(new Date());
//		pModel.addAttribute("serverTime", formattedDate);
//		pModel.addAttribute("principal",request.getUserPrincipal());
//		pModel.addAttribute("user",request.getRemoteUser());
//		pModel.addAttribute("client",clientManager.getWebClientByUsername(request.getUserPrincipal().getName()));
//		setServers(request,pModel);
//	    runMerger("pages/startPage.ftl", pModel, response, request);
//		return "template/pmabase";
	}
}