package com.oracle.jsp.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jsp.bean.AdminBean;
import com.oracle.jsp.util.Constants;

/**
 * 防止未登录就对页面的数据进行操作
 * 防止直接访问后台的页面
 * @author BaoHui
 *
 */
public class AdminFilter  implements Filter{
	
	//创建hash类型的String字符串
	private Set<String> urls = new HashSet<String>();
	//关闭过滤器 
	@Override
	public void destroy() {
	}
	
	//实现过滤功能，该方法就是对每个请求及响应增加的额外处理
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)throws
	ServletException,IOException{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 获取访问地址
		String path = request.getServletPath();
		//该过滤是处理后台管理员登录的判断
		if(path.startsWith("/admin")) 
		{
			//进行登陆注册的地址放过
			if(urls.contains(path)) {
				chain.doFilter(request, response);
			}
			else{
				//进行了登陆的过滤器处理
				//取session中的admin
				AdminBean adminBean=(AdminBean)request.getSession().getAttribute(Constants.SESSION_ADMIN_BEAN);
				if(adminBean!=null) {
					//已登录，放过
					//如果符合业务规则，则放过
					chain.doFilter(request, response);
				} else {
					//没有登录，跳回登录页面
					response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
				}
			}
		}
		
	}
	
	//过滤器加载时调用一次，完成 Filter 的初始化
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		urls.add("/admin/login.jsp");
		urls.add("/admin/adminServlet");
		urls.add("/admin/addUser.jsp");
	}

}
