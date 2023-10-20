<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
</head>
<body>
<h1 style="text-align: center;">Ремонт, Строительство и Ко</h1>
<hr>
<h4 style="text-align: center;">Авторизация</h4>
<br>
<form action="/login" method="post" style="">
    <label for="login">Логин</label><input type="text" id="login" required name="username"><br>
    <label for="password">Пароль</label><input type="password" name="password">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input style="margin-top: 10px;margin-bottom: 20px;" type="submit" class="b1" value="Вход"/>
</form>
<div style="text-align: center;">
    <a href="/registration" class="text-dark">Зарегистрироваться</a>
</div>
</body>
</html>


