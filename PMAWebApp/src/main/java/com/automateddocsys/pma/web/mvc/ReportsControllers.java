/**
 * 
 */
package com.automateddocsys.pma.web.mvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.automateddocsys.pma.web.service.WebClientManager;
import com.automateddocsys.pma.webdata.bo.WebClient;
import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.bo.projections.AccountTotal;
import com.automateddocsys.pmadata.service.UserAccountService;

/**
 * @author Robert
 *
 */
@Controller
@RequestMapping(value="/reports")
public class ReportsControllers extends AbstractBaseController {
	@Autowired
	WebClientManager clientManager;
	
	@Autowired
	UserAccountService userAccountService;
	
	@RequestMapping(value={"/list"})
	public String startingPlace(Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal",request.getUserPrincipal());
		pModel.addAttribute("user",request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client",client);
		pModel.addAttribute("updateDate",userAccountService.getUpdateDate());
		List<AccountTotal> totals = userAccountService.getTotalForUserAccount(new Integer(client.getClientNumber()));
		pModel.addAttribute("totals",totals);
		BigDecimal grandTotal = calculateGrandTotal(totals);
		pModel.addAttribute("grandTotal",grandTotal);
		setServers(request,pModel);
	    runMerger("pages/reports1.ftl", pModel, response, request);
		return "template/pmabase";
	}
	
	private BigDecimal calculateGrandTotal(List<AccountTotal> pFunds) {
		BigDecimal grandTotal = new BigDecimal("0");
		for (AccountTotal accountTotal : pFunds) {
			grandTotal = grandTotal.add(accountTotal.getTotalValue());
		}
		return grandTotal.setScale(2,BigDecimal.ROUND_HALF_EVEN);
	}

	private BigDecimal calculateGrandTotalFromPositions(List<PositionTotal> pFunds) {
		BigDecimal grandTotal = new BigDecimal("0");
		for (PositionTotal position : pFunds) {
			grandTotal = grandTotal.add(position.getMarketValue());
		}
		return grandTotal.setScale(2,BigDecimal.ROUND_HALF_EVEN);
	}

	@RequestMapping(value={"/details/{acctNumber}"})
	public String showDetail(
			@PathVariable(value="acctNumber") Integer pAcctNumber,
			Model pModel,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal",request.getUserPrincipal());
		pModel.addAttribute("user",request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client",client);
		pModel.addAttribute("acctNo", pAcctNumber);
		setServers(request,pModel);
		/* now check that this account is related to this client and if not throw an error */
		boolean hasRightsToThisAccount = userAccountService.hasRightsToThisAccount(new Integer(client.getClientNumber()),pAcctNumber);
		if (!hasRightsToThisAccount) {
			runMerger("pages/illegalAccess.ftl", pModel, response, request);
		} else {
			pModel.addAttribute("updateDate",userAccountService.getUpdateDate());
			List<AccountTotal> summary = userAccountService.getAccountDetails(pAcctNumber);
			List<PositionTotal> positions = userAccountService.getPositionTotals(pAcctNumber);
			pModel.addAttribute("funds", positions);
			BigDecimal grandTotal = calculateGrandTotal(summary);
			pModel.addAttribute("grandTotal",grandTotal);
			BigDecimal grandTotalPos = calculateGrandTotalFromPositions(positions);
			pModel.addAttribute("grandTotalPos",grandTotalPos);
			userAccountService.setPercentOfTotal(positions,grandTotalPos);
			pModel.addAttribute("positions", positions);
			runMerger("pages/accountDetails.ftl", pModel, response, request);
		}
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
