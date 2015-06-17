<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '/' /></#assign>
<div class="post" id="post-37">
<#include "../topMenu.ftl" />

<form method="POST" id="signupForm" name="signupForm" action="${serverDir}admin/changePassword">
  <fieldset>
    <legend>In order to change your password we need to confirm the old one</legend><br/>
<p>
<label class="pc" for="oldpassword">Old Password</label>
<input id="oldpassword" name="oldpassword" type="password" />
</p>
<p>
<label class="pc" for="password">New Password</label>
<input id="password" name="password" type="password"/>
</p>
<p>
<label class="pc" for="confirm_password">Confirm password</label>
<input id="confirm_password" name="confirm_password" type="password"/>
</p>
<p>
<input type="checkbox" id="showpassword">Show Passwords<br><br/>
<input class="submit" type="submit" value="Submit">
</p>
</fieldset>
</form>
<p id="passwordRules">
<h2>Password Rules</h2>
Must be between 8 and 20 characters long<br/>
Must have 1 lower case letter<br/>
Must have 1 upper case case letter<br/>
Must have 1 of @#$%<br/>
</p>
<script>
	$().ready(function() {
		$("#showpassword").click(function() {
			$("#signupForm").find('input:password').each(function() {
   $("<input type='text' />").attr({ name: this.name, value: this.value, id: this.id }).insertBefore(this);
}).remove();
		});
		// validate signup form on keyup and submit
		$("#signupForm").validate({
			rules: {
				oldpassword: {
					required: true,
				},
				password: {
					required: true,
					minlength: 8,
					pattern: "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%]).{8,20})"
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
					minlength: "Your password must be at least 8 characters long",
					pattern: "Must have at least one lowercase letter, one uppercase letter, one digit and one of @#$%"
				},
				oldpassword: {
					required: "Please provide a password",
					minlength: "Your password must be at least 8 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 8 characters long",
					equalTo: "Please enter the same password as above"
				}
			}
		});
});
</script>
</div>
