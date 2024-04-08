<%--
  Created by IntelliJ IDEA.
  User: 略略略
  Date: 2024/4/6
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户修改</title>
</head>
<body>
    <table align="center">
        <tr>
            <td>
                <form method="post" action="<c:url value="/user/modify.html"/>">
                    <!-- 登录用户为管理员时 -->
                    <c:if test="${loginUser.user_type}">
                        <c:if test="${loginUser.user_id != user.user_id}">
                            编号:
                            <input type="text" name="user_id" value="${user.user_id}" readonly>
                            <br>
                            用户:
                            <input type="text" name="userName" value="${user.userName}">
                            <br>
                            密码:
                            <input type="password" name="password" value="${user.password}">
                            <br>
                            <c:if test="${user.user_type}">
                                <input type="checkbox" checked name="user_type">
                            </c:if>
                            <c:if test="${!user.user_type}">
                                <input type="checkbox" name="user_type">
                            </c:if>
                            管理员
                            <br>
                            备注:
                            <input type="text" name="user_memo" value="${user.user_memo}">
                        </c:if>
                        <c:if test="${loginUser.user_id == user.user_id}">
                            编号:
                            <input type="text" name="user_id" value="${user.user_id}" readonly>
                            <br>
                            用户:
                            <input type="text" name="userName" value="${user.userName}" readonly>
                            <br>
                            密码:
                            <input type="password" name="password" value="${user.password}">
                            <br>
                            <input type="checkbox" name="user_type" checked readonly>
                            管理员
                            <br>
                            备注:
                            <input type="text" name="user_memo" value="${user.user_memo}">
                        </c:if>
                    </c:if>
                    <!-- 登录用户为普通用户时 -->
                    <c:if test="${!loginUser.user_type}">
                        编号:
                        <input type="text" name="user_id" value="${user.user_id}" readonly>
                        <br>
                        用户:
                        <input type="text" name="userName" value="${user.userName}" readonly>
                        <br>
                        密码:
                        <input type="password" name="password" value="${user.password}">
                        <br>
                        <input type="checkbox" name="user_type" disabled>
                        管理员
                        <br>
                        备注:
                        <input type="text" name="user_memo" value="${user.user_memo}">
                    </c:if>
                    <br>
                    <input type="hidden" name="loginUserId" value="${loginUser.user_id}">
                    <input type="submit" name="modify" value="修改">
                    <input type="button" name="comeBack" value="返回列表"
                           onclick="location.href='/Java_Test_war_exploded/user/comeBack.html?' +
                    'loginUserId=${loginUser.user_id}'">
                    <br>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
