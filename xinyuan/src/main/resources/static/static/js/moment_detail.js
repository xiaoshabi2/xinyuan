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
                if (username.length > 6)
                    $("#profile span").html(username.substring(0, 6) + "...");
                else
                    $("#profile span").html(username);
                var id = data.data.id;
                var user_photo = $("#profile img");
                user_photo.attr("src", "/xinyuan/img/user/" + id.toString());
            }
            else {
                alert("数据加载异常");
            }
        },
        error: function (xhr, status, error) {
            console.log(status.toString() + ": " + error);
        }
    });

    //加载心愿详情
    var url = window.location.href;
    var id = url.split("=")[1];
    $.ajax({
            url: "/xinyuan/wish/get/" + id,
            type: "POST",
            xhrFields: {
                withCredentials: true
            },
            success: function (result, status, xhr) {
                if (result.code === 200) {
                    var photo = $(".am-article-hd img");
                    var id = result.data.user.id.toString();
                    photo.attr("src", "http://120.77.32.233/xinyuan/img/user/" + id.toString());
                    var name = $(".am-article-hd h1");
                    name.html(result.data.user.username);
                    var date = $(".am-article-hd p");
                    date.html(result.data.wish.createTime.substring(0, 16));
                    var content = $(".am-article-bd p");
                    content.html(result.data.wish.content);
                    //加载评论

                  //  alert(result.data.comment.length);
                    var list = $("#test-list");
                    for (var i = 0; i < result.data.comment.length; i++) {
                        // var list = $("#test-list");
                        var user_id = result.data.comment[i].userId.toString();
                        var c_name = result.data.comment[i].username;
                        var c_date = result.data.comment[i].createTime;
                        var comment = result.data.comment[i].content;
                        // var item = "<li class=\"am-comment\"><a href=\"\"><img src=\"/xinyuan/img/user/" + user_id + " class=am-comment-avatar\" alt=\"\"/></a>\n" +
                        //     "<div class=\"am-comment-main\"><header class=\"am-comment-hd\"><div class=\"am-comment-meta\">\n" +
                        //     "<a href=\"#link-to-user\" class=\"am-comment-author\">" + c_name + "</a>评论于<time datetime=\"\">" + c_date + "</time>\n" +
                        //     "</div></header><div class=\"am-comment-bd\">" + comment + "</div></div></li>";
                        // if (i % 2)
                        //     item.attr("class", "am-comment-flip");
                        list.append("<li class=\"am-comment\"><a href=\"\"><img src=\"/xinyuan/img/user/" + user_id + " class=am-comment-avatar\" alt=\"\"/></a>\n" +
                            "<div class=\"am-comment-main\"><header class=\"am-comment-hd\"><div class=\"am-comment-meta\">\n" +
                            "<a href=\"#link-to-user\" class=\"am-comment-author\">" + c_name + "</a>评论于<time datetime=\"\">" + c_date + "</time>\n" +
                            "</div></header><div class=\"am-comment-bd\">" + comment + "</div></div></li>");
                    }
                }
                else {
                    alert("数据加载异常");
                }
            },
            error: function (xhr, status, error) {
                console.log(status.toString() + ": " + error);
            }
        }
    )
});

