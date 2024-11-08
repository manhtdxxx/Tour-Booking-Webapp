package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class Register
 */
@WebServlet("/do-register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_2 = request.getParameter("password2");
		
		request.setAttribute("username", username);
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.setAttribute("password2", password_2);
		
		KhachHangDAO kh_dao = new KhachHangDAO();
		String error = "";
		String url = "";
		
		if (kh_dao.checkExistence(username)) {
			error += "Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác! <br/>";
		}
		
		if (!password.equals(password_2)) {
			error += "Mật khẩu không khớp! <br/>";
		}
		
		request.setAttribute("error", error);
		
		if (error.length() > 0) {
			url = "/register.jsp";
		} else {
			//
			KhachHang kh = new KhachHang();
			kh.setUsername(username);
			kh.setPassword(password);
			kh.setMaKH(kh_dao.generateNewMaKH());
			kh_dao.insert(kh);
			//
			url = "/success.jsp";
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
