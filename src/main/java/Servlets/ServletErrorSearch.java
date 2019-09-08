package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Олег on 07.11.2016.
 */
@WebServlet("/Error")
public class ServletErrorSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session.getAttribute("authorized")!=null){
           int id = (Integer)session.getAttribute("UserId");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp?UID="+id);
            dispatcher.forward(req,resp);
        }
        else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req,resp);
        }
    }
}
