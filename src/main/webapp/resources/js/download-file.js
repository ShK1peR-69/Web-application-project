/*!
  * @author Igor Astafyev
  * Get saving file name
  */

$("#file-input").change(function (e) {
    let a = e.target.files[0];
    $("#image-url").text(a.name).css("display", "block");
    $("#article-content").prop("disabled", true);
});

