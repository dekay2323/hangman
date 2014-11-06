
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
		<div id="list-game" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="solution" title="${message(code: 'game.solution.label', default: 'Solution')}" />
					
						<g:sortableColumn property="question" title="${message(code: 'game.question.label', default: 'Question')}" />
					
						<g:sortableColumn property="answers" title="${message(code: 'game.answers.label', default: 'Answers')}" />
					
						<g:sortableColumn property="guess" title="${message(code: 'game.guess.label', default: 'Guess')}" />
					
						<g:sortableColumn property="score" title="${message(code: 'game.score.label', default: 'Score')}" />
					
						<th><g:message code="game.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameInstanceList}" status="i" var="gameInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameInstance.id}">${fieldValue(bean: gameInstance, field: "solution")}</g:link></td>
					
						<td>${fieldValue(bean: gameInstance, field: "question")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "answers")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "guess")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "score")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "user")}</td>
					
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
