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
 * Servlet implementation class UpdateCustomer
 */
@WebServlet("/update-customer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCustomer() {
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

		String maKH = request.getParameter("makh");
		String hoten = request.getParameter("hoten");
		String diachi = request.getParameter("diachi");
		String email = request.getParameter("email");
		String dienthoai = request.getParameter("dienthoai");

		KhachHang kh = new KhachHang(maKH, hoten, diachi, email, dienthoai);

		KhachHangDAO kh_dao = new KhachHangDAO();
		int isUpdated = kh_dao.update(kh);

		if (isUpdated == 1) {
			response.sendRedirect("index.jsp");
		} else if (isUpdated == 0) {
			response.getWriter().println("Error: Unable to update customer!");
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
