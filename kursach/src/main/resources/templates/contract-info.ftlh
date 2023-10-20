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
<h2>Подробная информация о договоре</h2>
<b>Бригадир </b><#if contract.employee??><a href="/employees/${contract.employee.id}">${contract.employee.name}</a><#else>уволен</#if><br>
<b>Стоимость </b>${contract.price} руб.<br>
<b>Описание </b>${contract.description}<br>
<b>Заказчик </b>${contract.clientName}<br>
<b>Телефон заказчика </b>${contract.clientPhone}<br>
<b>Дата заключения </b>${contract.conclusion}<br>
<b>Дата выполнения </b>${contract.completion}<br>
<b>Статус </b><#if contract.isDone==true>выполнен<#else> не выполнен</#if><br>
<hr>
<#if contract.isDone==false>
<h3>Добавить материал в смету</h3>
<form action="/contracts/${contract.id}" method="get">
    Поиск по наименованию<br>
    <input type="search" id="input" placeholder="Введите название материала" size="40" name="materialName" oninput="form.submit()"
                        value="${searchString}" autofocus onfocus="input.setSelectionRange(input.value.length, input.value.length)"/>
</form><br>
<#if searchString?has_content>
    <#list materials as material>
        <form action="/contracts/addMaterial" method="post" enctype="application/x-www-form-urlencoded" content="application/x-www-form-urlencoded;charset=UTF-8">
            <input type="hidden" name="contractId" value="${contract.id}">
            <input type="hidden" name="materialId" value="${material.id}">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <p>${material.name} (${material.description}) | <input type="number" placeholder="Введите количество" name="amount" step="1" size="5"> | <button>Добавить</button></p>
        </form>
    </#list>
</#if>
<#if contract.estimates?has_content>
    <b>Смета: </b>
    <#list contract.estimates as estimate>
        <#if estimate.id.material?has_content><p><a href="/materials/edit/${estimate.id.material.id}">${estimate.id.material.name}</a> ${estimate.amount} шт.</p>
        <#else> Материал был удален из системы</#if>
    </#list>
</#if>
<#if user.isDirector()>
<h3>Рассторгнуть/удалить договор</h3>
<form action="/contracts/delete/${contract.id}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить"/>
</form><br>
    <#else>
        <h3>Подтвердить завершение работ</h3>
        <form action="/contracts/${contract.id}/done" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input style="color:green" type="submit" value="Выполнено"/>
        </form><br>
</#if>
    <#if user.isDirector() && !contract.employee??>
        <form action="/contracts/${contract.id}/addForeman" method="post" enctype="multipart/form-data">
            Назначить бригадира <select required name="employeeId">
                <option value="">Выберите бригадира</option>
                <#list foremen as foreman>
                    <option value="${foreman.id}">${foreman.name}</option>
                </#list>
            </select><br><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Назначить"/>
        </form>
    </#if>
</#if>
</body>
</html>