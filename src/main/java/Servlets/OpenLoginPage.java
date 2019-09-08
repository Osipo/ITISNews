package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Олег on 14.10.2016.
 */
@WebServlet("/s")
public class OpenLoginPage extends HttpServlet {
    @Override
    /*Check user, if he has already logged in, or not.*/
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        HttpSession session1 = req.getSession();//for searching.
        if(session.getAttribute("authorized")!=null){
            int id = (Integer)session.getAttribute("UserId");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp?UID="+id);
            dispatcher.forward(req, resp);
        }
        //Go to the login page, if he hasn't.
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
             }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
