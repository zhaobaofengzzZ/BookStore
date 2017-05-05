package zbf.bookstore.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zbf.bookstore.domain.User;

public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		User user = (User) req.getSession().getAttribute("user");
		//PrintWriter pw = res.getWriter();
		
		if(user == null){
			res.getWriter().print("您还未登陆，请登陆后再访问该页面！");
			res.setHeader("refresh", "2;url="+req.getContextPath()+"/login.jsp");
			return;
		}
		if(!"admin".equals(user.getRole())){
			res.getWriter().print("您没有访问该页面的权限！");
			res.setHeader("refresh", "2;url="+req.getContextPath()+"/index.jsp");
			return;
		}
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
