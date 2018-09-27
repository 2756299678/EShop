<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>登陆</title>
        <!-- 导入bootstrap. css-->
		<link rel="stylesheet" href="../static/bootstrap-3.3.5-dist/css/bootstrap.css" />
		<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
		<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myValidate.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.validate.js" ></script>

      <style type="text/css">
      	.code
      	{
      		background-image: url(code.jpg);//背景图
      		font-family: arial;//字体
      		font-style: italic;//风格
      		color:red;//颜色
      		border: 0;//边
      		padding: 2px 3px;
      		letter-spacing: 3px;
      		font-weight:bolder;//粗细
      	}
      	.unchanged
      	{
      		border: 0;
      	}
      </style>
      <script type="text/javascript">
      	var code;
      	function createCode()
      	{
      		code = "";
      		var codeLength = 6;//验证码的长度
      		var checkCode = document.getElementById("checkCode");
      		var selectChar = new Array(
      			0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I',
      			'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'  		
      			);
      		for(var i=0;i<codeLength;i++)
      			{
      				var charIndex=Math.floor(Math.random()*36);
      				code += selectChar[charIndex];
      			}
      		//alert (code);
      		if(checkCode)
      		{
      			checkCode.className="code";
      			checkCode.value = code;
      		}
      		
      	}
      </script>
    </head>
    <body onload="createCode()">
    	<div class="row-fulid" style="margin-top: 200px;">
    		<div class="col-md-4"> </div>
    		<div class="col-md-4">
    			<form method="post" id="checkForm" role="from"
    				class="form-horizontal"  action="${pageContext.request.contextPath}/front/user/userServlet?method=login" method="post" id="checkForm">
    				<div class="form-group">
    					<label class="col-md-3 control-lable"  for="account">用户名</label>
    					<div class="col-md-9">
    						<input class="form-control" name="account" type="text" id="account" placeholder="Username" value=""/>
    					</div>
    				</div>
    				<div class="form-group">
    					<label class="col-md-3 control-lable"  for="inputPassword">密码</label>
    					<div class="col-md-9">
    						<input class="form-control" name="password" type="password" id="inputPassword" placeholder="Password" value=""/>
    					</div>
    				</div>
    				<div class="form-group">
    					<label class="col-md-3 control-lable"  for="checkCode">验证码</label>
    					<div class="col-md-4">
    						<input class="form-control" name="codeCheck" type="text" id="codeCheck" placeholder="Code" value=""/>
    					</div>
    					<div class="col-md-4">
    						<input type="text" onclick="createCode()" readOnly="true"
    							id="checkCode" class="unchanged" style="width: 80px;color: red;">
    					</div>
    				</div>
    				<div class="form-group">
    				</div>
    				<div class="form-group">
    					<div class="col-md-offset-3 col-md-4">
    						<button type="submit" class="btn btn-primary btn-block">登陆</button>
    					</div>		
    					<div class=" col-md-4">
    						<a type="submit" class="btn btn-primary btn-block" href="add.jsp">注册</a>
    					</div>		
    				</div>
    				<c:if test="${param.status.equals('1')}">
    					<div class="alert alert-danger" role="alert">用户名或密码错误</div>
    				</c:if>
    				<c:if test="${param.status.equals('3')}">
    					<div class="alert alert-danger" role="alert">该用户已被冻结</div>
    				</c:if>
    			</form>
    		</div>
    		<div class="col-md-4"></div>
    	</div>
 	</body>
</html>