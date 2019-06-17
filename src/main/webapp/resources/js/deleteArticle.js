/*!
  * @author Igor Astafyev
  * Deleting Article
  */

$('.delete-article-true').on("click", function () {
    event.preventDefault();
    var article_id = $('#delete-article-id').text().toString();
    $.ajax({
        type: "POST",
        url: "/admin/article/delete/" + article_id,
        success: function (data) {
            if (data === 'ok') {
                window.location.replace("/admin/main");
                alert("Статья успешно удалена");
            }
        },
        error: function () {
            window.location.replace("/admin/article/" + article_id);
        }
    });
});