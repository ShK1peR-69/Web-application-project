<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>

<!DOCTYPE html>
<html lang="en">

<#include "templates/headSettings.ftl"/>

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
                        <button type="button" class="list-group-item list-group-item-action active">
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