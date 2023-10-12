<!DOCTYPE html>
<html>
<head>
    <title>Ремонт, Строительство и Ко</title>
    <style>
        h4 a {
            margin-right: 20px;
            color: black;
            text-decoration: none;
            padding: 5px 10px;
            border-radius: 5px;
            background-color: #b9eeb9;
            transition: background-color 0.3s ease;
        }
    </style>
</head>
<body>
<h1>Ремонт, Строительство и Ко</h1><hr>
<h4>
    <p>
        <a href="/">Вернуться на главную</a>
        <a href="/contracts">Договоры</a>
        <a href="/employees">Работники</a>
        <a href="/materials">Материалы</a>
    </p>
</h4>
<hr>
<h2>Редактирование аккаунта</h2>
<form action="/user/editing/${user.id}" method="post" enctype="multipart/form-data">
    Логин: <input type="text" value="${user.login}" name="login"/><br><br>
    <h4>Для изменения пароля подтвердите текущий пароль</h4>
    Старый пароль: <input type="password" name="oldPassword"/><br><br>
    Новый пароль: <input type="password" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Изменить информацию"/>
</form>
<form action="/user/delete/${user.id}" method="post" enctype="multipart/form-data">
    <h4>Для удаления аккаунта подтвердите пароль</h4>
    Пароль: <input type="password" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить"/>
</form>
</body>
</html>