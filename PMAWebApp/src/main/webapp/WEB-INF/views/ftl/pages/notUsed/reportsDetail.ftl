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
<#include "topMenu.ftl" />
<h1>${client.getMasterClientName()} Reports for ${acctNo}</h1>
<form>
<label for="reportType">Report Type</label>
<select id="reportType" name="reportType">
<option>Transaction Summary</option>
<option>Market Value Statement</option>
<option>Performance Report</option>
</select>
<label for="reportPeriod">Report Period</label>
<select id="reportPeriod" name="reportPeriod">
<option>Annual</option>
<option>Quarterly</option>
<option>Monthly</option>
</select>
</form>
<table>
<tr><td><a href="">Q1 2014</a></td></tr>
<tr><td><a href="">Q2 2014</a></td></tr>
<tr><td><a href="">Q3 2014</a></td></tr>
<tr><td><a href="">Q4 2014</a></td></tr>
<tr><td><a href="">Q1 2015</a></td></tr>
<tr><td><a href="">Q2 2015</a></td></tr>
</table>
</div>