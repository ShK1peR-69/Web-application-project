/*!
  * @author Igor Astafyev
  * Validate information for new Article
  */

$('#article-content').on("keyup", function () {
    event.preventDefault();
    var imgUrl = ($("#article-content").val()).toString();
    var pat_img_url = /^(http|https):\/\/([/a-zA-Z0-9_.%\-=]+).(jpg|png|jpeg|gif)$/i;
    var pat_tube = /^(http|https):\/\/www\.youtube\.com\/watch\?v=([a-zA-Z0-9_\-]{11})$/i;
    var pat_vk = /^(http|https):\/\/vk\.com\/([a-zA-Z0-9_-]+)\?z=photo([&%a-zA-Z0-9_\-]+)$/i;
    var pat_inst = /^(http|https):\/\/www\.instagram\.com\/[/a-zA-Z0-9%&_\-]+$/i;
    if (pat_img_url.test(imgUrl) ||
        pat_tube.test(imgUrl) || pat_inst.test(imgUrl) || pat_vk.test(imgUrl)) {
        $("#article-content").css("border", "0.05rem solid #2b57ff");
        $("#error-url").css("display", "none");
        $("#saveArticle").prop("disabled", false);
        $("#file-input").prop("disabled", true);
        var vkPat = /^(http|https):\/\/vk\.com[\S]+$/i;
        var instagramPat = /^(http|https):\/\/www\.instagram\.com[\S]+$/i;
        var youtubePat = /^(http|https):\/\/www\.youtube\.com\/watch[\S]+$/i;
        if (vkPat.test(imgUrl)) {
            $("#article-content").css("border", "0.05rem solid #2b57ff");
            $("#error-url").css("display", "none");
            $("#saveArticle").prop("disabled", false);
            $.ajax({
                type: "POST",
                url: "/api/vk",
                data: {"photo": imgUrl},
                success: function (data) {
                    var info = JSON.parse(data);
                    $("#article-content").css("border", "0.05rem solid #2b57ff");
                    $("#error-url").css("display", "block")
                        .css("color", "black")
                        .css("font-size", "0.87rem")
                        .css("font-family", "Arial")
                        .html("<img alt='img' src='" + info.image_url + "' " +
                            "style='width:260px;height:190px;margin-top:5px;'><br><br>" +
                            "&nbsp;<img alt='likes' src='/resources/images/like.png' " +
                            "style='width:20px;height:18px;'>&nbsp;" +
                            (info.likes).toLocaleString('ru') +
                            "<img alt='reposts' src='/resources/images/repost.png' " +
                            "style='width:20px;height:17px;margin-left:15px;'>&nbsp;" +
                            (info.reposts).toLocaleString('ru'));
                },
                error: function () {
                    alert("Не удалось загрузить файл. \n" +
                        " Поовторите попытку!");
                }
            });
        }
        if (instagramPat.test(imgUrl)) {
            $("#article-content").css("border", "0.05rem solid #2b57ff");
            $("#error-url").css("display", "block");
            $("#saveArticle").prop("disabled", false);
            $.ajax({
                type: "POST",
                url: "/api/instagram",
                data: {"media": imgUrl},
                success: function (data) {
                    var info = JSON.parse(data);
                    $("#article-content").css("border", "0.05rem solid #2b57ff");
                    $("#error-url").css("display", "block")
                        .css("color", "black")
                        .css("font-size", "0.87rem")
                        .css("font-family", "Arial")
                        .html("<img alt='img' src='" + info.media_url + "' " +
                            "style='width:260px;height:190px;margin-top:5px;'><br><br>" +
                            "&nbsp;<img alt='likes' src='/resources/images/like.png' " +
                            "style='width:20px;height:18px;'>&nbsp;" +
                            (info.likes).toLocaleString('ru'));
                    if (info.place != null) {
                        $("#error-url").append("<img alt='location' " +
                            "src='/resources/images/location.png' " +
                            "style='width:17px;height:21px;margin-left:20px;'>&nbsp;" + info.place);
                    }
                    $("#error-url").append("<img alt='user' " +
                        "src='/resources/images/inst_user.png' " +
                        "style='width:17px;height:16px;margin-left:20px;'>&nbsp;" +
                        "<a href='" + info.author_url + "' target='_blank'>" +
                        info.author_name + "</a>");
                },
                error: function () {
                    alert("Не удалось загрузить файл. \n" +
                        "Поовторите попытку!");
                }
            });
        }
        if (youtubePat.test(imgUrl)) {
            $("#article-content").css("border", "0.05rem solid #2b57ff");
            $("#error-url").css("display", "none");
            $("#saveArticle").prop("disabled", false);
            $.ajax({
                type: "POST",
                url: "/api/youtube",
                data: {"video": imgUrl},
                success: function (data) {
                    var info = JSON.parse(data);
                    $("#article-content").css("border", "0.05rem solid #2b57ff");
                    $("#error-url").css("display", "block")
                        .css("color", "black")
                        .css("font-size", "0.85rem")
                        .css("font-family", "Arial")
                        .html("<iframe width='300px' height='210px' src='" +
                            info.video_url + "'></iframe><br><br>" +
                            "<img alt='views' src='/resources/images/view.png' " +
                            "style='width:30px;height:17px;'>&nbsp;" +
                            (info.views).toLocaleString('ru') +
                            "&nbsp;<img alt='likes' src='/resources/images/y_like.png' " +
                            "style='width:20px;height:19px;margin-left:10px;'>&nbsp;" +
                            (info.likes).toLocaleString('ru') +
                            "<img alt='dislikes' src='/resources/images/y_dislike.png' " +
                            "style='width:20px;height:19px;margin-left:15px;'>&nbsp;" +
                            (info.dislikes).toLocaleString('ru'));
                },
                error: function () {
                    alert("Не удалось загрузить файл. \n" +
                        "Поовторите попытку!");
                }
            });
        }

    } else {
        if (imgUrl === '') {
            $("#article-content").css("border", "0.5pt solid #2b57ff");
            $("#saveArticle").prop("disabled", false);
            $("#error-url").css("display", "none").text('');
            $("#file-input").prop("disabled", false);
            $("#image-url").html('').prop("disabled", false);
        } else {
            $("#article-content").css("border", "0.5pt solid red");
            $("#saveArticle").prop("disabled", true);
            $("#error-url").css("display", "block").text("Некорректный URL");
        }
    }
});

$('#saveArticle').on("click", function () {
        var title = $("#new-article-title");
        var text = $("#new-article-text");
        if ((/[^\s]/gim.test(title.val())) && (title.val().length >= 10)) {
            title.css("border", "0.05rem solid #2b57ff");
            $("#title-input-error").text('').css("display", "none");
            event.stopPropagation();
            if ((/[^\s]/gim.test(text.val())) && (text.val().length >= 50)) {
                text.css("border", "0.05rem solid #2b57ff");
                $("#text-input-error").text('').css("display", "none");
                event.stopPropagation();
            } else {
                text.css("border", "0.5pt solid red");
                $("#text-input-error").text("Поле должно содержать минимум 50 символов")
                    .css("display", "block");
                event.preventDefault();
            }
        } else {
            title.css("border", "0.5pt solid red");
            $("#title-input-error").text("Поле должно содержать минимум 10 символов")
                .css("display", "block");
            event.preventDefault();
        }
    }
);