package pl.sda.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "PostServlet")
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter");
        String loginSession = ((String)req.getSession().getAttribute("loginSession"));
        if(loginSession!=null){
            chain.doFilter(req,res);
        }else {
            res.sendRedirect(req.getContextPath() + "/");
        }

    }
}
