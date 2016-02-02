<#import "/spring.ftl" as spring />
<#assign serverDir><@spring.url '/' /></#assign>
<div class="post" id="post-37">
<form method="POST" id="verify" action="${serverDir}level2/resetAnswers" >
<h3>This button will reset your verification questions</h3> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br/>      
<input type="submit" value="Submit"/>
</form> 

</div>