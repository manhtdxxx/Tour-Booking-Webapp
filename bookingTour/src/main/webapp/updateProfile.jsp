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

.btn-submit, .btn-confirm, .btn-cancel {
	background-color: #007bff;
	border: none;
	color: #fff;
}

.btn-submit:hover, .btn-confirm:hover {
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

.btn-cancel {
	background-color: #6c757d;
}

.button-group {
	display: flex;
	gap: 10px; /* Space between buttons */
}
</style>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const updateButton = document.querySelector(".btn-submit");
    const confirmButton = document.querySelector(".btn-confirm");
    const cancelButton = document.querySelector(".btn-cancel");
    const inputFields = document.querySelectorAll(".form-control, .form-check-input");
    const successAlert = document.getElementById("success-alert");
    const errorAlert = document.getElementById("error-alert");

    // Store the original values as data attributes
    function saveOriginalValues() {
        inputFields.forEach(input => {
            input.dataset.originalValue = input.type === "radio" ? input.checked : input.value;
        });
    }

    // Restore values from the data attributes
    function restoreOriginalValues() {
        inputFields.forEach(input => {
            if (input.type === "radio") {
                input.checked = (input.dataset.originalValue === "true");
            } else {
                input.value = input.dataset.originalValue;
            }
        });
    }

    // Toggle edit mode
    function toggleEditMode(enable) {
        inputFields.forEach(input => {
            input.disabled = !enable;
            input.classList.toggle("input-enabled", enable);
            input.classList.toggle("input-disabled", !enable);
        });
        updateButton.style.display = enable ? "none" : "inline-block";
        confirmButton.style.display = enable ? "inline-block" : "none";
        confirmButton.disabled = true; // Initially disable "Đồng ý" button
        cancelButton.style.display = enable ? "inline-block" : "none";

        if (enable) {
            saveOriginalValues();
        } else {
            restoreOriginalValues();
        }
    }

    // Check if any value has changed from its original value
    function checkIfChanged() {
        let hasChanged = false;
        inputFields.forEach(input => {
            const originalValue = input.dataset.originalValue;
            if ((input.type === "radio" && input.checked.toString() !== originalValue) ||
                (input.type !== "radio" && input.value !== originalValue)) {
                hasChanged = true;
            }
        });
        confirmButton.disabled = !hasChanged; // Enable "Đồng ý" only if changes are detected
    }

    // Hide alerts when the update button is clicked
    updateButton.addEventListener("click", () => {
        toggleEditMode(true);
        if (successAlert) successAlert.style.display = "none";
        if (errorAlert) errorAlert.style.display = "none";
    });

    // Add event listeners
    inputFields.forEach(input => {
        input.addEventListener("input", checkIfChanged);
    });

    cancelButton.addEventListener("click", () => toggleEditMode(false));
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
				Vui lòng quay lại <a href="index.jsp" class="fw-bold text-primary text-decoration-underline">trang
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

			<%
			String success = (String) request.getAttribute("success");
			String error = (String) request.getAttribute("error");
			%>
			<%
			if (success != null) {
			%>
			<div class="alert alert-success alert-dismissible fade show w-100" role="alert"
				id="success-alert">
				<%=success%>
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			} else if (error != null) {
			%>
			<div class="alert alert-danger alert-dismissible fade show w-100" role="alert" id="error-alert">
				<%=error%>
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			}
			%>

			<form action="update-profile" method="post">
				<input type="hidden" name="maKH" value="<%=khachHang.getMaKH()%>">

				<div class="mb-3">
					<label for="customerName" class="form-label"><i class="bi bi-person me-1"></i>Tên khách
						hàng</label> <input type="text" class="form-control input-disabled" id="customerName"
						name="customerName" value="<%=khachHang.getTenKH()%>" placeholder="Nhập tên khách hàng"
						required disabled>
				</div>

				<div class="mb-3">
					<label class="form-label"><i class="bi bi-gender-ambiguous me-1"></i>Giới tính</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input input-disabled" type="radio" name="gender" id="male"
								value="Nam" <%="Nam".equals(khachHang.getGioiTinh()) ? "checked" : ""%> disabled> <label
								class="form-check-label" for="male">Nam</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input input-disabled" type="radio" name="gender" id="female"
								value="Nữ" <%="Nữ".equals(khachHang.getGioiTinh()) ? "checked" : ""%> disabled> <label
								class="form-check-label" for="female">Nữ</label>
						</div>
					</div>
				</div>

				<div class="mb-3">
					<label for="dob" class="form-label"><i class="bi bi-calendar-date me-1"></i>Ngày sinh</label> <input
						type="date" class="form-control input-disabled" id="dob" name="dob"
						value="<%=khachHang.getNgaySinh()%>" placeholder="Chọn ngày sinh" required disabled>
				</div>

				<div class="mb-3">
					<label for="phone" class="form-label"><i class="bi bi-telephone me-1"></i>Số điện thoại</label>
					<input type="tel" class="form-control input-disabled" id="phone" name="phone"
						value="<%=khachHang.getSoDienThoai()%>" placeholder="Nhập số điện thoại" required disabled>
				</div>

				<div class="mb-3">
					<label for="email" class="form-label"><i class="bi bi-envelope me-1"></i>Email</label> <input
						type="email" class="form-control input-disabled" id="email" name="email"
						value="<%=khachHang.getEmail()%>" placeholder="Nhập email" required disabled>
				</div>

				<div class="button-group mt-2">
					<button type="submit" class="btn btn-confirm w-100 text-white fw-bold" style="display: none;">
						Đồng ý</button>
					<button type="button" class="btn btn-cancel w-100 text-white fw-bold" style="display: none;">
						Hủy cập nhật</button>
				</div>

				<button type="button" class="btn btn-submit mt-2 w-100 text-white fw-bold">Cập nhật
					thông tin</button>
			</form>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>
