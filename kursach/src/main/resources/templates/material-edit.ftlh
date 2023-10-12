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
        <a href="/user/edit/${user.id}">Аккаунт</a>
    </p>
</h4>
<hr>
<h2>Редактирование материала</h2>
<form action="/materials/editing" method="post" enctype="multipart/form-data">
    Наименование <input type="text" value="${material.name}" name="name"/><br><br>
    Описание <input type="text" value="${material.description}"  name="description"/><br><br>
    Цена, руб. <input type="number" value="${material.toStringPrice()}" step="0.01" name="price" руб./><br><br>
    <input type="hidden" name="id" value="${material.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Сохранить"/>
</form><hr>
<h3>Удаление материала</h3>
<form action="/materials/delete/${material.id}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить материал"/>
</form><br>

</body>
</html>