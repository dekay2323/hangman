
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
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list game">
			
				<g:if test="${gameInstance?.solution}">
				<li class="fieldcontain">
					<span id="solution-label" class="property-label"><g:message code="game.solution.label" default="Solution" /></span>
					
						<span class="property-value" aria-labelledby="solution-label"><g:fieldValue bean="${gameInstance}" field="solution"/></span>
					
				</li>
				</g:if>
			
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
			
				<g:if test="${gameInstance?.currentSolution}">
				<li class="fieldcontain">
					<span id="currentSolution-label" class="property-label"><g:message code="game.currentSolution.label" default="Current Solution" /></span>
					
						<span class="property-value" aria-labelledby="currentSolution-label"><g:fieldValue bean="${gameInstance}" field="currentSolution"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.guess}">
				<li class="fieldcontain">
					<span id="guess-label" class="property-label"><g:message code="game.guess.label" default="Guess" /></span>
					
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
			
				<g:if test="${gameInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="game.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${gameInstance?.user?.id}">${gameInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="game.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${gameInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.score}">
				<li class="fieldcontain">
					<span id="score-label" class="property-label"><g:message code="game.score.label" default="Score" /></span>
					
						<span class="property-value" aria-labelledby="score-label"><g:fieldValue bean="${gameInstance}" field="score"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:gameInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${gameInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
