<%@page import="model.LoaiTour"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.LoaiTourDAO"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Thêm sản phẩm</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

.content-area {
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

h1 {
	font-weight: bold;
	color: #495057;
}

label {
	font-weight: 600;
	color: #495057;
}

textarea, input, select {
	border-radius: 5px;
}

textarea {
	resize: none;
}

.form-container {
	max-width: 1000px;
}
</style>

<script>
	function validateDateTimes() {
		const startInput = document.getElementById('thoiGianXuatPhat');
		const endInput = document.getElementById('thoiGianKetThuc');
		const submitButton = document.getElementById('submitButton');
		const messageDiv = document.getElementById('dateValidationMessage');

		const startDate = new Date(startInput.value);
		const endDate = new Date(endInput.value);

		if (endDate <= startDate) {
			messageDiv.textContent = 'Thời gian kết thúc phải sau thời gian xuất phát!';
			messageDiv.style.color = 'red';
			submitButton.disabled = true;
		} else {
			messageDiv.textContent = '';
			submitButton.disabled = false;
		}
	}

	function previewImage(input) {
		const file = input.files[0];
		const previewImg = document.getElementById('imagePreviewImg');
		const clearButton = document.getElementById('clearImageButton');

		if (file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				previewImg.src = e.target.result; // Set the src of the image
				previewImg.style.display = 'block'; // Show the image preview
				clearButton.classList.remove('d-none'); // Show the clear button
			}
			reader.readAsDataURL(file);
		} else {
			previewImg.style.display = 'none'; // Hide preview if no file is selected
			clearButton.classList.add('d-none'); // Hide clear button if no file is selected
		}
	}

	function clearImage() {
		const fileInput = document.getElementById('file');
		const previewImg = document.getElementById('imagePreviewImg');
		const clearButton = document.getElementById('clearImageButton');

		fileInput.value = ''; // Clear the file input
		previewImg.style.display = 'none'; // Hide the preview
		clearButton.classList.add('d-none'); // Hide the clear button
	}
</script>

</head>

<body>
	<div class="d-flex">
		<!-- Side Bar -->
		<jsp:include page="sidebar.jsp"></jsp:include>

		<%
		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();

		LoaiTourDAO loaiTourDAO = new LoaiTourDAO();
		ArrayList<LoaiTour> loaiTourList = loaiTourDAO.selectAll();
		%>

		<!-- Content Area -->
		<div class="content-area flex-grow-1 px-3 vh-100 overflow-auto">

			<div class="mb-4">
				<a href="<%=baseURL%>/adminView/manageProduct.jsp" class="btn btn-secondary"> <i
					class="bi bi-arrow-left me-2"></i>Quay Lại
				</a>
			</div>

			<div class="form-container mx-auto">

				<h1 class="text-center mb-4">Thêm Tour</h1>

				<%
				String successMessage = (String) request.getAttribute("success_message");
				String errorMessage = (String) request.getAttribute("error_message");

				String tenTour = (String) request.getAttribute("tenTour");
				String selectedMaLoaiTour = (String) request.getAttribute("maLoaiTour");
				String diemXuatPhat = (String) request.getAttribute("diemXuatPhat");
				String diemKetThuc = (String) request.getAttribute("diemKetThuc");
				String phuongTienDiChuyen = (String) request.getAttribute("phuongTienDiChuyen");
				String moTa = (String) request.getAttribute("moTa");
				Integer soLuongVeToiDa = (Integer) request.getAttribute("soLuongVeToiDa");
				Long giaVeHienTai = (Long) request.getAttribute("giaVeHienTai");
				String thoiGianXuatPhat = (String) request.getAttribute("thoiGianXuatPhat");
				String thoiGianKetThuc = (String) request.getAttribute("thoiGianKetThuc");
				%>

				<!-- Display success message -->
				<%
				if (successMessage != null && !successMessage.isEmpty()) {
				%>
				<div class="alert alert-success alert-dismissible fade show mb-4 w-50 mx-auto">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<strong>Success!</strong>
					<%=successMessage%>
				</div>
				<%
				}
				%>

				<!-- Display error message -->
				<%
				if (errorMessage != null && !errorMessage.isEmpty()) {
				%>
				<div class="alert alert-danger alert-dismissible fade show mb-4 w-50 mx-auto">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<strong>Error!</strong>
					<%=errorMessage%>
				</div>
				<%
				}
				%>

				<form action="<%=baseURL%>/manage-product" method="post" enctype="multipart/form-data">
					<input type="hidden" name="action" value="add">
					<div class="row">
						<div class="col-md-8">
							<div class="row mb-3">
								<div class="col-md-6">
									<label for="tenTour" class="form-label">Tên Tour</label>
									<textarea class="form-control" id="tenTour" name="tenTour" rows="1" required><%=tenTour != null ? tenTour : ""%></textarea>
								</div>
								<div class="col-md-6">
									<label for="maLoaiTour" class="form-label">Loại Tour</label> <select
										class="form-select" id="maLoaiTour" name="maLoaiTour" required>
										<option value="">Chọn loại tour</option>
										<%
										if (loaiTourList != null) {
											for (LoaiTour loaiTour : loaiTourList) {
												String maLoaiTour = loaiTour.getMaLoaiTour();
										%>
										<option value="<%=maLoaiTour%>"
											<%=maLoaiTour.equals(selectedMaLoaiTour) ? "selected" : ""%>>
											<%=maLoaiTour%> -
											<%=loaiTour.getTenLoaiTour()%>
										</option>
										<%
										}
										}
										%>
									</select>
								</div>
							</div>

							<div class="row mb-3">
								<div class="col-md-6">
									<label for="diemXuatPhat" class="form-label">Điểm Xuất Phát</label>
									<textarea class="form-control" id="diemXuatPhat" name="diemXuatPhat" rows="1"
										required><%=diemXuatPhat != null ? diemXuatPhat : ""%></textarea>
								</div>
								<div class="col-md-6">
									<label for="diemKetThuc" class="form-label">Điểm Kết Thúc</label>
									<textarea class="form-control" id="diemKetThuc" name="diemKetThuc" rows="1"
										required><%=diemKetThuc != null ? diemKetThuc : ""%></textarea>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<label for="thoiGianXuatPhat" class="form-label">Thời Gian Xuất Phát</label> <input
										type="datetime-local" class="form-control" id="thoiGianXuatPhat"
										name="thoiGianXuatPhat"
										value="<%=thoiGianXuatPhat != null ? thoiGianXuatPhat : ""%>" required
										onchange="validateDateTimes()">
								</div>
								<div class="col-md-6">
									<label for="thoiGianKetThuc" class="form-label">Thời Gian Kết Thúc</label> <input
										type="datetime-local" class="form-control" id="thoiGianKetThuc"
										name="thoiGianKetThuc"
										value="<%=thoiGianKetThuc != null ? thoiGianKetThuc : ""%>" required
										onchange="validateDateTimes()">
								</div>
							</div>
							<!-- Validation message -->
							<div id="dateValidationMessage" class="mt-2 text-danger text-center"></div>

							<div class="row mt-3 mb-3">
								<div class="col-md-6">
									<label for="phuongTienDiChuyen" class="form-label">Phương Tiện Di Chuyển</label>
									<textarea class="form-control" id="phuongTienDiChuyen" name="phuongTienDiChuyen"
										rows="1" required><%=phuongTienDiChuyen != null ? phuongTienDiChuyen : ""%></textarea>
								</div>
							</div>

							<div class="row mb-3">
								<div class="col-md-6">
									<label for="soLuongVeToiDa" class="form-label">Số Lượng Vé Tối Đa</label> <input
										type="number" class="form-control" id="soLuongVeToiDa" name="soLuongVeToiDa"
										value="<%=soLuongVeToiDa != null ? soLuongVeToiDa : ""%>" min="1" required>
								</div>
								<div class="col-md-6">
									<label for="giaVeHienTai" class="form-label">Giá Vé Hiện Tại</label>
									<div class="input-group">
										<input type="number" class="form-control" id="giaVeHienTai" name="giaVeHienTai"
											value="<%=giaVeHienTai != null ? giaVeHienTai : ""%>" min="0" step="1000"
											required> <span class="input-group-text">VNĐ</span>
									</div>
								</div>
							</div>

							<div class="row mb-3">
								<div class="col-md-12">
									<label for="moTa" class="form-label">Mô Tả</label>
									<textarea class="form-control" id="moTa" name="moTa" rows="3" required><%=moTa != null ? moTa : ""%></textarea>
								</div>
							</div>
						</div>

						<!-- Image -->
						<div class="col-md-4 text-center">
							<label for="file" class="form-label">Image Uploaded</label> <input type="file"
								class="form-control form-control-sm" id="file" name="file" accept="image/*"
								aria-describedby="fileHelp" required onchange="previewImage(this)"> <small
								id="fileHelp" class="form-text text-muted">Choose an image to upload.</small>

							<div id="imagePreview" class="mt-3">
								<img id="imagePreviewImg" src="" alt="Image Preview"
									class="border border-3 border-secondary-subtle rounded p-2"
									style="max-width: 100%; height: auto; display: none;" />
							</div>
							<button type="button" id="clearImageButton"
								class="btn btn-danger btn-sm mt-2 d-none" onclick="clearImage()">Clear
								Image</button>
						</div>

					</div>

					<!-- Submit Button -->
					<div class="row">
						<div class="col-12 text-center">
							<button type="submit" class="btn btn-primary" id="submitButton">
								<i class="bi bi-save me-2"></i>Thêm Tour
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
