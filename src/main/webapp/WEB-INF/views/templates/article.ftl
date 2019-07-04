<#if article?? && article?has_content>

    <div class="article-main-block">
        <div class="article">
            <div class="article-img">
                <#if article.getSource() != 'YouTube'>
                    <img src="${article.getImage()}" alt="Фото">
                <#else>
                    <iframe width="500" height="220" src="${article.getImage()}"
                            frameborder="0" allowfullscreen>
                    </iframe>
                </#if>
            </div>
            <div class="article-img-source-info">
                ${article.getSport()} <br/>
                <#if article.getSource() != 'YouTube'>
                    Источник фото: ${article.getSource()}
                <#else>
                    Источник видео: ${article.getSource()}
                </#if>
            </div>
            <div class="article-header">
                <span>«</span>${article.getTitle()}<span>»</span>
            </div>
            <div class="article-text">
                <p>${article.getText()}</p>
            </div>
            <div class="author-fio-article">Автор статьи: ${article.getAuthor().getName()}
                ${article.getAuthor().getSecondName()}
            </div>
        </div>

        <div id="comments-block">
            <div class="comments-header">Комментарии</div>
            <@sec.authorize access="isAuthenticated()">
            <#if comments?? && comments?has_content>
                <#list comments as comment>
                    <div class="article-comments">
                        <div class="article-comment-author">${comment.getAuthor().getName()}
                            ${comment.getAuthor().getSecondName()},
                            ${comment.getDate()}</div>
                        <div class="article-comment-text">${comment.getText()}
                        </div>
                    </div>
                </#list>
            <#else>
                <div id="none-comments">Комментариев к данной статье пока нет..</div>
            </#if>
        </div>

        <div class="article-adding-comment">
            <div id="comment-error"></div>
            <label for="commentText"></label>
            <textarea id="commentText" type="text" maxlength="500" minlength="1"
                      placeholder="Комментарий.." name="comment" required>
            </textarea>
            <button id="addComment" type="button" class="comment-button">Добавить</button>
            <div id="article_id" class="article-id disabled" disabled="true">${article.getId()}</div>
        </div>
        </@sec.authorize>
        <@sec.authorize ifAnyGranted="ROLE_ANONYMOUS">
            <div class="non-auth-message">Вы не можете оставлять или просматривать комментарии,
                так как не авторизованы в системе.
            </div>
        </@sec.authorize>
    </div>
<#else>
    <div class="none-articles"> Статья не надйена. <br> Приносим свои извинения</div>
</#if>

<script type="text/javascript" src="/resources/js/comment.js"></script>
