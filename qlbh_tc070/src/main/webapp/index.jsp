<%@ page import="java.util.ArrayList"%>
<%@ page import="model.KhachHang"%>
<%@ page import="database.KhachHangDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Danh sách khách hàng</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
	function confirmDelete(makh) {
		if (confirm("Are you sure you want to delete this customer?")) {
			window.location.href = "delete-customer?makh=" + makh;
		}
	}
</script>
</head>

<body>
	<div class="container mt-5">
		<h2 class="mb-4">Danh sách khách hàng</h2>

		<%
		KhachHangDAO kh_dao = new KhachHangDAO();
		ArrayList<KhachHang> list = kh_dao.selectAll();
		%>

		<!-- Search Form for Find button -->
		<form action="showDH.jsp" method="get">
			<button type="submit" class="btn btn-info mb-3">Find Orders for KH003</button>
		</form>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Makh</th>
					<th>Hoten</th>
					<th>Diachi</th>
					<th>Email</th>
					<th>Dienthoai</th>
					<th>Hành động</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (KhachHang kh : list) {
				%>
				<tr>
					<td><%=kh.getMaKH()%></td>
					<td><%=kh.getHoTen()%></td>
					<td><%=kh.getDiaChi()%></td>
					<td><%=kh.getEmail()%></td>
					<td><%=kh.getDienThoai()%></td>
					<td><a href="update.jsp?makh=<%=kh.getMaKH()%>" class="btn btn-warning btn-sm">Update</a>
						<a href="#" onclick="confirmDelete('<%=kh.getMaKH()%>')" class="btn btn-danger btn-sm">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>

		<!-- Add customer button -->
		<a href="add.jsp" class="btn btn-primary">Add New Customer</a>
	</div>
</body>
</html>
