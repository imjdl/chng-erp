<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="custom" uri="http://www.chng.com.cn" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0LeweY0SDrw1uwpSA1SmBRmn"></script>
    <custom:script type="text/javascript" dir="libraries/jquery" file="jquery-3.2.1.min.js" base=".."></custom:script>
    <custom:link type="text/css" rel="stylesheet" dir="libraries/jquery-easyui-1.5.1/themes/bootstrap" file="easyui.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="libraries/jquery-easyui-1.5.1/themes" file="icon.css" base=".."></custom:link>
    <custom:script type="text/javascript" dir="libraries/jquery-easyui-1.5.1" file="jquery.easyui.min.js" base=".."></custom:script>
    <custom:script type="text/javascript" dir="libraries/jquery-easyui-1.5.1/locale" file="easyui-lang-zh_CN.js" base=".."></custom:script>
    <style type="text/css">
        body {padding: 0px;margin: 0px;}
        #container {width: 100%;height: 100%;}
        .login-panel {width: 300px;height: 200px;background: rgba(0, 0, 0, 0.5);position: fixed;top: 30%;left: 40%;border-radius: 4px;text-align: center;}
        .item {height: 40px;width: 250px;margin-top: 20px;border-radius: 4px;border: 1px solid #D3D8DB;padding: 0px 10px;font-size: 14px;}
        .login-button {height: 40px;width: 250px;cursor: pointer;border-radius: 4px;background-color: #00AAEE;color: #FFFFFF;border: none;font-size: 16px;margin-top: 20px;}
    </style>
    <script type="text/javascript">
        $(function () {
            var map = new BMap.Map("container");
            var point = new BMap.Point(120.38442818, 36.10521490);
            map.centerAndZoom(point, 100);
            $("#loginName").focus();
        });

        function doLogin() {
            var loginFormNode = $("#login-form");
            if (!loginFormNode.form("validate")) {
                return;
            }
            var action = loginFormNode.attr("action");
            var loginName = $("#loginName").val();
            var password = $("#password").val();
            $.post(action, {loginName: loginName, password: password}, function(data) {
                if (data["successful"]) {
                    window.location.href = "../main/toMainPage";
                } else {
                    $.messager.alert("提示", data["error"], "error");
                }
            }, "json");
        }

        function handleOnKeyDown(event) {
            var keyCode = event["keyCode"];
            if (keyCode == 13) {
                doLogin();
            }
        }
    </script>
</head>
<body>
<div id="container"></div>

<div class="login-panel">
    <form action="../login/doLogin" method="POST" id="login-form">
        <div>
            <input type="text" placeholder="用户编号/手机号/邮箱" id="loginName" class="item easyui-validatebox" data-options="required:true" missingMessage="账号不能为空" invalidMessage="账号不能为空" onkeydown="handleOnKeyDown(event);">
        </div>
        <div>
            <input type="password" placeholder="请输入密码" id="password" class="item easyui-validatebox" data-options="required:true" missingMessage="密码不能为空" invalidMessage="密码不能为空" onkeydown="handleOnKeyDown(event);">
        </div>
        <div>
            <button type="button" class="login-button" onclick="doLogin();">登录</button>
        </div>
    </form>
</div>

</body>
</html>