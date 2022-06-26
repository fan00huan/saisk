<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/core/mybase.css" /> --%>
<!-- <link rel="stylesheet" href="../css/core/mybase.css" /> -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/lib/jquery-ui/jquery-ui.min.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/core/mybase.css" type="text/css"/>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>

<script type="text/javascript">


</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
        用户注册
</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/register.css" type="text/css">
</head>
<!-- 显示错误信息 -->
<c:if test="${allErrors!=null }">
	<c:forEach items="${allErrors }" var="error">
	${ error.defaultMessage}<br />
	</c:forEach>
</c:if>

<body>

	<form id="registForm"
        	action="${pageContext.request.contextPath }/registSubmit.action"
		method="post">
    注册用户：

		<table width="100%" border=1>
			<tr>
				<td>用户ID</td>
				<td>
				<input  type="text" name="id" value="${tUser.id}"/>

				<c:if test="${errorMsgId!=null }">
					<label class="user-id-error">${errorMsgId}</label>
				</c:if>

				</td>
			</tr>

			<tr>
				<td>用户姓名</td>
				<td><input type="text" name="username" value="${tUser.username}"/>
			</tr>
			<tr>
				<td>用户性别</td>
				<td><input type="radio" name="sex" <c:if test="${tUser.sex==1 }">checked="checked"</c:if> value="1" >men</input>
				<input type="radio" name="sex" <c:if test="${tUser.sex==0 }">checked="checked"</c:if> value="0" >women</input>
				 </td>
			</tr>

				<td>生日</td>
				<td>

<%-- 	不建议的写法			<input type="date"  name="birthday" value="${birthdayStr}"/> --%>
				<input type="date"  name="birthday" value="${tUser.birthdayStr}"/>
					</td>
			<tr>
				<td>用户密码</td>
				<td><input type="password" name="password"
					 /></td>
			</tr>


			<tr>
				<td align="center"><input type="submit" value="提交"/>
				</td>
				<td align="center">
					<!--<input type="submit" value="返回" onclick="returnToLogin();"/> -->
					<input type="button" id="id_return_to_login" value="返回" onclick="returnToLoginById();"/>
				</td>
			</tr>

		</table>

	</form>
</body>

</html>