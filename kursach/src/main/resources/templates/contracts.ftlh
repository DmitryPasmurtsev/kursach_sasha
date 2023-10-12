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
<#if user.isDirector()>
    <h2>Все договоры</h2>
    <#else>
        <h2>Ваши договоры</h2>
</#if>
<#if user.isDirector()>
    <form action="/contracts/create/start" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Заключить новый договор"/>
    </form>
</#if>
<#list contracts as contract>
    <div>
        <p> ${contract.conclusion} | ${contract.description} | <a href="/contracts/${contract.id}">Подробнее</a></p>
    </div>
    <#else>
    <h3>Договоров нет</h3>
</#list>
<hr>
</body>
</html>