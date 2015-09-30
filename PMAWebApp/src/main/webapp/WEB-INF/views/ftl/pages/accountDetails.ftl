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
<ul>
<li><div class="topMenu"><a href="${serverDir}/reports/list">Account Info</a></div></li>
<li><div class="topMenu">Reports</div></li>
<li><div class="topMenu"><a href="export/${acctNo}">Exports</a></div></li>
</ul>
<p>This information is based upon closing balances and prices and includes estimated proceeeds of pending transactions.</p>
<p>${updateDate}</p>
<#assign fundType = "zzz">
<#assign subTotal = -1>
<#assign grandTotalX = 0>
<h1>${accountName}</h1>
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
<td class="money">${subTotal}</td>
<#assign subPct = (subTotal / grandTotal) * 100>
<td class="money">${subPct?string["0.#"]}</td>
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
<td class="money">${fund.marketValue}</td>
<td class="money">${fund.percentOfTotal}</td>
</tr>

</#list>
<!-- sub total -->
<tr>
<td></td>
<td class="money"><strong>SUB-TOTAL</strong></td>
<td></td>
<td class="money">${subTotal}</td>
<#assign subPct = (subTotal / grandTotal) * 100>
<td class="money">${subPct?string["0.#"]}</td>
</tr>
<!-- grand total -->
<tr>
<td></td>
<td class="money"><strong>TOTAL</strong></td>
<td></td>
<td class="money">${grandTotalPos}</td>
<td></td>
</tr>

</table>
</div>