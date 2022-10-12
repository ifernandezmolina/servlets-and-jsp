<%@ page import="java.util.List" %>
<%@ page import="es.uah.domain.Player" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ACB Players</title>
</head>
<body>

<h3>Votaci&oacute;n al mejor jugador de la liga ACB 2013/2014</h3>

<hr>

<form action="<%= pageContext.getServletContext().getContextPath()%>/players" method="POST">

    <%
        final List<Player> players = (List<Player>) request.getAttribute("players");
        for(Player player : players){
    %>

    <img src="img/<%=player.getJpgImage()%>" width="100" height="67"/>
    <p>
        <input type="radio" name="player" value="<%=player.getId()%>"><%=player.getName()%> - <%=player.getVotes()%>
        Votos
    </p>

    <%
        }
    %>

    <p>
        <input type="submit" value="Votar">
    </p>

</form>

</body>
</html>
