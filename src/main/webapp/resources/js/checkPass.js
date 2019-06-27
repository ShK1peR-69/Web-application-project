/*!
  * @author Igor Astafyev
  * Check passwords for equality
  */

$('#second_pass').on("keyup", function () {
    let value_input1 = $('#first_pass').val();
    let value_input2 = $(this).val();

    if (value_input1 !== value_input2) {
        $(".error").html("Пароли не совпадают").css("display", "block");
        $('#second_pass').css('border', '0.05rem solid red');
        $('#first_pass').css('border', '0.05rem solid red');
        $('.registration-block-button').prop('disabled', true);

    } else {
        $('.registration-block-button').removeProp('disabled');
        $('#second_pass').css('border', '0.05rem solid green');
        $('#first_pass').css('border', '0.05rem solid green');
        $(".error").html("");
    }
});

$('#first_pass').on("keyup", function () {
    let value_input1 = $('#second_pass').val();
    let value_input2 = $(this).val();

    if (value_input1 !== value_input2) {
        $(".error").html("Пароли не совпадают").css("display", "block");
        $('#second_pass').css('border', '0.05rem solid red');
        $('#first_pass').css('border', '0.05rem solid red');
        $('.registration-block-button').prop('disabled', true);
    } else {
        $('.registration-block-button').removeProp('disabled');
        $('#second_pass').css('border', '0.05rem solid green');
        $('#first_pass').css('border', '0.05rem solid green');
        $(".error").html("");
    }
});