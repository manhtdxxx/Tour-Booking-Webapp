<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đổi mật khẩu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
/* Custom styles for the page */
body {
	background-color: #f8f9fa;
	font-family: 'Arial', sans-serif;
}

.form-container {
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	padding: 3rem;
	width: 100%;
	max-width: 500px;
	margin-top: 50px;
}

.form-container h1 {
	font-size: 2.5rem;
	margin-bottom: 1.5rem;
}

.form-container .form-label {
	font-size: 1rem;
	font-weight: 500;
}

.form-container input {
	border-radius: 4px;
	font-size: 1rem;
	padding: 0.75rem;
	border: 1px solid #ddd;
	width: 100%;
}

.form-container input:focus {
	border-color: #007bff;
	box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.form-container button {
	padding: 0.75rem;
	font-size: 1.1rem;
	border-radius: 4px;
}

.form-container .alert {
	margin-bottom: 1rem;
}

#msg {
	font-size: 0.9rem;
}

.footer {
	padding: 2rem 0;
	background-color: #343a40;
	color: white;
	text-align: center;
}

.footer a {
	color: #ffc107;
	text-decoration: none;
}

.footer a:hover {
	text-decoration: underline;
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
	<div class="container-fluid bg-light text-center py-5" role="alert">
		<h1 class="fw-bold text-danger">Bạn cần đăng nhập để truy cập trang này!</h1>
		<h5 class="mt-4">
			Vui lòng quay lại <a href="index.jsp" class="fw-bold text-primary">trang chủ</a>.
		</h5>
		<h5 class="mt-2">
			Hoặc truy cập <a href="login.jsp" class="fw-bold text-primary">trang đăng nhập</a> để tiếp tục.
		</h5>
	</div>
	<%
	} else {
	%>
	<!-- NAVBAR -->
	<jsp:include page="layout/nav.jsp"></jsp:include>

	<!-- CHANGE PASSWORD FORM -->
	<section class="d-flex justify-content-center">
		<div class="form-container">
			<h1 class="text-center mb-4">Đổi Mật Khẩu</h1>

			<!-- Error/Success Message -->
			<%
			String error = (String) request.getAttribute("error");
			String success = (String) request.getAttribute("success");
			error = (error != null) ? error : "";
			success = (success != null) ? success : "";

			if (!error.isEmpty()) {
			%>
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Lỗi: </strong><%=error%>
			</div>
			<%
			}
			if (!success.isEmpty()) {
			%>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Thành công: </strong><%=success%>
			</div>
			<%
			request.removeAttribute("success");
			}
			%>

			<form action="change-password" method="post">
				<div class="mb-3">
					<label for="currentPassword" class="form-label">Mật khẩu hiện tại</label> <input
						type="password" class="form-control" id="currentPassword" name="currentPassword"
						placeholder="Nhập mật khẩu hiện tại" required>
				</div>
				<div class="mb-3">
					<label for="newPassword" class="form-label">Mật khẩu mới</label> <input type="password"
						class="form-control" id="newPassword" name="newPassword" placeholder="Nhập mật khẩu mới">
				</div>
				<div class="mb-3">
					<label for="confirmPassword" class="form-label">Xác nhận mật khẩu mới</label> <input
						type="password" class="form-control" id="confirmPassword" name="confirmPassword"
						placeholder="Xác nhận mật khẩu mới" required>
					<div id="msg" class="text-danger fw-medium mb-3 mt-1"></div>
				</div>
				<button type="submit" class="btn btn-primary w-100 mt-1">Đổi Mật Khẩu</button>
			</form>
		</div>
	</section>

	<!-- FOOTER -->
	<jsp:include page="layout/footer.jsp"></jsp:include>
	<%
	}
	%>
</body>

<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", confirmPassword); // Run on page load

	function confirmPassword() {
		let pass_1 = document.getElementById("newPassword").value;
		let pass_2 = document.getElementById("confirmPassword").value;
		let msg = document.getElementById("msg");
		let submitBtn = document.querySelector("button[type='submit']");

		if (pass_1 !== pass_2) {
			msg.innerHTML = "Mật khẩu không khớp!";
			submitBtn.disabled = true;
		} else {
			msg.innerHTML = "";
			submitBtn.disabled = false;
		}
	}

	// Event listeners for keyup on both fields
	document.getElementById("newPassword").addEventListener("keyup",
			confirmPassword);
	document.getElementById("confirmPassword").addEventListener("keyup",
			confirmPassword);
</script>
</html>
