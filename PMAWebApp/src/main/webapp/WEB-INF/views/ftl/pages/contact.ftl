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
</ul>
<table width="604" border="0" cellspacing="0" cellpadding="0">
	<tr>

		<td class="TightCopy">
			<br>
			<br>
			This service offers clients a secure way to communicate with us.
			<br>
			Any information you submit will remain confidential.  You may withdraw
			<br>
			money or ask us questions about Your Account.
			<br>
			<form ACTION="communicate_submit_md.cfm?clientno=1759" method="post">
				<table border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td class="TableCell" style="background-color:E6E9ED"><I>Comments:</i></td>
					</tr>
					<tr>
						<td class="TableCell" style="background-color:E6E9ED">
							<textarea cols="50" rows="10" name="comments" style="border: 2px solid #765942;border-radius: 10px;;background-color:E6E9ED"> </textarea>
						</td>
					</tr>
				</table>
				
				<input Type="submit" value="Submit Message" align="middle">
				
			</form>
					</td>
	</tr>
</table>
</div>

