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

/**
 * Created by Олег on 22.12.2016.
 */
@WebServlet("/Update")
public class UpdateNew extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String title = req.getParameter("TitleField");
          HttpSession s = req.getSession();
          int id = (Integer) s.getAttribute("UserId");
          String NewContent = req.getParameter("ContentField");
          String NewDesc = req.getParameter("DescField");
          if(DBWorker.UpdateNew(title,id,NewContent,NewDesc)){
              RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp?UID="+id);
              dispatcher.forward(req,resp);
          }
        else{
              RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=search");
              dispatcher.forward(req,resp);
          }
    }
}
