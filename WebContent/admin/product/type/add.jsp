<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加分类</title>
	 <!-- 导入bootstrap. css-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
	<!--导入jquery.js-->
	   <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" ></script>	
	<!--导入bootstrap.js-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script>
	<!-- 导入validate验证 -->
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			$("#myForm").validate({
				errorElement : 'span',  
			      errorClass : 'help-block',  
			      focusInvalid : false, 
				rules :{
					name:{
						required : true
					}
				},
				messages:{
					name:{
						required : "请输入分类名"
					}
				},  
		        highlight : function(element) {  
		            $(element).closest('.form-group').addClass('has-error');  
		        },  
		        success : function(label) {  
		            label.closest('.form-group').removeClass('has-error');  
		            label.remove();  
		        },  
		        errorPlacement : function(error, element) {  
		            element.parent('div').append(error);  
		        }
			});
		});
		
		function addType(obj){
			$(obj).parent().nextAll().remove();
			var id = obj.value;
			if(id >0){
				//ajax请求
				$.post("productTypeServlet",{
					method : "getType",
					id :id
				},
				function(data){
					if(data != null && data.length>0){
						var content = "<div class='col-sm-2'>"+
						"<select name='parentId' class='form-control' onchange='addType(this)' id='type0'>"+
						"<option value='0'>--请选择父类--</option>";
						for(var type in data){
							content +="<option value='"+data[type].id+"'>"+data[type].name +"</option>"						}
					}
					content +="</select></div>";

					if(data[type].id!=undefined)
					{
						$("#types").append(content);	
					}		
				},"json");
			}
		}
	</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center text-danger">添加商品分类</h1>
		<form id="myForm" role="form" action="productTypeServlet?method=add" method="post">
		<!-- 账号密码提交 -->
		<div class="form-group col-md-12">
			<label id="lablel1" class="col-md-2 control-lable" for="name">分类名：</label>
				<div class="col-md-10">
					<input class="form-control" name="name" id="name" placeholder="分类名" value="${productTypeBean.name}"/>
				</div>
		</div>
		
		<div class="form-group col-md-12" id = "types">
			<label id="label1" class="col-sm-2 control-label" for="name">父类</label>
			<div class="col-sm-2">
				<select name="parentId" class="form-control" onchange="addType(this)" id="type0">
					<option value = "0">请选择父类</option>
					<c:forEach items="${productTypeList}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group col-md-12">
			<label id="label1" class="col-md-2 control-label"  for="name">分类序号：</label>
				<c:if test="${productTypeBean.sort==NULL}">
   					<div class="col-md-10">
					<input class="form-control" name="sort" type="text" id="sort" placeholder="分类序号" value="${productTypeBean.sort}"/>
					</div>    			
   				</c:if>
   				<c:if test="${productTypeBean.sort!=NULL}">
   					<label class="col-md-1 control-label for="username">${productTypeBean.sort}</label>			
   				</c:if>
				
		</div>
		<div class="form-group col-md-12">
			<label id="label1" class="col-md-2 control-label"  for="name">分类描述：</label>
				<div class="col-md-10">
					<input class="form-control" name="desc" type="text" id="desc" placeholder="分类描述" value="${productTypeBena.intro}"/>
				</div>
		</div>
		<div class="form-group col-md-12">
			<div class="col-md-offset-2 col-md-10">
				<button type="submit" id="subt" class="btn btn-primary btn-sm">提交</button>
			</div>
		</div>
		<input type="hidden" name="id" id="id" value="${productTypeBean.id}">
		<div class="form-group col-md-12">
			${param.status==false&&param.id==1?"<div class='alert alert-success' role='alert'>添加分类失败</div>":""}
			${param.status==true&&param.id==1?"<div class='alert alert-danger' role='alert'>添加分类成功</div>":""}
			${param.status==false&&param.id==2?"<div class='alert alert-success' role='alert'>修改分类失败</div>":""}
			${param.status==true&&param.id==2?"<div class='alert alert-danger' role='alert'>修改分类成功</div>":""}
		</div>
		</form>	
	</div>
</body>
</html>