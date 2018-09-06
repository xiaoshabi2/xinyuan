$(document).ready(function () {
    var click_time1 = 0, click_time2 = 0, click_time3 = 0;
    //用户名截取前6个字符
    var username = $("#profile span");
    username.html(username.html().substring(0, 6));
    //左侧用户菜单栏下拉框
    $("#list ul li").hide();
    $("#account").on("click", function () {
        if (click_time1 % 2)
            $("#account span:last").attr("class", "glyphicon glyphicon-chevron-up up-down");
        else
            $("#account span:last").attr("class", "glyphicon glyphicon-chevron-down up-down");
        $("#account-list li").slideToggle(500);
        click_time1++;
    });
    $("#setting").on("click", function () {
        if (click_time2 % 2)
            $("#setting span:last").attr("class", "glyphicon glyphicon-chevron-up up-down");
        else
            $("#setting span:last").attr("class", "glyphicon glyphicon-chevron-down up-down");
        $("#setting-list li").slideToggle(500);
        click_time2++;
    });
    $("#moment").on("click", function () {
        if (click_time3 % 2)
            $("#moment span:last").attr("class", "glyphicon glyphicon-chevron-up up-down");
        else
            $("#moment span:last").attr("class", "glyphicon glyphicon-chevron-down up-down");
        $("#moment-list li").slideToggle(500);
        click_time3++;
    });

});

