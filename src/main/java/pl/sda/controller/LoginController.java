package pl.sda.controller;

import pl.sda.service.UserService;
import pl.sda.service.UserServiceImpl;
import pl.sda.util.EmailRegister;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {

    EmailRegister emailRegister = new EmailRegister();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
//        String password = req.getParameter("password");

        System.out.println("login is sent as :" + login);

        String action = req.getParameter("action");
        switch (action) {
            case "login":
                System.out.println("Action: login");
                login(req, resp);
                break;
            case "register":
                System.out.println("Action: register");
                register(req, resp);
                break;
            default:
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + "/");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserService userService = new UserServiceImpl();

        if (login.trim() != null && !login.trim().isEmpty()
                && password.trim() != null && !password.trim().isEmpty() && userService.isUserValid(login, password)) {
            System.out.println("przechodzi if z doPOST");
            req.getSession().setAttribute("loginSession", login);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            System.out.println("else z doPOST");
            req.setAttribute("errorLogin", "true");
            getServletContext().getRequestDispatcher("/blog.jsp").forward(req, resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        System.out.println("register: login " + login + " password " + password);

        UserService userService = new UserServiceImpl();

        if (login.trim() != null && !login.trim().isEmpty()
                && password.trim() != null && !password.trim().isEmpty()) {
            System.out.println("Password validation is ok !");
            String token = userService.addUser(login, password);
            req.setAttribute("confirmRegister", "true");
            System.out.println("New username " + login + " is added!");
            emailRegister.send(login, token);
            resp.sendRedirect(req.getContextPath() + "/");

        } else {
            System.out.println("Password  is not valid !");
            req.setAttribute("errorRegister", "true");
            getServletContext().getRequestDispatcher("/").forward(req, resp);
            System.out.println("New username is not added!");
        }
    }

}
