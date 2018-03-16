<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Manager: Login</title>
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
    <h1>Login</h1>
</header>

<form class="login_form" method="post" action="login">
    <input type="number" name="id" value="1" hidden/>
    <input type="text" name="login" required/>
    <input type="password" name="password" required/>
    <input type="submit" required/>
</form>

</body>
</html>
