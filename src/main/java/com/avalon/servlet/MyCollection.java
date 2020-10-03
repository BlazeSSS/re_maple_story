package com.avalon.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.avalon.domain.Hero;
import com.avalon.domain.User;
import com.avalon.service.HeroService;
import com.avalon.service.UserService;

/**
 * Servlet implementation class MyCollection
 */
@WebServlet("/MyCollection")
public class MyCollection extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    @Autowired
    private HeroService heroService;

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

        User user = userService.queryById(id);

        String userHeroCollection = user.getHeroCollection().trim();
        String[] heroIdList = null;
        int heroId = 0;
        List<Hero> heroList = new ArrayList<Hero>();

        if ("".equals(userHeroCollection) || userHeroCollection == null) {

        } else {
            heroIdList = userHeroCollection.split(",");
            for (int i = 0; i < heroIdList.length; i++) {
                heroId = Integer.parseInt(heroIdList[i]);
                Hero hero = heroService.queryById(heroId);
                heroList.add(hero);
            }
        }

        request.setAttribute("heroList", heroList);
        request.getRequestDispatcher("/WEB-INF/views/user/myCollection.jsp").forward(request, response);
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
