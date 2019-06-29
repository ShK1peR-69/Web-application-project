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
                    <a href="/admin/main">
                        <button type="button" class="list-group-item list-group-item-action active">
                            Все статьи
                        </button>
                    </a>
                    <a href="/admin/new-article">
                        <button type="button" class="list-group-item list-group-item-action">
                            <span class="add-article">Добавить</span>
                        </button>
                    </a>
                    <a href="/">
                        <button type="button" class="list-group-item list-group-item-action">
                            Главная
                        </button>
                    </a>
                </div>
                <#include "templates/advertising.ftl"/>
            </div>

            <div class="main-information-block">
                <#if articles?? && articles?has_content>
                    <#list articles as art>
                        <div class="news-article">
                            <div class="article-category">Категория: ${art.getSport()}</div>
                            <div class="article-category">Автор: ${art.getAuthor().getName()}
                                ${art.getAuthor().getSecondName()}</div>
                            <#if art.getSource() != 'YouTube'>
                                <div class="news-article-img">
                                    <img alt="Изображение"
                                         src="${art.getImage()}">
                                </div>
                                <div class="article-category-source">Источник фото: ${art.getSource()}</div>
                            <#else>
                                <div class="news-article-img">
                                    <iframe width="170" height="105" src="${art.getImage()}"
                                            frameborder="0" allowfullscreen>
                                    </iframe>
                                </div>
                                <div class="article-category-source">Источник видео: ${art.getSource()}</div>
                            </#if>
                            <div class="news-article-text">
                                <a href="/admin/article/${art.getId()}"><p>${art.getTitle()}</p></a>
                                <div class="article-txt">${art.getText()}
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <div class="admin-clear-block">Статей не найдено</div>
                </#if>
            </div>
            <div class="up-button" id="toTop">
                <img src="/resources/images/upButton-img.jpg" alt="Вверх">
            </div>
        </div>
    </section>

    <#include "templates/footer.ftl"/>

</div>
</body>
</html>