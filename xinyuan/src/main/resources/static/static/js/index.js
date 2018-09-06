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

    //加载用户信息
    $.ajax({
        url: "/xinyuan/user/info",
        type: "POST",
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            if (data.code === 200) {
                var username = data.data.username;
                $("#profile span").html(username.toString());
                var id = data.data.id;
                var user_photo = $("#profile img");
                user_photo.attr("src", "http://120.77.32.233/xinyuan/img/user/" + id.toString());
            }
        },
        error: function (err) {
            alert(err);
        }
    });
    // var xhr1 = new XMLHttpRequest();
    // xhr1.open("POST", "http://120.77.32.233/xinyuan/user/info");
    // xhr1.onreadystatechange = function () {
    //     if (xhr1.readyState === 4 && xhr1.status === 200) {
    //         var response = eval("("+xhr1.responseText+")");
    //         if (response.code === 200){
    //             var username = response.data.username;
    //             $("#profile span").html(username.toString());
    //             var id = response.data.id;
    //             var user_photo = $("#profile img");
    //             user_photo.attr("src", "http://120.77.32.233/xinyuan/img/user/"+id.toString());
    //         }
    //     }
    // };
    // xhr1.send();

    //加载今日榜单
    var xhr2 = new XMLHttpRequest();
    var date = getFormatDate();
    xhr2.open("POST", "http://120.77.32.233/xinyuan/wish/get/day/1/" + date);
    xhr2.onreadystatechange = function () {
        if (xhr2.readyState === 4 && xhr2.status === 200) {
            var response = eval("(" + xhr2.responseText + ")");
            // alert(response.msg);
        }
    };
    xhr2.send();

    //加载实现榜单
    var xhr3 = new XMLHttpRequest();
    xhr3.open("POST", "http://120.77.32.233//xinyuan/wish/get/real/1");
    xhr3.onreadystatechange = function () {
        if (xhr3.readyState === 4 && xhr3.status === 200) {
            var response = eval("(" + xhr3.responseText + ")");
            // alert(response.msg);
        }
    };
    xhr3.send();
});

function getFormatDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month >= 1 && month <= 9)
        month = "0" + month.toString();
    if (day >= 1 && day <= 9)
        day = "0" + day.toString();
    return year.toString() + "-" + month.toString() + "-" + day.toString();
}

