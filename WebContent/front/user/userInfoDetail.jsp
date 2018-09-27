<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>个人信息</title>
	<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!-- validate验证 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myValidate.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.validate.js" ></script>
</head>
<body>
	<div class="container container-fluid " >
		<div  class="row">
		<div  class="col-md-6">
			<table class="table" border="0">
				<tr>
					<td>真实姓名:</td>
					<td>
					<div class="col-md-8">
					${userBean.truename}
					</div>
					<div class="col-md-offset-2">${userBean.sex}</div>
					</td>
				<tr>
				<tr>
					<td>手机号:</td>
					<td>${userBean.username}</td>
				<tr>
				<tr>
					<td>昵称:</td>
					<td>${userBean.nickname}</td>
				<tr>
				<tr>
					<td>当前密码:</td>
					<td>${userBean.password}</td>
				<tr>
			</table>
		</div>
		
		<div class="col-md-2">
		<button class="btn btn-defult btn-sm" data-toggle="modal" data-target="#myModal">修改信息</button>
		</div>
		<!-- 模态框（Modal） -->
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							修改个人信息
						</h4>
					</div>
					<div class="modal-body">
					<form action="${pageContext.request.contextPath}/front/user/userServlet?method=updatemessage&updateId=${userBean.id }" method="post" id="checkForm">
						<div class="form-group">
						    <label for="account">新手机号</label>
						    <input type="text" class="form-control" id="account"  name="account" placeholder="请输入新手机号" value="${userBean.username }">
					    </div>
					    <div class="form-group">
						    <label for="nickname">昵称</label>
						    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="请输入昵称"value="${userBean.nickname }">
					    </div>
					    <div class="form-group">
						    <label for="inputPassword">新密码</label>
						    <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="请输入密码">
					    </div>
					    <div class="form-group">
						    <label for="password2">确认密码</label>
						    <input type="password" class="form-control" id="password2" name="password2"placeholder="确认密码">
					    </div>
					
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
							<button type="submit" class="btn btn-primary">
								提交更改
							</button>
						</div>
						</form>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		
		<!-- 模态框结束 -->
		<div class="col-md-3">
			<div>
			<c:if test="${userBean.pic==NULL}">
				<img alt="图片" src="login.jpg" width="100px" height="100px" class="img-circle">
			</c:if>
			<c:if test="${userBean.pic!=NULL}">
				<img alt="图片" src="${userBean.pic}" width="100px" height="100px" class="img-circle">
			</c:if>
			</div>
			
			<div class="row-fulid">
				<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/user/userServlet?method=updatepic&updateId=${userBean.id }" method="post" id="checkForm">
				<div class="col-md-6">
				<input type="file"  class="form-control" name="pic" id="pic" placeholder="pic" value="http://localhost:8080/EShop/upload/user/20170704/192421.jpg"/>
				</div>
				<div class="col-md-1">
				<button type="submit" class="btn btn-defult">上传头像</button>
				</div>
				</form>
			</div>
		</div>
		
		</div>
		<div class="col-md-12">
			<c:if test="${param.status.equals('1')}">
   				<div class="alert alert-danger" role="alert">修改成功</div>    			
   			</c:if>
   			<c:if test="${param.status.equals('3')}">
   				<div class="alert alert-danger" role="alert">该用户已存在</div>    			
   			</c:if>
   			<c:if test="${param.status.equals('2')}">
   				<div class="alert alert-danger" role="alert">修改失败</div>    			
   			</c:if>
		</div>
		
	</div>

</body>
</html>