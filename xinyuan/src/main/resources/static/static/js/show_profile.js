$(document).ready(function () {
    for (var c = 0; c < 10; c++) {
        setTimeout("show_photo(c)", Math.round(Math.random() * 5000));
    }

});

function show_photo(i) {
    var photo_width = $("#photo-area").width();
    var photo_height = $("#photo-area").height();
    var canvas_width = $("#canvas-area").width();
    var canvas_height = $("#canvas-area").height();
    var min_x = (canvas_width - photo_width) / 2;
    var max_x = (canvas_width - photo_width) / 2 + photo_width;
    var min_y = (canvas_height - photo_height) / 2;
    var max_y = (canvas_height - photo_height) / 2 + photo_height;

    var photo = document.createElement("img");
    photo.setAttribute("src", "static/img/qq32.png");
    photo.setAttribute("id", "photo" + i.toString());
    photo.style.width = "32px";
    photo.style.height = "32px";
    photo.style.position = "absolute";
    photo.style.top = Math.round(Math.random() * (max_y - min_y)).toString() + "px";
    photo.style.left = Math.round(Math.random() * (max_x - min_x)).toString() + "px";
    photo.style.margin = "50px";
    photo.style.border = "2px solid black";
    photo.style.borderRadius = "16px";

    // photo.style.visibility = "hidden";
    $("#photo-area").append(photo);
    $("#photo" + i.toString()).fadeToggle(2000);
    photo.style.cursor = "pointer";

}