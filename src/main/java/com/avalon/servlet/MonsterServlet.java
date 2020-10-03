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

import com.avalon.domain.Monster;
import com.avalon.service.MonsterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class MonsterServlet
 */
@WebServlet("/MonsterServlet")
public class MonsterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MonsterService monsterService;

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

        String sid = request.getParameter("id");

        if (sid == null) {
            request.getRequestDispatcher("/WEB-INF/views/detail/detailMonster.jsp").forward(request, response);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);

            Monster monster = monsterService.queryById(id);

            Gson json = new Gson();
            String str = json.toJson(monster);
            System.out.println(str);

            PrintWriter out = response.getWriter();
            out.print(str);
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
