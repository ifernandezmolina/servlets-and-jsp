<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>ACB Players</title>
</head>
<body>

<h3>Votaci&oacute;n al mejor jugador de la liga ACB 2013/2014</h3>

<hr>

<form action="${pageContext.servletContext.contextPath}/players" method="POST">

    <c:forEach items="${requestScope.get('players')}" var="player">
        <c:set var="imageURL" value="img/${player.jpgImage}"></c:set>
        <img src="${imageURL}" width="100" height="67"/>
        <p>
            <input type="radio" name="player" value="${player.id}">${player.name} - ${player.votes}
            Votos
        </p>
    </c:forEach>

    <p>
        <input type="submit" value="Votar">
    </p>

</form>

</body>
</html>
