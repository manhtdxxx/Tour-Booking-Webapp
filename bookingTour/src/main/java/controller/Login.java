package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class Login
 */
@WebServlet("/do-login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("remember");

		KhachHang current_kh = new KhachHang();
		current_kh.setUsername(username);
		current_kh.setPassword(password);

		KhachHangDAO kh_dao = new KhachHangDAO();
		KhachHang existed_kh = kh_dao.selectByUsernameAndPassword(current_kh);

		String url = "";

		if (existed_kh != null) {
			HttpSession session = request.getSession();
			session.setAttribute("khachHang", existed_kh);
			url = "/index.jsp";

			// Handle "Remember Me" functionality
			if ("on".equals(rememberMe)) {
				// Set cookies for username and password
				Cookie usernameCookie = new Cookie("username", username);
				Cookie passwordCookie = new Cookie("password", password);
				usernameCookie.setMaxAge(1 * 24 * 60 * 60); // 1 day
				passwordCookie.setMaxAge(1 * 24 * 60 * 60); // 1 day
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
			} else {
				// If "remember me" is not selected, clear any existing cookies
				Cookie usernameCookie = new Cookie("username", "");
				Cookie passwordCookie = new Cookie("password", "");
				usernameCookie.setMaxAge(0); // Expire the cookie
				passwordCookie.setMaxAge(0); // Expire the cookie
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
			}
		} else {
			request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng! Vui lòng nhập lại.");
			url = "/login.jsp";
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
