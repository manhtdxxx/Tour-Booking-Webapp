<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng nhập</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
body {
	background: linear-gradient(135deg, #e0f7fa, #80deea);
	font-family: 'Roboto', sans-serif;
	height: 100vh;
	margin: 0;
}

.login-container {
	max-width: 450px;
	margin: 100px auto;
	background: #ffffff;
	border-radius: 15px;
	padding: 30px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s ease-in-out;
}

.login-container:hover {
	transform: scale(1.02);
}

.login-container h1 {
	font-size: 2rem;
	font-weight: 600;
	color: #1e3a8a;
	margin-bottom: 20px;
}

.btn-primary {
	background-color: #007bff;
	border: none;
	transition: background-color 0.3s ease;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.alert {
	margin-bottom: 20px;
	border-radius: 10px;
}

.alert-danger {
	background-color: #f8d7da;
	color: #721c24;
}

.alert-success {
	background-color: #d4edda;
	color: #155724;
}

.form-check-input {
	cursor: pointer;
}

.form-check-label {
	cursor: pointer;
	font-size: 0.9rem;
}

a {
	color: #007bff;
	text-decoration: none;
	font-size: 0.9rem;
}

a:hover {
	text-decoration: underline;
}

p.mt-3 {
	font-size: 1rem;
}

.btn-close {
	filter: invert(1);
}
</style>
</head>

<body>
	<!-- NAVBAR -->
	<jsp:include page="layout/nav.jsp"></jsp:include>

	<!-- LOGIN FORM -->
	<section>
		<div class="container login-container">
			<h1 class="text-center mb-4">Đăng nhập</h1>

			<!-- Error message -->
			<%
			String error = (String) request.getAttribute("error");
			error = (error != null) ? error : "";

			if (!error.isEmpty()) {
			%>
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<strong><%=error%></strong>
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			}
			%>

			<!-- Success message -->
			<%
			String success = (String) session.getAttribute("success");
			success = (success != null) ? success : "";

			if (!success.isEmpty()) {
			%>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
				<strong><%=success%></strong>
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			session.removeAttribute("success");
			}
			%>

			<!-- Cookies -->
			<%
			String savedUsername = "";
			String savedPassword = "";

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("username".equals(cookie.getName())) {
				savedUsername = cookie.getValue();
					}
					if ("password".equals(cookie.getName())) {
				savedPassword = cookie.getValue();
					}
				}
			}
			%>

			<form action="do-login" method="post">
				<div class="mb-3">
					<label for="username" class="form-label">Username:</label> <input type="text"
						class="form-control" id="username" placeholder="Enter username" name="username"
						value="<%=savedUsername%>" required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password:</label> <input type="password"
						class="form-control" id="password" placeholder="Enter password" name="password"
						value="<%=savedPassword%>" required>
				</div>
				<div class="d-flex align-items-center justify-content-between mb-3">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="remember" id="remember"
							<%=(!savedUsername.isEmpty() ? "checked" : "")%>> <label class="form-check-label"
							for="remember">Ghi nhớ đăng nhập</label>
					</div>
					<a href="#">Quên mật khẩu?</a>
				</div>

				<button type="submit" class="btn btn-primary w-100">Đăng nhập</button>

				<p class="mt-3 text-center">
					Chưa có tài khoản? <a href="register.jsp">Đăng ký</a>
				</p>
			</form>
		</div>
	</section>

	<!-- FOOTER -->
	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
