<!DOCTYPE html>
<html>
<head>
    <title>Ремонт, Строительство и Ко</title>
    <style>
        h1 {
            font-size: 36px;
            text-align: center;
            margin-top: 20px;
            color: black;
        }
        p {
            font-size: 18px;
            line-height: 1.5;
            word-spacing: 15px;
            margin-bottom: 10px;
            color: black;
        }
        a {
            color: red;
            text-decoration: none;
            padding: 5px 10px;
            border-radius: 5px;
            background-color: white;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: darkseagreen;
        }
        hr {
            border: none;
            height: 10px;
            background-color: white;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        h4 {
            font-size: 24px;
            margin-top: 20px;
            color: black;
        }

        h4 a {
            margin-right: 10px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .b1 {
            background-color: #b9eeb9;
            color: black;
            border-radius: 5px;
            padding-inline: 1rem;
        }
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1 style="text-align: center;">Ремонт, Строительство и Ко</h1>
<hr>
<h4 style="position: relative; left: 50%; transform: translate(-50%,0); text-indent: 45%">Регистрация</h4><br>
<form action="/registration" method="post">
    <label for="email">Эл.почта</label><input id="email" type="email" required name="email"><br>
    <label for="login">Логин</label><input id="login" type="text" required name="login"><br>
    <label for="password">Пароль</label><input id="password" type="password" required name="password">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" style="margin-top: 10px;margin-bottom: 20px;" class="b1" value="Зарегистрироваться"/>
</form>
<div style="text-align: center;">
    <a href="/login" class="text-dark">Войти</a>
</div>
<#if errorMessage??>
    <h5 style="color:firebrick; position: relative; left: 500px;">Ошибка регистрации. Проверьте указанную почту</h5>
</#if>
</body>
</html>