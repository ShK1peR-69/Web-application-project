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
                    <a href="/admin/main">
                        <button type="button" class="list-group-item list-group-item-action">
                            Все статьи
                        </button>
                    </a>
                    <a href="/admin/new-article">
                        <button type="button" class="list-group-item list-group-item-action admin-button">
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

            <#if article?? && article?has_content>

                <div class="article-main-block">
                    <div class="article">
                        <div class="article-page-category">Категория: ${article.getSport()}</div>
                        <div class="article-header"><span>«</span>${article.getTitle()}<span>»</span></div>

                        <#if article.getSource() != 'YouTube'>
                            <div class="article-img">
                                <img src="${article.getImage()}" alt="">
                            </div>
                            <div class="article-img-source-info">
                                Источник фото: ${article.getSource()}
                            </div>
                        <#else>
                            <div class="article-img">
                                <iframe width="500" height="220" src="${article.getImage()}"
                                        frameborder="0" allowfullscreen>
                                </iframe>
                            </div>
                            <div class="article-img-source-info">
                                Источник видео: ${article.getSource()}
                            </div>
                        </#if>

                        <div class="article-text">
                            <p>${article.getText()}</p>
                        </div>
                        <div class="author-fio-article">Автор статьи: ${article.getAuthor().getName()}
                            ${article.getAuthor().getSecondName()}
                        </div>
                    </div>
                    <div>
                        <#if comments?? && comments?has_content>
                        <div class="comments-header">Комментарии</div>
                        <#list comments as comment>
                            <div class="article-comments">
                                <div class="article-comment-author">${comment.getAuthor().getName()}
                                    ${comment.getAuthor().getSecondName()},
                                    ${comment.getDate()}</div>
                                <div class="article-comment-text">${comment.getText()}</div>
                                <a href="/admin/comment/delete/${article.getId()}/${comment.getId()}">
                                    <button class="delete-comment">Удалить</button>
                                </a>
                            </div>
                        </#list>
                        <#else>
                    </div>
                    <div id="admin-none-comments">Комментариев к данной статье нет.</div>
                    </#if>
                    <button type="button" class="delete-article" data-toggle="modal"
                            data-target="#deleteArticle">
                        Удалить статью
                    </button>
                </div>

            <#else>
                <div class="none-articles"> Статья не надйена. <br> Приносим свои извинения</div>
            </#if>
            <div class="up-button" id="toTop">
                <img src="/resources/images/upButton-img.jpg" alt="Вверх">
            </div>
        </div>
    </section>

    <#include "templates/footer.ftl"/>


    <!-- Modal -->
    <div class="modal fade" id="deleteArticle" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content delete-article-modal">
                <div class="modal-header">
                    <h5 class="modal-title delete-modal-header" id="exampleModalLongTitle">Пожалуйста, подвердите
                        удаление</h5>
                    <button type="button" class="close" data-dismiss="modal">
                    </button>
                </div>
                <div class="modal-footer delete-article-footer">
                    <button type="button" class="delete-article-true" data-dismiss="modal">Удалить</button>
                    <span id="delete-article-id">${article.getId()}</span>
                    <button type="button" class="delete-article-false" data-dismiss="modal">Отмена</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="../../resources/js/deleteArticle.js"></script>

</div>
</body>
</html>