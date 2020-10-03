package com.avalon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.avalon.domain.User;
import com.avalon.service.UserService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        ServletContext servletContext = this.getServletContext();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletContext);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从注册页面获取输入参数
        String id = request.getParameter("userid");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phonenum = request.getParameter("phonenum");
        // 获取打印流
        PrintWriter out = response.getWriter();

        if (name == null || name.trim().equals("") || id == null || id.trim().equals("")) {
            out.write("<script>alert('昵称不能为空 ');location.href = 'register.jsp'</script>");
        } else {

            User newUser = new User(id, name, password, email, phonenum, 0, 0);
            // 调用service
            int i = userService.register(newUser);
            if (i != 0) {
                out.write("<script>alert('注册成功 ');location.href = 'login.jsp'</script>");
//				response.sendRedirect("login.jsp"); //这条代码会立即跳转而不显示alert
            } else {
                out.write("<script>alert('注册失败，账号已存在 ');location.href = 'register.jsp'</script>");
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
