package gr.aueb.cf.schoolapppro.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        boolean authenticated = false;

        String requestedURI = req.getRequestURI();
//        if(requestedURI.endsWith(".css")){
//            filterChain.doFilter(req, res);
//        } else
            Cookie[] cookies = req.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")){
                        HttpSession session = req.getSession(false);
                        authenticated = (session != null) && (session.getAttribute("loginName") != null);
                    }
                }
            }


        if (authenticated) {
            filterChain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
