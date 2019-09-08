package Servlets;

import Servlets.Utilclasses.DBWorker;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Олег on 05.11.2016.
 */
@WebServlet("/Search")
public class SearchServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       char[] ars = req.getParameter("SearchField").toUpperCase().toCharArray();
        ArrayList<String> Answers = DBWorker.search(ars);
        HttpSession s = req.getSession(false);
        if(Answers.size()==0){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=search");
            dispatcher.forward(req,resp);
        }
        else {
            if(s.getAttribute("authorized")!=null)
            s.setAttribute("Searched",((Integer)s.getAttribute("Searched")+1));
            req.setAttribute("Searchlist", Answers);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/searchpage.jsp");
            dispatcher.include(req, resp);
        }
    }
}
