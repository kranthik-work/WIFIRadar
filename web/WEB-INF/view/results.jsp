<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krant
  Date: 7/31/2017
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Radar Results</title>
</head>
<body>
<h1>WELCOME</h1>
<hr>

<table border="1" align="center">
    <tr><td>SSID</td><td>IP</td><td>Signal Value in dBm</td><td>Up Value</td><td>Bottom Value</td></tr>
    <c:forEach var="listValue" items="${essids}">
        <tr align="center"><td>${listValue.essid}</td>    <td>${listValue.ipAddress}</td>    <td>${listValue.signal}</td>  <td>${listValue.upIndex}</td>    <td>${listValue.downIndex}</td></tr>
    </c:forEach>
</table>

<br><br>
Operating System: ${os}
<br><br>

</body>
</html>
