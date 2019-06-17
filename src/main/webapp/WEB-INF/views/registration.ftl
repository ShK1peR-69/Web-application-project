<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="windows-1251">
    <link rel="shortcut icon" href="../../resources/images/icon.ico" type="image/x-icon">
    <title>SportTime</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
    <script type="text/javascript" src="../../resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../resources/js/upButton.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</head>
<body>
<div class="mainpage">

    <#include "templates/header.ftl"/>
    <#include "templates/header-hr.ftl"/>

    <section>
        <div class="main-block">
            <div class="main-navigation-block">
                <div class="list-menu">
                    <@sec.authorize ifAnyGranted="ROLE_ADMIN">
                        <a href="/admin/main">
                            <button type="button" class="list-group-item list-group-item-action admin-button">
                                Управление (Админ)
                            </button>
                        </a>
                    </@sec.authorize>
                    <a href="/">
                        <button type="button" class="list-group-item list-group-item-action">
                            Главная
                        </button>
                    </a>
                    <@sec.authorize access="isAuthenticated()">
                        <a href="/profile">
                            <button type="button" class="list-group-item list-group-item-action">
                                Профиль
                            </button>
                        </a>
                    </@sec.authorize>
                    <a href="/football">
                        <button type="button" class="list-group-item list-group-item-action">
                            Футбол
                        </button>
                    </a>
                    <a href="/hockey">
                        <button type="button" class="list-group-item list-group-item-action">
                            Хоккей
                        </button>
                    </a>
                    <a href="/basketball">
                        <button type="button" class="list-group-item list-group-item-action">
                            Баскетбол
                        </button>
                    </a>
                    <a href="/volleyball">
                        <button type="button" class="list-group-item list-group-item-action">
                            Волейбол
                        </button>
                    </a>
                    <a href="/tennis">
                        <button type="button" class="list-group-item list-group-item-action">
                            Теннис
                        </button>
                    </a>
                </div>
                <#include "templates/advertising.ftl"/>
            </div>

            <div class="registration-block">
                <mark>Регистрация нового пользователя</mark>
                <#if error?? && error?has_content>
                    <div class="error_msg">Пароли не совпадают. Пожалуйста, попробуйте снова</div>
                </#if>
                <form action="/new-registration" method="post">
                    <label class="registration-label">
                        <span><b>* </b>Имя: </span>
                        <input type="text" maxlength="35" name="first_name" required/>
                    </label>
                    <label class="registration-label">
                        <span><b>* </b>Фамилия: </span>
                        <input type="text" maxlength="35" name="second_name" required/>
                    </label>
                    <label class="registration-label">
                        <span><b>* </b>E-mail: </span>
                        <div><input type="email" name="email" maxlength="50"
                                    oncontextmenu="return false" required id="mail_input"/><br/>
                            <div class="mail-error"></div>
                        </div>
                    </label>
                    <label class="registration-label">
                        <span><b>** </b>Придумайте пароль: </span>
                        <div><input type="password" maxlength="30" name="one_password" minlength="4"
                                    oncontextmenu="return false" required id="first_pass"/><br/>
                            <div class="error"></div>
                        </div>
                    </label>
                    <label class="registration-label">
                        <span><b>** </b>Повторите пароль: </span>
                        <div><input type="password" maxlength="30" name="two_password"
                                    oncontextmenu="return false" minlength="4"
                                    required id="second_pass"/><br/>
                            <div class="error"></div>
                        </div>
                    </label>
                    <p>* - обязательно для заполнения</p>
                    <p>** - пароли должны совпадать</p>
                    <button class="registration-block-button" type="submit">Зарегистрироваться</button>
                </form>
            </div>

        </div>
    </section>

    <#include "templates/footer.ftl"/>

    <div class="up-button" id="toTop">
        <img src="/resources/images/upButton-img.jpg" alt="Вверх">
    </div>

</div>

<script type="text/javascript" src="../../resources/js/checkPass.js"></script>
<script type="text/javascript" src="../../resources/js/checkUserMail.js"></script>

</body>
</html>