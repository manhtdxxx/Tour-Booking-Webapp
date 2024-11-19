package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/change-password")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		String url = "";
		String error = "";
		String success = "";

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("khachHang");
		KhachHang khachHang = null;
		if (obj != null) {
			khachHang = (KhachHang) obj;
		}

		if (khachHang == null) {
			error = "Bạn chưa đăng nhập vào hệ thống!";
			url = "/changePassword.jsp";
		} else {
			if (!currentPassword.equals(khachHang.getPassword())) {
				error = "Mật khẩu hiện tại không chính xác. Vui lòng nhập lại!";
				url = "/changePassword.jsp";
			} else {
				if (!newPassword.equals(confirmPassword)) {
					error = "Mật khẩu nhập lại không khớp. Vui lòng nhập lại!";
					url = "/changePassword.jsp";
				} else {
					khachHang.setPassword(newPassword);
					KhachHangDAO khDAO = new KhachHangDAO();
					if (khDAO.changePassword(khachHang) == 1) {
						// Invalidate the session after password change
						session.invalidate();
						// Start a new session to display the message
						session = request.getSession(true);
						session.setAttribute("success", "Thay đổi mật khẩu thành công. Vui lòng đăng nhập lại!");
						// Redirect to the login page
						url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath();
						response.sendRedirect(url + "/login.jsp");
						return;
					} else {
						error = "Có lỗi xảy ra trong quá trình thay đổi mật khẩu. Vui lòng thử lại!";
						url = "/changePassword.jsp";
					}
				}
			}
		}

		request.setAttribute("error", error);
		request.setAttribute("success", success);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
