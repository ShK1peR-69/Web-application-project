/*!
  * @author Igor Astafyev
  * Check e-mail with DB users
  */

let email = $('#mail_input');
let btn = $('.registration-block-button');
let error_block = $(".mail-error");

email.change(function () {
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: "/check-mail",
        data: {"email": email.val()},
        success: function (data) {
            if (data === 'ok') {
                btn.removeProp('disabled');
                email.css('border', '0.05rem solid green');
                error_block.html("");
            }
            if (data === 'error') {
                error_block.html("E-mail занят другим пользователем").css("display", "block");
                email.css('border', '0.05rem solid red');
                btn.prop('disabled', true);
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Повторите попытку позже");
        }
    });
});