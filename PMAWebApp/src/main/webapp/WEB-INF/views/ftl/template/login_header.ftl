<#import "/spring.ftl" as spring />
<#assign serverDir>
    <@spring.url '/' />
</#assign>
                <div class="content">
					<div class="subnav">
						<a href="${PMASERVER}/?page_id=35" class="contact">Contact Us</a>
                        <a href="${SecurePath}/login" class="account">Your Account</a>
                        <a href="<@spring.url '/login?logout' />" class="account">Log Out</a>
					</div>
					<a href="${PMASERVER}">
                    <img src="${PMASERVER}/wp-content/themes/pma/images/logo.png" alt="Prudent Management Associates"
						class="logo" /></a>
					<h1>Your Account</h1>
					<h2>This is a Secure Portal</h2>
					<div class="clear"></div>
				</div>