package com.automateddocsys.pma.web.mvc;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.automateddocsys.pma.web.service.WebClientManager;
import com.automateddocsys.pma.webdata.bo.WebClient;

@Controller
public class ClientController extends AbstractBaseController {
	
	@Autowired
	private WebClientManager clientManager;	
	
	
	@RequestMapping("/clients")
	public String clientList(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response,		
			@RequestParam(value = "page", defaultValue="0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue="20", required = false) Integer size) {
		//Page<WebClient> data = clientManager.pageWebClients(page, size);
		List<WebClient> data = clientManager.listWebClients();
		setServers(request,pModel);
		StringBuffer sb = new StringBuffer();
		sb.append(addScriptLibrary("//code.jquery.com/jquery-1.11.1.min.js"));
		sb.append("\n");
		sb.append(addScriptLibrary("//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"));
		sb.append("\n");
		sb.append(addStyleLibrary("//cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css"));
		pModel.addAttribute(_EXTRA_HEAD,sb.toString());
		pModel.addAttribute("data", data);
	    runMerger("pages/clientList.ftl", pModel, response, request);
		return "template/pmabase";		
	}

	@RequestMapping(value="/client/{username}",method=RequestMethod.GET)
	public String clientInfo(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response,		
			@PathVariable String username) {
		WebClient data = clientManager.getWebClientByUsername(username);
		setServers(request,pModel);
		pModel.addAttribute("WebClient", data);
		pModel.addAttribute("client", data);
	    runMerger("pages/clientInfo.ftl", pModel, response, request);
		return "template/pmabase";		
	}
	
	@RequestMapping(value="/client/{username}",method=RequestMethod.POST)
	//public String updateClientInfo(@Valid WebClient client, BindingResult errors, Model pModel,
	public String updateClientInfo(@Valid WebClient client, BindingResult errors, Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response,		
			@PathVariable String username) {
//		Enumeration<String> days = request.getAttributeNames();
//	     while (days.hasMoreElements()){
//	         System.out.println(days.nextElement()); 
//	      }
	     if (errors.hasErrors()) {
			setServers(request,pModel);
			pModel.addAttribute("WebClient", client);
			pModel.addAttribute("client", client);
			pModel.addAttribute(errors);
		    runMerger("pages/clientInfo.ftl", pModel, response, request);
			return "template/pmabase";		
		}
		WebClient data = clientManager.getWebClientByUsername(username);
		setServers(request,pModel);
		pModel.addAttribute("client", data);
	    runMerger("pages/clientInfo.ftl", pModel, response, request);
		return "template/pmabase";		
	}
	
	@RequestMapping(value="/c/{username}",method=RequestMethod.GET)
	public @ResponseBody WebClient clientInfoREST(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response,		
			@PathVariable String username) {
		WebClient data = clientManager.getWebClientByUsername(username);
		setServers(request,pModel);
		pModel.addAttribute("client", data);
		return data;		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
