<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý sản phẩm</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="d-flex">
		<%
		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		%>

		<!-- Side Bar -->
		<jsp:include page="sidebar.jsp"></jsp:include>

		<!-- Content Area -->
		<div class="flex-grow-1 px-3 vh-100 overflow-auto">
			<div class="container my-4">
				<h2 class="mb-4">Quản lý sản phẩm</h2>

				<!-- Search and Actions -->
				<div class="d-flex justify-content-between align-items-center mb-3">
					<div class="input-group w-50">
						<input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm"
							aria-label="Search">
						<button class="btn btn-primary" type="button">
							<i class="bi bi-search"></i>
						</button>
					</div>
					<button class="btn btn-success" data-bs-toggle="modal"
						data-bs-target="#addProductModal">
						<i class="bi bi-plus-circle"></i> Thêm sản phẩm
					</button>
				</div>

				<!-- Product Table -->
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead class="table-dark">
							<tr>
								<th>#</th>
								<th>Tên sản phẩm</th>
								<th>Giá</th>
								<th>Số lượng</th>
								<th>Danh mục</th>
								<th>Hành động</th>
							</tr>
						</thead>
						<tbody>
							<%
							// Example data, replace with actual data from database
							String[][] products = { { "1", "Laptop", "25,000,000 VND", "10", "Điện tử" },
									{ "2", "Điện thoại", "15,000,000 VND", "20", "Điện tử" },
									{ "3", "Tủ lạnh", "12,000,000 VND", "5", "Gia dụng" } };
							for (String[] product : products) {
							%>
							<tr>
								<td><%=product[0]%></td>
								<td><%=product[1]%></td>
								<td><%=product[2]%></td>
								<td><%=product[3]%></td>
								<td><%=product[4]%></td>
								<td>
									<button class="btn btn-primary btn-sm">
										<i class="bi bi-pencil"></i> Sửa
									</button>
									<button class="btn btn-danger btn-sm">
										<i class="bi bi-trash"></i> Xóa
									</button>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>

				<!-- Add Product Modal -->
				<div class="modal fade" id="addProductModal" tabindex="-1"
					aria-labelledby="addProductModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="addProductModalLabel">Thêm sản phẩm mới</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form>
									<div class="mb-3">
										<label for="productName" class="form-label">Tên sản phẩm</label> <input
											type="text" class="form-control" id="productName" required>
									</div>
									<div class="mb-3">
										<label for="productPrice" class="form-label">Giá</label> <input type="number"
											class="form-control" id="productPrice" required>
									</div>
									<div class="mb-3">
										<label for="productQuantity" class="form-label">Số lượng</label> <input
											type="number" class="form-control" id="productQuantity" required>
									</div>
									<div class="mb-3">
										<label for="productCategory" class="form-label">Danh mục</label> <select
											class="form-select" id="productCategory" required>
											<option value="" disabled selected>Chọn danh mục</option>
											<option value="Điện tử">Điện tử</option>
											<option value="Gia dụng">Gia dụng</option>
											<option value="Thời trang">Thời trang</option>
										</select>
									</div>
									<button type="submit" class="btn btn-primary">Lưu</button>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
