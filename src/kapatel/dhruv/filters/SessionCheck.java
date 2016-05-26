package kapatel.dhruv.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionCheck
 */
public class SessionCheck implements Filter {

	String loginPage;
	ArrayList<String> excludeurls = new ArrayList<String>();
	
    /**
     * Default constructor. 
     */
    public SessionCheck() {
        // TODO Auto-generated constructor stub
    }

	
    public void init(FilterConfig fConfig) throws ServletException {
    
    	loginPage = fConfig.getInitParameter("loginpage");
    	String urls = fConfig.getInitParameter("excludelist");
    	StringTokenizer token = new StringTokenizer(urls, ",");
    	
    	while(token.hasMoreTokens())
    	{
    		excludeurls.add(token.nextToken());
    	}
	}
    
    /*
     * to handle multiple exclude list
     * http://viralpatel.net/blogs/http-session-handling-tutorial-using-servlet-filters-session-error-filter-servlet-filter/
     * 
     */
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response; 
		
		String servletPath = httpServletRequest.getServletPath();
		
//		System.out.println(httpServletRequest.getServletPath());
		
		
		
			if(httpServletRequest.getSession().getAttribute("sessionAdminId") == null)
			{
				if(excludeurls.contains(servletPath))
				{
					chain.doFilter(request, response);
				}
				else
				{
					httpServletResponse.sendRedirect("login.jsp");
					return;
				}
			}
			else
			{
				if(servletPath.equals(loginPage))
				{
					httpServletResponse.sendRedirect("dashboard.jsp");
					return;
				}
				else
				{
					// pass the request along the filter chain
					chain.doFilter(request, response);
				}
			}
		
	}

	
	
	public void destroy() {
		 
	}

}
