<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '/' /></#assign>
<#function zebra index>
  <#if (index % 2) == 0>
    <#return "white" />
  <#else>
    <#return "#efefef" />
  </#if>
</#function>

<style>
table, th {
    border: 1px solid black;
}
</style>
<div class="post" id="post-37">

<h1>${client.getDisplayName()} Reports</h1>


<table width="100%">
  <tr>
    <th width="30%">Title</th>
    <th width="30%">Type</th> 
    <th width="15%">Period</th> 
    <th width="25%">Date</th>
  </tr>
<#list reports as report>
<tr>
<td bgcolor=${zebra(report_index)}>${report.reportTitle}</td>
<td bgcolor=${zebra(report_index)}>${report.reportType.getHumanReadable()}</td>
<td bgcolor=${zebra(report_index)}>${report.period}</td>
<td bgcolor=${zebra(report_index)}>${report.reportDate?date}</td>
</tr>
</#list>
</table>
<hr/>
</div>
