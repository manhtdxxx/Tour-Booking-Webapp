<%@ page import="java.util.ArrayList"%>
<%@ page import="model.DonDatHang"%>
<%@ page import="database.DonDatHangDAO"%>
<%@ page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đơn hàng của KH003</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container mt-5">
		<h2 class="mb-4">Đơn hàng của KH003</h2>

		<%
		// Get orders for customer with ID "KH003"
		DonDatHangDAO dh_dao = new DonDatHangDAO();
		ArrayList<DonDatHang> orders = dh_dao.selectOrdersByCustomerId("KH003");
		%>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Số Hóa Đơn</th>
					<th>Tên Khách Hàng</th>
					<th>Địa Chỉ</th>
					<th>Tên Hàng</th>
					<th>Số Lượng</th>
					<th>Giá</th>
					<th>Mã Nhân Viên</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (DonDatHang order : orders) {
				%>
				<tr>
					<td><%=order.getSoHoaDon()%></td>
					<td><%=order.getKhachHang().getHoTen()%></td>
					<td><%=order.getKhachHang().getDiaChi()%></td>
					<td><%=order.getTenHang()%></td>
					<td><%=order.getSoLuong()%></td>
					<td><%=order.getGia()%></td>
					<td><%=order.getNhanVien().getMaNV()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
