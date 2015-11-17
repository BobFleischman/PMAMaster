/**
 * 
 */
package com.automateddocsys.pma.web.mvc;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.automateddocsys.pma.web.service.ContactManager;
import com.automateddocsys.pma.web.service.WebClientManager;
import com.automateddocsys.pmadata.service.UserAccountService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author ads203
 *
 */
@Controller
public class AbstractBaseController {
	
	@Autowired
	WebClientManager clientManager;

	@Autowired
	ContactManager contactManager;
	
	@Autowired
	UserAccountService userAccountService;

    protected static final String _EXTRA_HEAD = "_extraHead_";

	public static final String _BODY_TAG = "_body_";

    public static final String SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE = "springMacroRequestContext";
	protected static final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,	DateFormat.LONG);

	private static final Logger logger = LoggerFactory.getLogger(AbstractBaseController.class);
	private static final String scriptTemplate = "<script type='text/javascript' src='{0}'></script>";
	private static final String cssTemplate = "<link rel='stylesheet' href='{0}' type='text/css' media='all' />";

    @Autowired
    @Qualifier("freemarkerConfig")
    FreeMarkerConfigurer freeMarkerConfigurer;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

	private static final String _FOOTER_TITLE = "_FOOTER_TITLE";
	private static final String _PROJECTNAME = "_PROJECTNAME";
	public static final String _FOOTERJS = "_footerjs";


	/**
	 * @param pModel
	 */
	protected void updateModel(Model pModel) {
		pModel.addAttribute(AbstractBaseController._PROJECTNAME, "Prudent Management Associates Client Services");
		pModel.addAttribute(AbstractBaseController._FOOTER_TITLE, "Prudent Management Associates");
        pModel.addAttribute("now", new Date());
        if (!pModel.containsAttribute(_EXTRA_HEAD)) {
            pModel.addAttribute(_EXTRA_HEAD,"");
        }
//		if (pModel.asMap().get(AbstractBaseController._FOOTERJS) == null) {
//			List<String> lst = new ArrayList<String>();
//			pModel.addAttribute(AbstractBaseController._FOOTERJS, lst);
//		}
	}

	public void runMerger( String pTemplateName, Model pModel, HttpServletResponse response, HttpServletRequest request) {
	    updateModel(pModel);
	    String templateName = pTemplateName;
	    if (!templateName.endsWith(".ftl")) {
	        templateName += ".ftl";
	    }
//        Collection<String> headers = response.getHeaderNames();
//        for (String string : headers) {
//			if ("Set-Cookie".equalsIgnoreCase(string)) {
//				System.out.println("   " + response.getHeader("Set-Cookie"));
//			}
//		}

        ServletContext servletContext = webApplicationContext.getServletContext();
	       try {
	            Template t = freeMarkerConfigurer.getConfiguration().getTemplate(pTemplateName);
	            Map<String,Object> dataModel = new HashMap<String,Object>();
	            RequestContext rc = new RequestContext(request, response, servletContext, pModel.asMap());
	            Errors er = rc.getErrors("PMAClient");
	            dataModel.put(SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE, new RequestContext(request, response, servletContext, pModel.asMap()));
	            dataModel.putAll(pModel.asMap());
	            Writer out = new StringWriter();
//	            headers = response.getHeaderNames();
//	            for (String string : headers) {
//					if ("Set-Cookie".equalsIgnoreCase(string)) {
//						System.out.println("   " + response.getHeader("Set-Cookie"));
//					}
//
//				}
	            t.process(dataModel, out );
	            out.close();
	            if (logger.isDebugEnabled()) {
	            	logger.debug(out.toString());
	            }
	            pModel.addAttribute(_BODY_TAG, out.toString());
	        } catch (IOException e) {
	            throw new RuntimeException("IO Exception in runMerger",e);
	            //e.printStackTrace();
	        } catch (TemplateException e) {
	            throw new RuntimeException("Template Exception in runMerger",e);
	            //e.printStackTrace();
	        }

	}
	
	protected void setServers(HttpServletRequest request, Model pModel) {
	    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	    if (csrfToken != null) {
	        pModel.addAttribute("_csrf",csrfToken);
	    }		
	    pModel.addAttribute("PMASERVER", "https://prudentmanagement.com");
		pModel.addAttribute("PMAServer", "https://prudentmanagement.com");		
		pModel.addAttribute("SecurePath", "https://secure.prudentmanagement.com/secure");
	}
	
	protected String addScriptLibrary(String pPath) {
		return scriptTemplate.replace("{0}", pPath);
	}
	
	protected String addStyleLibrary(String pPath) {
		return cssTemplate.replace("{0}", pPath);
	}


}
