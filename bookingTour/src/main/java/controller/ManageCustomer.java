package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class ManageCustomer
 */
@WebServlet("/manage-customer")
public class ManageCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageCustomer() {
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

		String action = request.getParameter("action");
		String currentPage = request.getParameter("page");
		String sortColumn = request.getParameter("sortColumn");
		String sortDirection = request.getParameter("sortDirection");
		String rawSearchQuery = request.getParameter("searchQuery");
		String searchQuery = (rawSearchQuery == null || "null".equals(rawSearchQuery)) ? "" : rawSearchQuery.trim();
		searchQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);

		if ("delete".equals(action)) {
			deleteCustomer(request, response, currentPage, sortColumn, sortDirection, searchQuery);
		} else if ("toggleStatus".equals(action)) {
			toggleCustomerStatus(request, response, currentPage, sortColumn, sortDirection, searchQuery);
		}
	}

	// Delete Customer
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String sortColumn, String sortDirection, String searchQuery) throws ServletException, IOException {
		String maKH = request.getParameter("maKH");
		KhachHang kh = new KhachHang();
		kh.setMaKH(maKH);

		KhachHangDAO khDAO = new KhachHangDAO();
		kh = khDAO.selectById(kh);

		int isDeleted = 0;
		if (kh != null) {
			isDeleted = khDAO.delete(kh);
		}

		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String url = "/adminView/manageCustomer.jsp?" + "searchQuery=" + searchQuery + "&sortColumn=" + sortColumn
				+ "&sortDirection=" + sortDirection + "&page=" + currentPage + "&isDeleted=" + String.valueOf(isDeleted)
				+ "&maKH=" + kh.getMaKH();
		;
		response.sendRedirect(baseURL + url);
	}

	// Toggle Customer Status
	private void toggleCustomerStatus(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String sortColumn, String sortDirection, String searchQuery) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String maKH = request.getParameter("maKH");
		String oldStatus = request.getParameter("status");
		String newStatus = oldStatus;

		KhachHang kh = new KhachHang();
		kh.setMaKH(maKH);
		KhachHangDAO khDAO = new KhachHangDAO();
		kh = khDAO.selectById(kh);

		boolean isStatusUpdated = false;
		if (kh != null) {
			isStatusUpdated = khDAO.toggleCustomerStatus(kh);
			if (isStatusUpdated) {
				newStatus = kh.getStatus();
			}
		}

		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String url = "/adminView/manageCustomer.jsp?" + "searchQuery=" + searchQuery + "&sortColumn=" + sortColumn
				+ "&sortDirection=" + sortDirection + "&page=" + currentPage + "&isStatusUpdated=" + isStatusUpdated
				+ "&newStatus=" + newStatus + "&maKH=" + kh.getMaKH();
		response.sendRedirect(baseURL + url);
	}
}
