<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <style type="text/css">
    	body{
    		margin: 0;
    		padding: 0;
    		font-size: 12px;
    		font-family: "微软雅黑",verdana,arial,helvetica,sans-serif
    	}
    	.leftMenu{
    		min-width: 220px;
    		width: 268px;
    		margin: 40px auto 0 auto;
    	}
    	.menu{
    		border:#bdd7f2 1px solid;
    		border-top: #0080c4 4px solid;
    		border-bottom: #0080c4 4px solid;
    		background:#f4f9ff repeat-y right;
    		margin-left:10px;
    	}
    	/* menu中的listtitle注意空格 */
    	.menu .ListTitle{
    		border-bottom: 1px #98c9ee solid;
    		display:block;
    		text-align: center;
    		height: 38px;
    		line-height: 38px;
    		cursor:pointer;
    		
    		+width：100%;
    	}
    	.ListTitlePanel{
    		position:relative;
    	}
    	.leftbgbt{
    		position:absolute;
    		background: no-repeat;
    		width: 11px;
    		height: 52px;
    		left:-11px;
    		top:-4px;
    	}
    	.leftbgbt2{
    		position:absolute;
    		background: no-repeat;
    		width: 11px;
    		height: 48px;
    		left:-11px;
    		top:-1px;
    	}
    	.menuList{
    		display:block;
    		height:auto;
    	}
    	/* menulist中的div注意空格 */
    	.menuList div{
    		height:28px;
    		line-height: 24px;
    		border-bottom: 1px #98c9ee dotted;
    	}
    	/* menulist中的div中的a标签 注意空格 */
    	.menuList div a{
    		display:block;
    		background: #fff;
    		line-height: 28px;
    		height:28px;
    		text-align: center;
    		color: #185697;
    		text-decoration: none;
    	}
    	/* menulist中的div中的a标签hover属性 注意空格 */
    	.menuList div a:hover{
    		color:#f30;
    		background:#0080c4;
    		color:#fff;
    	}
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.12.1.js"></script>
   	<!--  动态收缩制作 -->
    <script language="JavaScript">
	    $(document).ready(function() {
	    	var menuParent = $('.menu > .ListTitlePanel > div');//获取menu下的父层的DIV
	    	var menuList = $('.menuList');
	    	$('.menu > .menuParent > .ListTitlePanel > .ListTitle').each(function(i) {//获取列表的大标题并遍历
	    		$(this).click(function(){
	    			if($(menuList[i]).css('display') == 'none'){
	    				$(menuList[i]).slideDown(300);
	    			}
	    			else{
	    				$(menuList[i]).slideUp(300);
	    			}
	    		});
	    	});
	    });	
    	
</script>
<body style="margin-top: -30px;">
<div class="leftMenu">
	<div class="menu">
		<div class="menuParent">
			<div class="ListTitlePanel">
				<div class="ListTitle">
					<strong>管理员管理</strong>
					<div class="leftbgbt"> </div>
				</div>
			</div>
			<div class="menuList">
				<div> <a target="mainAction" href="../admin/addUser.jsp">添加管理员</a></div>
				<div> <a target="mainAction" href="${pageContext.request.contextPath}/admin/adminServlet?method=list">查看管理员信息</a> </div>
			</div>
		</div>
		<div class="menuParent">
			<div class="ListTitlePanel">
				<div class="ListTitle">
					<strong>商品分类管理</strong>
					<div class="leftbgbt2"> </div>
				</div>
			</div>
			<div class="menuList">
				<div> <a target="mainAction" href="product/type/productTypeServlet?method=toAdd">添加分类</a></div>
				<div> <a target="mainAction" href="product/type/productTypeServlet?method=list&id=0">查看分类</a></div>
			</div>
		</div>
		<div class="menuParent">
			<div class="ListTitlePanel">
				<div class="ListTitle">
					<strong>商品属性管理</strong>
					<div class="leftbgbt2"> </div>
				</div>
			</div>
			<div class="menuList">
				<div> <a target="mainAction" href="product/property/productPropertyServlet?method=toAdd">添加商品属性</a></div>
				<div> <a target="mainAction" href="product/property/productPropertyServlet?method=list">查看商品属性</a></div>
			</div>
		</div>
		<div class="menuParent">
			<div class="ListTitlePanel">
				<div class="ListTitle">
					<strong>商品属性选项管理</strong>
					<div class="leftbgbt2"> </div>
				</div>
			</div>
			<div class="menuList">
				<div> <a target="mainAction" href="product/option/productOptionServlet?method=toAdd">添加商品属性选项</a></div>
				<div> <a target="mainAction" href="product/option/productOptionServlet?method=list">查看商品属性选项</a></div>
			</div>
		</div>
		<div class="menuParent">
			<div class="ListTitlePanel">
				<div class="ListTitle">
					<strong>商品管理</strong>
					<div class="leftbgbt2"> </div>
				</div>
			</div>
			<div class="menuList">
				<div> <a target="mainAction" href="product/add.jsp">添加商品</a></div>
				<div> <a target="mainAction" href="product/productServlet?method=list">查询商品信息</a></div>
			</div>
		</div>
		<div class="menuParent">
			<div class="ListTitlePanel">
				<div class="ListTitle">
					<strong>用户管理</strong>
					<div class="leftbgbt2"> </div>
				</div>
			</div>
			<div class="menuList">
				<div> <a target="mainAction" href="${pageContext.request.contextPath}/admin/userServlet?method=list">用户信息查询</a></div>
				<div> <a target="mainAction" href="${pageContext.request.contextPath}/admin/frontUser/blockUser.jsp">冻结账号</a></div>
				<div> <a target="mainAction" href="${pageContext.request.contextPath}/admin/frontUser/userOrder.jsp">查询用户订单</a></div>
			</div>
		</div>
	</div>
</div>
<div style="text-align:center;">
</div>

</body>
</html>