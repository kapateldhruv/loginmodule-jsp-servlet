package kapatel.dhruv;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kapatel.dhruv.dao.AdminDao;

/**
 * Servlet implementation class AdminLoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AdminDao admindao = new AdminDao();
		int dbId = admindao.authenticateAdmin(username, password);
		
		ArrayList<String> errorMsg = new ArrayList<String> ();
		
		if(dbId == -1)
		{
//			wrong username and password
			errorMsg.add(ErrorMessages.USERNAME_OR_PASSWORD_INVALID);
			
			request.setAttribute("errorMsg",errorMsg);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if(dbId == 0)
		{
			errorMsg.add(ErrorMessages.USERNAME_OR_PASSWORD_INVALID);
			request.setAttribute("errorMsg",errorMsg);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("sessionAdminId",dbId);
			//request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			response.sendRedirect("dashboard.jsp");
			return;
		}
		
	}
	

}
