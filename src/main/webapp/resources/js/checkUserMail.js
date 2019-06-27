/*!
  * @author Igor Astafyev
  * Check e-mail with DB users
  */

$('#mail_input').change(function () {
    event.preventDefault();
    let email = $('#mail_input').val();
    $.ajax({
        type: "POST",
        url: "/check-mail",
        data: {"email": email},
        success: function (data) {
            if (data == 'ok') {
                $('.registration-block-button').removeProp('disabled');
                $('#mail_input').css('border', '0.05rem solid green');
                $(".mail-error").html("");
            }
            if (data == 'error') {
                $(".mail-error").html("E-mail занят другим пользователем").css("display", "block");
                $('#mail_input').css('border', '0.05rem solid red');
                $('.registration-block-button').prop('disabled', true);
            }
        },
        error: function () {
            alert("На сервере произошла ошибка. Повторите попытку позже");
        }
    });
});