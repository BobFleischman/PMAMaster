package com.automateddocsys.pma.web.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.automateddocsys.pma.web.service.WebClientManager;
import com.automateddocsys.pma.webdata.bo.WebClient;
import com.automateddocsys.pmadata.bo.ReportRenamedFilesForWeb;
import com.automateddocsys.pmadata.repository.ReportsRepository;
import com.automateddocsys.pmadata.service.UserAccountService;

@Controller
public class FileController  extends AbstractBaseController {
	Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private ReportsRepository reportsRepository; 
	
	@Autowired
	UserAccountService userAccountService;

	@Autowired
	WebClientManager clientManager;

	    @RequestMapping(value = { "/download-document/{pAcctNumber}/{pReportId}" }, method = RequestMethod.GET)
	    public void downloadDocument(@PathVariable(value = "pAcctNumber") Integer pAcctNumber,
	    		@PathVariable(value = "pReportId") Integer pReportId, 
	    		HttpServletRequest request,
	    		HttpServletResponse response) throws IOException {
			WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
			boolean hasRightsToThisAccount = userAccountService.hasRightsToThisAccount(new Integer(client.getClientNumber()), pAcctNumber);
			if (!hasRightsToThisAccount) {
				throw new RuntimeException("You do not have permission to view this account");
			}
	    	ReportRenamedFilesForWeb report = reportsRepository.findOne(pReportId);
	    	if (report.getPmaAccountNumber() != pAcctNumber) {
				throw new RuntimeException("You do not have permission to view this account");
	    	}
	    	String fileName = report.getReportNewName();
	    	String fileDirectory = report.getReportLocation();
	    	String INPUT_FILE = fileDirectory + "\\" + fileName;
	    	File file = new File(INPUT_FILE);
	    	InputStream inputStream = null;
	    	try {
			inputStream = new FileInputStream(file);
	        response.setContentType("application/pdf");
	        response.setContentLength((int) file.length());
	        response.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
	  
	        FileCopyUtils.copy(inputStream, response.getOutputStream());
	    	} finally {
	    		if (inputStream != null) {
	    			inputStream.close();
	    		}
	    		response.getOutputStream().close();
	    		
	    	}
	    }

}
