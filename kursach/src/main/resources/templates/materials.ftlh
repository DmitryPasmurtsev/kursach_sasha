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
    function setChecked() {
        document.getElementById("checkBox").checked = ${sortedByPrice?c};
    }
</script>

</head>
<body onload="setChecked()">
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
<h2>Материалы</h2>
<form action="/materials" method="get" id="searchForm">
    Поиск по названию материала<br>
  <input type="search" id="materialSearch" size="40" name="name" oninput="form.submit()"
                   value="${searchString}" autofocus onfocus="materialSearch.setSelectionRange(materialSearch.value.length, materialSearch.value.length)"/>
  <label for="checkBox"><br><br>Сортировка по стоимости  </label>
    <input type="checkbox" id="checkBox" name="sortedByPrice" onchange="form.submit()"/>
</form><br>
<#if materials?has_content>
<table border="1">
    <tr>
        <th>Название материала</th>
        <th>Описание</th>
        <th>Цена, руб.</th>
    </tr>
    <#list materials as material>
        <tr>
            <td><a href="/materials/edit/${material.id}">${material.name}</a></td>
            <td align="center">${material.description}</td>
            <td align="center">${material.price}</td>
        </tr>
    </#list>
</table>
<#else>
    <h3>Материалов нет</h3>
</#if>
<br>
<hr>
<h3>Добавление нового материала</h3>
<form action="/materials/create" method="post" enctype="multipart/form-data">
    Наименование <input type="text" required name="name"/><br><br>
    Описание <input type="text"  name="description"/><br><br>
    Цена за единицу <input type="number" required step="0.01" name="price" руб./><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить материал"/>
</form>
</body>
</html>