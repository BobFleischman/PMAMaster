<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '/' /></#assign>
<#macro makeRow label field>
<tr>
<td><label class="frmLabel">${label}</td>
<td><@spring.formInput "${field}", "", "text"/>
<@spring.showErrors "<br/>", "error"/></td>
</tr>
</#macro>
<div class="post" id="post-37">

<h1>Client List</h1>

<style>
.frmLabel {
  width: 50px;
}
</style>
<div id="login-box">
<@spring.bind "PMAClient" />
<@spring.showErrors "<br>" "" />
<form method="post" action="">
<table>
<@makeRow label="User Name:" field="PMAClient.username" />
<@makeRow label="First Name:" field="PMAClient.firstName" />
<@makeRow label="Last Name:" field="PMAClient.lastName" />
<@makeRow label="Client Accounts:" field="PMAClient.accountsAsCSV" />
<tr>
<td></td>
<td><input type="submit" value="Post Changes" /></td>
</tr>
</table>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />                       
</form>
</div>
</div>
