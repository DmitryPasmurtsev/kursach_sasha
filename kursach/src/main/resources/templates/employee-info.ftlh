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
<h2>Подробная информация о работнике</h2>
<#if employee.image_bytes??>
    <br> <img src="/employees/${employee.id}/image" height="100px"/><br><br>
    <#else>
        <br> <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5c2QCGWIDwM5VfLmcIWkU3aMzzQ18uf2ISQ&usqp=CAU" height="80px"/><br><br>
</#if>
<b>ФИО </b>${employee.name}<br>
<b>Должность </b>${employee.position.name}<br>
<b>Опыт работы </b>${employee.experience}<br>
<b>Электронная почта </b>${employee.email}<br>
<b>Номер телефона </b>${employee.phone}<br>
<b>Дата приема на работу </b>${employee.date}<br>
<#if employee.user?? && employee.user.isForeman()><b>Количество выполненных работ </b>${employee.amountOfDoneContracts()}<br></#if>
<br>
<#if !(user.isForeman() && (user.employee.id !=employee.id))>
    <a href="/employees/edit/${employee.id}">Редактирование информации</a>
</#if>
<hr>
<#if user.isDirector() && !employee.isOutstandingContracts() && !(employee.email == user.email)>
<h3>Удаление работника</h3>
<form action="/employees/delete/${employee.id}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить работника"/>
</form><br></#if>
<#if user.isDirector() && employee.user?? && employee.id!=user.employee.id>
    <h3>Удаление аккаунта</h3>
    <form action="/user/delete/${employee.user.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="employeeId" value="${employee.id}">
        <input type="hidden" name="directorId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить аккаунт"/>
    </form><br></#if>
</body>
</html>