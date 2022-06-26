<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户列表</title>
<script type="text/javascript" src="js/userinfo/userinfoList.js">
</script>
</head>
<body>

	<form name="usersForm"
		action="${pageContext.request.contextPath }/item/queryUsers.action"
		method="post">
		查询条件：
		<table width="100%" border=1>
			<tr>
				<td>用户名称：<input name="username" value="${voUserPic.username}"/>
				</td>
				<td><input type="button" value="查询" onclick="queryUserLst()" />
			</tr>
		</table>

		<c:if test="${voUserPic.userLst !=null && fn:length(voUserPic.userLst) > 0}">
		用户列表：
		<table width="100%" border=1>
				<tr>
					<th>选择</th>
					<th>用户名</th>
					<th>用户密码</th>
					<th>用户性别</th>
				</tr>

				<c:forEach items="${voUserPic.userLst}" var="user" varStatus="status">
					<tr>
						<td><input type="checkbox" name="users_id"
							value="${user.id}"></td>
						<td>${user.username }</td>
						<td>${user.password }</td>
						<td>${user.sex }</td>
						<td><a
							href="${pageContext.request.contextPath }/user/editUser.action?id=${user.id}">修改</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</form>
</body>

</html>