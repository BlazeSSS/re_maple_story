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
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String task = request.getParameter("task");
        if ("select".equals(task)) {
            User user = userService.queryById(id);
            request.setAttribute("user", user);
            // 转发：
            // 无论开头有没有 '/' 都是接在项目名后面
            request.getRequestDispatcher("/WEB-INF/views/user/userInfo.jsp").forward(request, response);
        }
        if ("update".equals(task)) {
            String userid = request.getParameter("id");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phonenum = request.getParameter("phonenum");
            User user = new User();
            user.setId(userid);
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhonenum(phonenum);
            System.out.println("个人信息更新:" + user.toString());
            int i = userService.updateUser(user);
            session.removeAttribute("id");
            if (i != 0) {
                // "/login.jsp" 接在端口号后面
                // "login.jsp" 接在项目名后面
                response.sendRedirect("login.jsp");
            }
        }
        if ("collect".equals(task)) {

            String heroId = (String) request.getParameter("heroId");
            System.out.println("成功收藏英雄，编号：" + heroId);
            User user = userService.queryById(id);
            String collection = user.getHeroCollection();
            collection = collection + heroId + ",";
            user.setHeroCollection(collection);
            int i = userService.updateCollection(user);
            if (i != 0) {
                PrintWriter out = response.getWriter();
                out.print(collection);
            }
        }
        if ("uncollect".equals(task)) {

            String heroId = (String) request.getParameter("heroId");
            System.out.println("取消收藏英雄，编号：" + heroId);
            User user = userService.queryById(id);
            String collection = user.getHeroCollection();
            String[] collectList = collection.split(",");
            String newCollect = "";
            for (int j = 0; j < collectList.length; j++) {
                if (!collectList[j].equals(heroId)) {
                    newCollect += collectList[j] + ",";
                }
            }
            user.setHeroCollection(newCollect);
            int i = userService.updateCollection(user);
            if (i != 0) {
                PrintWriter out = response.getWriter();
                out.print(newCollect);
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
