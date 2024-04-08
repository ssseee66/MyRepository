
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
    <title>欢迎</title>
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
                        ${user.password.replaceAll(".", "*")}
                </td>
                <td>
                        ${user.userType}
                </td>
                <td>
                        ${user.user_memo}
                </td>
                <!-- 当前表格行为登录管理员用户时 -->
                <!-- 无法对自己进行删除操作 -->
                <c:if test="${loginUser.user_id == user.user_id}">
                    <td>
                        <input id="${user.user_id}" type="button" name="addUser"
                               onclick="location.href='/Java_Test_war_exploded/' +
                                    'user/addUser.html?loginUserId=${loginUser.user_id}'" value="新增">
                        <input type="button" name="modify" value="修改"
                               onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                       'option=modify&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                        <input type="submit" name="delete" value="删除" disabled
                               onclick="deleteUser(${user.user_id})">
                        <input type="button" name="query" value="详细"
                               onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                       'option=query&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                    </td>
                </c:if>
                <c:if test="${loginUser.user_id != user.user_id}">
                    <td>
                        <input id="${user.user_id}" type="button" name="addUser"
                               onclick="location.href='/Java_Test_war_exploded/' +
                                    'user/addUser.html?loginUserId=${loginUser.user_id}'" value="新增">
                        <input type="button" name="modify" value="修改"
                               onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                       'option=modify&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                        <input type="submit" name="delete" value="删除"
                               onclick="deleteUser('${user.userName}', '${user.userType}', ${user.user_id})">
                        <input type="button" name="query" value="详细"
                               onclick="location.href='/Java_Test_war_exploded/user/view.html?' +
                                       'option=query&loginUserId=${loginUser.user_id}&userId=${user.user_id}'">
                    </td>
                    <form id="deleteForm${user.user_id}" method="get" action="<c:url value="/user/view.html"/>">
                        <input type="hidden" name="option" value="delete">
                        <input type="hidden" name="loginUserId" value="${loginUser.user_id}">
                        <input type="hidden" name="userId" value="${user.user_id}">
                    </form>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <script>
        function deleteUser(userName, userType, userId) {
            if (confirm("确认删除用户:" + userName + "(" + userType + ")" + "？")) {
                var form = document.getElementById("deleteForm" + userId);
                form.submit();
            }
        }
    </script>
</body>
</html>
