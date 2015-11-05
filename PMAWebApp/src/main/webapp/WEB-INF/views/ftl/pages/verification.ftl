<#import "/spring.ftl" as spring />
<#assign serverDir>
    <@spring.url '/' />
</#assign>
<style>
.question {
font-family: 'Cardo', serif;
}
</style>
<div class="post" id="post-37">
<#if error??><div class="error">${error}</div></#if>
<#if needAnswers == 1>
<h2>Please choose three different questions from the list below.</h2>
<form method="POST" id="verify" action="${serverDir}level2/register" >
<@spring.formSingleSelect 'answers.question1', questions, 'class="question"'/> 
<@spring.formInput 'answers.answer1', 'class="answer" required'  />
<@spring.formSingleSelect 'answers.question2', questions, 'class="question"'/>
<@spring.formInput 'answers.answer2', 'class="answer" required' /> 
<@spring.formSingleSelect 'answers.question3', questions, 'class="question"'/>
<@spring.formInput 'answers.answer3', 'class="answer" required' /> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br/>      
<input type="submit" value="Submit"/>
</form>
<script>
$("#verify").validate({
	errorElement: "div",
	errorPlacement: function(error, element) {
  		error.insertAfter(element);
	},
	submitHandler: function(form) {
    console.log('in submit handler');
    q1 = $('#question1').val();
    q2 = $('#question2').val();
    q3 = $('#question3').val();
    if (q1 == -1) {
      alert('You must selection a question for Selection 1');
      return;
    }
    if (q2 == -1) {
      alert('You must selection a question for Selection 2');
      return;
    }
    if (q2 == -1) {
      alert('You must selection a question for Selection 3');
      return;
    }
    if (q1 == q2) {
        alert('Questions one and two must be different.');
        return;
    } 
    if (q1 == q3) {
        alert('Questions one and three must be different.');
        return;
    } 
    if (q2 == q3) {
        alert('Questions two and three must be different.');
        return;
    } 
    form.submit();
  }
});
</script>
<#else>
<form method="POST" id="verify" action="${serverDir}level2/confirm" >
<h3>${verification.question}</h3> 
<@spring.formHiddenInput 'verification.questionNumber', '' /> 
<@spring.formInput 'verification.answer', 'class="answer" required' /> 
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br/>      
<input type="submit" value="Submit"/>
</form> 
<script>
$("#verify").validate({
	errorElement: "div",
	errorPlacement: function(error, element) {
  		error.insertAfter(element);
	}
});
</script></#if>
</div>
