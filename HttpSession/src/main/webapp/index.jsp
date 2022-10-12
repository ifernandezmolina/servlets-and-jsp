<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<body>
<h2>HttpSession example</h2>
</body>

<form action="/session" method="POST">
    <div>
        <label>Message from session: </label> <%= request.getSession().getAttribute("message")%>
    </div>
    <div>
        <button type="submit" name="Enviar">Enviar</button>
    </div>
</form>

</html>
