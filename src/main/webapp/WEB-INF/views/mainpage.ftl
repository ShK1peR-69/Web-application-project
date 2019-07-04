<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="../../resources/images/icon.ico" type="image/x-icon">
    <title>SportTime</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
    <script type="text/javascript" src="../../resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../resources/js/upButton.js"></script>
    <script type="text/javascript" src="../../resources/js/infoWindows.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</head>
<body onresize="">
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
                        <button type="button" class="list-group-item list-group-item-action active">
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
            <#include "templates/news.ftl"/>
            <div class="up-button" id="toTop">
                <img src="/resources/images/upButton-img.jpg" alt="Вверх">
            </div>
        </div>
    </section>

    <#include "templates/footer.ftl"/>

</div>
</body>
</html>