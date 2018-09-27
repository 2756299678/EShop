<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加管理员</title>
       <!-- 导入bootstrap. css-->
	<link rel="stylesheet" href="../static/bootstrap-3.3.5-dist/css/bootstrap.css" />
	<!--导入jquery.js-->
	   <script type="text/javascript" src="../static/js/jquery-1.12.1.js" ></script>	
	<!--导入bootstrap.js-->
	<script type="text/javascript" src="../static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script> 
    </head>
    <body>
    	<div class="row-fulid" style="margin-top: 200px;">
    		<div class="col-md-3"></div>
    		<div class="col-md-6">
    			<!--建立表单-->
    			<form role="form" class="form-horizontal" action="${pageContext.request.contextPath}/admin/adminServlet?method=addUser&updateId=${updateBean.id}" method="post" id="checkForm">
    			<!--输入用户名-->
    			<div class="form-group">
    				<label class="col-md-3 control-label for="username">用户名</label>
    				<c:if test="${updateBean.username==NULL}">
    					<div class="col-md-9">
    					<input class="form-control" name="username" type="text"
    						id="username" placeholder="用户名" value="${updateBean.username}"/>
    					</div>    			
    				</c:if>
    				<c:if test="${updateBean.username!=NULL}">
    					<label class="col-md-1 control-label for="username">${updateBean.username}</label>			
    				</c:if>
    				
    			</div>
    			
    			<!--输入密码-->
    			<div class="form-group">
    				<label class="col-md-3 control-label for="inputPassword">密码</label>
    				<div class="col-md-9">
    					<input class="form-control" name="password" type="password"
    						id="inputPassword" placeholder="密码" value="${updateBean.password}"/>
    				</div>
    			</div>
    			
    			<!--确认密码-->
    			<div class="form-group">
    				<label class="col-md-3 control-label for="password2">确认密码</label>
    				<div class="col-md-9">
    					<input class="form-control" name="password2" type="password"
    						id="password2" placeholder="确认密码" />
    				</div>
    			</div>
    			
    			<!--提交-->
    			<div class="form-group">
    				<div class="col-md-offset-3 col-md-9">
    					<button type="submit" class="btn btn-primary btn-block">提交</button>
    				</div>
    			</div>
    			<c:if test="${param.status.equals('1')}">
    				<div class="alert alert-danger" role="alert">添加成功</div>    			
    			</c:if>
    			<c:if test="${param.status.equals('2')}">
    				<div class="alert alert-danger" role="alert">该用户已存在</div>    			
    			</c:if>
    			<c:if test="${param.status.equals('3')}">
    				<div class="alert alert-danger" role="alert">成功</div>    			
    			</c:if>
    			
    			</form>
    		</div>
    		<div class="col-md-3"></div>
    	</div>
    	<script src="../static/js/jquery.validate.js"></script>
    	<script src="../static/js/myValidate.js"></script>	
    	
 	</body>
</html>