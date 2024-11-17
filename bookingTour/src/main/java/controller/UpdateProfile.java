package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/update-profile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfile() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String maKH = request.getParameter("maKH");
		String name = request.getParameter("customerName");
		String gender = request.getParameter("gender");
		String dobStr = request.getParameter("dob");
		String phone = request.getParameter("phoneNumber");
		String email = request.getParameter("email");

		Date dob = dobStr != null && !dobStr.isEmpty() ? Date.valueOf(dobStr) : null;

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
				request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại.");
			}

		} else {
			request.setAttribute("error", "Không tìm thấy thông tin khách hàng.");
		}
		request.getRequestDispatcher("/updateProfile.jsp").forward(request, response);
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
