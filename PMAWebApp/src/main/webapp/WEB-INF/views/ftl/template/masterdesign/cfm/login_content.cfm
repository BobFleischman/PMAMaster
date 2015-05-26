				<div class="post" id="post-37">
					<ul class="children"></ul>
					<div class="entry">
						<p>Welcome to the PMA secure client account login.</p>
                        <CFIF #IsDefined("InValid")#>
							<H3>Invalid UserID or Password</H3>
						</CFIF>
						<p>Enter your UserID and Password to access your PMA account balance or to send PMA a confidential, secure email.</p>
							<form action="<cfoutput>#PMAServerSecure#</cfoutput>/login/login2.cfm" method="post"  novalidate="novalidate">
								<p>
									<span class="wpcf7-form-control-wrap text-538">
                                    <input type="text" name="UserID" value="" size="40"	class="wpcf7-form-control wpcf7-text" placeholder="User ID" />
                                    </span><br /> 
                                   <span class="wpcf7-form-control-wrap password">
                                   <input type="password" name="Password" value="" size="40" class="wpcf7-form-control wpcf7-text" placeholder="Password" />
                                   </span>
								</p>
								<p>
									<input type="submit" value="Login" class="wpcf7-form-control wpcf7-submit" />
								</p>
								<div class="clear"></div>
								<div class="wpcf7-response-output wpcf7-display-none"></div>
							</form>
						<p>
							If you have forgotten your UserID or Password,<br /> call PMA at (215) 994-1062. This service is for current PMA clients
							only.
						</p>
						<p>
							If you would like information about opening an account,<br /> please <a title="Contact Us" href="#PMAServer#/?page_id=35">contact
								us</a>.
						</p>
					</div>
				</div>
