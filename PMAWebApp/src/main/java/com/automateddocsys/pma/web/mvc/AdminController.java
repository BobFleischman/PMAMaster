/**
 * 
 */
package com.automateddocsys.pma.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.automateddocsys.pma.web.mvc.models.PasswordChange;
import com.automateddocsys.pma.web.service.WebClientManager;

/**
 * @author Robert
 *
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController extends AbstractBaseController {
	@Autowired
	WebClientManager clientManager;
	
	@RequestMapping(value={"/clearQuestions"})
	public String clearQuestions(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		updateModel(pModel);
		System.out.println("About to clear questions for " + request.getUserPrincipal().getName());
		clientManager.clearAnswers(request.getUserPrincipal().getName());
		setServers(request,pModel);
		pModel.addAttribute("error",null);
	    runMerger("pages/admin/questionsCleared.ftl", pModel, response, request);
		return "template/pmabase";
	}
	
	@RequestMapping(value={"/password"})
	public String startingPlace(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		updateModel(pModel);
		setServers(request,pModel);
		pModel.addAttribute("error",null);
	    runMerger("pages/admin/passwordchange.ftl", pModel, response, request);
		return "template/pmabase";
	}
	@RequestMapping(value={"/maintenance"})
	public String startingMaintenance(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		updateModel(pModel);
		setServers(request,pModel);
	    runMerger("pages/admin/maintenance.ftl", pModel, response, request);
		return "template/pmabase";
	}
	
	@RequestMapping(value={"/changePassword"}, method={RequestMethod.POST})
	public String changePassword(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response,
			PasswordChange passwordChange
			) {
		updateModel(pModel);
		setServers(request,pModel);
		if (passwordChange.doesMatch()) {
			if (clientManager.changePassword(request.getUserPrincipal().getName(), passwordChange.getOldpassword(), passwordChange.getPassword())) {
				return "redirect:/reports/list";
			} else {
				pModel.addAttribute("error", "Old Password does not match");
			    runMerger("pages/admin/passwordchange.ftl", pModel, response, request);
				return "template/pmabase";
			}
		}
		pModel.addAttribute("error", "New Password and Confirmation Password do not match");
	    runMerger("pages/admin/passwordchange.ftl", pModel, response, request);
		return "template/pmabase";
	}
}
