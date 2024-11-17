<%@ page import="java.util.ArrayList"%>
<%@ page import="database.KhachHangDAO"%>
<%@ page import="model.KhachHang"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Quản lý khách hàng</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="d-flex">

		<!-- Side Bar -->
		<div class="d-flex flex-column flex-shrink-0 bg-dark text-white p-3"
			style="width: 280px; height: 100vh;">
			<a href="/"
				class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none"> <span
				class="fs-4">🌟 Sidebar</span>
			</a>
			<hr>
			<ul class="nav nav-pills flex-column mb-auto">
				<li class="nav-item"><a href="#" class="nav-link text-white" aria-current="page">🏠
						Home</a></li>
				<li><a href="#" class="nav-link text-white">📊 Dashboard</a></li>
				<li><a href="#" class="nav-link text-white">📋 Orders</a></li>
				<li><a href="#" class="nav-link text-white">📦 Products</a></li>
				<li><a href="#" class="nav-link active text-white">👥 Customers</a></li>
			</ul>
			<hr>
			<div class="dropdown">
				<a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
					data-bs-toggle="dropdown"> <img src="https://github.com/mdo.png" alt="" width="32"
					height="32" class="rounded-circle me-2"> <strong>Admin</strong>
				</a>
				<ul class="dropdown-menu dropdown-menu-dark text-small shadow">
					<li><a class="dropdown-item" href="#">New project...</a></li>
					<li><a class="dropdown-item" href="#">Settings</a></li>
					<li><a class="dropdown-item" href="#">Profile</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="#">Sign out</a></li>
				</ul>
			</div>
		</div>

		<!-- Content Area -->
		<div class="flex-grow-1 px-3 vh-100 overflow-auto">
			<div class="container-fluid bg-light py-1 text-center mb-2">
				<h1 class="mb-1">Welcome to the Admin Dashboard</h1>
				<h4 class="text-muted">Manage your data efficiently and monitor key metrics at a glance.</h4>
			</div>

			<!-- Search and Add -->
			<div class="d-flex justify-content-between align-items-center mb-2">
				<button class="btn btn-secondary d-flex align-items-center">
					<i class="bi bi-filter me-2"></i>Advanced Search
				</button>
				<form class="d-flex">
					<input class="form-control me-2" type="text" placeholder="Search ...">
					<button class="btn btn-primary text-nowrap" type="button">
						<i class="bi bi-search me-2"></i>Search
					</button>
				</form>
				<button class="btn btn-success d-flex align-items-center disabled">
					<i class="bi bi-plus-circle me-2"></i>Add a new customer
				</button>
			</div>

			<!-- Table -->
			<%
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			ArrayList<KhachHang> khachHangList = khachHangDAO.selectAll();
			%>
			<div class="table-responsive">
				<table class="table table-sm table-bordered table-striped table-hover align-middle">
					<thead class="table-dark">
						<tr class="text-center">
							<th>Mã khách hàng</th>
							<th>Tên khách hàng</th>
							<th>Giới tính</th>
							<th>Ngày sinh</th>
							<th>Email</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						for (KhachHang khachHang : khachHangList) {
							// Check if any field is null and set the background color
							String maKH = (khachHang.getMaKH() != null) ? khachHang.getMaKH()
							: "<span style='color: red; font-weight: 600;'>null</span>";
							String tenKH = (khachHang.getTenKH() != null) ? khachHang.getTenKH()
							: "<span style='color: red; font-weight: 600;'>null</span>";
							String gioiTinh = (khachHang.getGioiTinh() != null) ? khachHang.getGioiTinh()
							: "<span style='color: red; font-weight: 600;'>null</span>";
							Date ngaySinh = khachHang.getNgaySinh();
							String formattedDate = (ngaySinh != null) ? sdf.format(ngaySinh)
							: "<span style='color: red; font-weight: 600;'>null</span>";
							String email = (khachHang.getEmail() != null) ? khachHang.getEmail()
							: "<span style='color: red; font-weight: 600;'>null</span>";
						%>
						<tr class="text-center">
							<td><%=maKH%></td>
							<td><%=tenKH%></td>
							<td><%=gioiTinh%></td>
							<td><%=formattedDate%></td>
							<td><%=email%></td>
							<td>
								<div class="btn-group gap-2">
									<button class="btn btn-sm btn-warning">
										<i class="bi bi-pencil me-1"></i>Update
									</button>
									<button class="btn btn-sm btn-danger">
										<i class="bi bi-trash me-1"></i>Delete
									</button>
								</div>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

			<!-- Pagination -->
			<div class="d-flex justify-content-center">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li class="page-item <%=(currentPage == 1) ? "disabled" : ""%>"><a class="page-link"
							href="manage-customer ? action=paginate & page=<%=currentPage - 1%>"> <i
								class="bi bi-arrow-left-circle me-2"></i>Previous
						</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item <%=(currentPage == totalPages) ? "disabled" : ""%>""><a
							class="page-link" href="manage-customer ? action=paginate & page=<%=currentPage + 1%>">Next<i
								class="bi bi-arrow-right-circle ms-2"></i>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>
