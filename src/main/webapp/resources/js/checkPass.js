/*!
  * @author Igor Astafyev
  * Check password`s equality
  */

let first_pass = $('#first_pass');
let second_pass = $('#second_pass');
let btn = $('.registration-block-button');
let error_div = $(".error");

second_pass.on("keyup", function () {
    let value_input1 = first_pass.val();
    let value_input2 = $(this).val();

    if (value_input1 !== value_input2) {
        error_div.html("Пароли не совпадают").css("display", "block");
        second_pass.css('border', '0.05rem solid red');
        first_pass.css('border', '0.05rem solid red');
        btn.prop('disabled', true);

    } else {
        btn.removeProp('disabled');
        second_pass.css('border', '0.05rem solid green');
        first_pass.css('border', '0.05rem solid green');
        error_div.html("");
    }
});

first_pass.on("keyup", function () {
    let value_input1 = second_pass.val();
    let value_input2 = $(this).val();

    if (value_input1 !== value_input2) {
        error_div.html("Пароли не совпадают").css("display", "block");
        second_pass.css('border', '0.05rem solid red');
        first_pass.css('border', '0.05rem solid red');
        btn.prop('disabled', true);
    } else {
        btn.removeProp('disabled');
        second_pass.css('border', '0.05rem solid green');
        first_pass.css('border', '0.05rem solid green');
        error_div.html("");
    }
});