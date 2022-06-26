<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/core/mybase.css" /> --%>
<!-- <link rel="stylesheet" href="../css/core/mybase.css" /> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/lib/jquery-ui/jquery-ui.min.css" type="text/css"/>


<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/login.js"></script>

<script type="text/javascript">


</script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会員登録</title>
</head>
<!-- 显示错误信息 -->
<c:if test="${allErrors!=null }">
	<c:forEach items="${allErrors }" var="error">
	${ error.defaultMessage}<br />
	</c:forEach>
</c:if>
<c:if test="${errorMsgId!=null }">
	<label class="user-id-error">${errorMsgId}</label>
</c:if>
<body>
<div class="w">
<header ><img src="images/logo.png" alt=""></header>
	<form id="registForm"
        	action="${pageContext.request.contextPath }/registSubmit.action"
		method="post">
		 注册用户：

		 <div class="registerarea">
		  <div class="reg_form">
                   <ul>
                    <li><label for="">氏名：</label><input type="text" name="username" placeholder="（例）田中　花子">
                        <span class=""><i class=""></i></span></li>
                    <li><label for="">性別：</label>

                    <input type="radio" name="sex" value="1" >men</input>
				<input type="radio" name="sex" value="0" >women</input>

                        <span class=""><i class=""></i></span></li>
                    <li><label for="">生年月日：</label><input type="text" name="birthday" placeholder="（例）19910101">
                        <span class=""><i class=""></i></span></li>
                    <li><label for="">メールアドレス：</label><input type="text" name="mail" placeholder="">
                        <span class=""><i class=""></i></span></li>

                    <li><label for="">連絡先電話番号：</label><input type="text" name="tel">
                        <span class=""><i class=""></i></span></li>
                    <li><label for="">会員ID：</label><input type="text" name="id">
                        <span class=""><i class=""></i></span></li>
                    <li><label for="">パスワード：</label><input type="text" name="password">
                        <span class=""><i class=""></i></span></li>

                        <span class=""><i class=""></i></span></li>
                        <li><input type="submit" value="確認"  class="btn">
                        <input type="button" value="返回"  class="btn" onclick="return;"/></li>
                </ul>
                </div>
</div>


        <!-- footer                  开始-->
        <div class="footer">
        <div class="links">
            <dl>
                <dt>关于我们</dt>
                <dd><a href="#">品牌故事</a></dd>
                <dd><a href="#">联系我们</a></dd>
            </dl>
            <dl>
                <dt>帮助中心</dt>
                <dd><a href="#">常见问题</a></dd>
                <dd><a href="#">尺码导视</a></dd>
                <dd><a href="#">退/换货</a></dd>
                <dd><a href="#">物流信息</a></dd>
            </dl>
            <dl>
                <dt>会员中心</dt>
                <dd><a href="#">会员章程</a></dd>
                <dd><a href="#">注册会员</a></dd>
            </dl>
            <dl>
                <dt>加入我们</dt>
                <dd><a href="#">校园招聘</a></dd>
                <dd><a href="#">社会招聘</a></dd>
            </dl>

        </div>
    </div>

<!-- 		<table width="100%" border=1> -->
<!-- 			<tr> -->
<!-- 				<td>用户ID</td> -->
<!-- 				<td><input type="text" name="id"  /></td> -->
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>用户姓名</td> -->
<!-- 				<td><input type="text" name="username" -->
<!-- 					 /></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>用户性别</td> -->
<!-- 				<td><input type="radio" name="sex" value="1" >men</input> -->
<!-- 				<input type="radio" name="sex" value="0" >women</input> -->
<!-- 				 </td> -->
<!-- 			</tr> -->

<!-- 				<td>商品生产日期</td> -->
<!-- 				<td><input type="date"  name="birthday"  /> -->
<!-- 					</td> -->
<!-- 			<tr> -->
<!-- 				<td>用户密码</td> -->
<!-- 				<td><input type="text" name="password" -->
<!-- 					 /></td> -->
<!-- 			</tr> -->


<!-- 			<tr> -->
<!-- 				<td align="center"><input type="submit" value="提交"/> -->
<!-- 				</td> -->
<!-- 				<td align="center"><input type="button" value="返回" onclick="return;"/> -->
<!-- 				</td> -->
<!-- 			</tr> -->

<!-- 		</table> -->

	</form>
</body>

</html>