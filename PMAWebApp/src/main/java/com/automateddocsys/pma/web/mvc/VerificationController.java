package com.automateddocsys.pma.web.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.automateddocsys.pma.web.service.WebClientManager;
import com.automateddocsys.pma.webdata.bo.WebClient;

@Controller
@RequestMapping("/level2")
public class VerificationController extends AbstractBaseController {
	@Autowired
	WebClientManager clientManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationController.class);

//	@RequestMapping(method = RequestMethod.POST)
//	public String verifyCode(String code) {
//		String view = "redirect:/code?error";
//		try {
//			if (TimeBasedOneTimePassword.isVerificationCodeValid(Integer.parseInt(code))) {
//				grantAuthority();
//				view = "redirect:/home";
//			}
//		} catch (NumberFormatException e) {
//			// ignore
//		} catch (VerificationCodeException e) {
//			LOGGER.error("error while verifying verification code {}", e.getMessage());
//		}
//		return view;
//	}

	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String verifyCode(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		return "redirect:/level2";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showVerificationCodeForm(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal",request.getUserPrincipal());
		pModel.addAttribute("user",request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client",clientManager.getWebClientByUsername(request.getUserPrincipal().getName()));
//		if (client.getAnswers().size() == 0) {
			pModel.addAttribute("needAnswers", true);
			pModel.addAttribute("questions", clientManager.getQuestionsAsMap());
			Map<String, String> m = clientManager.getQuestionsAsMap();
			for(String l : m.keySet()) {
				System.out.println(l);
			}
			System.out.println("Added questions " + clientManager.getQuestionsAsMap());
			pModel.addAttribute("answers",new AnswerSet());
//		}
		setServers(request,pModel);
	    runMerger("pages/verification.ftl", pModel, response, request);
		return "template/pmabase";
	}

	private void grantAuthority() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
		authorities.add(new SimpleGrantedAuthority("ROLE_VERIFIED"));
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}

}
