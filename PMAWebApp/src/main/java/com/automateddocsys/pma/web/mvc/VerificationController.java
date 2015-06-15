package com.automateddocsys.pma.web.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;

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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.automateddocsys.pma.web.mvc.models.AnswerSet;
import com.automateddocsys.pma.web.mvc.models.VerificationItem;
import com.automateddocsys.pma.web.service.WebClientManager;
import com.automateddocsys.pma.webdata.bo.ClientAnswer;
import com.automateddocsys.pma.webdata.bo.WebClient;

@Controller
@RequestMapping("/level2")
public class VerificationController extends AbstractBaseController {
	@Autowired
	WebClientManager clientManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationController.class);

	private static final Random random = new Random();
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
	public String verifyCode(AnswerSet answerSet, Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response, Errors errors) {
		boolean hasError = false;
		if (answerSet.duplicateQuestions()) {
			pModel.addAttribute("error", "You must answer three different questions");
			hasError = true;
		}
		if (answerSet.notAllQuestionsHaveAnswers()){
			pModel.addAttribute("error", "All three questions must have answers");
			hasError = true;
		}
		if (hasError) {
			return askVerificationAnswers(request, response, pModel,answerSet);
		}
		clientManager.addAnswers(request.getUserPrincipal().getName(),answerSet);
		return "redirect:/level2";
	}

	@RequestMapping(value="/confirm",method = RequestMethod.POST)
	public String verifyCode(VerificationItem verification, Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		if (clientManager.didSupplyCorrectAnswer(request.getUserPrincipal().getName(),verification)) {
			grantAuthority();
			return "redirect:/reports";
		} else {
			pModel.addAttribute("error", "Your answer did not match!");
			return askVerificationQuestion(request,response,pModel);
		}
	}

	private String askVerificationQuestion(HttpServletRequest request, HttpServletResponse response, Model pModel) {
		pModel.addAttribute("needAnswers", 0);
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		updateModel(pModel);
		setServers(request,pModel);
		loadtVerificationQuestion(pModel,client);
	    runMerger("pages/verification.ftl", pModel, response, request);
		return "template/pmabase";
	}

	private String askVerificationAnswers(HttpServletRequest request, HttpServletResponse response, Model pModel, AnswerSet answerSet) {
		setServers(request,pModel);
		updateModel(pModel);
		loadMapForQuestions(pModel, answerSet);
	    runMerger("pages/verification.ftl", pModel, response, request);
		return "template/pmabase";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showVerificationCodeForm(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal",request.getUserPrincipal());
		pModel.addAttribute("user",request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client",client);
		if (client.getAnswers().size() == 0) {
			return askVerificationAnswers(request, response, pModel, new AnswerSet());
		} else {
			return askVerificationQuestion(request, response, pModel);
		}
	}

	private void loadtVerificationQuestion(Model pModel, WebClient client) {
		int which = random.nextInt(2);
		int ct = 0;
		for (ClientAnswer answer : client.getAnswers()) {
			if (ct == which) {
				VerificationItem vi = new VerificationItem();
				vi.setQuestionNumber(answer.getQuestion().getQuestionId());
				vi.setQuestion(answer.getQuestion().getQuestion());
				vi.setAnswer("");
				pModel.addAttribute("verification", vi);
				break;
			}
			ct++;
		}
	}

	private void loadMapForQuestions(Model pModel, AnswerSet answerSet) {
		pModel.addAttribute("needAnswers", 1);
		pModel.addAttribute("questions", clientManager.getQuestionsAsMap());
		pModel.addAttribute("answers",answerSet);
	}

	private void grantAuthority() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
		authorities.add(new SimpleGrantedAuthority("ROLE_VERIFIED"));
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}


	@RequestMapping(value="/resetAnswers", method = RequestMethod.POST)
	public String resetQuestions(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		clientManager.clearAnswers(request.getUserPrincipal().getName());
		return "redirect:/level2";
	}

	@RequestMapping(value="/reset", method = RequestMethod.GET)
	public String showClearAnswers(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		setServers(request,pModel);
		updateModel(pModel);
	    runMerger("pages/reset.ftl", pModel, response, request);
		return "template/pmabase";
	}
}
