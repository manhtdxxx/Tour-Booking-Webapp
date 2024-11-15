<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng ký</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
body {
	background: linear-gradient(to right, #6a11cb, #2575fc);
	font-family: 'Arial', sans-serif;
	color: #fff;
}

.container {
	max-width: 450px;
	background: linear-gradient(145deg, #ffffff, #e6e6e6);
	border-radius: 15px;
	box-shadow: 4px 4px 15px rgba(0, 0, 0, 0.2), -4px -4px 15px
		rgba(255, 255, 255, 0.3);
	padding: 2rem;
	margin-top: 50px;
	margin-bottom: 50px;
	color: #333;
}

.btn-primary {
	background-color: #2575fc;
	border: none;
	transition: all 0.3s ease-in-out;
}

.btn-primary:hover {
	background-color: #6a11cb;
	box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
}

.alert {
	animation: fadeIn 0.5s ease-in-out;
}

@
keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
#msg {
	font-size: 0.9rem;
}

#submit {
	transition: all 0.3s ease-in-out;
}
</style>
</head>

<body>
	<!-- NAVBAR -->
	<jsp:include page="layout/nav.jsp"></jsp:include>

	<!-- REGISTRATION FORM -->
	<%
	String error = (String) request.getAttribute("error");
	error = (error != null) ? error : "";
	String username = (String) request.getAttribute("username");
	String email = (String) request.getAttribute("email");
	String password = (String) request.getAttribute("password");
	String password2 = (String) request.getAttribute("password2");
	%>
	<section>
		<div class="container">
			<div class="text-center mb-4">
				<h1 class="text-primary">Đăng ký tài khoản</h1>
			</div>

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

			<form action="do-register" method="post">
				<div class="mb-3">
					<label for="username" class="form-label">Username</label> <input type="text"
						class="form-control" id="username" placeholder="Enter username" name="username"
						value="<%=username != null ? username : ""%>" required oninput="showSubmitButton()">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Email</label> <input type="email" class="form-control"
						id="email" placeholder="Enter email" name="email" value="<%=email != null ? email : ""%>"
						required oninput="showSubmitButton()">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input type="password"
						class="form-control" id="password" placeholder="Enter password" name="password" required
						onkeyup="confirmPassword(); showSubmitButton()">
				</div>
				<div class="mb-3">
					<label for="password2" class="form-label">Confirm Password</label> <input type="password"
						class="form-control" id="password2" placeholder="Enter password again" name="password2"
						required onkeyup="confirmPassword(); showSubmitButton()">
					<div id="msg" class="text-danger"></div>
				</div>
				<div class="form-check mb-3">
					<input class="form-check-input" type="checkbox" name="agree" id="agree"
						onchange="showSubmitButton()"> <label class="form-check-label"> I agree to the
						<a href="#">terms of service</a>
					</label>
				</div>
				<button type="submit" class="btn btn-primary w-100" id="submit" style="visibility: hidden;">Đăng
					ký</button>
			</form>
		</div>
	</section>

	<!-- FOOTER -->
	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>

<script>
	function confirmPassword() {
		let pass1 = document.getElementById("password").value;
		let pass2 = document.getElementById("password2").value;
		let msg = document.getElementById("msg");
		if (pass1 !== pass2) {
			msg.innerHTML = "Mật khẩu không khớp!";
			return false;
		} else {
			msg.innerHTML = "";
			return true;
		}
	}

	function showSubmitButton() {
		let username = document.getElementById("username").value.trim();
		let email = document.getElementById("email").value.trim();
		let password = document.getElementById("password").value.trim();
		let password2 = document.getElementById("password2").value.trim();
		let agree = document.getElementById("agree").checked;
		let submitButton = document.getElementById("submit");

		if (username && email && password && password2 && confirmPassword()
				&& agree) {
			submitButton.style.visibility = "visible";
		} else {
			submitButton.style.visibility = "hidden";
		}
	}
</script>
</html>
