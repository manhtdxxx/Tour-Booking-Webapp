<%@ page import="model.KhachHang"%>
<%@ page import="database.KhachHangDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Cập nhật khách hàng</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	background-color: #f0f2f5;
	font-family: 'Arial', sans-serif;
}

.container {
	max-width: 500px;
	background-color: #ffffff;
	border-radius: 12px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
	padding: 25px;
	margin-top: 60px;
}

h2 {
	font-size: 1.8rem;
	color: #343a40;
	margin-bottom: 1.5rem;
	text-align: center;
	font-weight: bold;
}

label {
	font-weight: 600;
	color: #495057;
	margin-bottom: 0.5rem;
	display: block;
}

.form-control {
	border-radius: 6px;
	border: 1px solid #ced4da;
	padding: 10px;
	transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.form-control:focus {
	border-color: #80bdff;
	box-shadow: 0 0 8px rgba(0, 123, 255, 0.25);
}

.btn-primary {
	background-color: #007bff;
	border: none;
	padding: 10px 16px;
	border-radius: 6px;
	font-size: 1rem;
	transition: background-color 0.3s ease;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.btn-secondary {
	background-color: #6c757d;
	border: none;
	padding: 10px 16px;
	border-radius: 6px;
	font-size: 1rem;
	transition: background-color 0.3s ease;
}

.btn-secondary:hover {
	background-color: #5a6268;
}

.mb-3 {
	margin-bottom: 1.5rem;
}

.d-grid {
	gap: 10px;
}

.d-md-flex .btn {
	margin-top: 10px;
}

@media ( max-width : 768px) {
	.container {
		padding: 20px;
		margin-top: 30px;
	}
	h2 {
		font-size: 1.5rem;
	}
	.btn {
		font-size: 0.9rem;
	}
}
</style>

</head>

<body>
	<div class="container">
		<%
		String makh = request.getParameter("makh");
		KhachHang kh = new KhachHang();
		kh.setMaKH(makh);

		KhachHangDAO kh_dao = new KhachHangDAO();
		kh = kh_dao.selectById(kh);

		if (kh != null) {
		%>
		<h2 class="text-center">Cập nhật thông tin khách hàng</h2>
		<form action="update-customer" method="POST">
			<!-- Hidden input field to pass customer ID -->
			<input type="hidden" name="makh" value="<%=kh.getMaKH()%>">

			<div class="mb-3">
				<label for="hoten" class="form-label">Họ và tên</label> <input type="text" class="form-control"
					id="hoten" name="hoten" value="<%=kh.getHoTen()%>" required>
			</div>
			<div class="mb-3">
				<label for="diachi" class="form-label">Địa chỉ</label> <input type="text" class="form-control"
					id="diachi" name="diachi" value="<%=kh.getDiaChi()%>" required>
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email</label> <input type="email" class="form-control"
					id="email" name="email" value="<%=kh.getEmail()%>" required>
			</div>
			<div class="mb-3">
				<label for="dienthoai" class="form-label">Điện thoại</label> <input type="text"
					class="form-control" id="dienthoai" name="dienthoai" value="<%=kh.getDienThoai()%>" required>
			</div>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-1">
				<button type="submit" class="btn btn-primary">Cập nhật</button>
				<a href="index.jsp" class="btn btn-secondary ms-2">Quay lại</a>
			</div>
		</form>

		<%
		} else {
		%>
		<div class="alert alert-danger text-center" role="alert"
			style="font-size: 1.2rem; padding: 15px; margin-top: 20px;">Không tìm thấy khách hàng này!
		</div>
		<%
		}
		%>
	</div>
</body>
</html>
