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
        function setDefaultValues() {
            setChecked();
        }
        function setChecked() {
            document.getElementById("checkBox").checked = ${sortedByExperience?c};
        }
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
<body onload="setDefaultValues()">
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
<h2>Работники нашего прекрасного заведения</h2>
<form action="/employees" method="get">
    Поиск по ФИО <input type="search" id="fio" size="40" name="fullName" oninput="form.submit()"
                        value="${searchString}" autofocus onfocus="fio.setSelectionRange(fio.value.length, fio.value.length)"/>
    <label for="checkBox"><br><br>Сортировка по опыту  </label>
    <input type="checkbox" id="checkBox" name="sortedByExperience" onchange="form.submit()"/>
</form><br>

<#if employees?has_content>
<table border="1">
    <tr>
        <th>ФИО</th>
        <th>Должность</th>
        <th>Опыт работы</th>
    </tr>
    <#list employees as employee>
        <tr>
            <td><a href="/employees/${employee.id}">${employee.name}</a></td>
            <td align="center">${employee.position.name}</td>
            <td align="center">${employee.experience}</td>
        </tr>
    </#list>
</table>
<#else>
    <h3>Таких сотрудников тут отродясь не наблюдалось</h3>
</#if>
<hr>
<#if user.isDirector()>
<h3>Добавление нового сотрудника</h3>
<form action="/employees/create" id="form" method="post" enctype="multipart/form-data">
    ФИО <input type="text" required size="40" name="name"/><br><br>
    Опыт работы <input type="number" required min="0" name="experience"/><br><br>
    Электронная почта <input type="email" id="email" required name="email"/><br>
    <div id="error-message"></div><br>
    Номер телефона <input type="text" required maxlength="13" minlength="11" name="phone"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="button" onclick="validateEmailAndSubmit()" value="Добавить сотрудника"/>
</form>
</#if>
</body>
</html>