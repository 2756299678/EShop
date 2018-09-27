<%@page import="java.sql.ResultSet" %>
<%@page import = "java.sql.Statement" %>
<%@page import = "java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@taglib uri="http://localhost:8080/3-28/util" prefix ="util"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>查看管理员</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css"/>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fulid">
			<div class="spanl2">
				<h1>管理员列表</h1>
			</div>
		</div>
		
		<div class="row-fulid">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<table class="table table-striped">
					<tr>
						<td>id</td>
						<td>username</td>
						<td>password</td>
						<td>salt</td>
						<td>操作</td>
						<td>操作</td>
					</tr>
					<!--foreach 遍历出adminBean-->
					<c:forEach items="${adminBeans}" var="item" varStatus="status">
						<tr>
							<td>${item.id}</td>
							<td><a>${item.username}</a></td>
							<td>${item.password}</td>
							<td>${item.salt}</td>
							<td>		
								<a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/admin/adminServlet?method=toUpdate&id=${item.id}">修改</a>
							</td>
							<td>
								<!-- Large modal -->
								<button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target=".bs-example-modal-lg">删除</button>
								
								<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
								  <div class="modal-dialog modal-lg" role="document">
								  	<div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									        <h4 class="modal-title">管理系统</h4>
									      </div>
									      <div class="modal-body">
									        <p>请确认是否删除&hellip;</p>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
									        
									       <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/adminServlet?method=delete&id=${item.id}">是</a> 
									      </div>
									  </div><!-- /.modal-content -->
								  </div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${param.status.equals('2')}">
					<div class="alert alert-success" role="alert">修改成功</div>
				</c:if>
				<c:if test="${param.status.equals('3')}">
					<div class="alert alert-success" role="alert">删除成功</div>
				</c:if>
				<c:if test="${param.status.equals('1')}">
					<div class="alert alert-info" role="alert">没有权限操作超级管理员</div>
				</c:if>
			</div>
			
			<div class="col-md-1"></div>
			
		</div>
		<div class="row-fulid">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div>
					<util:page pagingBean="${pagingBean}"/>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>