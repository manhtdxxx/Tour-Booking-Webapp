package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class AddCustomer
 */
@WebServlet("/add-customer")
public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public AddCustomer() {
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

		String makh = request.getParameter("makh");
		String hoten = request.getParameter("hoten");
		String diachi = request.getParameter("diachi");
		String email = request.getParameter("email");
		String dienthoai = request.getParameter("dienthoai");

		KhachHangDAO kh_dao = new KhachHangDAO();
		KhachHang kh = new KhachHang();
		kh.setMaKH(makh);
		kh = kh_dao.selectById(kh);

		if (kh != null) {
			// Customer ID already exists, show error message
			request.setAttribute("error", "Mã khách hàng đã tồn tại. Vui lòng sử dụng mã khách hàng khác.");
			request.getRequestDispatcher("add.jsp").forward(request, response);
		} else {
			// Customer ID does not exist, proceed to insert
			KhachHang new_kh = new KhachHang(makh, hoten, diachi, email, dienthoai);
			kh_dao.insert(new_kh);
			response.sendRedirect("index.jsp");
		}
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
