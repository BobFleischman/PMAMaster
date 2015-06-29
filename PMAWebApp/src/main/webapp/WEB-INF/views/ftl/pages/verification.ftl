<#import "/spring.ftl" as spring />
<#assign serverDir>
    <@spring.url '/' />
</#assign>
<div class="post" id="post-37">
<#if error??><div class="error">${error}</div></#if>
<#if needAnswers == 1>
<form method="POST" id="verify" action="${serverDir}level2/register" >
<@spring.formSingleSelect 'answers.question1', questions, ''/> 
<@spring.formInput 'answers.answer1', 'class="answer" required'  />
<@spring.formSingleSelect 'answers.question2', questions, ''/>
<@spring.formInput 'answers.answer2', 'class="answer" required' /> 
<@spring.formSingleSelect 'answers.question3', questions, ''/>
<@spring.formInput 'answers.answer3', 'class="answer" required' /> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br/>      
<input type="submit" value="Submit"/>
</form>
<#else>
<form method="POST" id="verify" action="${serverDir}level2/confirm" >
<h3>${verification.question}</h3> 
<@spring.formHiddenInput 'verification.questionNumber', '' /> 
<@spring.formInput 'verification.answer', 'class="answer" required' /> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br/>      
<input type="submit" value="Submit"/>
</form> 
</#if>
</div>
<script>
$("#verify").validate({
	errorElement: "div",
	errorPlacement: function(error, element) {
  		error.insertAfter(element);
	}
});
</script>