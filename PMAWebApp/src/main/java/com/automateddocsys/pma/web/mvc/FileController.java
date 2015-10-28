package com.automateddocsys.pma.web.mvc;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileController {
	Logger log = LoggerFactory.getLogger(FileController.class);
	
	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
	public void getFile(
	    @PathVariable("file_name") String fileName, 
	    HttpServletResponse response) {
		response.setContentType("application/pdf");
	    try {
	      // get your file as InputStream
	      InputStream is = new In;
	      // copy it to response's OutputStream
	      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	      response.flushBuffer();
	    } catch (IOException ex) {
	      log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
	      throw new RuntimeException("IOError writing file to output stream");
	    }

	}

	1
	2
	3
	4
	5
	6
	7
	8
	9
	10
	11
	12
	13
	14
	15
	16
	17
	18
	19
	20
	21
	22
	23
	24
	25
	26
	27
	28
	29
	30
	31
	32
	33
	34
	35
	36
	37
	38
	39
	40
	41
	42
	43
	44
	45
	46
	47
	48
	49
	50
	51
	52
	53
	54
	55
	56
	57
	58
	59
	60
	61
	62
	63
	64
	65
	66
	67
	68
	69
	70
	71
	72
	73
	74
	75
	76
	77
	78
	79
	80
		
	package net.codejava.spring;
	 
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.OutputStream;
	 
	import javax.servlet.ServletContext;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	 
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	 
	     
	    /**
	     * Size of a byte buffer to read/write file
	     */
	    private static final int BUFFER_SIZE = 4096;
	             
	    /**
	     * Path of the file to be downloaded, relative to application's directory
	     */
	    private String filePath = "/downloads/SpringProject.zip";
	     
	    /**
	     * Method for handling file download request from client
	     */
	    @RequestMapping(method = RequestMethod.GET)
	    public void doDownload(HttpServletRequest request,
	            HttpServletResponse response) throws IOException {
	 
	        // get absolute path of the application
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        System.out.println("appPath = " + appPath);
	 
	        // construct the complete absolute path of the file
	        String fullPath = appPath + filePath;      
	        File downloadFile = new File(fullPath);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	         
	        // get MIME type of the file
	        String mimeType = context.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
	 
	    }
	
}
