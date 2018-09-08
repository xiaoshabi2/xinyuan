$(document).ready(function () {
    var click_time1 = 0, click_time2 = 0, click_time3 = 0;
    //用户名截取前6个字符
    // var username = $("#profile span");
    // username.html(username.html().substring(0, 6));
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
                if (username.length > 6)
                    $("#profile span").html(username.substring(0, 6) + "...");
                else
                    $("#profile span").html(username);
                var id = data.data.id;
                var user_photo = $("#profile img");
                user_photo.attr("src", "http://120.77.32.233/xinyuan/img/user/" + id.toString());
            }
            else {
                alert("数据加载异常");
            }
        },
        error: function (xhr, status, error) {
            console.log(status.toString() + ": " + error);
        }
    });


    //加载今日榜单
    var date = getFormatDate(); //  格式化今日日期
    $.ajax({
        url: "/xinyuan/wish/get/day/1/",
        type: "POST",
        xhrFields: {
            withCredentials: true
        },
        success: function (result, status, xhr) {
            if (result.code === 200) {
                //加载成功
                var today = $("#today-list ol");
                var list = result.data;   //榜单数组
                for (var i = 0; i < list.length; ++i) {
                    var id = list[i].id.toString();
                    var content = list[i].content;
                    var link = $("<a></a>");
                    if (content.length > 10)
                        link.html(content.substring(0, 10) + "...");
                    else
                        link.html(content);
                    if (i === 0)
                        link.css("color", "red");
                    else if (i === 1)
                        link.css("color", "orange");
                    else
                        link.css("color", "greenyellow");
                    link.attr("id", id);
                    link.attr("href", "/xinyuan/wish/"+id);
                    var item = $("<li></li>");
                    item.append(link);
                    today.append(item);
                }
            }
            else {
                //加载异常
                alert("数据加载异常");
            }
        },
        error: function (xhr, status, error) {
            console.log(status.toString() + ": " + error);
        }

    });
    // var xhr2 = new XMLHttpRequest();
    // var date = getFormatDate();
    // xhr2.open("POST", "/xinyuan/wish/get/day/1/" + date);
    // xhr2.onreadystatechange = function () {
    //     if (xhr2.readyState === 4 && xhr2.status === 200) {
    //         var response = eval("(" + xhr2.responseText + ")");
    //         // alert(response.msg);
    //     }
    // };
    // xhr2.send();

    //加载实现榜单
    $.ajax({
        url: "/xinyuan/wish/get/real/1",
        type: "POST",
        xhrFields: {
            withCredentials: true
        },
        success: function (result, status, xhr) {
            if (result.code === 200) {
                var real = $("#real-list ol");
                var list = result.data;
                for (var i = 0; i < list.length; ++i) {
                    var id = list[i].id.toString();
                    var content = list[i].content;
                    var link = $("<a></a>");
                    if (content.length > 10)
                        link.html(content.substring(0, 10) + "...");
                    else
                        link.html(content);
                    if (i === 0)
                        link.css("color", "red");
                    else if (i === 1)
                        link.css("color", "orange");
                    else
                        link.css("color", "greenyellow");
                    link.attr("id", id);
                    link.attr("href", "/xinyuan/wish?wishId="+id);
                    var item = $("<li></li>");
                    item.append(link);
                    real.append(item);
                }
            }
            else {
                alert("数据加载异常");
            }
        },
        error: function (xhr, status, error) {
            console.log(status.toString() + ": " + error);
        }
    });
    // var xhr3 = new XMLHttpRequest();
    // xhr3.open("POST", "//xinyuan/wish/get/real/1");
    // xhr3.onreadystatechange = function () {
    //     if (xhr3.readyState === 4 && xhr3.status === 200) {
    //         var response = eval("(" + xhr3.responseText + ")");
    //         // alert(response.msg);
    //     }
    // };
    // xhr3.send();

    //加载热门榜单
    $.ajax({
        url: "/xinyuan/wish/get/all/1",
        type: "POST",
        xhrFields: {
            withCredentials: true
        },
        success: function (result, status, xhr) {
            if (result.code === 200) {
                var hit = $("#major-topic ol");
                var list = result.data;
                for (var i = 0; i < list.length; ++i) {
                    var id = list[i].id.toString();
                    var content = list[i].content;
                    var item = $("<li></li>");
                    var link = $("<a></a>");
                    if (content.length > 10)
                        link.html(content.substring(0, 10) + "...");
                    else
                        link.html(content);
                    if (i === 0)
                        link.css("color", "red");
                    else if (i === 1)
                        link.css("color", "orange");
                    else
                        link.css("color", "greenyellow");
                    link.attr("id", id);
                    link.attr("href", "/xinyuan/wish?wishId="+id);
                    item.append(link);
                    hit.append(item);
                }
            }
            else {
                alert("数据加载异常");
            }
        },
        error: function (xhr, status, error) {
            console.log(status.toString() + ": " + error)
        }
    })
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

