<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang đăng nhập</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
    body {
        background: linear-gradient(135deg, #74b9ff, #0984e3);
        font-family: Arial, sans-serif;
    }

    .container {
        max-width: 400px;
        background-color: #fff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.2);
        animation: fadeIn 1s ease;
    }

    @keyframes fadeIn {
        from { opacity: 0; }
        to { opacity: 1; }
    }

    h1 {
        font-size: 1.8rem;
        font-weight: bold;
        color: #333;
    }

    .form-label {
        font-weight: 600;
        color: #555;
    }

    .form-control {
        border-radius: 8px;
        border: 1px solid #ced4da;
    }

    .form-check-label {
        font-size: 0.9rem;
        color: #777;
    }

    a {
        color: #0984e3;
        text-decoration: none;
    }

    a:hover {
        color: #0652dd;
        text-decoration: underline;
    }

    .btn-primary {
        background-color: #0984e3;
        border: none;
        border-radius: 8px;
        padding: 10px 20px;
        font-weight: bold;
        transition: background-color 0.3s, transform 0.2s;
    }

    .btn-primary:hover {
        background-color: #0652dd;
        transform: translateY(-2px);
    }

    p {
        margin-top: 15px;
        text-align: center;
        color: #666;
    }
</style>
</head>

<body class="d-flex align-items-center justify-content-center vh-100">

    <div class="container">
        <h1 class="text-center mb-4">Đăng nhập</h1>
        <form action="do-login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username:</label>
                <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
            </div>
            <div class="d-flex align-items-center justify-content-between mb-3">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="remember" id="remember">
                    <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
                </div>
                <a href="#">Quên mật khẩu?</a>
            </div>

            <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>

            <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký</a></p>
        </form>
    </div>

</body>
</html>
