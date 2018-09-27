<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的收获地址</title>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<!-- validate验证 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myValidate.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.validate.js" ></script>

<script type="text/javascript">
function chCity(obj) {
	$(obj).parent().nextAll().remove();
	var id = obj.value;
	if (id > 0) {
		$
				.post(
						"addressServlet",
						{
							method : "getCity",
							id : id
						},
						function(data) {
							if (data != null && data.length > 0) {
								var content = "<div class='col-sm-3' > <select name='CityId' class='form-control' onchange='chArea(this)' id='city0'> <option value='0'>-- 请选择市 --</option>";
								for ( var city in data) {
									content += "<option value='"+data[city].id+"'>"
											+ data[city].name + "</option>";
								}
								content += "</select></div>";
								$("#types").append(content);
							}
						}, "json");
	}

}

function chArea(obj) {
	var id = obj.value;
	if (id > 0) {
		$
				.post(
						"addressServlet",
						{
							method : "getArea",
							id : id
						},
						function(data) {
							if (data != null && data.length > 0) {
								var content = "<div class='col-sm-3' > <select name='AreaId' class='form-control' id='area0'> <option value='0'>-- 请选择县区 --</option>";
								for ( var area in data) {
									content += "<option value='"+data[area].id+"'>"
											+ data[area].name + "</option>";
								}
								content += "</select></div>";
								$("#types").append(content);
								document.getElementById("area0").innerHTML = content;
							}
						}, "json");
	}
}
</script>
</head>
<body>
	<div class="container">
		<div class="col-md-12">
			<button class="btn btn-defult btn-sm" data-toggle="modal" data-target="#myModal">
				新增收获地址
			</button>
		</div>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							添加收获地址
						</h4>
					</div>
					<div class="modal-body">
					<form action="${pageContext.request.contextPath}/front/user/addressServlet?method=add&userId=${userBean.id }" method="post" id="checkForm">
						<div class="form-group col-md-12">
						    <label for="name">收货人姓名：</label>
						    <input type="text" class="form-control" id="name"  name="name" placeholder="请输入收货人信息" ">
					    </div>
					    
					    <!-- <div class="form-group">
						    <label for="area">所在地区</label>
						    <input type="password" class="form-control" id="area" name="area" placeholder="请输入密码">
					    </div> -->
					    <!-- 地区寻找 -->
						<div class="form-group col-md-12" id="types">
							<label class="col-md-12 control-label" for="address">所在地区 </label>
							<div class="col-sm-3">
								<select name="ProvinceId" class="form-control"
									onchange="chCity(this)" id="province0">
									<option value="0">-- 请选择省 --</option>
									<c:forEach items="${provinces }" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					    
					    <div class="form-group col-md-12">
						    <label for="address">收获地址：</label>
						    <input type="text" class="form-control" id="address" name="address" placeholder="输入收获地址">
					    </div>
					    
					    <div class="form-group col-md-12">
						    <label for="account">联系手机：</label>
						    <input type="text" class="form-control" id="account" name=account placeholder="请输入联系手机"">
					    </div>
					
						<div class="modal-footer ">
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
		
		<div class="col-md-12">
		<c:forEach items="${addressBeans }" var="item" varStatus="status">
	<div class="panel panel-default">
	    <div class="panel-body">
				<div class="col-md-12">
				&nbsp;
				</div>
				<div class="col-md-10">
					<table class="table table-condensed">
					<tr class="col-md-12">
						<td>收货人：</td>
						<td>${item.name}</td>
					</tr>
					<tr class="col-md-12">
						<td>所在地区：</td>
						<td>${item.province }${item.city }${item.region }</td>
					</tr>
					<tr class="col-md-12">
						<td>地址：</td>
						<td>${item.address }</td>
					</tr>
					<tr class="col-md-12">
						<td>联系手机：</td>
						<td>${item.cellphone }</td>
					</tr>
					<tr class="col-md-12">
						<td>用户：</td>
						<td>${userBean.nickname }</td>
					</tr>
					</table>
				</div>
				<div class="col-md-2">
					<div class="col-md-12"></div>
						<div class="col-md-12"></div>
						<div class="col-md-12"><a href="${pageContext.request.contextPath}/front/user/addressServlet?method=delete&id=${item.id}&userId=${item.user_id } ">删除</a></div>
					<c:if test="${item.status==0 }">
						<div class="col-md-12"><a href="${pageContext.request.contextPath}/front/user/addressServlet?method=update&id=${item.id}&userId=${item.user_id } ">设为默认地址</a></div>
					</c:if>
					<c:if test="${item.status==1 }">
						<div class="col-md-12">默认地址</div>
					</c:if>
				</div>
			</div>
		</div>
			</c:forEach>
			
		</div>
		
		
		<div class="col-md-12">
			<c:if test="${param.status.equals('1')}">
				<div class="alert alert-success" role="alert">添加成功</div>
			</c:if>
		</div>
	</div>
	
</body>
</html>