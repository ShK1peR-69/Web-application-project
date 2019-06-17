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
                        <button type="button" class="list-group-item list-group-item-action">
                            Все статьи
                        </button>
                    </a>
                    <a href="/admin/new-article">
                        <button type="button" class="list-group-item list-group-item-action active">
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

            <div class="adding-article-block">
                <form action="/admin/add-new-article" method="post" enctype="multipart/form-data">
                    <label class="radio-block">
                        <span><b>*</b> Выберите вид спорта: </span>
                        <div class="choose-sport">
                            <label><input name="sport" type="radio" value="Футбол" checked>Футбол</label>
                            <label><input name="sport" type="radio" value="Хоккей">Хоккей</label>
                            <label><input name="sport" type="radio" value="Баскетбол">Баскетбол</label>
                            <label><input name="sport" type="radio" value="Волейбол">Волейбол</label>
                            <label><input name="sport" type="radio" value="Теннис">Теннис</label>
                        </div>
                    </label>
                    <div class="adding-article-label-title">
                        <span><b>*</b> Заголовок статьи (10 - 300 символов): </span>
                        <div class="new-article-error_msg" id="title-input-error"></div>
                        <label for="new-article-title"></label>
                        <textarea name="title" maxlength="300" id="new-article-title" required></textarea>
                    </div>
                    <div class="adding-article-label-text">
                        <span><b>*</b> Содержание (50 - 15000 символов): </span>
                        <div class="new-article-error_msg" id="text-input-error"></div>
                        <label for="new-article-text"></label>
                        <textarea name="text" maxlength="15000" minlength="50" id="new-article-text"></textarea>
                    </div>
                    <div class="adding-article-label">
                        <span>Фото (или ссылка на видео с YouTube) **: </span>
                        <div class="download-block">
                            <div class="download-image">загрузить файл</div>
                            <input class="download" type="file" name="photo" id="file-input" value="" default="123"
                                   multiple accept="image/jpg, image/jpeg, image/png, image/gif">
                        </div>
                        <div class="image-url" id="image-url"></div>
                        <mark>или</mark>
                        <input class="adding-article-input" type="text"
                               name="content" id="article-content" placeholder="Вставить ссылку.."/>
                        <div class="error_msg" id="error-url" disabled></div>
                    </div>
                    <p>* - обязательно для заполнения</p>
                    <p>** - добавление видео только с YouTube</p>
                    <button class="profile-block-button" id="saveArticle">Сохранить</button>
                </form>
            </div>
        </div>

    </section>

    <#include "templates/footer.ftl"/>

    <div class="up-button" id="toTop">
        <img src="/resources/images/upButton-img.jpg" alt="Вверх">
    </div>

    <script type="text/javascript" src="../../resources/js/checkInputsForNewArticle.js"></script>
    <script type="text/javascript" src="../../resources/js/download-file.js"></script>

</div>
</body>
</html>