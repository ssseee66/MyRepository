<%--
  Created by IntelliJ IDEA.
  User: 略略略
  Date: 2024/4/7
  Time: 3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table align="center">
        <tr>
            <td>
                编号:
                <input type="text" name="user_id" value="${user.user_id}" readonly>
                <br>
                用户:
                <input type="text" name="userName" value="${user.userName}" readonly>
                <br>
                <c:if test="${user.user_type}">
                    <input type="checkbox" checked name="user_type" disabled>
                </c:if>
                <c:if test="${!user.user_type}">
                    <input type="checkbox" name="user_type" disabled>
                </c:if>
                管理员
                <br>
                备注:
                <input type="text" name="user_memo" value="${user.user_memo}" readonly>
                <br>
                <input type="button" name="comeBack" value="返回列表"
                       onclick="location.href='/Java_Test_war_exploded/user/comeBack.html?' +
                               'loginUserId=${loginUser.user_id}'">
            </td>
        </tr>
    </table>
</body>
</html>
