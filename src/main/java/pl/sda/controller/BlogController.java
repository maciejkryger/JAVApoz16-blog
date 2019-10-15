package pl.sda.controller;

import pl.sda.dao.UserDao;
import pl.sda.dao.UserDaoImpl;
import pl.sda.service.UserService;
import pl.sda.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "")
public class BlogController extends HttpServlet {

   UserService userService=new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginSession = ((String) req.getSession().getAttribute("loginSession"));
        System.out.println("doGET -> jesteś zalogowany jako: " + loginSession);
        String token = req.getParameter("regId");
        if(token!=null){
            System.out.println("activation token: " + token);
            boolean activationStatus = userService.activateUser(token);
            String activatedUser = userService.getUsernameByToken(token);
            if (activationStatus) {
                req.getSession().setAttribute("loginSession", activatedUser);
                req.setAttribute("activatedUser", "true");
                System.out.println("User: " + activatedUser + " is activated! ");
            }else {
                System.out.println("User: "+ activatedUser + " is NOT activated! ");
            }
        }
        getServletContext().getRequestDispatcher("/blog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginSession = ((String) req.getSession().getAttribute("loginSession"));
        System.out.println("do Post -> jesteś zalogowany jako: " + loginSession);

        if(loginSession == null || loginSession.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/login");
        }else {
            getServletContext().getRequestDispatcher("/").forward(req, resp);
        }
    }





}
