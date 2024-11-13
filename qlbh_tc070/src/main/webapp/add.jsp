<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Thêm khách hàng</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	background-color: #f4f7fc;
	font-family: 'Arial', sans-serif;
}

.container {
	background-color: #fff;
	border-radius: 8px;
	padding: 30px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	max-width: 500px;
}

h2 {
	color: #333;
	margin-bottom: 20px;
}

.form-label {
	font-weight: bold;
	color: #555;
}

.btn-primary {
	background-color: #007bff;
	border: none;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.btn-secondary {
	background-color: #6c757d;
	border: none;
}

.btn-secondary:hover {
	background-color: #5a6268;
}

.form-control {
	border-radius: 4px;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
}

.mb-3 {
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center fw-bold">Thêm khách hàng mới</h2>

		<!-- Display error message if it exists -->
		<%
		String error = (String) request.getAttribute("error");
		error = (error != null) ? error : "";
		if (!error.isEmpty()) {
		%>
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<strong>Lỗi: </strong>
			<%=error%>
		</div>
		<%
		}
		%>

		<!-- Form to add a new customer -->
		<form action="add-customer" method="POST">
			<div class="mb-3">
				<label for="makh" class="form-label">Mã khách hàng</label> <input type="text"
					class="form-control" id="makh" name="makh" required>
			</div>
			<div class="mb-3">
				<label for="hoten" class="form-label">Họ và tên</label> <input type="text" class="form-control"
					id="hoten" name="hoten" required>
			</div>
			<div class="mb-3">
				<label for="diachi" class="form-label">Địa chỉ</label> <input type="text" class="form-control"
					id="diachi" name="diachi" required>
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email</label> <input type="email" class="form-control"
					id="email" name="email" required>
			</div>
			<div class="mb-4">
				<label for="dienthoai" class="form-label">Điện thoại</label> <input type="text"
					class="form-control" id="dienthoai" name="dienthoai" required>
			</div>
			<button type="submit" class="btn btn-primary">Thêm khách hàng</button>
			<a href="index.jsp" class="btn btn-secondary ms-2">Quay lại</a>
		</form>
	</div>
</body>
</html>
