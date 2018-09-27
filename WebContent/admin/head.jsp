<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- 导入bootstrap. css-->
	<link rel="stylesheet" href="../static/bootstrap-3.3.5-dist/css/bootstrap.css" />
	<!--导入jquery.js-->
	   <script type="text/javascript" src="../static/js/jquery-1.12.1.js" ></script>	
	<!--导入bootstrap.js-->
	<script type="text/javascript" src="../static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script>
</head>
<body>
<div class="navbar navbar-default" style="padding:20px 0 10px;margin:0;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapse"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>		
			</button>
			<span class="navbar-brand" href="#">ec-web综合管理平台</span>
		</div>
		<!--Collect the nav links,froms,andother connect for toggling-->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a target="_parent" href="${pageContext.request.contextPath}/admin/main.jsp">首页</a>
				</li>
				<li role="separator" class="divider"></li>
				<li>
					<a target="_parent" href="${pageContext.request.contextPath}/admin/adminServlet?method=end&status=1">退出登陆</a>
				</li>
				
			</ul>
				
		</div>
	</div>
</div>
</body>
</html>