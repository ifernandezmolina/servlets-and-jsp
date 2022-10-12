package es.uah.lifecycle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{

    public MyServlet(){
        super();
        System.out.println("--> Calling servlet constructor");
    }

    @Override
    public void init(){
        System.out.println("--> Calling init() method. This method should be called only when the servlet is created.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("--> Calling doGet(req, resp)");
    }

    @Override
    public void destroy(){
        System.out.println("--> Destroying MyServlet.");
    }

}
