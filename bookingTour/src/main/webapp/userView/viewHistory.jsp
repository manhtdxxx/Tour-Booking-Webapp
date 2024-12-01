<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Locale"%>
<%@ page import="model.KhachHang"%>
<%@ page import="database.TourDAO"%>
<%@ page import="model.Tour"%>
<%@ page import="database.ChiTietDatTourDAO"%>
<%@ page import="model.ChiTietDatTour"%>
<%@ page import="database.DatTourDAO"%>
<%@ page import="model.DatTour"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lịch sử đặt Tour</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	background-color: #f8f9fa;
}

.container {
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	padding: 30px;
}

table th, table td {
	text-align: center;
	vertical-align: middle;
}

.btn-sm {
	padding: 5px 10px;
}

.table-hover tbody tr:hover {
	background-color: #f1f1f1;
}

.table-dark th, .table-dark td {
	color: white;
}

.status-label {
	font-weight: bold;
}

.action-btns a {
	margin: 0 5px;
}
</style>
</head>
<body>
	<%
	Object obj = session.getAttribute("khachHang");
	KhachHang khachHang = null;
	if (obj != null) {
		khachHang = (KhachHang) obj;
	}

	if (khachHang == null) {
	%>
	<jsp:include page="loginRequiredError.jsp"></jsp:include>
	<%
	} else {
	%>
	<!-- NAV -->
	<jsp:include page="../layout/nav.jsp"></jsp:include>

	<%
	String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();

	SimpleDateFormat dateFormatterDate = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatterTime = new SimpleDateFormat("HH:mm");
	Locale vietnamLocale = Locale.of("vi", "VN");
	NumberFormat currencyFormatter = NumberFormat.getInstance(vietnamLocale);

	String maKH = khachHang.getMaKH();

	DatTourDAO dtDAO = new DatTourDAO();
	ChiTietDatTourDAO ctdtDAO = new ChiTietDatTourDAO();

	ArrayList<DatTour> datTourList = dtDAO.selectDatToursByMaKH(maKH);
	%>
	<div class="container mt-4 p-3">
		<h1 class="text-center mb-4 fw-bold">Lịch sử Đặt Tour</h1>

		<%
		if (datTourList.isEmpty()) {
		%>
		<div class="alert alert-warning text-center" role="alert">Không có lịch sử đặt
			tour.</div>
		<%
		} else {
		%>

		<div class="table-responsive">
			<table class="table table-bordered table-striped table-hover align-middle">
				<thead class="table-dark">
					<tr>
						<th>STT</th>
						<th>Mã Đặt Tour</th>
						<th>Tên Tour</th>
						<th>Ngày Đặt</th>
						<th>Số Lượng Vé</th>
						<th>Tổng Tiền</th>
						<th>Trạng thái đặt tour</th>
						<th>Hành động</th>
					</tr>
				</thead>
				<tbody>
					<%
					int count = 1;
					for (DatTour datTour : datTourList) {

						String maDatTour = datTour.getMaDatTour();
						ArrayList<ChiTietDatTour> chiTietList = ctdtDAO.selectChiTietDatToursByMaDatTour(maDatTour);

						for (ChiTietDatTour chiTiet : chiTietList) {
							if (chiTiet.getDatTour().getMaDatTour().equals(maDatTour)) {
						Tour tour = chiTiet.getTour();
						String formattedMoney = currencyFormatter.format(chiTiet.getTongTien());
						String formattedDate = dateFormatterDate.format(datTour.getThoiGianDatTour());
						String formattedTime = dateFormatterTime.format(datTour.getThoiGianDatTour());
						String status = datTour.getTrangThaiDatTour();
					%>
					<tr>
						<td><%=count++%></td>
						<td><%=maDatTour%></td>
						<td><a href="<%=baseURL + "/productDetails.jsp?maTour=" + tour.getMaTour()%>"
							class="text-decoration-none"> <%=tour.getTenTour()%>
						</a></td>
						<td><%=formattedDate + "<br>" + formattedTime%></td>
						<td><%=chiTiet.getSoLuongVeDat()%></td>
						<td><%=formattedMoney%></td>
						<td
							class="text-uppercase fw-bold <%=("pending".equals(status)) ? "text-info"
		: ("accepted".equals(status)) ? "text-success"
				: ("completed".equals(status)) ? "text-secondary"
						: ("cancelled".equals(status)) ? "text-danger" : "text-muted"%> status-label">
							<%=status%>
						</td>
						<td class="action-btns">
							<%
							if ("pending".equals(status)) {
							%> <a
							href="<%=baseURL + "/cancelTour.jsp?maDatTour=" + maDatTour%>"
							class="btn btn-danger btn-sm">Hủy</a> <%
 } else {
 %> <span class="text-muted">N/A</span> <%
 }
 %>
						</td>
					</tr>
					<%
					}
					}
					}
					%>
				</tbody>
			</table>
			<%
			}
			%>
		</div>
	</div>

	<!-- FOOTER -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	<%
	}
	%>

</body>
</html>