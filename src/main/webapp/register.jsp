<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.03.2018
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Manager: Register</title>
    <style type="text/css">
        body {
            background: url(http://muzotkrytka.net/_ph/1/2/612853968.gif) repeat;
            text-align: center;
        }

        header h1 {
            font-size: 90px;
            color: white;
        }

        h2 {
            color: white;
        }
    </style>
</head>
<body>

<header>
    <h1>Register</h1>
</header>

<form class="register_form" method="post" action="register">
    <input type="number" name="id" value="1" hidden/>
    <input type="text" name="login" required/>
    <input type="password" name="password" required/>
    <input type="submit" required/>
</form>

</body>
</html>
