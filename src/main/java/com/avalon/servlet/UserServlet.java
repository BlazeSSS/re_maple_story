package com.avalon.servlet;

import java.io.IOException;

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
import com.avalon.util.PageDao;
import com.github.pagehelper.Page;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

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

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String task = request.getParameter("task");
        System.out.println("用户列表task:" + task);

        String userId = request.getParameter("id");
        User user = userService.queryById(userId);

        if ("select".equals(task)) {
            // 拿到模糊查询的关键字
            String name = request.getParameter("username");
            Page<User> list = null;
            int rsCount = 0;
            
            // 分页工具类
            PageDao page = new PageDao(request);
            // 获取当前页数
            int currentPage = page.getCurrentPage();
            // 获取pageSize
            int pageSize = page.getPagesize();

            if (name != null && !name.trim().equals("")) {
                list = (Page<User>) userService.queryLike(name, currentPage, pageSize);
            } else {
                list = (Page<User>) userService.queryAll(currentPage, pageSize);
            }
            rsCount = (int) list.getTotal();

            // 设置总记录数
            page.setRscount(rsCount);
            
            String pageTools = page.pagetool(PageDao.BbsText);
            request.setAttribute("pageTools", pageTools);
            request.setAttribute("userList", list);
            request.getRequestDispatcher("/WEB-INF/views/user/userList.jsp").forward(request, response);
        }

        if ("delete".equals(task)) {
            System.out.println("删除的用户id:" + user.getId());
            int i = userService.deleteUser(user.getId());
            System.out.println("成功删除条数:" + i);
            if (i != 0) {
                response.sendRedirect("UserServlet?task=select");
            }
        }

        if ("freeze".equals(task)) {
            System.out.println("冻结的用户id:" + user.getId());
            user.setIsFreeze(1);
            int i = userService.updateFreezeStatus(user);
            System.out.println("成功冻结条数:" + i);
            if (i != 0) {
                response.sendRedirect("UserServlet?task=select");
            }
        }

        if ("unfreeze".equals(task)) {
            System.out.println("解除冻结的用户id:" + user.getId());
            user.setIsFreeze(0);
            int i = userService.updateFreezeStatus(user);
            System.out.println("成功解除冻结条数:" + i);
            if (i != 0) {
                response.sendRedirect("UserServlet?task=select");
            }
        }

        // 管理员对用户的修改
        if ("beforeUpdate".equals(task)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/user/updateUser.jsp").forward(request, response);
        }

        if ("update".equals(task)) {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phonenum = request.getParameter("phonenum");
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhonenum(phonenum);
            System.out.println("更新后的用户信息:" + user.toString());
            int i = userService.updateUser(user);
            if (i != 0) {
                response.sendRedirect("UserServlet?task=select");
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
