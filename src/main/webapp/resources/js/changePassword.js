/*!
  * @author Igor Astafyev
  * Change user password
  */

$('#changePassword').on("click", function () {
        event.preventDefault();

        var old_pass = ($("#old_pass").val()).toString();
        var new_pass = ($("#new_pass").val()).toString();
        if (/[^\s]/gim.test(old_pass)) {
            $("#old_pass").css("border", "0.05rem solid #2b57ff");
            $("#old_pass_error").text('').css("display", "none");
            event.preventDefault();
            if (/[^\s]/gim.test(new_pass)) {
                $("#new_pass").css("border", "0.05rem solid #2b57ff");
                $("#new_pass_error").text('').css("display", "none");
                event.preventDefault();
                if (old_pass !== new_pass) {
                    $("#new_pass").css("border", "0.5pt solid red");
                    $("#old_pass").css("border", "0.5pt solid red");
                    $("#new_pass_error").text('Пароли не совпадают')
                        .css("display", "block")
                        .css("color", "red");
                    $("#old_pass_error").text('Пароли не совпадают')
                        .css("display", "block")
                        .css("color", "red");
                } else {

                    $.ajax({
                        type: "POST",
                        url: "/profile/change-password",
                        data: {
                            "old_pass": old_pass,
                            "new_pass": new_pass
                        },
                        success: function (data) {
                            if (data === 'ok') {
                                $('#old_pass').val('');
                                $('#new_pass').val('');
                                alert('Пароль успешно обновлен!');
                            }
                        },
                        error: function () {
                            alert("На сервере произошла ошибка. Попробуйте повторить действие");
                        }

                    });
                }
            } else {
                $("#new_pass").css("border", "0.5pt solid red").prop("disabled", false);
                $("#new_pass_error").text("Поле не заполнено");
            }
        } else {
            $("#old_pass").css("border", "0.5pt solid red").prop("disabled", false);
            $("#old_pass_error").text("Поле не заполнено");
        }
    }
);