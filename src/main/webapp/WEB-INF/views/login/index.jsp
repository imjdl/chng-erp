<%--
  Created by IntelliJ IDEA.
  User: liuyandong
  Date: 2017/3/20
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="custom" uri="http://www.chng.com.cn" %>
<html>
<head>
    <title>华能电厂</title>
    <link rel="icon" href="<custom:createLink controller="contentManagement" action="renderFavicon" base=".."></custom:createLink>">
    <custom:link type="text/css" rel="stylesheet" dir="libraries/jquery-easyui-1.5.1/themes/bootstrap" file="easyui.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="libraries/jquery-easyui-1.5.1/themes" file="icon.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="stylesheets/common" file="clear.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="stylesheets/common" file="common.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="stylesheets/login" file="login.css" base=".."></custom:link>
    <custom:script type="text/javascript" dir="libraries/jquery-easyui-1.5.1" file="jquery.min.js" base=".."></custom:script>
    <custom:script type="text/javascript" dir="libraries/jquery-easyui-1.5.1" file="jquery.easyui.min.js" base=".."></custom:script>
    <custom:script type="text/javascript" dir="libraries/jquery-easyui-1.5.1/locale" file="easyui-lang-zh_CN.js" base=".."></custom:script>
    <script type="text/javascript">
        $(function () {
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
                if (data.successful) {
                    window.location.href = "../main/toMainPage";
                } else {
                    $.messager.alert("提示", data["error"], "error");
                }
            }, "json");
        }
        function handleOnKeyDown(event) {
            var keyCode = event.keyCode;
            if (keyCode == 13) {
                doLogin();
            }
        }
    </script>
</head>
<body>
<section class="login">
    <div class="login-wrap">
        <div class="login-w-logo1">
            <img src="<custom:createLink controller="contentManagement" action="renderLoginLogo" base=".."></custom:createLink>">
        </div>

        <div class="login-title-txt rel"><span class="abs">员工登录</span></div>

        <div class="login-btn">
            <div class="fl login-l">
                <form action="../login/doLogin" method="POST" id="login-form">
                    <p class="rel">
                        <span class="inline-block">账 号：</span>
                        <input type="text" id="loginName" placeholder="请输入用户账号" value="${loginName}" class="easyui-validatebox" data-options="required:true" onkeydown="handleOnKeyDown(event);">
                    </p>

                    <p class="rel">
                        <span class="inline-block">密 码：</span>
                        <input type="password" id="password" placeholder="请输入用户密码" class="easyui-validatebox" data-options="required:true" onkeydown="handleOnKeyDown(event)">
                    </p>

                    <p class="login-button-l">
                        <span class="inline-block"></span>
                        <input type="button" value="登 录" onclick="doLogin();">
                    </p>
                </form>
            </div>
        </div>
    </div>

    <div class="copyright-txt abs">
        <p><custom:renderCopyright></custom:renderCopyright></p>
    </div>
</section>
</body>
</html>
