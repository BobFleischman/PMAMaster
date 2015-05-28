<#import "/spring.ftl" as spring />
<#assign serverDir>
    <@spring.url '/' />
</#assign>
<#if needAnswers == 1>
<div class="post" id="post-37">
<form method="POST" action="${serverDir}level2/register" >
<@spring.formSingleSelect 'answers.question1', questions, ''/> 
<@spring.formInput 'answers.answer1', '' />
<@spring.formSingleSelect 'answers.question2', questions, ''/>
<@spring.formInput 'answers.answer2', '' /> 
<@spring.formSingleSelect 'answers.question3', questions, ''/>
<@spring.formInput 'answers.answer3', '' /> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br/>      
<input type="submit" value="Submit"/>
</form>
<#else>
<div class="post" id="post-37">
<form method="POST" action="${serverDir}level2/confirm" >
<h3>${verification.question}</h3> 
<@spring.formHiddenInput 'verification.questionNumber', '' /> 
<@spring.formInput 'verification.answer', '' /> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br/>      
<input type="submit" value="Submit"/>
</form> 
</#if>
</div>
