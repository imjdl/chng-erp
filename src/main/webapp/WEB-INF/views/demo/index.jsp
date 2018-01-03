<%--
  Created by IntelliJ IDEA.
  User: liuyandong
  Date: 2018/1/3
  Time: 下午8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="custom" uri="http://www.chng.com.cn" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <custom:script type="text/javascript" dir="libraries/jquery" file="jquery-3.2.1.min.js" base=".."></custom:script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0LeweY0SDrw1uwpSA1SmBRmn"></script>
    <script type="text/javascript">
        $(function () {
            var map = new BMap.Map("container");
            var point = new BMap.Point(120.38442818, 36.10521490);
            map.centerAndZoom(point, 100);
            map.enableScrollWheelZoom(true);
        });
    </script>
</head>
<body>
<div id="container" style="width: 600px;height: 300px;"></div>
<br><br><br>
<select name="province" style="width: 100px;height: 30px;border: 1px solid #00AAEE;">
    <option value="150000">内蒙古</option>
    <option value="150000">内蒙古</option>
    <option value="150000">内蒙古</option>
</select>
<select name="province" style="width: 100px;height: 30px;border: 1px solid #00AAEE;">
    <option value="150000">内蒙古</option>
    <option value="150000">内蒙古</option>
    <option value="150000">内蒙古</option>
</select>
<select name="province" style="width: 100px;height: 30px;border: 1px solid #00AAEE;">
    <option value="150000">内蒙古</option>
    <option value="150000">内蒙古</option>
    <option value="150000">内蒙古</option>
</select>
</body>
</html>
