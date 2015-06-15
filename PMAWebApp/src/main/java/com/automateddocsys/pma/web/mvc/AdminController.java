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
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value={"/password"})
	public String startingPlace(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		updateModel(pModel);
		setServers(request,pModel);
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
}
