//手机号码校验
$("#phone-field").blur(function () {
    var phone_number = $(this).val();
    // 正则匹配手机号码,必须以1开头，长度11位
    var regex = /^1\d{10}$/;
    if (!regex.test(phone_number)) {
        console.log("请输入正确的手机号码");
        $(this).val(null);
        // $(this).focus();
    }
});

// 验证码校验和发送
$("#send-msg").click(function () {
    var xhr = new XMLHttpRequest();
    var number = $("#phone-field").val();
    var code = "1";
    xhr.open("POST", "http://120.77.32.233/xinyuan/sms/send/" + number.toString() + "/" + code);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = eval("(" + xhr.responseText + ")");
            if (response.code === 200) {
                $("#send-msg").html("已发送");
                $("#send-msg").addClass("disabled");
            }
            if (response.code === 202) {
                alert("该手机号码已被注册！");
                console.log(response.msg);
            }
        }
    };
    xhr.send();
});

//密码一致性校验
$("#psw-confirm").blur(function () {
    var psw_1 = $("#psw-field");
    if (psw_1.val() !== $(this).val()) {
        console.log("密码不一致，请重新输入");
        $(this).val(null);
        // $(this).focus();
    }
});

//用户名长度校验
$("#user-field").blur(function () {

});

//登录数据校验

function login_validate() {
    var username = $("#user-field");
    var password = $("#psw-field");
    if (username.val === "" || password.val() === "") {
        alert("请填写用户名和密码");
        return false;
    }
    else
        return true;
}