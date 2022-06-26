<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/core/mybase.css" /> --%>
<!-- <link rel="stylesheet" href="../css/core/mybase.css" /> -->
<link rel="stylesheet" href="../js/lib/jquery-ui/jquery-ui.min.css" type="text/css"/>
<link rel="stylesheet" href="../css/core/mybase.css" type="text/css"/>

<script type="text/javascript" src="../js/lib/jquery.min.js"></script>
<script type="text/javascript" src="../js/lib/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/item/editItems.js"></script>

<script type="text/javascript">

function commit() {
	alert();


}
$(document).ready(function(){
	$("#id_btn_testJson01").click(function(){

        //Ajax调用处理
        $.ajax({
           type: "POST",
           url: "testJson01.action",
           //data: "name=&age=18",
           data: {
               "name":"张三",
               "age": 18
             },
           success: function(data){
        	   if (data.flag) {
					alert(data.message);
				} else {
					alert("error");
				}
              }
        });

	 });
       $("#id_btn_testVoidForward").click(function(){
    		document.getElementById('itemForm').action = "testVoidForward.action";
    		document.getElementById("itemForm").submit();
       });
          $("#id_btn_testVoidRedirect").click(function(){
      		document.getElementById('itemForm').action = "testVoidRedirect.action";
    		document.getElementById("itemForm").submit();
          });
             $("#id_btn_testVoidJson").click(function(){

                 //Ajax调用处理
                 $.ajax({
                    type: "POST",
                    url: "testVoidJson.action",
                    data: {
                        "name":"张三",
                        "age": 18
                      },
                    success: function(data){
                 	   if (data.flag) {
         					alert(data.message);
         				} else {
         					alert("error");
         				}
                       }
                 });

	});
	});


</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
    <c:choose>
        <c:when test="${voItem.itemId == null }">
        新規商品信息
        </c:when>
        <c:otherwise>
        修改商品信息
        </c:otherwise>
    </c:choose>
</title>

</head>
<!-- 显示错误信息 -->
<c:if test="${allErrors!=null }">
	<c:forEach items="${allErrors }" var="error">
	${ error.defaultMessage}<br />
	</c:forEach>
</c:if>



<body>

	<form id="itemForm"

	    <c:choose>
        <c:when test="${voItem.itemId == null }">
        	action="${pageContext.request.contextPath }/items/addItemsSubmit.action"
        </c:when>
        <c:otherwise>
        	action="${pageContext.request.contextPath }/items/editItemsSubmit.action"
        </c:otherwise>
    </c:choose>
		method="post" enctype="multipart/form-data">
    <c:choose>
        <c:when test="${voItem.picMode == 'insert_mode' }">
        新規商品信息：
        </c:when>
        <c:otherwise>
        修改商品信息：
        </c:otherwise>
    </c:choose>


    <input type='hidden' name='picMode' value="${voItem.picMode}"/>

		<table width="100%" border=1>
			<tr>
				<td>商品ID</td>
				<td>
				     <c:choose>
						<c:when test="${voItem.picMode == 'insert_mode' }">
							<input type="text" name="itemId" value="${voItem.itemId }" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="itemId" value="${voItem.itemId }" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td>商品名称</td>
				<td><input type="text" name="itemName"
					value="${voItem.itemName }" /></td>
			</tr>


			<tr>
				<td>商品价格</td>
				<td><input type="text" name="itemPrice"
					value="${voItem.itemPrice }" /></td>
			</tr>
			<tr>
				<td>商品生产日期</td>
				<td><input type="text" name="itemProductDate" value="<fmt:formatDate value="${voItem.itemProductDate}" pattern="yyyy/MM/dd"/>" />
					<div class="item-datapicker">
					<img id="id_picker_productDate" alt="..." src="${pageContext.request.contextPath}/img/calendar_icon.gif"/>
					<img id="id_delete_productDate" alt="..." src="${pageContext.request.contextPath}/img/delete_icon.gif" />
					</div>
					</td>
			</tr>
			<tr>
				<td>商品图片</td>
				<td><c:if test="${voItem.itemPic !=null}">
						<img src="/pic/${voItem.itemPic}" width=300 height=300 />
						<br />
					</c:if>
					<input type="file" name="multiItemPic" /></td>
			</tr>

			<tr>
				<td>商品简介</td>
				<td><textarea rows="3" cols="30" name="itemDetail" dbVal="${voItem.itemDetail }">${voItem.itemDetail }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="提交"/>
				</td>
				<td align="center"><input type="button" value="返回" onclick="returnToSearch();"/>
				</td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回JAVA对象 ModelAndView</td>
				<td align="left"><input type="button" value="editItemsRetMV"
					onclick="fun_editItemsRetMV();" /></td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回String JSP画面</td>
				<td align="left"><input type="button" value="editItemsRetV"
					onclick="fun_editItemsRetV();" /></td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回String 重定向</td>
				<td align="left"><input type="button" value="redirect"
					onclick="fun_editItemsRetRedirect();" /></td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回String 定向</td>
				<td align="left"><input type="button" value="forward"
					onclick="fun_editItemsRetforward();" /></td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回JSON</td>
				<td align="left"><input type="button" id="id_btn_testJson01"
					value="testJson01" /></td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回VOID 定向</td>
				<td align="left"><input type="button"
					id="id_btn_testVoidForward" value="testVoidForward" /></td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回VOID 重定向</td>
				<td align="left"><input type="button"
					id="id_btn_testVoidRedirect" value="testVoidRedirect" /></td>
			</tr>
			<tr>
				<td align="right">测试Controller 返回VOID 输出JSON</td>
				<td align="left"><input type="button" id="id_btn_testVoidJson"
					value="testVoidJson" /></td>
			</tr>
		</table>

	</form>
</body>

</html>