/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.96
 * Generated at: 2024-11-09 14:04:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Trang đăng nhập</title>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\"\r\n");
      out.write("	rel=\"stylesheet\">\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("body {\r\n");
      out.write("	background: linear-gradient(135deg, #a29bfe, #6c5ce7);\r\n");
      out.write("	font-family: Arial, sans-serif;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".container {\r\n");
      out.write("	max-width: 400px;\r\n");
      out.write("	background-color: #fff;\r\n");
      out.write("	padding: 35px;\r\n");
      out.write("	border-radius: 12px;\r\n");
      out.write("	box-shadow: 0px 12px 35px rgba(0, 0, 0, 0.25);\r\n");
      out.write("	animation: fadeIn 1.2s ease;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("@\r\n");
      out.write("keyframes fadeIn {from { opacity:0;\r\n");
      out.write("	transform: translateY(-20px);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("to {\r\n");
      out.write("	opacity: 1;\r\n");
      out.write("	transform: translateY(0);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("h1 {\r\n");
      out.write("	font-size: 2rem;\r\n");
      out.write("	font-weight: bold;\r\n");
      out.write("	color: #2d3436;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".form-label {\r\n");
      out.write("	font-weight: 600;\r\n");
      out.write("	color: #2d3436;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".form-control {\r\n");
      out.write("	border-radius: 10px;\r\n");
      out.write("	border: 1px solid #ced4da;\r\n");
      out.write("	transition: border-color 0.3s, box-shadow 0.3s;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".form-control:focus {\r\n");
      out.write("	border-color: #6c5ce7;\r\n");
      out.write("	box-shadow: 0 0 6px rgba(108, 92, 231, 0.3);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".form-check-label {\r\n");
      out.write("	font-size: 0.9rem;\r\n");
      out.write("	color: #636e72;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a {\r\n");
      out.write("	color: #6c5ce7;\r\n");
      out.write("	text-decoration: none;\r\n");
      out.write("	transition: color 0.2s;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a:hover {\r\n");
      out.write("	color: #341f97;\r\n");
      out.write("	text-decoration: underline;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".btn-primary {\r\n");
      out.write("	background-color: #6c5ce7;\r\n");
      out.write("	border: none;\r\n");
      out.write("	border-radius: 10px;\r\n");
      out.write("	padding: 12px 25px;\r\n");
      out.write("	font-weight: bold;\r\n");
      out.write("	transition: background-color 0.3s, transform 0.2s;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".btn-primary:hover {\r\n");
      out.write("	background-color: #341f97;\r\n");
      out.write("	transform: translateY(-3px);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("p {\r\n");
      out.write("	margin-top: 18px;\r\n");
      out.write("	text-align: center;\r\n");
      out.write("	color: #636e72;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"d-flex align-items-center justify-content-center vh-100\">\r\n");
      out.write("	");

	String error = (String) request.getAttribute("error");
	error = (error != null) ? error : "";
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<div class=\"container\">\r\n");
      out.write("		<h1 class=\"text-center mb-4\">Đăng nhập</h1>\r\n");
      out.write("\r\n");
      out.write("		<!-- Error Message -->\r\n");
      out.write("		");

		if (!error.isEmpty()) {
		
      out.write("\r\n");
      out.write("		<div class=\"alert alert-danger alert-dismissible fade show\">\r\n");
      out.write("			<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\"></button>\r\n");
      out.write("			<strong>");
      out.print(error);
      out.write("</strong>\r\n");
      out.write("		</div>\r\n");
      out.write("		");

		}
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("		<form action=\"do-login\" method=\"post\">\r\n");
      out.write("			<div class=\"mb-3\">\r\n");
      out.write("				<label for=\"username\" class=\"form-label\">Username:</label> <input type=\"text\"\r\n");
      out.write("					class=\"form-control\" id=\"username\" placeholder=\"Enter username\" name=\"username\" required>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"mb-3\">\r\n");
      out.write("				<label for=\"password\" class=\"form-label\">Password:</label> <input type=\"password\"\r\n");
      out.write("					class=\"form-control\" id=\"password\" placeholder=\"Enter password\" name=\"password\" required>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"d-flex align-items-center justify-content-between mb-3\">\r\n");
      out.write("				<div class=\"form-check\">\r\n");
      out.write("					<input class=\"form-check-input\" type=\"checkbox\" name=\"remember\" id=\"remember\"> <label\r\n");
      out.write("						class=\"form-check-label\" for=\"remember\">Ghi nhớ đăng nhập</label>\r\n");
      out.write("				</div>\r\n");
      out.write("				<a href=\"#\">Quên mật khẩu?</a>\r\n");
      out.write("			</div>\r\n");
      out.write("\r\n");
      out.write("			<button type=\"submit\" class=\"btn btn-primary w-100\">Đăng nhập</button>\r\n");
      out.write("\r\n");
      out.write("			<p>\r\n");
      out.write("				Chưa có tài khoản? <a href=\"register.jsp\">Đăng ký</a>\r\n");
      out.write("			</p>\r\n");
      out.write("		</form>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
