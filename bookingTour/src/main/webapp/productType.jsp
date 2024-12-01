<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="database.LoaiTourDAO"%>
<%@page import="model.LoaiTour"%>
<%@page import="database.TourDAO"%>
<%@page import="model.Tour"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>

<%
String maLoaiTour = request.getParameter("maLoaiTour");
LoaiTour loaiTour = new LoaiTour();
loaiTour.setMaLoaiTour(maLoaiTour);

LoaiTourDAO loaiTourDAO = new LoaiTourDAO();
loaiTour = loaiTourDAO.selectById(loaiTour);
%>

<!DOCTYPE html>
<html>
<head>
<title><%=loaiTour.getTenLoaiTour()%></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<!-- NAV -->
	<jsp:include page="layout/nav.jsp"></jsp:include>

	<%
	String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();

	SimpleDateFormat dateFormatterDate = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatterTime = new SimpleDateFormat("HH:mm");
	Locale vietnamLocale = Locale.of("vi", "VN");
	NumberFormat currencyFormatter = NumberFormat.getInstance(vietnamLocale);

	String category = request.getParameter("category");
	String pageParam = request.getParameter("page");
	int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
	int pageSize = 6;
	int offset = (currentPage - 1) * pageSize;

	TourDAO tourDAO = new TourDAO();
	ArrayList<Tour> tourList = new ArrayList<>();

	int totalRecords = 0;
	int totalPages;
	long maxPrice = 3000000;
	long minPrice = 5000000;
	int minSoLuongVeDat = 5;

	if (category == null || category.equals("all")) {
		tourList = tourDAO.selectAllTours(maLoaiTour, pageSize, offset);
		totalRecords = tourDAO.countAllTours(maLoaiTour);
	} else if (category.equals("short")) {
		tourList = tourDAO.selectShortTours(maLoaiTour, pageSize, offset);
		totalRecords = tourDAO.countShortTours(maLoaiTour);
	} else if (category.equals("extended")) {
		tourList = tourDAO.selectExtendedTours(maLoaiTour, pageSize, offset);
		totalRecords = tourDAO.countExtendedTours(maLoaiTour);
	} else if (category.equals("cheap")) {
		tourList = tourDAO.selectCheapTours(maLoaiTour, maxPrice, pageSize, offset);
		totalRecords = tourDAO.countCheapTours(maLoaiTour, maxPrice);
	} else if (category.equals("premium")) {
		tourList = tourDAO.selectPremiumTours(maLoaiTour, minPrice, pageSize, offset);
		totalRecords = tourDAO.countPremiumTours(maLoaiTour, minPrice);
	} else if (category.equals("hot")) {
		tourList = tourDAO.selectHotTours(maLoaiTour, minSoLuongVeDat, pageSize, offset);
		totalRecords = tourDAO.countHotTours(maLoaiTour, minSoLuongVeDat);
	} else if (category.equals("discount")) {
		tourList = tourDAO.selectDiscountedTours(maLoaiTour, pageSize, offset);
		totalRecords = tourDAO.countDiscountedTours(maLoaiTour);
	}

	totalPages = (int) Math.ceil((double) totalRecords / pageSize);
	%>

	<div class="container mt-4">

		<div class="row">

			<!-- List Group on the Left -->
			<div class="col-md-3">
				<div class="list-group">
					<a href="?maLoaiTour=<%=maLoaiTour%>&category=all"
						class="list-group-item list-group-item-action <%=(category == null || category.equals("all")) ? "active" : ""%>">
						All Tours </a> <a href="?maLoaiTour=<%=maLoaiTour%>&category=cheap"
						class="list-group-item list-group-item-action <%="cheap".equals(category) ? "active" : ""%>">
						Budget-Friendly Tours </a> <a href="?maLoaiTour=<%=maLoaiTour%>&category=premium"
						class="list-group-item list-group-item-action <%="premium".equals(category) ? "active" : ""%>">
						Premium Tours </a> <a href="?maLoaiTour=<%=maLoaiTour%>&category=short"
						class="list-group-item list-group-item-action <%="short".equals(category) ? "active" : ""%>">Short
						Tours (1-3 Days)</a> <a href="?maLoaiTour=<%=maLoaiTour%>&category=extended"
						class="list-group-item list-group-item-action <%="extended".equals(category) ? "active" : ""%>">Extended
						Tours (4+ Days)</a> <a href="?maLoaiTour=<%=maLoaiTour%>&category=hot"
						class="list-group-item list-group-item-action <%="hot".equals(category) ? "active" : ""%>">Most
						Booked Tours</a> <a href="?maLoaiTour=<%=maLoaiTour%>&category=discount"
						class="list-group-item list-group-item-action <%="discount".equals(category) ? "active" : ""%>">Most
						Discounted Tours</a>
				</div>
			</div>

			<!-- Product Cards on the Right -->
			<div class="col-md-9">

				<div class="row">

					<%
					if (tourList.isEmpty()) {
					%>
					<div class="col-12 vh-100">
						<div class="alert alert-warning text-center">No tours available for this
							category.</div>
					</div>
					<%
					} else {
					%>
					<%
					for (Tour tour : tourList) {
					%>

					<div class="col-md-4">
						<div class="card mb-4">
							<img
								src="<%=tour.getFileName() != null ? baseURL + "/images/" + tour.getFileName() : "https://via.placeholder.com/150"%>"
								class="card-img-top" alt="<%=tour.getTenTour()%>">

							<div class="card-body">

								<!-- Display Tour Name -->
								<h5 class="card-title"><%=tour.getTenTour()%></h5>

								<!-- Display Start Date and End Date -->
								<p class="card-text">
									<strong>Start Date:</strong>
									<%=dateFormatterDate.format(tour.getThoiGianXuatPhat())%>
									<br> <strong>End Date:</strong>
									<%=dateFormatterDate.format(tour.getThoiGianKetThuc())%>
								</p>

								<!-- Display Available Seats -->
								<!-- Display Current Price -->
								<p class="card-text">
									<strong>Available Seats:</strong>
									<%=tour.getSoLuongVeHienCo()%>
									/
									<%=tour.getSoLuongVeToiDa()%>
									<br> <strong>Current Price:</strong>
									<%=currencyFormatter.format(tour.getGiaVeHienTai())%>
									VNĐ
								</p>

								<!-- Button to View Details -->
								<a href="productDetails.jsp?maTour=<%=tour.getMaTour()%>" class="btn btn-primary">View
									Details</a>
							</div>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>

				<!-- Pagination -->
				<div class="d-flex justify-content-center">
					<nav aria-label="page navigation">

						<!-- Previous Button -->
						<ul class="pagination">
							<li class="page-item <%=(currentPage == 1) ? "disabled" : ""%>"><a
								class="page-link"
								href="?maLoaiTour=<%=maLoaiTour%>&category=<%=category%>&page=<%=currentPage - 1%>">
									<i class="bi bi-arrow-left-circle me-2"></i>Previous
							</a></li>

							<!-- Page Numbers -->
							<%
							for (int i = 1; i <= totalPages; i++) {
							%>
							<li class="page-item <%=(i == currentPage) ? "active" : ""%>"><a
								class="page-link"
								href="?maLoaiTour=<%=maLoaiTour%>&category=<%=category%>&page=<%=i%>"><%=i%></a></li>
							<%
							}
							%>

							<!-- Next Button -->
							<li class="page-item <%=(currentPage == totalPages) ? "disabled" : ""%>"><a
								class="page-link"
								href="?maLoaiTour=<%=maLoaiTour%>&category=<%=category%>&page=<%=currentPage + 1%>">
									Next<i class="bi bi-arrow-right-circle ms-2"></i>
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>

	<!-- FOOTER -->
	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
