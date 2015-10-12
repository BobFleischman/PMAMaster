/**
 * 
 */
package com.automateddocsys.pma.web.mvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.automateddocsys.pma.masterreport.bo.ReportRenamedFilesForWeb;
import com.automateddocsys.pma.masterreport.service.ReportsService;
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
@RequestMapping(value = "/reports")
public class ReportsControllers extends AbstractBaseController {
	@Autowired
	WebClientManager clientManager;

	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	ReportsService reportService;

	@RequestMapping(value = { "/list" })
	public String startingPlace(Model pModel, HttpServletRequest request, HttpServletResponse response) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal", request.getUserPrincipal());
		pModel.addAttribute("user", request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client", client);
		pModel.addAttribute("updateDate", userAccountService.getUpdateDate());
		List<AccountTotal> totals = userAccountService.getTotalForUserAccount(new Integer(client.getClientNumber()));
		pModel.addAttribute("totals", totals);
		BigDecimal grandTotal = calculateGrandTotal(totals);
		pModel.addAttribute("grandTotal", grandTotal);
		setServers(request, pModel);
		runMerger("pages/reports1.ftl", pModel, response, request);
		return "template/pmabase";
	}

	private BigDecimal calculateGrandTotal(List<AccountTotal> pFunds) {
		BigDecimal grandTotal = new BigDecimal("0");
		for (AccountTotal accountTotal : pFunds) {
			grandTotal = grandTotal.add(accountTotal.getTotalValue());
		}
		return grandTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	private BigDecimal calculateGrandTotalFromPositions(List<PositionTotal> pFunds) {
		BigDecimal grandTotal = new BigDecimal("0");
		for (PositionTotal position : pFunds) {
			grandTotal = grandTotal.add(position.getMarketValue());
		}
		return grandTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	@RequestMapping(value = { "/details/{acctNumber}" })
	public String showDetail(@PathVariable(value = "acctNumber") Integer pAcctNumber, Model pModel,
			HttpServletRequest request, HttpServletResponse response) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal", request.getUserPrincipal());
		pModel.addAttribute("user", request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client", client);
		pModel.addAttribute("acctNo", pAcctNumber);
		setServers(request, pModel);
		/*
		 * now check that this account is related to this client and if not
		 * throw an error
		 */
		boolean hasRightsToThisAccount = userAccountService
				.hasRightsToThisAccount(new Integer(client.getClientNumber()), pAcctNumber);
		if (!hasRightsToThisAccount) {
			runMerger("errors/illegalAccess.ftl", pModel, response, request);
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(addScriptLibrary("//code.jquery.com/jquery-1.11.1.min.js"));
			sb.append("\n");
			sb.append(addScriptLibrary("//code.jquery.com/ui/1.11.4/jquery-ui.js"));
			sb.append("\n");			
			sb.append(addScriptLibrary("/PMAClientData/resources/js/pma_reports.js"));
			sb.append("\n");			
			sb.append(addStyleLibrary("//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"));
			
			pModel.addAttribute(_EXTRA_HEAD,sb.toString());

			
			pModel.addAttribute("updateDate", userAccountService.getUpdateDate());
			List<AccountTotal> summary = userAccountService.getAccountDetails(pAcctNumber);
			for (AccountTotal accountTotal : summary) {
				if (accountTotal.getClientNumber().equals(pAcctNumber)) {
					pModel.addAttribute("accountName", accountTotal.getAccountName());
				}
			}
			List<PositionTotal> positions = userAccountService.getPositionTotals(pAcctNumber);
			pModel.addAttribute("funds", positions);
			BigDecimal grandTotal = calculateGrandTotal(summary);
			pModel.addAttribute("grandTotal", grandTotal);
			BigDecimal grandTotalPos = calculateGrandTotalFromPositions(positions);
			pModel.addAttribute("grandTotalPos", grandTotalPos);
			userAccountService.setPercentOfTotal(positions, grandTotalPos);
			pModel.addAttribute("positions", positions);
			runMerger("pages/accountDetails.ftl", pModel, response, request);
		}
		return "template/pmabase";
	}

	@RequestMapping(value = { "/details/export/{acctNumber}" })
	public void exportDetail(@PathVariable(value = "acctNumber") Integer pAcctNumber, Model pModel,
			HttpServletRequest request, HttpServletResponse response) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal", request.getUserPrincipal());
		pModel.addAttribute("user", request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client", client);
		pModel.addAttribute("acctNo", pAcctNumber);
		setServers(request, pModel);
		/*
		 * now check that this account is related to this client and if not
		 * throw an error
		 */
		boolean hasRightsToThisAccount = userAccountService
				.hasRightsToThisAccount(new Integer(client.getClientNumber()), pAcctNumber);
		if (!hasRightsToThisAccount) {
			throw new RuntimeException("You do not have permission to view this account");
		} else {
			String csvFileName = "funds.csv";

			response.setContentType("text/csv");

			// creates mock data
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
			response.setHeader(headerKey, headerValue);

			// uses the Super CSV API to generate CSV data from the model data
			ICsvBeanWriter beanWriter = null;
			try {
				beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

				// only map 5 of the 10 fields
				String[] header = { "Fund Type","Fund Name", "Ticker", "Shares", "Market Value" };
				String[] field = { "ObjectName","FundNameOnly", "ticker", "Shares", "MarketValue" };

				// assign a default value for married (if null), and write
				// numberOfKids as an empty column if null
				final CellProcessor[] processors = new CellProcessor[] { new UniqueHashCode(), new NotNull(),
						new NotNull(), new ConvertNullTo("no response", new FmtBool("yes", "no")), new Optional() };

				// write the header
				beanWriter.writeHeader(header);
				List<PositionTotal> positions = userAccountService.getPositionTotals(pAcctNumber);
				for (PositionTotal positionTotal : positions) {
					//beanWriter.write(positionTotal, header, processors);
					beanWriter.write(positionTotal, field);
				}

			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} finally {
				if (beanWriter != null) {
					try {
						beanWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	@RequestMapping(value = { "/d/{x}" })
	public String showD(@PathVariable(value = "x") String x, Model pModel, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(x);
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal", request.getUserPrincipal());
		pModel.addAttribute("user", request.getRemoteUser());
		pModel.addAttribute("client", clientManager.getWebClientByUsername(request.getUserPrincipal().getName()));
		pModel.addAttribute("acctNo", x);
		setServers(request, pModel);
		runMerger("pages/reportsDetail.ftl", pModel, response, request);
		return "template/pmabase";
	}

	@RequestMapping(value = { "/PDF/{pAcctNumber}" })
	public String showPDF(@PathVariable(value = "pAcctNumber") Integer pAcctNumber, Model pModel, HttpServletRequest request,
			HttpServletResponse response) {
		updateModel(pModel);
		String formattedDate = dateFormat.format(new Date());
		pModel.addAttribute("serverTime", formattedDate);
		pModel.addAttribute("principal", request.getUserPrincipal());
		pModel.addAttribute("user", request.getRemoteUser());
		WebClient client = clientManager.getWebClientByUsername(request.getUserPrincipal().getName());
		pModel.addAttribute("client", client);
		pModel.addAttribute("acctNo", pAcctNumber);
		pModel.addAttribute("accountName",userAccountService.getClientName(pAcctNumber));
		boolean hasRightsToThisAccount = userAccountService.hasRightsToThisAccount(new Integer(client.getClientNumber()), pAcctNumber);
		if (!hasRightsToThisAccount) {
			throw new RuntimeException("You do not have permission to view this account");
		}
		setServers(request, pModel);
		List<ReportRenamedFilesForWeb> monthlyReports = new ArrayList<ReportRenamedFilesForWeb>();
		List<ReportRenamedFilesForWeb> quarterlyReports = new ArrayList<ReportRenamedFilesForWeb>();
		List<ReportRenamedFilesForWeb> annualReports = new ArrayList<ReportRenamedFilesForWeb>();
		List<ReportRenamedFilesForWeb> reports = reportService.findByPmaAccountNumber(pAcctNumber);
		for (ReportRenamedFilesForWeb rrfw : reports) {
			if ("M".equalsIgnoreCase(rrfw.getReportPeriod())) {
				monthlyReports.add(rrfw);
			} else if ("Q".equalsIgnoreCase(rrfw.getReportPeriod())) {
				quarterlyReports.add(rrfw);
			} else if ("A".equalsIgnoreCase(rrfw.getReportPeriod())) {
				annualReports.add(rrfw);
			}  
		}
		pModel.addAttribute("months", monthlyReports);
		pModel.addAttribute("quarters", quarterlyReports);
		pModel.addAttribute("annuals", annualReports);
		runMerger("pages/reportsAvailable.ftl", pModel, response, request);
		return "template/pmabase";
	}

	
}
