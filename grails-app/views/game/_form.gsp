<%@ page import="com.hangman.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'solution', 'error')} required">
	<label for="solution">
		<g:message code="game.solution.label" default="Solution" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="solution" required="" value="${gameInstance?.solution}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="game.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="question" required="" value="${gameInstance?.question}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'answers', 'error')} ">
	<label for="answers">
		<g:message code="game.answers.label" default="Answers" />
		
	</label>
	<g:textField name="answers" value="${gameInstance?.answers}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'score', 'error')} required">
	<label for="score">
		<g:message code="game.score.label" default="Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="score" type="number" value="${gameInstance.score}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="game.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.hangman.User.list()}" optionKey="id" required="" value="${gameInstance?.user?.id}" class="many-to-one"/>

</div>

