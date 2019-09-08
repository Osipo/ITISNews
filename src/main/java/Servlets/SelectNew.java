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
 * Created by Олег on 22.12.2016.
 */
@WebServlet("/Select")
public class SelectNew extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> Titles = new ArrayList<String>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<Integer> u_ids = new ArrayList<Integer>();
        HttpSession s = req.getSession();
        int id = (Integer) s.getAttribute("UserId");
        String title = req.getParameter("TextTitle");
        if(DBWorker.SelectNewById(id,title,Titles,u_ids)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("table.jsp");
            req.setAttribute("Titles",Titles);
            req.setAttribute("U_IDS",u_ids);
            dispatcher.include(req,resp);
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=search");
            dispatcher.forward(req,resp);
        }
    }
}