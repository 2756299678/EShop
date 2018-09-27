<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品类型查询</title>
  <!-- 导入bootstrap. css-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
	<!--导入jquery.js-->
	   <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" ></script>	
	<!--导入bootstrap.js-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script> 
	<script type="text/javascript">
		function shoowIndex(){
			<c:set var="item0" scope="session" value="${productTypeBean}"></c:set>
			var content = "<li><a href='productTypeServlet?method=list&id=0'>Home</a></li>"
			var contentM = "";
			
			if("${item0!=null}")
			{
				contentM="<li><a href='productTypeServlet?method=list&id="+
				"${item0.id}"+"'>"+"${item0.name}"+"</a></li>"+contentM;
				<c:set var="item0" scope="session" value="${item0.parentBean}"></c:set>
			}
			content +=contentM;
			content += "</select></div>";
			$("#index0").append(content);
		}
	</script>
    </head>
    <body>
    	<div class="container">
    		<h1>商品类型查询</h1>
    		<table class="table table-striped">
    			<ol class="breadcrumb">
    				<li><a href="productTypeServlet?method=list&id=0">Home</a></li>
    				<li><a href="productTypeServlet?method=list&id=${productTypeBean.parentBean.id}">上一级</a></li>
    				<li class="active">${productTypeBean.name}</li>
    			</ol>
    		<c:forEach items="${productTypeBean.childBeans}" var="item" varStatus="status">
    			<c:if test="${status.index==0}">
    				<tr>
    					<td>id</td>
    					<td>分类号</td>
    					<td>序号</td>
    					<td>描述</td>
    					<td>创建时间</td>
    					<td>操作</td>
    					<td>操作</td>
    				</tr>
    			</c:if>
    			<tr>
    				<td>${item.id}</td>
    				<td><a href="productTypeServlet?method=list&id=${item.id}">${item.name}</a></td>
    				<td>${item.sort}</td>
    				<td>${item.intro}</td>
    				<td>${item.createDate}</td>
    				<td><a href="productTypeServlet?method=update&id=${item.id}">修改</a></td>
    				<td><a href="productTypeServlet?method=delete&id=${item.id}" data-target="#myModal">删除</a></td>
    			</tr>
    		</c:forEach>
    		</table>
    	</div>
 	</body>
</html>