package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ChiTietDatTourDAO;
import database.DatTourDAO;
import database.KhachHangDAO;
import database.TourDAO;
import model.ChiTietDatTour;
import model.DatTour;
import model.KhachHang;
import model.Tour;

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
		} else if ("place-order".equals(action)) {
			placeOrder(request, response);
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
						String baseURL = request.getScheme() + "://" + request.getServerName() + ":"
								+ request.getServerPort() + request.getContextPath();
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

	// Place Order
	private void placeOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String successMSG = null;
		String errorMSG = null;

		try {
			// Get parameters
			String maKH = request.getParameter("maKH");
			String maTour = request.getParameter("maTour");
			String giaVeLucBookingStr = request.getParameter("giaVeLucBooking");
			String soLuongVeDatStr = request.getParameter("soLuongVeDat");
			String tongTienStr = request.getParameter("tongTien");
			String thoiGianDatTourStr = request.getParameter("thoiGianDatTour");
			String hinhThucThanhToan = request.getParameter("hinhThucThanhToan");
			String ghiChu = request.getParameter("ghiChu");

			// Validate required fields
			if (maKH == null || maTour == null || giaVeLucBookingStr == null || soLuongVeDatStr == null
					|| tongTienStr == null || thoiGianDatTourStr == null || hinhThucThanhToan == null) {
				errorMSG = "Dữ liệu không đầy đủ. Vui lòng kiểm tra lại!";
			} else {
				long giaVeLucBooking = Long.parseLong(giaVeLucBookingStr);
				int soLuongVeDat = Integer.parseInt(soLuongVeDatStr);
				long tongTien = Long.parseLong(tongTienStr);

				// Parse thoiGianDatTour with ISO 8601 format
				Timestamp thoiGianDatTour = null;
				try {
					Instant instant = Instant.parse(thoiGianDatTourStr); // Parse ISO 8601 format
					LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
					thoiGianDatTour = Timestamp.valueOf(localDateTime);
				} catch (Exception e) {
					errorMSG = "Thời gian đặt tour không đúng định dạng ISO 8601. Vui lòng nhập theo chuẩn ISO.";
				}

				if (thoiGianDatTour != null) {
					// Fetch customer
					KhachHang kh = new KhachHang();
					kh.setMaKH(maKH);
					KhachHangDAO khDAO = new KhachHangDAO();
					kh = khDAO.selectById(kh);

					// Fetch tour
					Tour tour = new Tour();
					tour.setMaTour(maTour);
					TourDAO tourDAO = new TourDAO();
					tour = tourDAO.selectById(tour);

					if (kh == null || tour == null) {
						errorMSG = "Không tìm thấy thông tin khách hàng hoặc tour. Vui lòng thử lại!";
					} else {
						// Check ticket availability
						ChiTietDatTourDAO ctdtDAO = new ChiTietDatTourDAO();
						int totalSoLuongVeDaDat = ctdtDAO.getTotalSoLuongVeDatByMaTour(maTour);
						int soLuongVeToiDa = tour.getSoLuongVeToiDa();
						int soLuongVeConLai = soLuongVeToiDa - totalSoLuongVeDaDat;

						if (soLuongVeConLai < soLuongVeDat) {
							errorMSG = "Số lượng vé đặt vượt quá số lượng vé hiện có.";
						} else {
							// Insert DatTour
							DatTourDAO datTourDAO = new DatTourDAO();
							String maDatTour = datTourDAO.generateNewMaDatTour();
							String trangThaiDatTour = "pending";
							DatTour datTour = new DatTour(maDatTour, kh, thoiGianDatTour, hinhThucThanhToan, ghiChu,
									trangThaiDatTour);
							int isDatTourInserted = datTourDAO.insert(datTour);

							if (isDatTourInserted == 0) {
								errorMSG = "Không thể tạo đơn đặt tour. Vui lòng thử lại!";
							} else {
								// Insert ChiTietDatTour
								String maChiTietDatTour = ctdtDAO.generateNewMaChiTietDatTour();
								ChiTietDatTour ctdt = new ChiTietDatTour(maChiTietDatTour, tour, datTour,
										giaVeLucBooking, soLuongVeDat, tongTien);
								int isCTDTInserted = ctdtDAO.insert(ctdt);

								if (isCTDTInserted == 0) {
									errorMSG = "Không thể thêm chi tiết đặt tour. Vui lòng thử lại!";
								} else {
									// Update ticket availability
									int isTourUpdated = tourDAO.updateSoLuongVeHienCo(maTour,
											soLuongVeConLai - soLuongVeDat);

									if (isTourUpdated == 1) {
										successMSG = "Đặt tour thành công!";
									} else {
										errorMSG = "Không thể cập nhật số lượng vé hiện có. Vui lòng thử lại!";
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMSG = "Đã xảy ra lỗi trong quá trình đặt tour. Chi tiết: " + e.getMessage();
		}

		// Set messages and forward to the view
		if (successMSG != null) {
			request.setAttribute("successMSG", successMSG);
		}
		if (errorMSG != null) {
			request.setAttribute("errorMSG", errorMSG);
		}

		String url = "/userView/placeOrder.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
