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
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="d-flex">
		<!-- Side Bar -->
		<jsp:include page="sidebar.jsp"></jsp:include>

		<%
		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();

		KhachHangDAO khDAO = new KhachHangDAO();
		%>

		<!-- Content Area -->
		<div class="flex-grow-1 px-3 vh-100 overflow-auto">

			<!-- Intro -->
			<div class="container-fluid text-center mb-3 pt-2">
				<h2 class="mb-1">Welcome to the Customer Management Dashboard</h2>
				<h6 class="text-secondary">
					Easily track and manage your customers, view active customer data, monitor
					engagement, and <br> handle customer-related tasks all in one place.
				</h6>
			</div>

			<!-- Quick Stats Section -->
			<div
				class="container-fluid mb-3 py-3 border-top border-bottom border-2 border-dark-subtle">
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
			<%
			String rawSearchQuery = request.getParameter("searchQuery");
			String searchQuery = (rawSearchQuery == null || "null".equals(rawSearchQuery)) ? "" : rawSearchQuery.trim();
			%>
			<!-- Search Section -->
			<div class="d-flex justify-content-between align-items-center mb-2">
				<!-- Filter Button -->
				<button class="btn btn-secondary">
					<i class="bi bi-filter me-2"></i>Filter
				</button>

				<!-- Search Button -->
				<form action="" method="get" class="d-flex">
					<div class="input-group me-2"">
						<span class="input-group-text"><i class="bi bi-search"></i></span> <input
							class="form-control" type="text" placeholder="Enter ID or Name ..."
							aria-label="Search" name="searchQuery" value="<%=searchQuery%>">
					</div>
					<button class="btn btn-primary text-nowrap" type="submit">Search</button>
				</form>

				<!-- Add Button -->
				<button class="btn btn-success disabled">
					<i class="bi bi-plus-circle me-2"></i>Add
				</button>

			</div>

			<!-- Display Message Section -->

			<!-- message after toggling customer status -->
			<%
			String isStatusToggledParam = request.getParameter("isStatusUpdated");
			if (isStatusToggledParam != null) {
				boolean isStatusToggled = Boolean.parseBoolean(isStatusToggledParam);
				if (isStatusToggled) {
			%>
			<div class="alert alert-success alert-dismissible fade show my-3 fs-6 mx-auto"
				role="alert">
				<strong>Success!</strong> Status of customer <strong
					class="text-dark text-decoration-underline"><%=request.getParameter("maKH")%></strong>
				has been updated successfully to <strong
					class="text-dark text-uppercase text-decoration-underline"><%=request.getParameter("newStatus")%></strong>.
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			} else {
			%>
			<div class="alert alert-danger alert-dismissible fade show my-3 fs-6 mx-auto"
				role="alert">
				<strong>Error!</strong> Failed to update the status <strong
					class="text-dark text-uppercase"><%=request.getParameter("newStatus")%></strong> of
				customer <strong class="text-dark text-decoration-underline text-decoration-underline"><%=request.getParameter("maKH")%></strong>.
				Please try again!
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			}
			}
			%>

			<!-- message after deleting customer -->
			<%
			String isDeletedParam = request.getParameter("isDeleted");
			if (isDeletedParam != null) {
				int isDeleted = Integer.parseInt(isDeletedParam);
				if (isDeleted == 1) {
			%>
			<div class="alert alert-success alert-dismissible fade show my-3 fs-6 mx-auto"
				role="alert">
				<strong>Success!</strong> Customer <strong class="text-dark text-decoration-underline"><%=request.getParameter("maKH")%></strong>
				has been deleted successfully.
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			} else {
			%>
			<div class="alert alert-danger alert-dismissible fade show my-3 fs-6 mx-auto"
				role="alert">
				<strong>Error!</strong> Failed to delete the customer <strong
					class="text-dark text-decoration-underline"><%=request.getParameter("maKH")%></strong>.
				Please try again!
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			}
			}
			%>

			<!-- Table -->
			<%
			String pageParam = request.getParameter("page");
			int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

			int pageSize = 6;
			int offset = (currentPage - 1) * pageSize;

			String sortColumn = request.getParameter("sortColumn") != null ? request.getParameter("sortColumn") : "maKH";
			String sortDirection = request.getParameter("sortDirection") != null ? request.getParameter("sortDirection") : "asc";

			ArrayList<KhachHang> khachHangList;
			int totalRecords;
			int totalPages;
			if (searchQuery != null && !searchQuery.isEmpty()) {
				khachHangList = khDAO.searchIdOrName(searchQuery, offset, pageSize, sortColumn, sortDirection);
				totalRecords = khDAO.countSearchResults(searchQuery);
			} else {
				khachHangList = khDAO.selectPaginatedWithSortedData(offset, pageSize, sortColumn, sortDirection);
				totalRecords = khDAO.countTotalRecords();
			}
			totalPages = (int) Math.ceil((double) totalRecords / pageSize);
			%>
			<div class="table-responsive">
				<table class="table table-bordered table-striped table-hover align-middle">
					<thead class="table-dark">
						<tr class="text-center">
							<th>Mã khách hàng <a class="text-light text-decoration-none ms-2"
								href="?searchQuery=<%=searchQuery%>&sortColumn=maKH&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "maKH".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("maKH".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Tên khách hàng <a class="text-light text-decoration-none ms-2"
								href="?searchQuery=<%=searchQuery%>&sortColumn=tenKH&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "tenKH".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("tenKH".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Giới tính <a class="text-light text-decoration-none ms-2"
								href="?searchQuery=<%=searchQuery%>&sortColumn=gioiTinh&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "gioiTinh".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("gioiTinh".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Ngày sinh <a class="text-light text-decoration-none ms-2"
								href="?searchQuery=<%=searchQuery%>&sortColumn=ngaySinh&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "ngaySinh".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("ngaySinh".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Status <a class="text-light text-decoration-none ms-2"
								href="?searchQuery=<%=searchQuery%>&sortColumn=status&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "status".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("status".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						if (khachHangList != null && !khachHangList.isEmpty()) {
							for (KhachHang khachHang : khachHangList) {
								String tenKH = (khachHang.getTenKH() != null) ? khachHang.getTenKH()
								: "<span style='color: #d9534f;'>null</span>";
								String gioiTinh = (khachHang.getGioiTinh() != null) ? khachHang.getGioiTinh()
								: "<span style='color: #d9534f;'>null</span>";
								String formattedDate = (khachHang.getNgaySinh() != null) ? sdf.format(khachHang.getNgaySinh())
								: "<span style='color: #d9534f;'>null</span>";

								String status = "";
								if ("active".equalsIgnoreCase(khachHang.getStatus())) {
							status = "<strong style='color: #4caf50;'>" + khachHang.getStatus().toUpperCase() + "</strong>";
								} else if ("inactive".equalsIgnoreCase(khachHang.getStatus())) {
							status = "<strong style='color: #e57373;'>" + khachHang.getStatus().toUpperCase() + "</strong>";
								}
						%>
						<tr class="text-center">
							<td><%=khachHang.getMaKH()%></td>
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
									<button class="btn btn-sm btn-warning" data-bs-toggle="modal"
										data-bs-target="#statusModal_<%=khachHang.getMaKH()%>">
										<i class="bi bi-toggle-on me-1"></i>Toggle Status
									</button>
									<!-- Modal for Toggling Status -->
									<div class="modal fade" id="statusModal_<%=khachHang.getMaKH()%>"
										data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
										aria-labelledby="statusModalLabel_<%=khachHang.getMaKH()%>" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="statusModalLabel_<%=khachHang.getMaKH()%>">Change
														Customer Status</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal"
														aria-label="Close"></button>
												</div>
												<form action="<%=baseURL%>/manage-customer" method="post">
													<div class="modal-body">
														<input type="hidden" name="action" value="toggleStatus"> <input
															type="hidden" name="page" value="<%=currentPage%>"> <input
															type="hidden" name="sortColumn" value="<%=sortColumn%>"> <input
															type="hidden" name="sortDirection" value="<%=sortDirection%>"> <input
															type="hidden" name="searchQuery" value="<%=searchQuery%>"><input
															type="hidden" name="maKH" value="<%=khachHang.getMaKH()%>"> <input
															type="hidden" name="status" value="<%=khachHang.getStatus()%>">
														<div>
															<%
															String newStatus = "active".equalsIgnoreCase(khachHang.getStatus()) ? "inactive" : "active";
															if ("active".equalsIgnoreCase(newStatus)) {
																newStatus = "<strong style='color: #4caf50;'>" + newStatus.toUpperCase() + "</strong>";
															} else if ("inactive".equalsIgnoreCase(newStatus)) {
																newStatus = "<strong style='color: #e57373;'>" + newStatus.toUpperCase() + "</strong>";
															}
															%>
															Are you sure you want to change the status of <br> customer <strong><%=khachHang.getTenKH()%></strong>
															(<strong><%=khachHang.getMaKH()%></strong>) <br> to
															<%=newStatus%>?
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
									<button class="btn btn-sm btn-danger" data-bs-toggle="modal"
										data-bs-target="#deleteModal_<%=khachHang.getMaKH()%>">
										<i class="bi bi-trash3 me-1"></i>Delete
									</button>
									<!-- Modal for Deleting Customer -->
									<div class="modal fade" id="deleteModal_<%=khachHang.getMaKH()%>" tabindex="-1"
										aria-labelledby="deleteModalLabel_<%=khachHang.getMaKH()%>" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="deleteModalLabel_<%=khachHang.getMaKH()%>">Delete
														Customer</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal"
														aria-label="Close"></button>
												</div>
												<form action="<%=baseURL%>/manage-customer" method="post">
													<input type="hidden" name="action" value="delete"> <input
														type="hidden" name="page" value="<%=currentPage%>"> <input
														type="hidden" name="sortColumn" value="<%=sortColumn%>"> <input
														type="hidden" name="sortDirection" value="<%=sortDirection%>"> <input
														type="hidden" name="searchQuery" value="<%=searchQuery%>"> <input
														type="hidden" name="maKH" value="<%=khachHang.getMaKH()%>">
													<div class="modal-body">
														<div>
															Are you sure you want to delete <br> customer <strong><%=khachHang.getTenKH()%></strong>
															(<strong><%=khachHang.getMaKH()%></strong>)?
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
							<td colspan="6" class="text-center text-danger fw-bold">No customer data
								available.</td>
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
						<li class="page-item <%=(currentPage == 1) ? "disabled" : ""%>"><a
							class="page-link"
							href="?searchQuery=<%=searchQuery%>&sortColumn=<%=sortColumn%>&sortDirection=<%=sortDirection%>&page=<%=currentPage - 1%>">
								<i class="bi bi-arrow-left-circle me-2"></i>Previous
						</a></li>

						<!-- Page Numbers -->
						<%
						for (int i = 1; i <= totalPages; i++) {
						%>
						<li class="page-item <%=(i == currentPage) ? "active" : ""%>"><a
							class="page-link"
							href="?searchQuery=<%=searchQuery%>&sortColumn=<%=sortColumn%>&sortDirection=<%=sortDirection%>&page=<%=i%>"><%=i%></a>
						</li>
						<%
						}
						%>

						<!-- Next Button -->
						<li class="page-item <%=(currentPage == totalPages) ? "disabled" : ""%>"><a
							class="page-link"
							href="?searchQuery=<%=searchQuery%>&sortColumn=<%=sortColumn%>&sortDirection=<%=sortDirection%>&page=<%=currentPage + 1%>">
								Next<i class="bi bi-arrow-right-circle ms-2"></i>
						</a></li>

					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>
