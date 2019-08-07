const TIME_HIDE = 4000;
const TIME_SHOW = 2700;
$(document).ready(function () {
    anim();
});

function anim() {
    let block_1 = $("#animate-advert-1");
    let block_2 = $("#animate-advert-2");
    let block_3 = $("#animate-advert-3");
    block_1.animate({
        opacity: 0
    }, TIME_HIDE, function () {
        block_1.animate({
            opacity: 1
        }, TIME_SHOW);
    });

    block_2.animate({
        opacity: 0
    }, TIME_HIDE, function () {
        block_2.animate({
            opacity: 1
        }, TIME_SHOW);
    });
    block_3.animate({
        opacity: 0
    }, TIME_HIDE, function () {
        block_3.animate({
            opacity: 1
        }, TIME_SHOW);
    });
    return setTimeout(anim, 8000);
}