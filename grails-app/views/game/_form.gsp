<%@ page import="com.hangman.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'solution', 'error')} required">
	<label for="solution">
		<g:message code="game.solution.label" default="Solution" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="solution" maxlength="40" pattern="${gameInstance.constraints.solution.matches}" required="" value="${gameInstance?.solution}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="game.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="question" cols="40" rows="5" maxlength="255" required="" value="${gameInstance?.question}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'answers', 'error')} ">
	<label for="answers">
		<g:message code="game.answers.label" default="Answers" />
		
	</label>
	<g:textField name="answers" maxlength="50" value="${gameInstance?.answers}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'currentSolution', 'error')} ">
	<label for="currentSolution">
		<g:message code="game.currentSolution.label" default="Current Solution" />
		
	</label>
	<g:textField name="currentSolution" maxlength="40" value="${gameInstance?.currentSolution}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'guess', 'error')} ">
	<label for="guess">
		<g:message code="game.guess.label" default="Guess" />
		
	</label>
	<g:textField name="guess" maxlength="1" pattern="${gameInstance.constraints.guess.matches}" value="${gameInstance?.guess}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'dateWon', 'error')} ">
	<label for="dateWon">
		<g:message code="game.dateWon.label" default="Date Won" />
		
	</label>
	<g:datePicker name="dateWon" precision="day"  value="${gameInstance?.dateWon}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'dateLost', 'error')} ">
	<label for="dateLost">
		<g:message code="game.dateLost.label" default="Date Lost" />
		
	</label>
	<g:datePicker name="dateLost" precision="day"  value="${gameInstance?.dateLost}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="game.user.label" default="User" />
		
	</label>
	<g:select id="user" name="user.id" from="${com.hangman.User.list()}" optionKey="id" value="${gameInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'score', 'error')} required">
	<label for="score">
		<g:message code="game.score.label" default="Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="score" type="number" value="${gameInstance.score}" required=""/>

</div>

