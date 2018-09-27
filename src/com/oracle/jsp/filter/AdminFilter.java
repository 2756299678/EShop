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
 * ��ֹδ��¼�Ͷ�ҳ������ݽ��в���
 * ��ֱֹ�ӷ��ʺ�̨��ҳ��
 * @author BaoHui
 *
 */
public class AdminFilter  implements Filter{
	
	//����hash���͵�String�ַ���
	private Set<String> urls = new HashSet<String>();
	//�رչ����� 
	@Override
	public void destroy() {
	}
	
	//ʵ�ֹ��˹��ܣ��÷������Ƕ�ÿ��������Ӧ���ӵĶ��⴦��
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)throws
	ServletException,IOException{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// ��ȡ���ʵ�ַ
		String path = request.getServletPath();
		//�ù����Ǵ����̨����Ա��¼���ж�
		if(path.startsWith("/admin")) 
		{
			//���е�½ע��ĵ�ַ�Ź�
			if(urls.contains(path)) {
				chain.doFilter(request, response);
			}
			else{
				//�����˵�½�Ĺ���������
				//ȡsession�е�admin
				AdminBean adminBean=(AdminBean)request.getSession().getAttribute(Constants.SESSION_ADMIN_BEAN);
				if(adminBean!=null) {
					//�ѵ�¼���Ź�
					//�������ҵ�������Ź�
					chain.doFilter(request, response);
				} else {
					//û�е�¼�����ص�¼ҳ��
					response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
				}
			}
		}
		
	}
	
	//����������ʱ����һ�Σ���� Filter �ĳ�ʼ��
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		urls.add("/admin/login.jsp");
		urls.add("/admin/adminServlet");
		urls.add("/admin/addUser.jsp");
	}

}
