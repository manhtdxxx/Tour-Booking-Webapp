<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>
<%@ page import="database.TourDAO"%>
<%@ page import="model.Tour"%>
<%@ page import="model.KhachHang"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đặt Tour</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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

	Locale vietnamLocale = Locale.of("vi", "VN");
	NumberFormat currencyFormatter = NumberFormat.getInstance(vietnamLocale);

	String maTour = request.getParameter("maTour");
	Tour tour = new Tour();
	tour.setMaTour(maTour);

	TourDAO tourDAO = new TourDAO();
	tour = tourDAO.selectById(tour);

	String tenTour = tour.getTenTour();
	String fileName = tour.getFileName();
	int soLuongVeHienCo = tour.getSoLuongVeHienCo();
	int soLuongVeToiDa = tour.getSoLuongVeToiDa();
	long giaVeHienTai = tour.getGiaVeHienTai();
	String formattedGiaVeHienTai = currencyFormatter.format(giaVeHienTai) + " VNĐ";
	%>
	<div class="container mt-4" style="max-width: 1200px;">
		<div class="row">

			<div class="col-md-6 d-flex justify-content-center">
				<img
					src="<%=tour.getFileName() != null ? baseURL + "/images/" + tour.getFileName() : "https://via.placeholder.com/600x200"%>"
					alt="<%=tenTour%>" class="img-fluid rounded-circle" style="object-fit: cover;">
			</div>

			<div
				class="col-md-6 d-flex flex-column justify-content-center p-3 text-center border-bottom border-3 rounded-pill shadow-sm">
				<h1 class="mb-4 display-6 fw-bold">
					<a href="<%=baseURL + "/productDetails.jsp?maTour=" + maTour%>" class="text-dark">
						<%=tenTour%>
					</a>
				</h1>
				<p>
					<strong>Giá Vé Hiện Tại:</strong>
					<%=formattedGiaVeHienTai%></p>
				<p>
					<strong>Số Lượng Vé Hiện Có:</strong>
					<%=soLuongVeHienCo%>
					/
					<%=soLuongVeToiDa%></p>
				<p class="text-muted">Hãy đặt ngay trước khi hết chỗ!</p>
			</div>
		</div>
	</div>

	<!-- Payment Form -->
	<div class="container mt-4 p-3" style="max-width: 600px;">
		<h1 class="mb-4 display-5 fw-bold text-center w-100">Thông Tin Thanh Toán</h1>

		<%
		if (request.getAttribute("successMSG") != null) {
		%>
		<div class="alert alert-success alert-dismissible">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<strong>Success!</strong>
			<%=request.getAttribute("successMSG")%>
		</div>
		<%
		}
		%>
		<%
		if (request.getAttribute("errorMSG") != null) {
		%>
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<strong>Error!</strong>
			<%=request.getAttribute("errorMSG")%>
		</div>
		<%
		}
		%>

		<form id="paymentForm" action="<%=baseURL + "/khach-hang"%>" method="post">
			<input type="hidden" name="action" value="place-order"> <input type="hidden"
				name="maKH" value="<%=khachHang.getMaKH()%>"> <input type="hidden"
				name="maTour" value="<%=maTour%>"> <input type="hidden" id="giaVeLucBooking"
				name="giaVeLucBooking" value="<%=giaVeHienTai%>"> <input type="hidden"
				id="tongTien" name="tongTien"> <input type="hidden" id="thoiGianDatTour"
				name="thoiGianDatTour">

			<div class="row">

				<div class="col-md-6">
					<div class="mb-4">
						<label for="tenKH" class="form-label">Tên Khách Hàng</label> <input type="text"
							class="form-control" id="tenKH" name="tenKH" value="<%=khachHang.getTenKH()%>"
							required disabled>
					</div>
					<div class="mb-4">
						<label for="soDienThoai" class="form-label">Số Điện Thoại</label> <input type="text"
							class="form-control" id="soDienThoai" name="soDienThoai"
							value="<%=khachHang.getSoDienThoai()%>" required disabled>
					</div>
					<div class="mb-4">
						<label for="email" class="form-label">Email</label> <input type="email"
							class="form-control" id="email" name="email" value="<%=khachHang.getEmail()%>"
							required disabled>
					</div>
				</div>

				<div class="col-md-6">
					<div class="mb-4">
						<label for="soLuongVeDat" class="form-label">Số Lượng Vé Đặt</label> <input
							type="number" class="form-control" id="soLuongVeDat" name="soLuongVeDat" min="1"
							max="<%=soLuongVeHienCo%>" required
							oninput="calculateTotalPrice(); validateForm();">
					</div>
					<div class="mb-4">
						<label for="tongTien" class="form-label">Tổng Tiền</label> <input type="text"
							class="form-control" id="formattedTongTien" name="formattedTongTien" readonly>
					</div>
					<div class="mb-4">
						<label for="hinhThucThanhToan" class="form-label">Hình Thức Thanh Toán</label> <select
							class="form-select" id="hinhThucThanhToan" name="hinhThucThanhToan" required
							onchange="validateForm();">
							<option value="" disabled selected>Chọn hình thức</option>
							<option value="Tiền mặt">Tiền Mặt</option>
							<option value="Chuyển khoản">Chuyển Khoản</option>
							<option value="Thẻ tín dụng">Thẻ Tín Dụng</option>
							<option value="Ví điện tử">Ví điện tử</option>
						</select>
					</div>
				</div>

				<div class="col-12">
					<label for="ghiChu" class="form-label">Ghi Chú</label>
					<textarea class="form-control" id="ghiChu" name="ghiChu" rows="3"
						placeholder="Nhập ghi chú nếu cần..."></textarea>
				</div>

				<div class="col-12 mt-4 d-flex justify-content-center">
					<button type="button" id="confirmButton" class="btn btn-primary" disabled
						data-bs-toggle="modal" data-bs-target="#confirmModal">Thanh Toán</button>
				</div>
			</div>
		</form>
	</div>

	<!-- Confirmation Modal -->
	<div class="modal fade" id="confirmModal" tabindex="-1"
		aria-labelledby="confirmModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmModalLabel">
						Xác Nhận Đặt Tour
						<%=maTour%></h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="d-flex justify-content-between align-items-center mb-2">
						<strong>Số lượng vé:</strong> <span id="modalSoLuongVeDat"
							class="badge bg-light text-dark fs-6 px-3 py-2"></span>
					</div>
					<div class="d-flex justify-content-between align-items-center">
						<strong>Tổng tiền:</strong> <span id="modalTongTien"
							class="badge bg-light text-dark fs-6 px-3 py-2"></span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
					<button type="button" class="btn btn-primary" onclick="submitForm()">Xác
						Nhận</button>
				</div>
			</div>
		</div>
	</div>

	<!-- FOOTER -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script>
		const giaVeHienTai =
	<%=giaVeHienTai%>
		;

		function calculateTotalPrice() {
			const soLuongVe = document.getElementById('soLuongVeDat').value;
			const tongTien = soLuongVe * giaVeHienTai;
			const formattedTongTien = new Intl.NumberFormat('vi-VN', {
				style : 'currency',
				currency : 'VND'
			}).format(tongTien);

			document.getElementById('tongTien').value = tongTien || 0;
			document.getElementById('formattedTongTien').value = formattedTongTien
					|| '';
			document.getElementById('modalSoLuongVeDat').innerText = soLuongVe
					|| '0';
			document.getElementById('modalTongTien').innerText = formattedTongTien
					|| '0 VND';
		}

		function validateForm() {
			const soLuongVeDat = document.getElementById('soLuongVeDat').value;
			const hinhThucThanhToan = document
					.getElementById('hinhThucThanhToan').value;

			if (soLuongVeDat && hinhThucThanhToan) {
				document.getElementById('confirmButton').disabled = false;
			} else {
				document.getElementById('confirmButton').disabled = true;
			}
		}

		function submitForm() {
			const currentTimestamp = new Date().toISOString();
			document.getElementById('thoiGianDatTour').value = currentTimestamp;
			document.getElementById('paymentForm').submit();
		}
	</script>

	<%
	}
	%>
</body>
</html>
