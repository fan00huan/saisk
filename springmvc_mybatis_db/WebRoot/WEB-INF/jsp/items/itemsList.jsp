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
<title>查询商品列表</title>

<script type="text/javascript" src="../js/item/itemsList.js"></script>
<!-- <script type="text/javascript"> -->
<!-- // 	function editItemsAll() { -->
<!-- // 		//提交form -->
<%-- // 		document.itemsForm.action = "${pageContext.request.contextPath }/items/editItemsAll.action"; --%>
<!-- // 		document.itemsForm.submit(); -->
<!-- // 	} -->
<!-- // 	function editItemsMap() { -->
<!-- // 		//提交form -->
<%-- // 		document.itemsForm.action = "${pageContext.request.contextPath }/items/editItemsMap.action"; --%>
<!-- // 		document.itemsForm.submit(); -->
<!-- // 	} -->

<!-- // 	function queryItems() { -->
<!-- // 		//提交form -->
<%-- // 		document.itemsForm.action = "${pageContext.request.contextPath }/items/queryItems.action"; --%>
<!-- // 		document.itemsForm.submit(); -->
<!-- // 	} -->

<!-- // 	function addItem() { -->
<!-- // 		//提交form -->
<%-- // 		document.itemsForm.action = "${pageContext.request.contextPath }/items/addItem.action"; --%>
<!-- // 		document.itemsForm.submit(); -->
<!-- // 	} -->

<!-- // 	function deleteItems() { -->
<!-- // 		//提交form -->
<%-- // 		document.itemsForm.action = "${pageContext.request.contextPath }/items/deleteItems.action"; --%>
<!-- // 		document.itemsForm.submit(); -->
<!-- // 	} -->

<!-- </script> -->
</head>
<body>

		<table>
			<tr>
				<td>当前用户：${username}</td>
				<c:if test="${username!=null }">
					<td><a href="${pageContext.request.contextPath }/logout.action">退出</a></td>
				</c:if>
				<td align="right"><a href="${pageContext.request.contextPath }/initUserLst.action">用户一览</a></td>
			</tr>
		</table>

	<form name="itemsForm"
		action="${pageContext.request.contextPath }/item/queryItem.action"
		method="post">
		查询条件：
		<table width="100%" border=1>
			<tr>
				<td>商品名称：<input name="itemName" value="${voItemPic.itemName}" />


					商品类型：
				<select name="itemType">
					<c:forEach items="${itemtypes}" var="itemtype">
					    <c:choose>
					        <c:when test="${voItemPic.itemType == itemtype.key }">
					        	<option value="${itemtype.key }" selected="selected">${itemtype.value }</option>
					        </c:when>
					        <c:otherwise>
					        	<option value="${itemtype.key }">${itemtype.value }</option>
					        </c:otherwise>
				    	</c:choose>
					</c:forEach>
				</select>





				</td>
				<td><input type="button" value="查询" onclick="queryItems()" />
				<input type="button" value="新規" onclick="addItem()" />
					<input type="button" value="批量删除" onclick="deleteItems()" /> <input
					type="button" value="批量修改提交" onclick="editItemsAll()" /> <input
					type="button" value="批量修改提交Map" onclick="editItemsMap()" /></td>
			</tr>
		</table>

		<c:if test="${voItemPic.itemLst !=null && fn:length(voItemPic.itemLst) > 0}">
		商品列表：
		<table width="100%" border=1>
				<tr>
					<th>选择</th>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>生产日期</th>
					<th>商品描述</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${voItemPic.itemLst}" var="item" varStatus="status">
					<tr>
						<td><input type="checkbox" name="items_id" currentStatusxx="aa_${status.count}"
							value="${item.itemId}"></td>
						<td>${item.itemName }</td>
						<td>${item.itemPrice }</td>
						<td><fmt:formatDate value="${item.itemProductDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${item.itemDetail }</td>
						<td><a
							href="${pageContext.request.contextPath }/items/editItem.action?id=${item.itemId}">修改</a></td>
					</tr>
					<!-- 				<tr> -->
					<!-- 					<td></td> -->
					<%-- 					<td><input name="itemLst[${status.index}].itemName" --%>
					<%-- 						value="${item.itemName }" /></td> --%>
					<%-- 					<td><input name="itemLst[${status.index}].itemPrice" --%>
					<%-- 						value="${item.price }" /></td> --%>
					<%-- 					<td><input name="itemLst[${status.index}].itemProductDate" --%>
					<%-- 						value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /></td> --%>
					<%-- 					<td><input name="itemLst[${status.index}].itemDetail" --%>
					<%-- 						value="${item.detail }" /></td> --%>
					<!-- 					<td></td> -->
					<!-- 				</tr> -->
				</c:forEach>
			</table>
		</c:if>
	</form>
</body>

</html>