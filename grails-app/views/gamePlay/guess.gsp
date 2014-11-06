
<%@ page import="com.hangman.Game" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list game">
			
			<li class="fieldcontain">
				<span id="solution-label" class="property-label">user</span>				<span class="property-value" aria-labelledby="solution-label">${gameInstance?.user}</span>	
			</li>
			<li class="fieldcontain">
				<span id="solution-label" class="property-label">question</span>				<span class="property-value" aria-labelledby="solution-label">${gameInstance?.question}</span>	
			</li>
			<li class="fieldcontain">
				<span id="solution-label" class="property-label">score</span>				<span class="property-value" aria-labelledby="solution-label">${gameInstance?.score}</span>	
			</li>
			<li class="fieldcontain">
				<span id="solution-label" class="property-label">answers</span>				<span class="property-value" aria-labelledby="solution-label">${gameInstance?.answers}</span>	
			</li>		
			</ol>
		</div>
		<div id="edit-game" class="content scaffold-edit" role="main">
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
			<g:form url="[resource:gameInstance, controller:'gamePlay', action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${gameInstance?.version}" />
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'guess', 'error')} ">
						<label for="guess">
							<g:message code="game.guess.label" default="Guess" />		
						</label>
						<g:textField name="guess" maxlength="1" value="${gameInstance?.guess}"/>
					</div>				
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>