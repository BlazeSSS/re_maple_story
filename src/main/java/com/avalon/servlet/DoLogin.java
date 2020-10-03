package com.avalon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.avalon.domain.User;
import com.avalon.service.UserService;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {

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
        // 获取页面中用户写入的参数
        String id = request.getParameter("userid");
        String password = request.getParameter("password");

        // servlet中调用service层
        User loginUser = new User();
        loginUser.setId(id);
        loginUser.setPassword(password);

        loginUser = userService.login(loginUser);
        HttpSession session = request.getSession();

        if (loginUser != null) {
            if (loginUser.getIsFreeze() == 1) {
                PrintWriter out = response.getWriter();
                out.write("<script>alert('账号被冻结 ');location.href = 'login.jsp'</script>");
            } else {
                String userid = loginUser.getId();
                String username = loginUser.getName();
                session.setAttribute("id", userid);
                session.setAttribute("name", username);
                response.sendRedirect("welcome.jsp");
            }
        } else {
            // 获取打印流
            PrintWriter out = response.getWriter();
            out.write("<script>alert('登录失败 ');location.href = 'login.jsp'</script>");
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
