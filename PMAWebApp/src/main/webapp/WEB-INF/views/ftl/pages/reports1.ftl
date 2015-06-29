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
<tr>
<td><a href="details">A1234</a></td>
<td>Joe Smith</td>
<td class="money">$1,234,567.00</td>
</tr>
<tr>
<td><a href="details">A1235</a></td>
<td>Jill Smith</td>
<td class="money">$234,567.00</td>
</tr>
<tr>
<td><a href="details">A1236</a></td>
<td>Smith Children Trust</td>
<td class="money">$987,654.00</td>
</tr>
<tr>
<td></td>
<td><strong>Total</strong></td>
<td class="money">$2456788.00</td>
</tr>
</table>
</div>