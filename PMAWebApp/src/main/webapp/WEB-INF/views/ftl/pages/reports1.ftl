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
<ul class="children">
<li class="page_item page-item-159"><a href=<@spring.url "/reports/list"/>>Account Information</a></li>
<li class="page_item page-item-166"><a href=<@spring.url "/login?logout"/>>Logout</a></li>
</ul>
<h1>${client.getMasterClientName()} Reports</h1>
<p>This information is based upon closing balances and prices and includes estimated proceeeds of pending transactions.</p>
<p>${updateDate}</p>
<p>Click on any account for more detail.</p>
<table>
<tr>
<th>Account Number</th>
<th>Account Name</th>
<th>Balance</th>
</tr>
<#list totals as total>
<tr>
<td><a href="details/${total.clientNumber?c}">${total.clientNumber?c}</a></td>
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

<p style="padding-top:15px; border-top:1px solid red;margin-top:15px;">If you would like to reset your password or change your security questions kindly <a href="<@spring.url "/account/maintenance"/>">click here</a>
</p>
</div>