package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.LoaiTourDAO;
import database.TourDAO;
import model.LoaiTour;
import model.Tour;

/**
 * Servlet implementation class ManageProduct
 */
@WebServlet("/manage-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)

public class ManageProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			deleteProduct(request, response, currentPage, sortColumn, sortDirection, searchQuery);
		} else if ("add".equals(action)) {
			addProduct(request, response);
		}
	}

	// Delete tour
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String sortColumn, String sortDirection, String searchQuery) throws ServletException, IOException {
		String maTour = request.getParameter("maTour");
		Tour tour = new Tour();
		tour.setMaTour(maTour);
		;

		TourDAO tourDAO = new TourDAO();
		tour = tourDAO.selectById(tour);

		int isDeleted = 0;
		if (tour != null) {
			isDeleted = tourDAO.delete(tour);
		}

		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String url = "/adminView/manageProduct.jsp?" + "searchQuery=" + searchQuery + "&sortColumn=" + sortColumn
				+ "&sortDirection=" + sortDirection + "&page=" + currentPage + "&isDeleted=" + String.valueOf(isDeleted)
				+ "&maTour=" + tour.getMaTour();
		;
		response.sendRedirect(baseURL + url);
	}

	// Add tour
	private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tenTour = request.getParameter("tenTour");
		String maLoaiTour = request.getParameter("maLoaiTour");
		String diemXuatPhat = request.getParameter("diemXuatPhat");
		String diemKetThuc = request.getParameter("diemKetThuc");
		String phuongTienDiChuyen = request.getParameter("phuongTienDiChuyen");
		String moTa = request.getParameter("moTa");
		int soLuongVeToiDa = Integer.parseInt(request.getParameter("soLuongVeToiDa"));
		long giaVeHienTai = Long.parseLong(request.getParameter("giaVeHienTai"));

		int soLuongVeHienCo = soLuongVeToiDa;
		long giaVeLucTruoc = giaVeHienTai;

		String rawThoiGianXuatPhat = request.getParameter("thoiGianXuatPhat");
		String rawThoiGianKetThuc = request.getParameter("thoiGianKetThuc");
		Timestamp thoiGianXuatPhat = Timestamp.valueOf(rawThoiGianXuatPhat.replace("T", " ") + ":00");
		Timestamp thoiGianKetThuc = Timestamp.valueOf(rawThoiGianKetThuc.replace("T", " ") + ":00");

		LoaiTour loaiTour = new LoaiTour();
		loaiTour.setMaLoaiTour(maLoaiTour);
		LoaiTourDAO loaiTourDAO = new LoaiTourDAO();
		loaiTour = loaiTourDAO.selectById(loaiTour);

		TourDAO tourDAO = new TourDAO();
		String maTour = tourDAO.generateNewMaTour();

		request.setAttribute("tenTour", tenTour);
		request.setAttribute("maLoaiTour", maLoaiTour);
		request.setAttribute("diemXuatPhat", diemXuatPhat);
		request.setAttribute("diemKetThuc", diemKetThuc);
		request.setAttribute("phuongTienDiChuyen", phuongTienDiChuyen);
		request.setAttribute("moTa", moTa);
		request.setAttribute("soLuongVeToiDa", soLuongVeToiDa);
		request.setAttribute("giaVeHienTai", giaVeHienTai);
		request.setAttribute("thoiGianXuatPhat", rawThoiGianXuatPhat);
		request.setAttribute("thoiGianKetThuc", rawThoiGianKetThuc);

		// Get input type "file"
		Part file = request.getPart("file");
		String fileName = file.getSubmittedFileName();

		// Validate file type (ensure it's an image)
		String contentType = file.getContentType();
		if (!contentType.startsWith("image/")) {
			request.setAttribute("error_message", "File không phải là hình ảnh. Vui lòng chọn file hình ảnh.");
			RequestDispatcher rd = request.getRequestDispatcher("/adminView/addProduct.jsp");
			rd.forward(request, response);
			return;
		}

		// Validate file name (prevent directory traversal attacks)
		if (fileName != null) {
			fileName = fileName.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
		}

		// Check if either tenTour or fileName exists in the database
		if (tourDAO.tourExists(tenTour, fileName)) {
			request.setAttribute("error_message", "Tên tour hoặc tên file đã tồn tại. Vui lòng nhập lại!");
			RequestDispatcher rd = request.getRequestDispatcher("/adminView/addProduct.jsp");
			rd.forward(request, response);
			return;
		}

		// Get the real path to the images folder (this is the path during runtime)
		File realPath = new File(request.getServletContext().getRealPath("/images"));
		// C:\Users\manh\Documents\ECLIPSE_WORKSPACE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\PROJECT_NAME\images

		File workspacePath = realPath.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile()
				.getParentFile().getParentFile();
		// C:\Users\manh\Documents\ECLIPSE_WORKSPACE

		String projectName = request.getServletContext().getContextPath().substring(1);
		// getContextPath() returns /projectName then using substring(1) to eliminate /

		String imageDir = "images";

		String uploadPath = workspacePath.getAbsolutePath() + File.separator + projectName + File.separator + "src"
				+ File.separator + "main" + File.separator + "webapp" + File.separator + imageDir;
		// C:\Users\manh\Documents\ECLIPSE_WORKSPACE\PROJECT_NAME\src\main\webapp\imageDir

		// Ensure the directory exists
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		// Save the file to the directory
		try {
			file.write(uploadPath + File.separator + fileName);
		} catch (IOException e) {
			request.setAttribute("error_message", "Lỗi khi lưu file. Vui lòng thử lại.");
			RequestDispatcher rd = request.getRequestDispatcher("/adminView/addProduct.jsp");
			rd.forward(request, response);
			return;
		}

		Tour tour = new Tour(maTour, loaiTour, tenTour, diemXuatPhat, diemKetThuc, phuongTienDiChuyen, thoiGianXuatPhat,
				thoiGianKetThuc, giaVeHienTai, giaVeLucTruoc, soLuongVeToiDa, soLuongVeHienCo, moTa, fileName);
		int isInserted = tourDAO.insert(tour);

		if (isInserted == 1) {
			request.setAttribute("success_message", "Tour đã được thêm thành công.");
		} else {
			request.setAttribute("error_message", "Có lỗi khi thêm tour. Vui lòng thử lại.");
		}

		String url = "/adminView/addProduct.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
