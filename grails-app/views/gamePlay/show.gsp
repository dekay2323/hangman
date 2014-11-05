
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
			<g:form url="[resource:gameInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${gameInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
