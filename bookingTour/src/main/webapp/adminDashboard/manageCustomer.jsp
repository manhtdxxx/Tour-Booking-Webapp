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
				<li><a href="manageCustomer.jsp" class="nav-link active text-white">👥 Customers</a></li>
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

		<%
		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();

		KhachHangDAO khDAO = new KhachHangDAO();
		%>
		<!-- Content Area -->
		<div class="flex-grow-1 px-3 vh-100 overflow-auto">

			<!-- Intro -->
			<div class="container-fluid text-center mb-3 pt-2">
				<h1 class="mb-1">Welcome to the Admin Dashboard</h1>
				<h4 class="text-secondary">Manage your data efficiently and monitor key metrics at a
					glance.</h4>
			</div>

			<!-- Quick Stats Section -->
			<div class="container-fluid mb-3 py-3 border-top border-bottom border-2 border-dark-subtle">
				<div class="row">
					<div class="col-sm-6 col-md-3">
						<div class="card bg-light text-dark shadow-sm border-0">
							<div class="card-body d-flex align-items-center justify-content-between">
								<div>
									<h6 class="card-title mb-1">Total Customers</h6>
									<p class="card-text fs-5 fw-bold"><%=khDAO.countTotalRecords()%></p>
								</div>
								<i class="bi bi-people-fill fs-2 text-primary"></i>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="card bg-light text-dark shadow-sm border-0">
							<div class="card-body d-flex align-items-center justify-content-between">
								<div>
									<h6 class="card-title mb-1">Active Customers</h6>
									<p class="card-text fs-5 fw-bold"><%=khDAO.countActiveRecords()%></p>
								</div>
								<i class="bi bi-person-lines-fill fs-2 text-success"></i>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="card bg-light text-dark shadow-sm border-0">
							<div class="card-body d-flex align-items-center justify-content-between">
								<div>
									<h6 class="card-title mb-1">...</h6>
									<p class="card-text fs-5 fw-bold">...</p>
								</div>
								<i class="bi bi-box-seam fs-2 text-warning"></i>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="card bg-light text-dark shadow-sm border-0">
							<div class="card-body d-flex align-items-center justify-content-between">
								<div>
									<h6 class="card-title mb-1">...</h6>
									<p class="card-text fs-5 fw-bold">...</p>
								</div>
								<i class="bi bi-exclamation-triangle-fill fs-2 text-danger"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Search Section -->
			<div class="d-flex justify-content-between align-items-center mb-2">
				<!-- Filter Button -->
				<button class="btn btn-secondary d-flex align-items-center">
					<i class="bi bi-filter me-2"></i>Filter
				</button>

				<!-- Search Button -->
				<form class="d-flex flex-grow-1 justify-content-center">
					<div class="input-group me-2" style="width: 350px;">
						<span class="input-group-text"><i class="bi bi-search"></i></span> <input class="form-control"
							type="text" placeholder="Enter customer ID or username here..." aria-label="Search">
					</div>
					<button class="btn btn-primary text-nowrap" type="button">Search</button>
				</form>

				<!-- Add Button -->
				<button class="btn btn-success d-flex align-items-center disabled">
					<i class="bi bi-plus-circle me-2"></i>Add
				</button>
			</div>

			<!-- Table -->
			<%
			String pageParam = request.getParameter("page");
			int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

			int pageSize = 10;
			int offset = (currentPage - 1) * pageSize;

			int totalRecords = khDAO.countTotalRecords();
			int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

			ArrayList<KhachHang> khachHangList = khDAO.selectPaginatedData(offset, pageSize);
			%>
			<div class="table-responsive">
				<table class="table table-bordered table-striped table-hover align-middle">
					<thead class="table-dark">
						<tr class="text-center">
							<th>Mã khách hàng</th>
							<th>Tên khách hàng</th>
							<th>Giới tính</th>
							<th>Ngày sinh</th>
							<th>Trạng thái</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						if (khachHangList != null && !khachHangList.isEmpty()) {
							for (KhachHang khachHang : khachHangList) {
								String maKH = (khachHang.getMaKH() != null)
								? khachHang.getMaKH()
								: "<span style='color: red; font-weight: 400;'>NULL</span>";
								String username = (khachHang.getUsername() != null)
								? khachHang.getUsername()
								: "<span style='color: red; font-weight: 400;'>NULL</span>";
								String tenKH = (khachHang.getTenKH() != null)
								? khachHang.getTenKH()
								: "<span style='color: red; font-weight: 400;'>NULL</span>";
								String gioiTinh = (khachHang.getGioiTinh() != null)
								? khachHang.getGioiTinh()
								: "<span style='color: red; font-weight: 400;'>NULL</span>";
								String formattedDate = (khachHang.getNgaySinh() != null)
								? sdf.format(khachHang.getNgaySinh())
								: "<span style='color: red; font-weight: 400;'>NULL</span>";
								String status = "<span style='color: red; font-weight: 400;'>NULL</span>";
								if (khachHang.getStatus() != null) {
							String upperStatus = khachHang.getStatus().toUpperCase();
							if ("active".equalsIgnoreCase(khachHang.getStatus())) {
								status = "<span style='color: green; font-weight: 600;'>" + upperStatus + "</span>";
							} else if ("inactive".equalsIgnoreCase(khachHang.getStatus())) {
								status = "<span style='color: red; font-weight: 600;'>" + upperStatus + "</span>";
							}
								}
						%>
						<tr class="text-center">
							<td style="width: 150px;"><%=maKH%></td>
							<td><%=tenKH%></td>
							<td><%=gioiTinh%></td>
							<td><%=formattedDate%></td>
							<td><%=status%></td>
							<td style="width: 500px;">
								<div class="btn-group gap-2">
									<!-- View Details Button -->
									<button class="btn btn-sm btn-info">
										<i class="bi bi-eye me-1"></i>View Details
									</button>

									<!-- Reset Password Button -->
									<button class="btn btn-sm btn-secondary">
										<i class="bi bi-gear me-1"></i>Reset Password
									</button>

									<!-- Status Toggle Button -->
									<button class="btn btn-sm btn-warning" data-bs-toggle="modal" data-bs-target="#statusModal"
										onclick="changeCustomerStatus('<%=khachHang.getMaKH()%>', '<%=khachHang.getTenKH()%>','<%=khachHang.getStatus()%>')">
										<i class="bi bi-toggle-on me-1"></i>Toggle Status
									</button>
									<!-- Modal for Toggling Status -->
									<div class="modal fade" id="statusModal" data-bs-backdrop="static" data-bs-keyboard="false"
										tabindex="-1" aria-labelledby="statusModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-scrollable">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="statusModalLabel">Change Customer Status</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<form action="<%=baseURL%>/manage-customer?action=activate" method="post">
													<div class="modal-body">
														<input type="hidden" id="customerId_toSetStatus_input" name="maKH"> <input
															type="hidden" id="currentStatus" name="status">
														<div>
															Are you sure you want to change the status of <br> customer <span
																id="customerName_toSetStatus"></span> (<span id="customerId_toSetStatus_span"></span>)
															<br> to <span id="newStatus"></span>?
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
														<button type="submit" class="btn btn-primary">Confirm</button>
													</div>
												</form>
											</div>
										</div>
									</div>

									<!-- Delete Button -->
									<button class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal"
										onclick="confirmDeleteCustomer('<%=khachHang.getMaKH()%>', '<%=khachHang.getTenKH()%>')">
										<i class="bi bi-trash3 me-1"></i>Delete
									</button>
									<!-- Modal for Deleting Customer -->
									<div class="modal fade" id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false"
										tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-scrollable">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="deleteModalLabel">Delete Customer</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<form action="<%=baseURL%>/manage-customer?action=delete" method="post">
													<div class="modal-body">
														<input type="hidden" id="customerId_toDelete_input" name="maKH">
														<div>
															Are you sure you want to delete the <br> customer <span
																id="customerName_toDelete"></span> (<span id="customerId_toDelete_span"></span>)?
														</div>
														<div class="text-danger mt-1">This action cannot be undone!</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
														<button type="submit" class="btn btn-danger">Delete</button>
													</div>
												</form>
											</div>
										</div>
									</div>

								</div>
							</td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="6" class="text-center text-danger fw-bold">No customer data available.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

			<!-- Pagination -->
			<div class="d-flex justify-content-center">
				<nav aria-label="page navigation">
					<ul class="pagination">

						<!-- Previous Button -->
						<li class="page-item <%=(currentPage == 1) ? "disabled" : ""%>"><a class="page-link"
							href="?page=<%=currentPage - 1%>"><i class="bi bi-arrow-left-circle me-2"></i>Previous </a></li>

						<!-- Page Numbers -->
						<%
						for (Integer i = 1; i <= totalPages; i++) {
						%>
						<li class="page-item <%=(i == currentPage) ? "active" : ""%>"><a class="page-link"
							href="?page=<%=i%>"><%=i%></a></li>
						<%
						}
						%>

						<!-- Next Button -->
						<li class="page-item <%=(currentPage == totalPages) ? "disabled" : ""%>"><a
							class="page-link" href="?page=<%=currentPage + 1%>">Next<i
								class="bi bi-arrow-right-circle ms-2"></i>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>

<script>
	//Function to change customer status
	function changeCustomerStatus(customerId, customerName, currentStatus) {
		// Set hidden input values
		document.getElementById("customerId_toSetStatus_input").value = customerId;
		document.getElementById("currentStatus").value = currentStatus;

		// Update modal display values
		const customerIdSpan = document
				.getElementById("customerId_toSetStatus_span");
		const customerNameSpan = document
				.getElementById("customerName_toSetStatus");
		const newStatusElement = document.getElementById("newStatus");

		const newStatus = currentStatus.toLowerCase() === 'active' ? 'inactive'
				: 'active';

		customerIdSpan.textContent = customerId;
		customerNameSpan.textContent = customerName;
		newStatusElement.textContent = newStatus;

		// Apply dynamic styles for new status
		newStatusElement.style.color = newStatus === 'active' ? 'green' : 'red';
		newStatusElement.style.fontWeight = 'bold';
		newStatusElement.style.textTransform = 'uppercase';
		customerIdSpan.style.fontWeight = 'bold';
		customerNameSpan.style.fontWeight = 'bold';
	}

	// Function to confirm customer deletion
	function confirmDeleteCustomer(customerId, customerName) {
		// Set hidden input values
		document.getElementById("customerId_toDelete_input").value = customerId;

		// Update modal display values
		const customerIdDeleteSpan = document
				.getElementById("customerId_toDelete_span");
		const customerNameDeleteSpan = document
				.getElementById("customerName_toDelete");

		customerIdDeleteSpan.textContent = customerId;
		customerNameDeleteSpan.textContent = customerName;

		// Apply dynamic styles for deletion modal
		customerIdDeleteSpan.style.fontWeight = 'bold';
		customerNameDeleteSpan.style.fontWeight = 'bold';
	}
</script>
</html>
