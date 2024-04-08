
<%@ page import="com.smart.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: 略略略
  Date: 2024/3/28
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>普通用户界面</title>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <th>编号</th>
        <th>用户</th>
        <th>密码</th>
        <th>类型</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                    ${user.user_id}
            </td>
            <td>
                    ${user.userName}
            </td>
            <td>
                    ${user.password}
            </td>
            <td>
                    ${user.userType}
            </td>
            <td>
                    ${user.user_memo}
            </td>
            <!-- 普通用户对自己账号能进行修改和查看以及对其他用户信息进行查看但是无法修改 -->
            <c:if test="${loginUser.user_id == user.user_id}">
                <td>
                    <input id="${user.user_id}" type="button" name="addUser" disabled
                           onclick="location.href='/Java_Test_war_exploded/' +
                                    'user/addUser.html'" value="新增">
                    <input type="button" name="modify" value="修改"
                           onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                   'option=modify&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                    <input type="button" name="delete" value="删除" disabled>
                    <input type="button" name="query" value="详细"
                           onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                   'option=query&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                </td>
            </c:if>
            <c:if test="${loginUser.user_id != user.user_id}">
                <td>
                    <input id="${user.user_id}" type="button" name="addUser" disabled
                           onclick="location.href='/Java_Test_war_exploded/' +
                                    'user/addUser.html'" value="新增">
                    <input type="button" name="modify" value="修改" disabled
                           onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                   'option=modify&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                    <input type="button" name="delete" value="删除" disabled>
                    <input type="button" name="query" value="详细"
                           onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                   'option=query&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
