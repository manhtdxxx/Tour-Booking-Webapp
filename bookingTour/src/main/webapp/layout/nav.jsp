<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	<div class="container-fluid px-3">

		<!-- LOGO -->
		<a class="navbar-brand me-auto d-flex align-items-center" href="#"> <img
			src="images/banner1.jpg" alt="Logo" class="rounded-circle me-2"
			style="width: 50px; height: auto;"> <span class="navbar-text text-light fw-bold">Logo</span>
		</a>

		<!-- TOGGLER BUTTON FOR MOBILE VIEW -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- NAV LINKS SECTION -->
		<div class="collapse navbar-collapse justify-content-center me-auto" id="navbarNav">
			<!-- NAV LINKS -->
			<ul class="navbar-nav">
				<li class="nav-item me-3"><a class="nav-link text-light" href="index.jsp">Trang chủ</a></li>
				<li class="nav-item dropdown me-3"><a class="nav-link dropdown-toggle text-light" href="#"
					role="button" data-bs-toggle="dropdown"> Danh Mục Sản Phẩm </a>
					<ul class="dropdown-menu shadow">
						<li><a class="dropdown-item" href="#">Link 1</a></li>
						<li><a class="dropdown-item" href="#">Another Link</a></li>
						<li><a class="dropdown-item" href="#">Third Link</a></li>
					</ul></li>
				<li class="nav-item me-3"><a class="nav-link text-light" href="#">About</a></li>
				<li class="nav-item"><a class="nav-link text-light" href="#">Contact</a></li>
			</ul>
		</div>

		<!-- LOGIN/REGISTER OR PROFILE SECTION -->
		<div class="d-flex ms-auto align-items-center gap-2">
			<%
			Object obj = session.getAttribute("khachHang");
			KhachHang khachHang = (obj != null) ? (KhachHang) obj : null;

			if (khachHang == null) {
			%>
			<a href="login.jsp" class="btn btn-outline-light text-nowrap">Đăng nhập</a> <a href="register.jsp"
				class="btn btn-primary text-nowrap">Đăng ký</a>
			<%
			} else {
			%>
			<div class="dropdown">
				<button type="button" class="btn btn-outline-light dropdown-toggle" data-bs-toggle="dropdown">
					<%=khachHang.getUsername()%>
				</button>
				<ul class="dropdown-menu dropdown-menu-end shadow">
					<li><a class="dropdown-item" href="updateProfile.jsp">Thông tin cá nhân</a></li>
					<li><a class="dropdown-item" href="changePassword.jsp">Đổi mật khẩu</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="do-logout">Thoát</a></li>
				</ul>
			</div>
			<%
			}
			%>
		</div>
	</div>
</nav>