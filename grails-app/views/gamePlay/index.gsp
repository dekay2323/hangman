
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
			</ul>
		</div>
		<div id="list-game" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>

						<g:sortableColumn property="question" title="${message(code: 'game.question.label', default: 'Question')}" />
					
						<g:sortableColumn property="currentSolution" title="${message(code: 'game.currentSolution.label', default: 'Current Solution')}" />
					
						<g:sortableColumn property="score" title="${message(code: 'game.score.label', default: 'Score')}" />
					
						<g:sortableColumn property="dateWon" title="${message(code: 'game.dateWon.label', default: 'Date Won')}" />

						<g:sortableColumn property="dateLost" title="${message(code: 'game.dateLost.label', default: 'Date Lost')}" />					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameInstanceList}" status="i" var="gameInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${gameInstance.id}">${fieldValue(bean: gameInstance, field: "question")}</g:link></td>
					
						<td>${fieldValue(bean: gameInstance, field: "currentSolution")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "score")}</td>
					
						<td><g:formatDate date="${gameInstance.dateWon}" /></td>

						<td><g:formatDate date="${gameInstance.dateLost}" /></td>				
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
