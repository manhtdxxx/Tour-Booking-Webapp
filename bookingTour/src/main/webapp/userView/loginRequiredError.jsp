<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

<div class="container-fluid bg-light text-center py-3" role="alert">
	<h1 class="fw-bold text-danger">Bạn cần đăng nhập để truy cập trang này!</h1>
	<h5 class="mt-4">
		Vui lòng quay lại <a href="<%=baseURL%>/index.jsp"
			class="fw-bold text-primary text-decoration-underline">trang chủ</a>.
	</h5>
	<h5 class="mt-2">
		Hoặc truy cập <a href="<%=baseURL%>/login.jsp"
			class="fw-bold text-primary text-decoration-underline">trang đăng nhập</a> để tiếp tục.
	</h5>
</div>