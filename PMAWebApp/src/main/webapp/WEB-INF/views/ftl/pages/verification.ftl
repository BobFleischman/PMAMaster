<#import "/spring.ftl" as spring />
<#assign serverDir>
    <@spring.url '/' />
</#assign>
${answers}
#{answers.getQuestion1()}
#{answers.question1}

<div class="post" id="post-37">
<form method="POST">
<@spring.formSingleSelect 'answers.question1', questions, ''/> 
<@spring.formInput 'answers.answer1', '' />
<@spring.formSingleSelect 'answers.question2', questions, ''/>
<@spring.formInput 'answers.answer2', '' /> 
<@spring.formSingleSelect 'answers.question3', questions, ''/>
<@spring.formInput 'answers.answer3', '' /> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />      
<input type="submit" value="Add Pet" />
</form>
</div>