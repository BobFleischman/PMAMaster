<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '/' /></#assign>
<style>
.money {
text-align: right;
}
td {
padding: 5px;
}

</style>
<div class="post" id="post-37">
<ul class="children">
<li class="page_item page-item-159"><a href=<@spring.url "/reports/list"/>>Account Information</a></li>
<li class="page_item page-item-159"><a href=<@spring.url "/reports/PDF/${acctNo?c}"/>>Reports</a></li>
<li class="page_item page-item-159"><a href="export/${acctNo?c}">Exports</a></li>
</ul>
<p>This information is based upon closing balances and prices and includes estimated proceeeds of pending transactions.</p>
<p>${updateDate}</p>
<#assign fundType = "zzz">
<#assign subTotal = -1>
<#assign grandTotalX = 0>
<h2>${acctNo?c} ${accountName}</h2>
<table>
<tr><th>Shares</th><th>Funds</th><th>Ticker</th><th>Market Value</th><th>% Total</th></tr>
<#list funds as fund>
<#assign grandTotalX = grandTotalX + fund.marketValue>
<#if fund.objectName != fundType>
<#assign fundType = fund.objectName>
<#if subTotal != -1>
<tr>
<td></td>
<td class="money"><strong>SUB-TOTAL</strong></td>
<td></td>
<td class="money">${subTotal?string.currency}</td>
<#assign subPct = (subTotal / grandTotal) * 100>
<td class="money">${subPct?string["0.##"]}</td>
</tr>
</#if>
<#assign subTotal = 0>
<tr>
<td class="money"></td>
<td><strong>${fund.objectName}</strong></td>
<td></td>
<td class="money"></td>
<td class="money"></td>
</tr>
</#if>
<#assign subTotal = subTotal + fund.marketValue>
<tr>
<td class="money">${fund.shares}</td>
<td>${fund.fundNameOnly}</td>
<td>${fund.ticker}</td>
<td class="money">${fund.marketValue?string.currency}</td>
<td class="money">${fund.percentOfTotal?string["0.##"]}</td>
</tr>

</#list>
<!-- sub total -->
<tr>
<td></td>
<td class="money"><strong>SUB-TOTAL</strong></td>
<td></td>
<td class="money">${subTotal?string.currency}</td>
<#assign subPct = (subTotal / grandTotal) * 100>
<td class="money">${subPct?string["0.##"]}</td>
</tr>
<!-- grand total -->
<tr>
<td></td>
<td class="money"><strong>TOTAL</strong></td>
<td></td>
<td class="money">${grandTotalPos?string.currency}</td>
<td></td>
</tr>

</table>
</div>
Send PMA a secure message about this account.<br/>
Communicate with PMA. <a href=<@spring.url "/contact"/>>Click Here</a>