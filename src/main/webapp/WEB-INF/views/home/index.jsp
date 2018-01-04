<%--
  Created by IntelliJ IDEA.
  User: liuyandong
  Date: 2018/1/4
  Time: 下午8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="custom" uri="http://www.chng.com.cn" %>
<html>
<head>
    <title>主页</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0LeweY0SDrw1uwpSA1SmBRmn"></script>
    <custom:script type="text/javascript" dir="libraries/jquery" file="jquery-3.2.1.min.js" base=".."></custom:script>
    <style type="text/css">
        body {padding: 0px;margin: 0px;}
        #container {width: 100%;height: 100%;}
    </style>
    <script type="text/javascript">
        $(function () {
            var map = new BMap.Map("container");
            var point = new BMap.Point(120.38442818, 36.10521490);
            map.centerAndZoom(point, 100);
        });
    </script>
</head>
<body>
<div id="container"></div>
</body>
</html>
