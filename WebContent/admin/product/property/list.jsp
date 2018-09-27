<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品属性查询</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
	<!--导入jquery.js-->
	   <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" ></script>	
	<!--导入bootstrap.js-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script>
	<script type="text/javascript">
	function chType(obj){
		$(obj).parent().nextAll().remove();
		var id = obj.value;
		if(id>0){
			//ajax请求
			$.post("../type/productTypeServlet",{
				method : "getType",
				id : id
			},
			function(data){
				if(data != null&& data.length > 0){
					var content = "<div class ='col-sm-2'><select name='parentId' "+
					"class ='form-control' onchange='chType(this)' id='type0'><option value='0'>--请选择父类--</option>";
					for(var type in data){
						content +="<option value='"+data[type].id+"'>"+data[type].name+"</option>";
					}
					content +="</select></div>";
					$("#types").append(content);
				}
			},"json"
			);
		}
		showProperty(obj);
	}
	function showProperty(obj){
		var id=obj.value;
		$.post("../property/productPropertyServlet",{
			method : "getProperty",
			id : id
		},
		function(data){
			if(data!=null&& data.length>0){
				var content = "";
				content += "<tr><td>id</td>";
				content += "<td>属性名称</td>";
				content += "<td>排序</td>";
				content += "<td>所属分类id</td>";
				content += "<td>创建时间</td>";
				content += "<td>操作</td>";
				content += "<td>操作</td>";
				for(var property in data){
					content +="<tr><td>"+data[property].id+"</td>";
					content +="<td>"+data[property].name+"</td>";
					content +="<td>"+data[property].sort+"</td>";
					content +="<td>"+data[property].productTypeId+"</td>";
					content +="<td>"+data[property].createDate+"</td>";
					content +="<td><a href='productPropertyServlet?method=update&id="+data[property].id+"'>修改</a></td>";
					content +="<td><a href='productPropertyServlet?method=delete&id="+data[property].id+"'>删除</a></td></tr>";
					}
				document.getElementById("property0").innerHTML = content;
			}
		},"json"
		);
	}
	</script> 
</head>
<body>
<div class = "container">
<h1>商品属性查询</h1>
<div class="form-group col-md-12" id ="types">
	<label id="label1" class="col-sm-12 control-lable" for="name">分类：</label>
		<div class="col-sm-2">
		<select name="parentId" class="form-control" onchange="chType(this)" id="type0">
			<option value="0">--请选择分类--</option>
			<c:forEach items="${productTypeList}" var = "item">
				<option value="${item.id}">${item.name}</option>
			</c:forEach>
		</select>
		</div>
</div>
<table class="table table-striped" id="property0">
</table>
<div class="form-group col-md-12">
${param.status==1?"<div class='alert alert-success' role = 'alert'>修改属性成功</div>":"" }
</div>
<c:if test="${param.status.equals('2')}">
	<div class="alert alert-info" role="alert">删除属性成功</div>
</c:if>
</div>

</body>
</html>