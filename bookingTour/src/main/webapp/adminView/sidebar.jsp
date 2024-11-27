<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

<div class="d-flex flex-column flex-shrink-0 bg-dark text-white p-3"
	style="width: 280px; height: 100vh;">
	<a href="/"
		class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
		<span class="fs-4">🌟 Sidebar</span>
	</a>
	<hr>
	<ul class="nav nav-pills flex-column mb-auto">
		<li class="nav-item"><a href="<%=baseURL%>/adminView/adminHome.jsp"
			class="nav-link text-white <%=request.getRequestURI().contains("adminHome.jsp") ? "active" : ""%>"
			aria-current="page">🏠 Home</a></li>
		<li><a href="#" class="nav-link text-white">📊 Dashboard</a></li>
		<li><a href="<%=baseURL%>/adminView/manageOrder.jsp"
			class="nav-link text-white <%=request.getRequestURI().contains("manageOrder.jsp") ? "active" : ""%>">📋
				Orders</a></li>
		<li><a href="<%=baseURL%>/adminView/manageProduct.jsp"
			class="nav-link text-white <%=request.getRequestURI().contains("Product.jsp") ? "active" : ""%>">📦
				Products</a></li>
		<li><a href="<%=baseURL%>/adminView/manageCustomer.jsp"
			class="nav-link text-white <%=request.getRequestURI().contains("manageCustomer.jsp") ? "active" : ""%>">👥
				Customers</a></li>
	</ul>
	<hr>
	<div class="dropdown">
		<a href="#"
			class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
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
