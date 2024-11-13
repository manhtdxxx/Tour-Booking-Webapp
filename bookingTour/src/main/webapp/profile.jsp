<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<title>Thông tin cá nhân</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	background-color: #f8f9fa;
}

.form-container {
	max-width: 600px;
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.form-title {
	font-weight: bold;
	color: #007bff;
}

.form-label {
	font-weight: 500;
}

.btn-submit {
	background-color: #007bff;
	border: none;
	color: #fff;
}

.btn-submit:hover {
	background-color: #0056b3;
}

.input-disabled {
	background-color: #f8f9fa;
	border-color: #ced4da;
	color: #6c757d;
}

.input-enabled {
	background-color: #ffffff;
	border-color: #007bff;
	color: #000;
}

.btn-enable {
	background-color: #007bff;
	color: white;
}

.btn-disable {
	background-color: #6c757d;
	color: white;
}
</style>

<script>
document.addEventListener("DOMContentLoaded", function() {
    // Function to toggle enable/disable state of input fields
    function toggleInput(button, input) {
        if (input.disabled) {
            input.disabled = false;
            input.classList.remove("input-disabled");
            input.classList.add("input-enabled");
            button.textContent = "Disable";
            button.classList.remove("btn-enable");
            button.classList.add("btn-disable");
        } else {
            input.disabled = true;
            input.classList.remove("input-enabled");
            input.classList.add("input-disabled");
            button.textContent = "Enable";
            button.classList.remove("btn-disable");
            button.classList.add("btn-enable");
        }
    }

    // Select all "Enable" buttons and set up click event listeners
    document.querySelectorAll(".btn-enable").forEach(button => {
        button.addEventListener("click", function() {
            const inputGroup = button.closest(".mb-3");
            const inputFields = inputGroup.querySelectorAll(".form-control, .form-check-input");
            
            // Toggle each input field within the group (works for radio buttons as well)
            inputFields.forEach(inputField => {
                toggleInput(button, inputField);
            });
        });
    });
});

</script>
</head>

<body>
	<div class="container d-flex justify-content-center align-items-center min-vh-100">
		<%
		Object obj = session.getAttribute("khachHang");
		KhachHang khachHang = null;

		if (obj != null) {
			khachHang = (KhachHang) obj;
		}

		if (khachHang == null) {
		%>
		<div class="alert alert-danger text-center py-4 px-5 shadow-lg rounded-pill" role="alert"
			aria-live="assertive">
			<h1 class="display-5 fw-normal">
				Bạn cần đăng nhập <br> để truy cập trang này!
			</h1>
			<div class="mt-4">
				Vui lòng quay lại <a href="index.jsp" class="fw-bolder text-primary text-decoration-underline">trang
					chủ</a>.
			</div>
			<div class="mt-1">
				Hoặc truy cập <a href="login.jsp" class="fw-bold text-primary text-decoration-underline">trang
					đăng nhập</a> để tiếp tục.
			</div>
		</div>

		<%
		} else {
			
		%>
		<div class="form-container px-5 py-4">
			<h1 class="text-center form-title mb-4">Thông tin cá nhân</h1>
			<form action="update-profile">
				<div class="mb-3">
					<label for="customerName" class="form-label"><i class="bi bi-person me-1"></i>Tên khách
						hàng</label>
					<div class="input-group">
						<input type="text" class="form-control input-disabled" id="customerName" name="customerName"
							placeholder="Nhập tên khách hàng" required disabled>
						<button type="button" class="btn btn-enable">Enable</button>
					</div>
				</div>

				<div class="mb-3">
					<label class="form-label"><i class="bi bi-gender-ambiguous me-1"></i>Giới tính</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input input-disabled" type="radio" name="gender" id="male"
								value="male" required disabled> <label class="form-check-label" for="male">Nam</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input input-disabled" type="radio" name="gender" id="female"
								value="female" required disabled> <label class="form-check-label" for="female">Nữ</label>
						</div>
						<button type="button" class="btn btn-enable ms-2">Enable</button>
					</div>
				</div>

				<div class="mb-3">
					<label for="dob" class="form-label"><i class="bi bi-calendar-date me-1"></i>Ngày sinh</label>
					<div class="input-group">
						<input type="date" class="form-control input-disabled" id="dob" name="dob"
							placeholder="Chọn ngày sinh" required disabled>
						<button type="button" class="btn btn-enable">Enable</button>
					</div>
				</div>

				<div class="mb-3">
					<label for="phone" class="form-label"><i class="bi bi-telephone me-1"></i>Số điện thoại</label>
					<div class="input-group">
						<input type="tel" class="form-control input-disabled" id="phone" name="phone"
							placeholder="Nhập số điện thoại" required disabled>
						<button type="button" class="btn btn-enable">Enable</button>
					</div>
				</div>

				<div class="mb-3">
					<label for="email" class="form-label"><i class="bi bi-envelope me-1"></i>Email</label>
					<div class="input-group">
						<input type="email" class="form-control input-disabled" id="email" name="email"
							placeholder="Nhập email" required disabled>
						<button type="button" class="btn btn-enable">Enable</button>
					</div>
				</div>

				<button type="submit" class="btn btn-submit mt-2 w-100 text-white fw-bold">Cập nhật
					thông tin</button>
			</form>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>