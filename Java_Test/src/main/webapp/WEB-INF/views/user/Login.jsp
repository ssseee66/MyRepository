<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>新增用户</title>
</head>
<body>
    <table align="center">
        <tr>
            <td>
                <form method="post" action="<c:url value="/user/Login.html"/>">
                    用户：
                    <input name="userName" type="text">
                    <br>
                    密码：
                    <input name="password" type="password">
                    <br>
                    <input type="submit" value="登录">
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
