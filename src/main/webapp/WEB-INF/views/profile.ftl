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
                            <button type="button" class="list-group-item list-group-item-action active">
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

            <div class="profile-block">
                <div class="profile-block-info">
                    <#if user?? && user?has_content>
                        <div class="profile-block-div"><b>Имя:</b>&nbsp;${user.getName()}</div> <#--${first_name}-->
                        <div class="profile-block-div"><b>Фамилия:</b>&nbsp;${user.getSecondName()}</div>
                        <div class="profile-block-div"><b>E-mail:</b>&nbsp;${user.getEmail()}</div>
                    </#if>
                    <div class="margin-div">
                        <label class="profile-label">
                            <span>Новый пароль: </span>
                            <input type="password" id="old_pass" minlength="4" required/>
                            <div id="old_pass_error" disabled></div>
                        </label>
                        <label class="profile-label">
                            <span>Повторите пароль: </span>
                            <input type="password" minlength="4" id="new_pass" required/>
                            <div id="new_pass_error" disabled></div>
                        </label>
                        <button type="submit" class="profile-block-button" id="changePassword">Обновить пароль</button>
                        <a href="/logout" class="logout-button">
                            <button class="profile-logout-button">Выйти</button>
                        </a>
                    </div>
                </div>
                <div class="profile-block-articles">
                    <b>Список ваших статьей</b>
                    <@sec.authorize ifAnyGranted="ROLE_ADMIN">
                        <#if articles?? && articles?has_content>
                            <ol>
                                <#list articles as a>
                                    <li><a href="/article/${a.getId()}">${a.getTitle()}</a></li>
                                </#list>
                            </ol>
                        <#else>
                            <div class="article-empty-list">Вы пока не добавляли статей. <br/>
                                Если хотите это сделать, перейдите <br/>
                                <a href="/admin/new-article">сюда</a>
                            </div>
                        </#if>
                    </@sec.authorize>
                    <@sec.authorize ifAnyGranted="ROLE_USER">
                        <p>Вы не можете добавлять статьи,
                            так как не являетесь <br/> Администратором сайта.</p>
                    </@sec.authorize>
                </div>
            </div>
            <div class="up-button" id="toTop">
                <img src="/resources/images/upButton-img.jpg" alt="Вверх">
            </div>
        </div>
    </section>

    <#include "templates/footer.ftl"/>

    <script type="text/javascript" src="../../resources/js/changePassword.js"></script>


</div>
</body>
</html>