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
    <script>
        function validateEmailAndSubmit() {
            var email = document.getElementById("email").value;
            const url = "/isEmailUnique?email=" + email;
            fetch(url)
                .then(
                    response => response.text()
                )
                .then(
                    text => {
                        if (text === "false") document.getElementById("error-message").innerHTML = "<p style='color: red'>Электронная почта должна быть уникальной</p>";
                        else document.getElementById("form").submit();
                    }
                );
        }
    </script>
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
<h2>Редактирование информации о работнике</h2>
<#if employee.image_bytes??>
    <img src="/employees/${employee.id}/image" height="100px"/><br>
    <br> <form action="/employees/${employee.id}/deleteImage" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить фото"/>
</form><br><br>
    <#else >
        <form action="/employees/${employee.id}/addImage" method="post" enctype="multipart/form-data">
            <input type="file" name="file"/><br><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Добавить фото"/>
        </form><br>
</#if>
<form action="/employees/editing" method="post" id="form" enctype="multipart/form-data" onsubmit="validateEmailAndSubmit()">
    ФИО <input type="text" value="${employee.name}" size="40" name="name"/><br><br>
    <#if user.isDirector()>
    Опыт работы <input type="number" value="${employee.experience}" min="0" name="experience"/><br><br>
        <#else>
            <input type="hidden" name="experience" value="${employee.experience}">
    </#if>
    Электронная почта <input type="email" id="email" value="${employee.email}"  name="email"/><br>
    <div id="error-message"></div><br>
    Номер телефона <input type="text" value="${employee.phone}" maxlength="13" minlength="11" name="phone"/><br><br>
    <input type="hidden" value="${employee.date}" name="date"/><br><br>
    <input type="hidden" value="${employee.id}" name="id">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="button" onclick="validateEmailAndSubmit()" value="Изменить информацию"/>
</form>
</body>
</html>