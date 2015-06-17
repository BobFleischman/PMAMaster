<#import "/spring.ftl" as spring />
<#assign thisServer><@spring.url "/" /></#assign>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US">

<head profile="http://gmpg.org/xfn/11">
<#include "masterdesign/masterdesign_head.ftl">
${_extraHead_}
<!-- stuff here -->
</head>

<body class="page page-id-37 page-template-default">
	<div id="container">
		<div id="wrapper">
			<div id="header">
                <#include "login_header.ftl">
			</div>
			<div class="content">
                ${_body_}				
                <#include "masterdesign/masterdesign_sidebar.ftl">				
			</div>
			<div class="clear"></div>
		</div>
        <#include "masterdesign/masterdesign_footer.ftl">
	</div>
    <#include "masterdesign/masterdesign_footer_js.ftl">
</body>
</html>