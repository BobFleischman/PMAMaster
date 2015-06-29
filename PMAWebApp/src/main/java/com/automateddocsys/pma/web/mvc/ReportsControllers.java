/**
 * 
 */
package com.automateddocsys.pma.web.mvc;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.automateddocsys.pma.web.service.WebClientManager;

/**
 * @author Robert
 *
 */
@Controller
@RequestMapping(value="/reports")
public class ReportsControllers extends AbstractBaseController {
	@Autowired
	WebClientManager clientManager;
	@RequestMapping(value={"/list"})
	public String startingPlace(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal",request.getUserPrincipal());
		pModel.addAttribute("user",request.getRemoteUser());
		pModel.addAttribute("client",clientManager.getWebClientByUsername(request.getUserPrincipal().getName()));
		setServers(request,pModel);
	    runMerger("pages/reports1.ftl", pModel, response, request);
		return "template/pmabase";
	}
	
	@RequestMapping(value={"/details"})
	public String showDetail(
			Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal",request.getUserPrincipal());
		pModel.addAttribute("user",request.getRemoteUser());
		pModel.addAttribute("client",clientManager.getWebClientByUsername(request.getUserPrincipal().getName()));
		pModel.addAttribute("acctNo", "A1234");
		setServers(request,pModel);
	    runMerger("pages/reportsDetail.ftl", pModel, response, request);
		return "template/pmabase";
	}
	
	@RequestMapping(value={"/d/{x}"})
	public String showD(@PathVariable(value="x") String x,
			Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		System.out.println(x);
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal",request.getUserPrincipal());
		pModel.addAttribute("user",request.getRemoteUser());
		pModel.addAttribute("client",clientManager.getWebClientByUsername(request.getUserPrincipal().getName()));
		pModel.addAttribute("acctNo", x);
		setServers(request,pModel);
	    runMerger("pages/reportsDetail.ftl", pModel, response, request);
		return "template/pmabase";
	}
	
}
