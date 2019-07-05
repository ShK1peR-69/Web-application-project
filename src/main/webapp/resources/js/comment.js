/*!
  * @author Igor Astafyev
  * Adding new comment for Article
  */

$(function () {
    let comment = $("#commentText");
    let error_block = $("#comment-error");
    let btn = $('#addComment');
    let article_id = $("#article_id").text();
    btn.on("click", function () {
        event.preventDefault();
        if (/[^\s]/gim.test((comment.val()).toString())) {
            comment.css("border", "0.8pt solid rgba(180, 180, 180, 0.75)");
            error_block.text("");
            event.preventDefault();
            let parts = ((comment.val()).toString()).split("\n");
            let edit_comment = "";
            for (let i = 0; i < parts.length; i++) {
                edit_comment = edit_comment + parts[i] + "<br/>";
            }
            $.ajax({
                type: "POST",
                url: "/comment/add/" + article_id,
                data: {"comment": edit_comment},
                success: function (data) {
                    if (data !== null && data !== "") {
                        $('#none-comments').css("display", "none");
                        $('#comments-block').append('<div class="article-comments">\n' +
                            '<div class="article-comment-author">' + "Вы, " + data + '</div>\n' +
                            '<div class="article-comment-text">' + edit_comment + '</div></div>');
                        comment.val('');
                    }
                },
                error: function () {
                    alert("На сервере произошла ошибка. Попробуйте повторить попытку позже.");
                    comment.val('');
                }
            });
        } else {
            comment.css("border", "0.7pt solid red");
            error_block.text("Заполните поле для комментария").css("margin-left", "45px");
        }
    })
});