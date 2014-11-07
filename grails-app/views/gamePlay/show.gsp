
<%@ page import="com.hangman.Game" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${gameInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${gameInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>			
			<ol class="property-list game">
			
			
				<g:if test="${gameInstance?.question}">
				<li class="fieldcontain">
					<span id="question-label" class="property-label"><g:message code="game.question.label" default="Question" /></span>
					
						<span class="property-value" aria-labelledby="question-label"><g:fieldValue bean="${gameInstance}" field="question"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.answers}">
				<li class="fieldcontain">
					<span id="answers-label" class="property-label"><g:message code="game.answers.label" default="Answers" /></span>
					
						<span class="property-value" aria-labelledby="answers-label"><g:fieldValue bean="${gameInstance}" field="answers"/></span>
					
				</li>
				</g:if>
			
				<li class="fieldcontain">
					<span id="currentSolution-label" class="property-label"><g:message code="game.currentSolution.label" default="Current Solution" /></span>
					
						<span class="property-value" aria-labelledby="currentSolution-label"><g:fieldValue bean="${gameInstance}" field="currentSolution"/></span>
					
				</li>
			
				<g:if test="${gameInstance?.guess}">
				<li class="fieldcontain">
					<span id="guess-label" class="property-label"><g:message code="game.guess.label" default="Last Guess" /></span>
					
						<span class="property-value" aria-labelledby="guess-label"><g:fieldValue bean="${gameInstance}" field="guess"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.dateWon}">
				<li class="fieldcontain">
					<span id="dateWon-label" class="property-label"><g:message code="game.dateWon.label" default="Date Won" /></span>
					
						<span class="property-value" aria-labelledby="dateWon-label"><g:formatDate date="${gameInstance?.dateWon}" /></span>
					
				</li>
				</g:if>

				<g:if test="${gameInstance?.dateLost}">
				<li class="fieldcontain">
					<span id="dateLost-label" class="property-label"><g:message code="game.dateLost.label" default="Date Lost" /></span>
					
						<span class="property-value" aria-labelledby="dateLost-label"><g:formatDate date="${gameInstance?.dateLost}" /></span>
					
				</li>
				</g:if>
			
			
				<g:if test="${gameInstance?.score}">
				<li class="fieldcontain">
					<span id="score-label" class="property-label"><g:message code="game.score.label" default="Score" /></span>
					
						<span class="property-value" aria-labelledby="score-label"><g:fieldValue bean="${gameInstance}" field="score"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="game.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${gameInstance?.user?.id}">${gameInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="update" id="${gameInstance?.id}" method="PUT" >
				<g:hiddenField name="version" value="${gameInstance?.version}" />
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'guess', 'error')} ">
						<label for="guess">
							<g:message code="game.guess.label" default="Guess" />
							
						</label>
						<g:textField name="guess" maxlength="1" pattern="${gameInstance.constraints.guess.matches}" value=""/>

					</div>			
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="Guess" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
