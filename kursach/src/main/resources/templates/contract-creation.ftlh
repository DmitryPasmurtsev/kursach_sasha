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
        function setMinConclusionDate() {
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1; //January is 0!
            var yyyy = today.getFullYear();
            if (dd < 10) dd = '0' + dd;
            if (mm < 10) mm = '0' + mm;
            today = yyyy + '-' + mm + '-' + dd;
            document.getElementById("conclusion").setAttribute("min", today);
            document.getElementById("conclusion").value = today;
            setMinCompletionDate();
        }
        function setMinCompletionDate() {
            return document.getElementById("completion").setAttribute("min", document.getElementById("conclusion").value);
        }
    </script>
</head>
<body onload="setMinConclusionDate()">
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
<h2>Заключение договора</h2><hr>
    <form action="/contracts" method="post" enctype="multipart/form-data">
        Описание <input type="text" required size="40" name="description"/><br><br>
        Заказчик <input type="text" required size="40" name="clientName"/><br><br>
        Номер телефона заказчика <input type="text" required maxlength="13" minlength="11" name="clientPhone"/><br><br>
        Дата заключения <input type="date" id="conclusion" required name="conclusion" onchange="setMinCompletionDate()"/><br><br>
        Дата выполнения <input type="date" id="completion" required name="completion"/><br><br>
        Начальная стоимость работ <input type="number" required step="0.01" name="price" руб./><br><br>
        Бригадир <select required name="employee">
            <option value="">Выберите бригадира</option>
            <#list foremen as foreman>
                <option value="${foreman.id}">${foreman.name}</option>
            </#list>
        </select><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Заказ"/>
    </form>
</body>
</html>