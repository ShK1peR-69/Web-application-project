<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<meta charset="UTF-8">

<header>
    <div class="main-header">
        <div class="header-image">
            <a href="/">
                <img src="/resources/images/football.png" alt="logo">
            </a>
        </div>
        <a href="/" class="logo">
            <img alt="Главная" src="/resources/images/logo.png">
        </a>
    </div>

    <div class="login-menu">
        <div class="login-buttons">
            <@sec.authorize ifAnyGranted="ROLE_ANONYMOUS">
                <div class="login-buttons-enter">
                    <a href="#" data-toggle="modal" data-target="#myModal">Вход</a>
                    <a href="/registration">Регистрация</a>
                </div>
                <#if login?? && login?has_content>
                    <div id="invalid_login">Неправильный логин или пароль</div>
                </#if>
            </@sec.authorize>
            <@sec.authorize access="isAuthenticated()">
                <#if user?? && user?has_content>
                    <div class="profile-header">Привет,&nbsp;<a href="/profile"> ${user.getName()} </a>&nbsp;!</div>
                </#if>
            </@sec.authorize>
        </div>
    </div>
</header>


<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Авторизация</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/j_spring_security_check" method="post">
                <div class="modal-body">
                    <label class="login-form">
                        <input placeholder="E-mail" type="email" name="j_username"
                               maxlength="50" required oncontextmenu="return false"/>
                    </label>
                    <label class="login-form">
                        <input placeholder="Пароль" type="password" name="j_password"
                               maxlength="30" required oncontextmenu="return false"/>
                    </label>
                </div>
                <label class="remember-me">
                    <input name="j_spring_security_remember_me" type="checkbox"/>
                    Запомнить
                </label>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </div>
            </form>
        </div>
    </div>
</div>