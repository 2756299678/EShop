<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品展示</title>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>
<body>
<body>
	<div class="container">
	<h1>商品查询</h1>
		<table class="table table-striped">
		<c:forEach items="${ productBeans}" var="item" varStatus="status">
			<c:if test="${status.index==0 }">
			<!-- <ol class="breadcrumb" id="index0"></ol><script>showIndex();</script> -->
			<tr>
			<td>id</td>
			<td>name</td>
			<td>操作</td>
			<td>操作</td>
			</tr>
			</c:if>
		<tr>
			<td>${item.id }</td>
			<td><a href="productServlet?method=listDetails&id=${item.id }">${item.name}</a></td>
			<td><a href="productServlet?method=update&id=${item.id }">修改</a></td>
			<td><a href="productServlet?method=delete&id=${item.id }">删除</a></td>
		</tr>
		</c:forEach>
		</table>
		<c:if test="${param.status.equals('2')}">
    		<div class="alert alert-success" role="alert">删除成功</div>    			
    	</c:if>
    	<c:if test="${param.status.equals('1')}">
    		<div class="alert alert-success" role="alert">修改成功</div>    			
    	</c:if>
	</div>
</body>
</html>