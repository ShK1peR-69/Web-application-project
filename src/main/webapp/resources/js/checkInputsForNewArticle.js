/*!
  * @author Igor Astafyev
  * Validating information for new Article
  */

let content = $('#article-content');
let save_btn = $("#saveArticle");
let error_div = $("#error-url");
let imgUrl = (content.val()).toString();

let pat_img_url = /^(http|https):\/\/([/a-zA-Z0-9_.%\-=]+).(jpg|png|jpeg|gif)$/i;
let pat_tube = /^(http|https):\/\/www\.youtube\.com\/watch\?v=([a-zA-Z0-9_\-]{11})$/i;
let pat_vk = /^(http|https):\/\/vk\.com\/([a-zA-Z0-9_-]+)\?z=photo([&%a-zA-Z0-9_\-]+)$/i;
let pat_inst = /^(http|https):\/\/www\.instagram\.com\/[/a-zA-Z0-9%&_\-]+$/i;

let vkPat = /^(http|https):\/\/vk\.com[\S]+$/i;
let instagramPat = /^(http|https):\/\/www\.instagram\.com[\S]+$/i;
let youtubePat = /^(http|https):\/\/www\.youtube\.com\/watch[\S]+$/i;

content.on("keyup", function () {
    event.preventDefault();
    if (pat_img_url.test(imgUrl) ||
        pat_tube.test(imgUrl) || pat_inst.test(imgUrl) || pat_vk.test(imgUrl)) {
        content.css("border", "0.05rem solid #2b57ff");
        error_div.css("display", "none");
        save_btn.prop("disabled", false);
        error_div.prop("disabled", true);
        if (vkPat.test(imgUrl)) {
            content.css("border", "0.05rem solid #2b57ff");
            error_div.css("display", "none");
            save_btn.prop("disabled", false);
            $.ajax({
                type: "POST",
                url: "/api/vk",
                data: {"photo": imgUrl},
                success: function (data) {
                    let info = JSON.parse(data);
                    content.css("border", "0.05rem solid #2b57ff");
                    error_div.css("display", "block")
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
            content.css("border", "0.05rem solid #2b57ff");
            error_div.css("display", "block");
            save_btn.prop("disabled", false);
            $.ajax({
                type: "POST",
                url: "/api/instagram",
                data: {"media": imgUrl},
                success: function (data) {
                    let info = JSON.parse(data);
                    content.css("border", "0.05rem solid #2b57ff");
                    error_div.css("display", "block")
                        .css("color", "black")
                        .css("font-size", "0.87rem")
                        .css("font-family", "Arial")
                        .html("<img alt='img' src='" + info.media_url + "' " +
                            "style='width:260px;height:190px;margin-top:5px;'><br><br>" +
                            "&nbsp;<img alt='likes' src='/resources/images/like.png' " +
                            "style='width:20px;height:18px;'>&nbsp;" +
                            (info.likes).toLocaleString('ru'));
                    if (info.place != null) {
                        error_div.append("<img alt='location' " +
                            "src='/resources/images/location.png' " +
                            "style='width:17px;height:21px;margin-left:20px;'>&nbsp;" + info.place);
                    }
                    error_div.append("<img alt='user' " +
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
            content.css("border", "0.05rem solid #2b57ff");
            error_div.css("display", "none");
            save_btn.prop("disabled", false);
            $.ajax({
                type: "POST",
                url: "/api/youtube",
                data: {"video": imgUrl},
                success: function (data) {
                    let info = JSON.parse(data);
                    content.css("border", "0.05rem solid #2b57ff");
                    error_div.css("display", "block")
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
            content.css("border", "0.5pt solid #2b57ff");
            save_btn.prop("disabled", false);
            error_div.css("display", "none").text('');
            $("#file-input").prop("disabled", false);
            $("#image-url").html('').prop("disabled", false);
        } else {
            content.css("border", "0.5pt solid red");
            save_btn.prop("disabled", true);
            error_div.css("display", "block").text("Некорректный URL");
        }
    }
});

save_btn.on("click", function () {
        let text_error = $("#text-input-error");
        let title_error = $("#title-input-error");
        let title = $("#new-article-title");
        let text = $("#new-article-text");
        if ((/[^\s]/gim.test(title.val())) && (title.val().length >= 10)) {
            title.css("border", "0.05rem solid #2b57ff");
            title_error.text('').css("display", "none");
            event.stopPropagation();
            if ((/[^\s]/gim.test(text.val())) && (text.val().length >= 50)) {
                text.css("border", "0.05rem solid #2b57ff");
                text_error.text('').css("display", "none");
                event.stopPropagation();
            } else {
                text.css("border", "0.5pt solid red");
                text_error.text("Поле должно содержать минимум 50 символов")
                    .css("display", "block").css("margin-top", "15px");
                event.preventDefault();
            }
        } else {
            title.css("border", "0.5pt solid red");
            title_error.text("Поле должно содержать минимум 10 символов")
                .css("display", "block").css("margin-top", "15px");
            event.preventDefault();
        }
    }
);