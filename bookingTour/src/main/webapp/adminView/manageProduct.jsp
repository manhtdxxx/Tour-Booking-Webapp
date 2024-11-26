<%@page import="database.TourDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Tour"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>
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
		<!-- Side Bar -->
		<jsp:include page="sidebar.jsp"></jsp:include>

		<%
		String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();

		TourDAO tourDAO = new TourDAO();
		%>

		<!-- Content Area -->
		<div class="flex-grow-1 px-3 vh-100 overflow-auto">
			<!-- Intro -->
			<div class="container-fluid text-center mb-4 pt-2">
				<h2 class="mb-1">Welcome to the Tour Management Dashboard</h2>
				<h6 class="text-secondary">
					Effortlessly organize and manage tours, view upcoming schedules, monitor bookings,
					and <br> handle tour-related tasks all in one place.
				</h6>
			</div>


			<!-- Quick Stats Section -->
			<div
				class="container-fluid mb-4 py-2 border-top border-bottom border-2 border-dark-subtle">
				<div class="row">
					<div class="col-sm-6 col-md-3">
						<div class="card bg-light text-dark shadow-sm border-0">
							<div class="card-body d-flex align-items-center justify-content-between">
								<div>
									<h6 class="card-title mb-1">Total Tours</h6>
									<p class="card-text fs-5 fw-bold"><%=tourDAO.countTotalTours()%></p>
								</div>
								<i class="bi bi-people-fill fs-2 text-primary"></i>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="card bg-light text-dark shadow-sm border-0">
							<div class="card-body d-flex align-items-center justify-content-between">
								<div>
									<h6 class="card-title mb-1">Tour Types</h6>
									<p class="card-text fs-5 fw-bold"><%=tourDAO.countTotalTourTypes()%></p>
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

			<!-- Button Section -->
			<%
			String rawSearchQuery = request.getParameter("searchQuery");
			String searchQuery = (rawSearchQuery == null || "null".equals(rawSearchQuery)) ? "" : rawSearchQuery.trim();
			%>
			<div class="d-flex justify-content-between align-items-center mb-2">
				<!-- Filter Button -->
				<button class="btn btn-secondary">
					<i class="bi bi-filter me-2"></i>Filter
				</button>

				<!-- Search Button -->
				<form action="" method="get" class="d-flex" style="width: 40%;">
					<div class="input-group me-2">
						<span class="input-group-text"><i class="bi bi-search"></i></span> <input
							class="form-control" type="text"
							placeholder="Nhập mã tour, tên tour, loại tour, ..." aria-label="Search"
							name="searchQuery" value="<%=searchQuery%>">
					</div>
					<button class="btn btn-primary text-nowrap" type="submit">Search</button>
				</form>

				<!-- Add Button -->
				<button class="btn btn-success disabled">
					<i class="bi bi-plus-circle me-2"></i>Add
				</button>
			</div>

			<!-- Message after deleting tour -->
			<%
			String isDeletedParam = request.getParameter("isDeleted");
			if (isDeletedParam != null) {
				int isDeleted = Integer.parseInt(isDeletedParam);
				if (isDeleted == 1) {
			%>
			<div class="alert alert-success alert-dismissible fade show my-3 fs-6 mx-auto w-50"
				role="alert">
				<strong>Success!</strong> Tour <strong class="text-dark text-decoration-underline"><%=request.getParameter("maTour")%></strong>
				has been deleted successfully.
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%
			} else {
			%>
			<div class="alert alert-danger alert-dismissible fade show my-3 fs-6 mx-auto w-50"
				role="alert">
				<strong>Error!</strong> Failed to delete the tour <strong
					class="text-dark text-decoration-underline"><%=request.getParameter("maTour")%></strong>.
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

			int pageSize = 5;
			int offset = (currentPage - 1) * pageSize;

			String sortColumn = request.getParameter("sortColumn") != null ? request.getParameter("sortColumn") : "maTour";
			String sortDirection = request.getParameter("sortDirection") != null ? request.getParameter("sortDirection") : "asc";

			ArrayList<Tour> tourList;
			int totalRecords;
			int totalPages;
			if (searchQuery != null && !searchQuery.isEmpty()) {
				tourList = tourDAO.searchByIdOrName(searchQuery, offset, pageSize, sortColumn, sortDirection);
				totalRecords = tourDAO.countSearchResults(searchQuery);
			} else {
				tourList = tourDAO.selectPaginatedWithSortedData(offset, pageSize, sortColumn, sortDirection);
				totalRecords = tourDAO.countTotalTours();
			}
			totalPages = (int) Math.ceil((double) totalRecords / pageSize);
			%>
			<div class="table-responsive">
				<table class="table table-bordered table-striped table-hover align-middle">
					<thead class="table-dark">
						<tr class="text-center align-middle">
							<th>Mã Tour <br> <a class="text-light text-decoration-none"
								href="?searchQuery=<%=searchQuery%>&sortColumn=maTour&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "maTour".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("maTour".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Tên Tour <br> <a class="text-light text-decoration-none"
								href="?searchQuery=<%=searchQuery%>&sortColumn=tenTour&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "tenTour".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("tenTour".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Loại Tour <br> <a class="text-light text-decoration-none"
								href="?searchQuery=<%=searchQuery%>&sortColumn=tenLoaiTour&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "tenLoaiTour".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("tenLoaiTour".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Thời gian <br> xuất phát <br> <a
								class="text-light text-decoration-none"
								href="?searchQuery=<%=searchQuery%>&sortColumn=thoiGianXuatPhat&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "thoiGianXuatPhat".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("thoiGianXuatPhat".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>

							<th>Thời gian <br> kết thúc <br> <a
								class="text-light text-decoration-none"
								href="?searchQuery=<%=searchQuery%>&sortColumn=thoiGianKetThuc&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "thoiGianKetThuc".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("thoiGianKetThuc".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>
							<th>Số lượng vé <br> hiện có <br> <a
								class="text-light text-decoration-none"
								href="?searchQuery=<%=searchQuery%>&sortColumn=soLuongVeHienCo&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "soLuongVeHienCo".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("soLuongVeHienCo".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>
							<th>Giá vé <br> hiện tại <br> <a
								class="text-light text-decoration-none"
								href="?searchQuery=<%=searchQuery%>&sortColumn=giaVeHienTai&sortDirection=<%=("asc".equalsIgnoreCase(sortDirection) && "giaVeHienTai".equalsIgnoreCase(sortColumn)) ? "desc" : "asc"%>&page=<%=currentPage%>">
									<%=("giaVeHienTai".equalsIgnoreCase(sortColumn)) ? ("asc".equalsIgnoreCase(sortDirection) ? "▲" : "▼") : "↕"%>
							</a>
							</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
						SimpleDateFormat dateFormatterDate = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat dateFormatterTime = new SimpleDateFormat("HH:mm");
						Locale vietnamLocale = Locale.of("vi", "VN");
						NumberFormat currencyFormatter = NumberFormat.getInstance(vietnamLocale);

						if (tourList != null && !tourList.isEmpty()) {
							for (Tour tour : tourList) {
								String formattedDateXuatPhat = dateFormatterDate.format(tour.getThoiGianXuatPhat());
								String formattedTimeXuatPhat = dateFormatterTime.format(tour.getThoiGianXuatPhat());
								String formattedDateKetThuc = dateFormatterDate.format(tour.getThoiGianKetThuc());
								String formattedTimeKetThuc = dateFormatterTime.format(tour.getThoiGianKetThuc());
								String formattedGiaVeHienTai = currencyFormatter.format(tour.getGiaVeHienTai()) + " VNĐ";
						%>
						<tr class="text-center">
							<td><%=tour.getMaTour()%></td>
							<td class="text-start"><%=tour.getTenTour()%></td>
							<td class="text-start"><%=tour.getLoaiTour().getTenLoaiTour()%></td>
							<td><%=formattedDateXuatPhat%> <br> <%=formattedTimeXuatPhat%></td>
							<td><%=formattedDateKetThuc%> <br> <%=formattedTimeKetThuc%></td>
							<td><%=tour.getSoLuongVeHienCo()%></td>
							<td class="text-nowrap"><%=formattedGiaVeHienTai%></td>
							<td>
								<div class="btn-group gap-2">
									<!-- View Details Button -->
									<button class="btn btn-sm btn-info">
										<i class="bi bi-eye me-1"></i>View Details
									</button>

									<!-- Delete Button -->
									<button class="btn btn-sm btn-danger" data-bs-toggle="modal"
										data-bs-target="#deleteModal_<%=tour.getMaTour()%>">
										<i class="bi bi-trash3 me-1"></i>Delete
									</button>

									<!-- Modal for Deleting Customer -->
									<div class="modal fade" id="deleteModal_<%=tour.getMaTour()%>" tabindex="-1"
										aria-labelledby="deleteModalLabel_<%=tour.getMaTour()%>" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="deleteModalLabel_<%=tour.getMaTour()%>">Delete
														Product</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal"
														aria-label="Close"></button>
												</div>
												<form action="<%=baseURL%>/manage-product" method="post">
													<input type="hidden" name="action" value="delete"> <input
														type="hidden" name="page" value="<%=currentPage%>"> <input
														type="hidden" name="sortColumn" value="<%=sortColumn%>"> <input
														type="hidden" name="sortDirection" value="<%=sortDirection%>"> <input
														type="hidden" name="searchQuery" value="<%=searchQuery%>"> <input
														type="hidden" name="maTour" value="<%=tour.getMaTour()%>">
													<div class="modal-body">
														<div>
															Are you sure you want to delete <br> tour <strong><%=tour.getTenTour()%></strong>
															(<strong><%=tour.getMaTour()%></strong>)?
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
							<td colspan="8" class="text-center text-danger fw-bold">No customer data
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
