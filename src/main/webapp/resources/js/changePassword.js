/*!
  * @author Igor Astafyev
  * Change user password
  */

$('#changePassword').on("click", function () {
        event.preventDefault();
        let old_pass = $("#old_pass");
        let new_pass = $("#new_pass");
        let error_old = $("#old_pass_error");
        let error_new = $("#new_pass_error");
        if (/[^\s]/gim.test(old_pass)) {
            old_pass.css("border", "0.05rem solid #2b57ff");
            error_old.text('').css("display", "none");
            event.preventDefault();
            if (/[^\s]/gim.test((new_pass.val()).toString())) {
                new_pass.css("border", "0.05rem solid #2b57ff");
                error_new.text('').css("display", "none");
                event.preventDefault();
                if ((old_pass.val()).toString() !== (new_pass.val()).toString()) {
                    new_pass.css("border", "0.5pt solid red");
                    old_pass.css("border", "0.5pt solid red");
                    error_new.text('Пароли не совпадают')
                        .css("display", "block")
                        .css("color", "red");
                    error_old.text('Пароли не совпадают')
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
                                old_pass.val('');
                                new_pass.val('');
                                alert('Пароль успешно обновлен!');
                            }
                        },
                        error: function () {
                            alert("На сервере произошла ошибка. Попробуйте повторить действие");
                        }

                    });
                }
            } else {
                new_pass.css("border", "0.5pt solid red").prop("disabled", false);
                error_new.text("Поле не заполнено");
            }
        } else {
            old_pass.css("border", "0.5pt solid red").prop("disabled", false);
            error_old.text("Поле не заполнено");
        }
    }
);