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
 * Created by Олег on 31.10.2016.
 */
@WebServlet("/Create")
public class CreateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String Key = request.getParameter("TitleField").toUpperCase();
        String Type = request.getParameter("ContentField").toLowerCase();
        String Desc = request.getParameter("DescField");
        HttpSession s = request.getSession(false);//get current session.
        if ((Key != null && Key != "") && (Type != null && Type != "") && (Desc != null && Desc != "")) {
            if (DBWorker.corlog(Key)) {
                DBWorker.addLog(Key, Type, Desc, s.getAttribute("UserId"));
                ArrayList<Integer> ar = DBWorker.dBFindco("SELECT u_id FROM users.userlog WHERE u_id='" + s.getAttribute("UserId") + "'", "u_id");
                s.setAttribute("Created", ar.size());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/hello.jsp?UID="+s.getAttribute("UserId"));
                dispatcher.forward(request, resp);
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp?Error=exist");
                dispatcher.forward(request, resp);
            }
        }
            else {
                System.out.println("Error! Invalid data!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/createnew.jsp");
                dispatcher.forward(request, resp);
            }
        }
    }