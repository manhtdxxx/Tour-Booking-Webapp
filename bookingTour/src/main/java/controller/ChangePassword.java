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
		// TODO Auto-generated method stub
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		String error = "";
		String success = "";
		String url = "";

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("khachHang");
		KhachHang khachHang = null;

		if (obj != null) {
			khachHang = (KhachHang) obj;
		}

		if (khachHang == null) {
			error = "Bạn chưa đăng nhập vào hệ thống!";
			url = "/changepassword.jsp";
		} else {
			if (!currentPassword.equals(khachHang.getPassword())) {
				error = "Mật khẩu hiện tại không chính xác!";
				url = "/changepassword.jsp";
			} else {
				if (!newPassword.equals(confirmPassword)) {
					error = "Mật khẩu nhập lại không khớp!";
					url = "/changepassword.jsp";
				} else {
					khachHang.setPassword(newPassword);

					KhachHangDAO kh_dao = new KhachHangDAO();
					if (kh_dao.changePassword(khachHang) == 1) {
						session.invalidate(); // Invalidate the session after password change
						
						session = request.getSession(true); // Start a new session to display the message
						session.setAttribute("success", "Thay đổi mật khẩu thành công! Vui lòng đăng nhập lại.");

						url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath();
						response.sendRedirect(url + "/login.jsp"); // Redirect to the login page
						return;
					} else {
						error = "Quá trình thay đổi mật khẩu không thành công!";
						url = "/changepassword.jsp";
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
