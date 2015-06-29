<#import "/spring.ftl" as spring />
<#assign serverDir>
    <@spring.url '/' />
</#assign>
<style>
    .error {
        padding: 15px;
        margin-bottom: 20px;
        border: 1px solid transparent;
        border-radius: 4px;
        color: #a94442;
        background-color: #f2dede;
        border-color: #ebccd1;
    }
    .msg {
        padding: 15px;
        margin-bottom: 20px;
        border: 1px solid transparent;
        border-radius: 4px;
        color: #31708f;
        background-color: #d9edf7;
        border-color: #bce8f1;
    }
    #login-box {
        width: 300px;
        padding: 20px;
        margin: 100px auto;
        background: #fff;
        -webkit-border-radius: 2px;
        -moz-border-radius: 2px;
        border: 1px solid #000;
    }
</style>


<div class="post" id="post-37">
<!--    <div id="login-box"> -->
        <p>Welcome to the PMA secure client account login.</p>
        <h3>Login with Username and Password</h3>

        <#if error??>
            <div class="error">${error}</div>
        </#if>
        <#if msg??>
            <div class="msg">${msg}</div>
        </#if>

        <form id="loginForm" name='loginForm' action="<@spring.url '/login' />" method='POST'>

            <table>
                <tr>
                    <td>User:</td>
                    <td>
                        <input type='text'  id="username" name="username" value='' required>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <input type='password' id="password" name="password" required />
                    </td>
                </tr>
                <tr>
                    <td colspan='2'>
                        <input name="submit" type="submit" value="submit" />
                    </td>
                </tr>
            </table>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <p>
            If you have forgotten your UserID or Password,
            <br />call PMA at (215) 994-1062. This service is for current PMA clients only.
        </p>
        <p>
            If you would like information about opening an account,
            <br />please <a title="Contact Us" href="${PMAServer}/?page_id=35">contact
								us</a>.
        </p>
<!--    </div> -->
</div>
<script>
	$().ready(function() {
	$("loginForm").validate({
	  rules: {
    password: {
      required: true
    },
      username: {
    		field: {
      		required: true
    }
	  }}
	});
});

</script>