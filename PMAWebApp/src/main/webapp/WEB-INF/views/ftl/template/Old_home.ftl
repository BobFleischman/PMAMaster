<#import "/spring.ftl" as spring /> <#assign urlPrefix = '' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="<@spring.url '/resources/js/jquery/jquery-1.9.1.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources/js/jquery/jquery-ui-1.10.3.custom.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/ui-lightness/jquery-ui-1.10.3.custom.css'/>" />
<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/myCSS.css'/>" />
</head>
<body>
<div id="container">
	<div id="header">
		<h1>
			Hello world! by freemarker
		</h1>
	</div>
	<div id="navigation">
		<ul>
			<li><a href="#">Home</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Contact us</a></li>
		</ul>
	</div>
	<div id="content-container">
		<div id="content">
			<h2>
				Page heading
			</h2>
			<p>
				Lorem ipsum dolor sit amet consect etuer adipi scing elit sed diam nonummy nibh euismod tinunt ut laoreet dolore magna aliquam erat volut. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. 
			</p>
			<p>
				Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. 
			</p>
			<p>
				Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. 
			</p>
		</div>
		<div id="aside">
			<h3>
				Aside heading
			</h3>
			<p>
				The time on the server is ${serverTime}.
			</p>
		</div>
		<div id="footer">
			Copyright � Duane Morris LLP, 2013
		</div>
	</div>
</div>
</body>
</html>
