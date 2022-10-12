package es.uah.session;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpSessionServlet extends HttpServlet{

    public static final String MESSAGE = "message";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        final String message = (String) req.getSession().getAttribute(MESSAGE);
        req.setAttribute(MESSAGE, message);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        final HttpSession session = req.getSession();
        session.setAttribute(MESSAGE, "Helloworld " + new Date().getTime());
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }

}
