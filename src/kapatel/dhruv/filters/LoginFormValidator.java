package kapatel.dhruv.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import kapatel.dhruv.ErrorMessages;



/**
 * Servlet Filter implementation class LoginFormValidatior
 */
public class LoginFormValidator implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFormValidator() {
       
    }

    public void init(FilterConfig fConfig) throws ServletException {
		
	}
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(request.getParameter("login") != null)
		{
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ArrayList<String> errorMsg = new ArrayList<String> ();
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			
			if(username.equals(""))
			{
				errorMsg.add(ErrorMessages.USERNAME_REQUIRED);
			}
			else if(!(Pattern.matches("^([\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4})?",username)))
			{
				errorMsg.add(ErrorMessages.INVALID_USERNAME);
			}
				
			if(password.equals(""))
			{
				errorMsg.add(ErrorMessages.PASSWORD_REQUIRED);
			}
			
			if(errorMsg.size() == 0)
			{
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
			else
			{
				request.setAttribute("errorMsg",errorMsg);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		else
		{
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendRedirect("login.jsp");
			return;
		}
	}
	
	
	public void destroy() {
		
	}

	

	

}
