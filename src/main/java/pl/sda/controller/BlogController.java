package pl.sda.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "")
public class BlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginSession = ((String) req.getSession().getAttribute("loginSession"));
        System.out.println("jesteś zalogowany jako: " + loginSession);
        getServletContext().getRequestDispatcher("/blog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginSession = ((String) req.getSession().getAttribute("loginSession"));
        System.out.println("jesteś zalogowany jako: " + loginSession);
        if(loginSession == null || loginSession.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/login");
        }else {
            getServletContext().getRequestDispatcher("/").forward(req, resp);
        }


    }





}
