<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 略略略
  Date: 2024/3/28
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table align="center">
    <tr>
        <td>
            <form method="post" action="<c:url value="/user/addUserConfirm.html"/>">
                用户：
                <input name="userName" type="text" required="required">
                <br>
                密码：
                <input name="password" type="password" required="required">
                <br>
                备注：
                <input name="user_memo" type="text">
                <br>
                <input type="checkbox" name="user_type" required="required">
                管理员
                <br>
                <input type="hidden" name="loginUserId" value="${loginUser.user_id}">
                <input type="submit" name="addUser" value="添加用户">
                <input type="button" name="comeBack" value="返回列表"
                    onclick="location.href='/Java_Test_war_exploded/user/comeBack.html?' +
                            'loginUserId=${loginUser.user_id}'">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
