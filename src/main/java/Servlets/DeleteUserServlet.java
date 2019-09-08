package Servlets;

import Servlets.Utilclasses.DBWorker;

import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Олег on 16.12.2016.
 */
@WebServlet("/Delete")
public class DeleteUserServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Predicate = req.getParameter("TextTitleD");
        if(Predicate!=null||Predicate!=""){
            if(DBWorker.deleteUser(Predicate)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp?UID=1");
                dispatcher.forward(req, resp);
            }
            else{
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=search");
                dispatcher.forward(req, resp);
            }
        }
    }
}
