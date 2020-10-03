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

import com.avalon.domain.Hero;
import com.avalon.domain.User;
import com.avalon.service.HeroService;
import com.avalon.service.UserService;
import com.avalon.util.PageDao;
import com.github.pagehelper.Page;

/**
 * Servlet implementation class HeroServlet
 */
@WebServlet("/HeroServlet")
public class HeroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HeroService heroService;

    @Autowired
    private UserService userService;

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        ServletContext servletContext = this.getServletContext();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletContext);
    }
    
    @SuppressWarnings("resource")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String task = request.getParameter("task");
        System.out.println("英雄列表任务task：" + task);

        Page<Hero> list = null;
        
        PageDao page = new PageDao(request);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPagesize();
        int rsCount = 0;
        
        if ("init".equals(task)) {
            list = (Page<Hero>) heroService.queryAll(currentPage, pageSize);
            rsCount = (int) list.getTotal();

            page.setRscount(rsCount);
            String pageTools = page.pagetool(PageDao.BbsText);

            request.setAttribute("pageTools", pageTools);
            request.setAttribute("heroList", list);
            request.getRequestDispatcher("/WEB-INF/views/dataList/heroList.jsp").forward(request, response);
        }

        if ("select".equals(task)) {
            String name = request.getParameter("heroname");

            if (name != null && !name.trim().equals("")) {
                list = (Page<Hero>) heroService.queryLike(name, currentPage, pageSize);
            } else {
                list = (Page<Hero>) heroService.queryAll(currentPage, pageSize);
            }
            rsCount = (int) list.getTotal();

            // 设置总记录数
            page.setRscount(rsCount);

            String pageTools = page.pagetool(PageDao.BbsText);
            request.setAttribute("pageTools", pageTools);
            request.setAttribute("heroList", list);
            request.getRequestDispatcher("/WEB-INF/views/dataList/heroList.jsp").forward(request, response);
        }

        if ("detail".equals(task)) {
            String id = request.getParameter("id");
            int heroId = Integer.parseInt(id);
            Hero hero = heroService.queryById(heroId);

            String userid = (String) request.getSession().getAttribute("id");
            User user = userService.queryById(userid);

            request.setAttribute("user", user);
            request.setAttribute("hero", hero);
            request.getRequestDispatcher("/WEB-INF/views/detail/detailHero.jsp").forward(request, response);
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
