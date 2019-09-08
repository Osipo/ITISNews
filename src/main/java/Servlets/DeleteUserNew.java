package Servlets;

import Servlets.Utilclasses.DBWorker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Олег on 18.12.2016.
 */
@WebServlet("/DeleteNew")
public class DeleteUserNew extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        int id = (Integer) s.getAttribute("UserId");
        String title = req.getParameter("TextTitleD");
            if (DBWorker.DeleteUserNew(title, id)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp?UID=" + id);
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=search");
                dispatcher.forward(req, resp);
            }
        }
    }
