<%--
  Created by IntelliJ IDEA.
  User: liuyandong
  Date: 2018-01-04
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="custom" uri="http://www.chng.com.cn" %>
<html>
<head>
    <title>主页面</title>
    <link rel="icon" href="<custom:createLink controller="contentManagement" action="renderFavicon" base=".."></custom:createLink>">
    <custom:link type="text/css" rel="stylesheet" dir="libraries/jquery-easyui-1.5.1/themes/bootstrap" file="easyui.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="stylesheets/common" file="clear.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="stylesheets/common" file="common.css" base=".."></custom:link>
    <custom:link type="text/css" rel="stylesheet" dir="stylesheets/main" file="main.css" base=".."></custom:link>
    <custom:script type="text/javascript" dir="libraries/jquery" file="jquery-3.2.1.min.js" base=".."></custom:script>
    <custom:script type="text/javascript" dir="libraries/jquery-easyui-1.5.1" file="jquery.easyui.min.js" base=".."></custom:script>
    <custom:script type="text/javascript" dir="libraries/jquery-easyui-1.5.1/locale" file="easyui-lang-zh_CN.js" base=".."></custom:script>
    <custom:script type="text/javascript" dir="javascripts/common" file="jquery-custom-valiate.js" base=".."></custom:script>
    <script type="text/javascript">
        $(function () {
            autoWindow();
            $("#mainFrame").attr("src", "../home/index");
            bindOneLevelMenuClick();
        });

        function bindOneLevelMenuClick() {
            $(".menu1").bind("click", function () {
                if ($(".menu1[mid='" + $(this).attr("mid") + "'] i").hasClass("current")) {
                    $(".menu1[mid='" + $(this).attr("mid") + "'] i").removeClass("current");
                    $(".menu2[pid='" + $(this).attr("mid") + "']").hide("slow");
                } else {
                    $(".menu1[mid!='" + $(this).attr("mid") + "'] i").removeClass("current");
                    $(this).find("i").addClass("current");
                    $(".menu2[pid!='" + $(this).attr("mid") + "']").hide("slow");
                    $("[pid='" + $(this).attr("mid") + "']").show("slow");
                }
            });
        }

        window.onresize = function () {
            autoWindow();
        }

        function autoWindow() {
            var height = $(window).height();
            var width = $(window).width();
            $("#mainArea").css({"height": (height - 70) + "px", "width": width + "px"});
            $("#menuArea").css({"height": (height - 70 - 24) + "px"});
            $("#contentArea").css({"height": (height - 70 - 24) + "px", "float": "right", "width": (width - 160) + "px"});

            $("#mainFrame").css({
                "height": (height - 70 - 24 - 20 - 20) + "px",
                "width": (width - 160 - 20 - 20) + "px"
            })
        }

        function openMenu(obj, controllerName, actionName) {
            $("[menuId='menu3']").removeClass("current");
            $(obj).addClass("current");
            $("#mainFrame").attr("src", "../" + controllerName + "/" + actionName);
        }

        function logout() {
            window.location.href = "<custom:createLink controller="login" action="logout" base=".."></custom:createLink>";
        }

        function openChangePasswordDialog() {
            $("#changePasswordWindow").dialog("open");
        }

        function changePassword() {
            if (!$("#changePasswordForm").form("validate")) {
                return;
            }
            var originalPassword = $("#originalPassword").textbox("getValue");
            var password = $("#password").textbox("getValue");
            var confirmPassword = $("#confirmPassword").textbox("getValue");
            if (originalPassword == password) {
                $.messager.alert("系统提示", "新密码与原始密码不能一致！", "warning");
                return;
            }
            var changePasswordUrl = "<custom:createLink controller="systemUser" action="changePassword" base=".."></custom:createLink>";
            $.post(changePasswordUrl, {originalPassword: originalPassword, password: password, confirmPassword: confirmPassword}, function (data) {
                if (data["successful"]) {
                    closeWindow("changePasswordWindow");
                    $.messager.alert("系统提示", data["message"], "info", function () {
                        window.location.href = "<custom:createLink controller="login" action="logout" base=".."></custom:createLink>";
                    });
                } else {
                    $.messager.alert("系统提示", data["error"], "error");
                }
            }, "json");
        }

        function closeWindow(windowId) {
            $("#" + windowId).dialog("close");
        }
    </script>
</head>
<body>

<div style="height: 100%;">
    <div class="header">
        <h2 class="logo fl">
            <a href="<custom:createLink controller="login" action="login" base=".."></custom:createLink>">
                <img src="<custom:createLink controller="contentManagement" action="renderLogo" base=".."></custom:createLink>">
            </a>
        </h2>

        <div class="user-txt fl">
            <p>欢迎您，<span id="tenant_userName">${userName}[${powerStationName}]</span></p>

            <p><a href="javascript:void(0)" onclick="logout();">[ 退 出 ]</a></p>
        </div>

        <div class="header-nav fr ovf">
            <ul>
                <li class="headnav-order" onclick="openChangePasswordDialog();">
                    <a href="javascript:void(0);" class="icon">修改密码</a>
                </li>
            </ul>
        </div>
    </div>

    <div style="height: 70px;width: 100%"></div>

    <div style="width: 100%;" id="mainArea">
        <div class="box-nav fl" id="menuArea" style="overflow: auto;">
            <ul>
                <li>
                    <h4 class="rel menu1" onclick="openMenu(this, 'home', 'index');">首页<i class="icon abs current"></i></h4>
                </li>
                <li>
                    <c:forEach items="${menus}" var="menu">
                        <h4 class="rel menu1" onclick="" mid="${menu.key.id}">${menu.key.resourceName}<i class="icon abs"></i></h4>
                        <ul class="box-nav-child menu2 hidden" pid="${menu.key.id}">
                            <c:forEach items="${menu.value}" var="twoLevelMenu">
                                <li class="" menuId="menu3" onclick="openMenu(this, '${twoLevelMenu.controllerName}', '${twoLevelMenu.actionName}');">${twoLevelMenu.resourceName}<i class="icon icon-1"></i></li>
                            </c:forEach>
                        </ul>
                    </c:forEach>
                </li>
            </ul>
        </div>
        <div id="contentArea">
            <div class="box-table-wrap">
                <iframe id="mainFrame" style="border: none;">
                </iframe>
            </div>
        </div>
    </div>

    <div class="footer">
        <p><custom:renderCopyright></custom:renderCopyright></p>
    </div>
</div>
<div id="changePasswordWindow" class="easyui-dialog" buttons="#change-password-window-buttons" data-options="modal: true,title: '修改密码',width: 400,height: 250,closed: true,closed: true,closable: false,top: 120">
    <form id="changePasswordForm">
        <div style="height: 40px;line-height: 40px;text-align: center;">
            <span style="width: 80px;height: 32px;text-align: right;display: inline-block;">原始密码：</span>
            <input type="password" id="originalPassword" class="easyui-textbox" data-options="required:true" validType="originalPassword[6, 20]" style="width:200px;height:32px">
        </div>
        <div style="height: 40px;line-height: 40px;text-align: center;">
            <span style="width: 80px;height: 32px;text-align: right;display: inline-block;">新密码：</span>
            <input type="password" id="password" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" validType="password[6, 20]">
        </div>
        <div style="height: 40px;line-height: 40px;text-align: center;">
            <span style="width: 80px;height: 32px;text-align: right;display: inline-block;">确认密码：</span>
            <input type="password" id="confirmPassword" class="easyui-textbox" data-options="required:true" validType="confirmPassword['password', 'textbox']" style="width:200px;height:32px">
        </div>
    </form>
</div>
<div id="change-password-window-buttons" style="display: none;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="changePassword();">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWindow('changePasswordWindow')">取消</a>
</div>
</body>
</html>
