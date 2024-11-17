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
	background-color: #f0f8ff;
	font-family: 'Arial', sans-serif;
}

.form-container {
	max-width: 650px;
	background-color: #ffffff;
	border-radius: 12px;
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
	padding: 30px;
	margin-top: 50px;
}

.form-title {
	font-weight: bold;
	color: #007bff;
	font-size: 32px;
}

.form-label {
	font-weight: 600;
	color: #495057;
}

.input-disabled {
	background-color: #f1f1f1;
	border-color: #ced4da;
	color: #6c757d;
}

.input-enabled {
	background-color: #ffffff;
	border-color: #007bff;
	color: #000;
}

.button-group {
	display: flex;
	gap: 12px;
	margin-top: 20px;
}

.btn-submit, .btn-confirm, .btn-cancel {
	background-color: #007bff;
	border: none;
	color: white;
	padding: 10px 15px;
	border-radius: 8px;
	font-weight: 600;
	transition: all 0.3s ease-in-out;
}

.btn-submit:hover, .btn-confirm:hover {
	background-color: #0056b3;
}

.btn-cancel {
	background-color: #6c757d;
}

.btn-submit:disabled, .btn-confirm:disabled, .btn-cancel:disabled {
	background-color: #ddd;
	cursor: not-allowed;
}

.alert {
	border-radius: 12px;
	padding: 20px;
	font-size: 16px;
}

.alert-success {
	background-color: #d4edda;
	color: #155724;
}

.alert-danger {
	background-color: #f8d7da;
	color: #721c24;
}

.alert .btn-close {
	font-size: 16px;
}

@media ( max-width : 576px) {
	.form-container {
		padding: 20px;
	}
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
			Vui lòng quay lại <a href="index.jsp" class="fw-bold text-primary text-decoration-underline">trang
				chủ</a>.
		</h5>
		<h5 class="mt-2">
			Hoặc truy cập <a href="login.jsp" class="fw-bold text-primary text-decoration-underline">trang
				đăng nhập</a> để tiếp tục.
		</h5>
	</div>

	<%
	} else {
	%>
	<!-- NAVBAR -->
	<jsp:include page="layout/nav.jsp"></jsp:include>

	<!-- PROFILE FORM -->
	<section class="d-flex justify-content-center">
		<div class="form-container px-5 py-4">
			<h1 class="text-center mb-4">Thông tin cá nhân</h1>

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
					<label for="dob" class="form-label"><i class="bi bi-calendar me-1"></i>Ngày sinh</label> <input
						type="date" class="form-control input-disabled" id="dob" name="dob"
						value="<%=khachHang.getNgaySinh() != null ? khachHang.getNgaySinh() : ""%>"
						placeholder="Chọn ngày sinh" required disabled>
				</div>

				<div class="mb-3">
					<label for="phoneNumber" class="form-label"><i class="bi bi-telephone me-1"></i>Số điện
						thoại</label> <input type="tel" class="form-control input-disabled" id="phoneNumber"
						name="phoneNumber" value="<%=khachHang.getSoDienThoai()%>" placeholder="Nhập số điện thoại"
						required disabled>
				</div>

				<div class="mb-3">
					<label for="email" class="form-label"><i class="bi bi-envelope me-1"></i>Email</label> <input
						type="email" class="form-control input-disabled" id="email" name="email"
						value="<%=khachHang.getEmail()%>" placeholder="Nhập email" required disabled>
				</div>

				<div class="button-group">
					<button type="button" class="btn-submit" id="btnEdit">Chỉnh sửa</button>
					<button type="submit" class="btn-confirm" style="display: none;">Đồng ý</button>
					<button type="button" class="btn-cancel" style="display: none;">Hủy</button>
				</div>
			</form>
		</div>
	</section>

	<!-- FOOTER -->
	<jsp:include page="layout/footer.jsp"></jsp:include>
	<%
	}
	%>
</body>
</html>
