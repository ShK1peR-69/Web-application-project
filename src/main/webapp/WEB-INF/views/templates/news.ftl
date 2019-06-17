<div class="main-information-block">
    <#if articles?? && articles?has_content>
        <#list articles as article>
            <div class="news-article">
                <#if article.getImage()?? && article.getImage()?has_content>
                    <div class="news-article-img">
                        <#if article.getSource() != 'YouTube'>
                            <img src="${article.getImage()}" alt="Фото">
                        <#else>
                            <iframe width="176" height="117" src="${article.getImage()}"
                                    frameborder="0" allowfullscreen>
                            </iframe>
                        </#if>
                    </div>
                </#if>
                <div class="news-article-text">
                    <a href="/article/${article.getId()}"><p>${article.getTitle()}</p></a>
                    <div class="article-txt">${article.getText()}</div>
                </div>
                <div class="article-author">Автор: ${article.getAuthor().getName()}
                    ${article.getAuthor().getSecondName()}</div>
            </div>
        </#list>
    <#else>
        <div class="none-articles"> Публикаций не найдено.<br/>Попробуйте зайти позже</div>
    </#if>
</div>