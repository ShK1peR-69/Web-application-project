let wid = screen.width;

$(document).ready(function () {
    $("body").css("width", wid);
    $(window).resize(function () {
        console.log("Browser: " + wid);
        console.log("Window: " + window.innerWidth);
        changeSize();
    })
});

function changeSize() {
    if (window.innerWidth > wid) {
        $("body").css("width", "100%");
    } else {
        $("body").css("width", wid);
    }
}