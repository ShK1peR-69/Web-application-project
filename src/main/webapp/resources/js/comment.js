/*!
  * @author Igor Astafyev
  * Adding new comment for Article
  */

$(function () {
    $('#addComment').on("click", function () {
        event.preventDefault();
        let comment = ($("#commentText").val()).toString();
        if (/[^\s]/gim.test(comment)) {
            $("#commentText").css("border", "0.8pt solid rgba(180, 180, 180, 0.75)");
            $("#comment-error").text("");
            event.preventDefault();
            let article_id = $("#article_id").text();
            let parts = comment.split("\n");
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
                        $("#commentText").val('');
                    }
                },
                error: function () {
                    alert("На сервере произошла ошибка. Попробуйте повторить попытку позже.");
                    $("#commentText").val('');
                }
            });
        } else {
            $("#commentText").css("border", "0.7pt solid red");
            $("#comment-error").text("Заполните поле для комментария");
        }
    })
});