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
import database.NhanVienDAO;
import model.KhachHang;
import model.NhanVien;

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("remember");

		KhachHang current_kh = new KhachHang();
		current_kh.setUsername(username);
		current_kh.setPassword(password);

		KhachHangDAO khDAO = new KhachHangDAO();
		KhachHang existed_kh = khDAO.selectByUsernameAndPassword(current_kh);

		NhanVien current_nv = new NhanVien();
		current_nv.setUsername(username);
		current_nv.setPassword(password);

		NhanVienDAO nvDAO = new NhanVienDAO();
		NhanVien existed_nv = nvDAO.selectByUsernameAndPassword(username, password);

		String url = "";

		if (existed_kh != null) {
			if ("active".equals(existed_kh.getStatus())) {
				HttpSession session = request.getSession();
				session.setAttribute("khachHang", existed_kh);
				url = "/index.jsp";

				// Handle "Remember Me" functionality for KhachHang
				handleRememberMe(username, password, rememberMe, response);

			} else {
				request.setAttribute("error",
						"Tài khoản của bạn hiện đang không hoạt động. Vui lòng liên hệ quản trị viên!");
				url = "/login.jsp";
			}
		} else if (existed_nv != null) {
			HttpSession session = request.getSession();
			session.setAttribute("nhanVien", existed_nv);
			url = "/adminView/adminHome.jsp";

			// Handle "Remember Me" functionality for NhanVien
			handleRememberMe(username, password, rememberMe, response);

		} else {
			request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng nhập lại!");
			url = "/login.jsp";
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// Method to handle "Remember Me" functionality
	private void handleRememberMe(String username, String password, String rememberMe, HttpServletResponse response) {
		if ("on".equals(rememberMe)) {
			Cookie usernameCookie = new Cookie("username", username);
			Cookie passwordCookie = new Cookie("password", password);
			usernameCookie.setMaxAge(1 * 24 * 60 * 60); // 1 day
			passwordCookie.setMaxAge(1 * 24 * 60 * 60); // 1 day
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);
		} else {
			Cookie usernameCookie = new Cookie("username", "");
			Cookie passwordCookie = new Cookie("password", "");
			usernameCookie.setMaxAge(0); // Expire the cookie
			passwordCookie.setMaxAge(0); // Expire the cookie
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);
		}
	}
}
