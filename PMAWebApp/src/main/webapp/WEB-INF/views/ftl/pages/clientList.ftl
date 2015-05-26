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
<script>
$(document).ready(function(){
    $('#clientList').DataTable();
});
</script>

<div class="post" id="post-37">

<h1>Client List</h1>

<#assign content = data>
<p>
<table width="100%" id="clientList" name="clientList">
<thead>
  <tr>
    <th width="20%">username</th>
    <th width="20%">First</th> 
    <th width="20%">Last</th> 
    <th width="15%">Client Number</th>
    <th width="15%">Edit</th>
  </tr>
</thead>
<tbody>  
<#list content as client>
<tr>
<td bgcolor=${zebra(client_index)}>${client.username}</td>
<td bgcolor=${zebra(client_index)}>${client.firstName}</td>
<td bgcolor=${zebra(client_index)}>${client.lastName}</td>
<td bgcolor=${zebra(client_index)}>${client.clientNumber}</td>
<td bgcolor=${zebra(client_index)}>
<a href="client/${client.username}">Edit</a>
<!-- used to test Ajax <a href="c/${client.username}">Edit</a> -->
</td>
</tr>
</#list>
</tbody>
</table>
<hr/>
</div>
