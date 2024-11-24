package controller;

import java.io.IOException;
import java.sql.Date;

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
 * Servlet implementation class KhachHangController
 */
@WebServlet("/khach-hang")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KhachHangController() {
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

		String action = request.getParameter("action");
		if ("update-profile".equals(action)) {
			updateProfile(request, response);
		} else if ("change-password".equals(action)) {
			changePassword(request, response);
		}
	}

	// Update Profile
	private void updateProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maKH = request.getParameter("maKH");
		String name = request.getParameter("customerName");
		String gender = request.getParameter("gender");
		String dobStr = request.getParameter("dob");
		Date dob = dobStr != null && !dobStr.isEmpty() ? Date.valueOf(dobStr) : null;
		String phone = request.getParameter("phoneNumber");
		String email = request.getParameter("email");

		HttpSession session = request.getSession();
		KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");

		if (khachHang != null && khachHang.getMaKH().equals(maKH)) {
			khachHang.setTenKH(name);
			khachHang.setGioiTinh(gender);
			khachHang.setNgaySinh(dob);
			khachHang.setSoDienThoai(phone);
			khachHang.setEmail(email);

			boolean isUpdated = new KhachHangDAO().updateInfo(khachHang);
			if (isUpdated) {
				// Update successful - store the updated object in the session
				session.setAttribute("khachHang", khachHang);
				request.setAttribute("success", "Cập nhật thông tin thành công!");
			} else {
				request.setAttribute("error", "Có lỗi xảy ra trong quá trình cập nhật thông tin. vui lòng thử lại.");
			}

		} else {
			request.setAttribute("error", "Không tìm thấy thông tin khách hàng.");
		}

		String url = "/userView/updateProfile.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// Change Password
	private void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		String url = "/userView";
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
			url += "/changePassword.jsp";
		} else {
			if (!currentPassword.equals(khachHang.getPassword())) {
				error = "Mật khẩu hiện tại không chính xác. Vui lòng nhập lại!";
				url += "/changePassword.jsp";
			} else {
				if (!newPassword.equals(confirmPassword)) {
					error = "Mật khẩu nhập lại không khớp. Vui lòng nhập lại!";
					url += "/changePassword.jsp";
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
						String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath();
						response.sendRedirect(baseURL + "/login.jsp");
						return;
					} else {
						error = "Có lỗi xảy ra trong quá trình thay đổi mật khẩu. Vui lòng thử lại!";
						url += "/changePassword.jsp";
					}
				}
			}
		}

		request.setAttribute("error", error);
		request.setAttribute("success", success);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
