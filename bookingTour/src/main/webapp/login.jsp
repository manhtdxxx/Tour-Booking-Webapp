<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang đăng nhập</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
body {
	background: linear-gradient(135deg, #a29bfe, #6c5ce7);
	font-family: Arial, sans-serif;
}

.container {
	max-width: 400px;
	background-color: #fff;
	padding: 35px;
	border-radius: 12px;
	box-shadow: 0px 12px 35px rgba(0, 0, 0, 0.25);
	animation: fadeIn 1.2s ease;
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(-20px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
h1 {
	font-size: 2rem;
	font-weight: bold;
	color: #2d3436;
}

.form-label {
	font-weight: 600;
	color: #2d3436;
}

.form-control {
	border-radius: 10px;
	border: 1px solid #ced4da;
	transition: border-color 0.3s, box-shadow 0.3s;
}

.form-control:focus {
	border-color: #6c5ce7;
	box-shadow: 0 0 6px rgba(108, 92, 231, 0.3);
}

.form-check-label {
	font-size: 0.9rem;
	color: #636e72;
}

a {
	color: #6c5ce7;
	text-decoration: none;
	transition: color 0.2s;
}

a:hover {
	color: #341f97;
	text-decoration: underline;
}

.btn-primary {
	background-color: #6c5ce7;
	border: none;
	border-radius: 10px;
	padding: 12px 25px;
	font-weight: bold;
	transition: background-color 0.3s, transform 0.2s;
}

.btn-primary:hover {
	background-color: #341f97;
	transform: translateY(-3px);
}

p {
	margin-top: 18px;
	text-align: center;
	color: #636e72;
}
</style>
</head>

<body class="d-flex align-items-center justify-content-center vh-100">
	<%
	String error = (String) request.getAttribute("error");
	error = (error != null) ? error : "";
	%>

	<div class="container">
		<h1 class="text-center mb-4">Đăng nhập</h1>

		<!-- Error Message -->
		<%
		if (!error.isEmpty()) {
		%>
		<div class="alert alert-danger alert-dismissible fade show">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<strong><%=error%></strong>
		</div>
		<%
		}
		%>

		<form action="do-login" method="post">
			<div class="mb-3">
				<label for="username" class="form-label">Username:</label> <input type="text"
					class="form-control" id="username" placeholder="Enter username" name="username" required>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password:</label> <input type="password"
					class="form-control" id="password" placeholder="Enter password" name="password" required>
			</div>
			<div class="d-flex align-items-center justify-content-between mb-3">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="remember" id="remember"> <label
						class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
				</div>
				<a href="#">Quên mật khẩu?</a>
			</div>

			<button type="submit" class="btn btn-primary w-100">Đăng nhập</button>

			<p>
				Chưa có tài khoản? <a href="register.jsp">Đăng ký</a>
			</p>
		</form>
	</div>

</body>
</html>
