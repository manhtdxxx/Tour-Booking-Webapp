<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="database.LoaiTourDAO"%>
<%@page import="model.LoaiTour"%>
<%@page import="database.TourDAO"%>
<%@page import="model.Tour"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>

<%
String maTour = request.getParameter("maTour");
Tour tour = new Tour();
tour.setMaTour(maTour);

TourDAO tourDAO = new TourDAO();
tour = tourDAO.selectById(tour);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=tour.getTenTour()%></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
body {
	background: linear-gradient(to bottom right, #f9f9f9, #e3f2fd);
	color: #343a40;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

h1 {
	color: #007bff;
	font-weight: 700;
	font-size: 3rem; /* Increased size */
	text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
}

.container {
	background: #ffffff;
	border-radius: 15px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	padding: 20px;
	transition: transform 0.3s;
}

.container:hover {
	transform: scale(1.02);
}

img {
	border-radius: 15px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

p {
	font-size: 1.2rem; /* Slightly larger size */
	margin-bottom: 1rem;
}

strong {
	color: #0056b3;
	font-weight: bold; /* Made text bolder */
	font-size: 1.2rem; /* Increased size */
}

.text-decoration-line-through {
	color: #dc3545;
	font-size: 1.1rem; /* Increased size */
}

.btn-order {
	background-color: #28a745;
	color: #ffffff;
	font-size: 1.3rem; /* Slightly larger */
	padding: 12px 24px; /* Larger padding */
	border-radius: 50px;
	border: none;
	transition: background-color 0.3s ease, transform 0.3s ease;
}

.btn-order:hover {
	background-color: #218838;
	transform: scale(1.05);
}

.btn-order:active {
	transform: scale(1);
}
</style>
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
	%>

	<div class="container mt-4 vh-100 d-flex justify-content-center align-items-center"
		style="max-width: 1200px;">
		<div class="row w-100 d-flex align-items-stretch">

			<!-- IMAGE -->
			<div class="col-md-6 d-flex justify-content-center">
				<img
					src="<%=tour.getFileName() != null ? baseURL + "/images/" + tour.getFileName() : "https://via.placeholder.com/600x600"%>"
					class="img-fluid" alt="<%=tour.getTenTour()%>" style="object-fit: cover;">
			</div>

			<!-- DETAILS -->
			<div class="col-md-6 d-flex flex-column justify-content-center p-3">
				<h1 class="mb-4 text-center w-100"><%=tour.getTenTour()%></h1>

				<div class="container-fluid">
					<p>
						<strong>Category:</strong>
						<%=tour.getLoaiTour() != null ? tour.getLoaiTour().getTenLoaiTour() : "N/A"%>
					</p>
					<p>
						<strong>Start Point:</strong>
						<%=tour.getDiemXuatPhat()%>
					</p>
					<p>
						<strong>End Point:</strong>
						<%=tour.getDiemKetThuc()%>
					</p>
					<p>
						<strong>Transportation:</strong>
						<%=tour.getPhuongTienDiChuyen()%>
					</p>
					<p>
						<strong>Start Date:</strong>
						<%=dateFormatterDate.format(tour.getThoiGianXuatPhat())%>
						at
						<%=dateFormatterTime.format(tour.getThoiGianXuatPhat())%>
					</p>
					<p>
						<strong>End Date:</strong>
						<%=dateFormatterDate.format(tour.getThoiGianKetThuc())%>
						at
						<%=dateFormatterTime.format(tour.getThoiGianKetThuc())%>
					</p>
					<p>
						<strong>Available Seats:</strong>
						<%=tour.getSoLuongVeHienCo()%>
						/
						<%=tour.getSoLuongVeToiDa()%>
					</p>
					<p>
						<strong>Current Price:</strong>
						<%=currencyFormatter.format(tour.getGiaVeHienTai())%>
					</p>
					<%
					if (tour.getGiaVeLucTruoc() != 0) {
					%>
					<p class="text-decoration-line-through">
						<strong>Previous Price:</strong>
						<%=currencyFormatter.format(tour.getGiaVeLucTruoc())%>
					</p>
					<%
					}
					%>
					<p>
						<strong>Description:</strong> <br>
						<%=tour.getMoTa()%>
					</p>
				</div>
			</div>

			<!-- Place Order Button -->
			<div class="d-flex justify-content-center mt-4">
				<form action="<%=baseURL%>/userView/placeOrder.jsp" method="get">
					<input type="hidden" name="maTour" value="<%=maTour%>">
					<button type="submit" class="btn-order">Place Order</button>
				</form>
			</div>
		</div>
	</div>

	<!-- FOOTER -->
	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
