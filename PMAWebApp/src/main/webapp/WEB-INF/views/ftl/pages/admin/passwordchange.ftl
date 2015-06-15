
<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '/' /></#assign>
<style>
</style>
<div class="post" id="post-37">
<#include "../topMenu.ftl" />
<h1>Reports will go here</h1>
<form method="POST" id="signupForm" name="signupForm" action="${serverDir}admin/changePassword">
<p>
<label class="pc" for="oldpassword">Old Password</label>
<input id="oldpassword" name="oldpassword" type="password">
</p>
<p>
<label class="pc" for="password">New Password</label>
<input id="password" name="password" type="password">
</p>
<p>
<label class="pc" for="confirm_password">Confirm password</label>
<input id="confirm_password" name="confirm_password" type="password">
</p>
<p>
</form>
</div>
<script>
	$().ready(function() {
		// validate signup form on keyup and submit
		$("#signupForm").validate({
			rules: {
				oldpassword: {
					required: true,
					minlength: 8
				},
				password: {
					required: true,
					minlength: 8
				},
				confirm_password: {
					required: true,
					minlength: 8,
					equalTo: "#password"
				}
			},
			messages: {
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				oldpassword: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				}
			}
		});

</script>