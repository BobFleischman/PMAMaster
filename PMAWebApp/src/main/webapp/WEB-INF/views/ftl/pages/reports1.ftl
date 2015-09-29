<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '' /></#assign>
<style>
.money {
text-align: right;
}
td {
padding: 5px;
}

</style>
<div class="post" id="post-37">
<#include "topMenu.ftl" />
<h1>${client.getMasterClientName()} Reports</h1>
<p>This information is based upon closing balances and prices and includes estimated proceeeds of pending transactions.</p>
<p>Click on any account for more detail.</p>
<table>
<tr>
<th>Account Number</th>
<th>Account Name</th>
<th>Balance</th>
</tr>
<#list totals as total>
<tr>
<td><a href="details">${total.clientNumber?c}</a></td>
<td>${total.accountName}</td>
<td class="money">$${total.twoDigitTotal}</td>
</tr>
</#list>
<tr>
<tr>
<td></td>
<td><strong>Total</strong></td>
<td class="money">$${grandTotal}</td>
</tr>
</table>
</div>