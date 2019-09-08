package Servlets;

import Servlets.Utilclasses.DBWorker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;

/**
 * Created by Олег on 14.10.2016.
 */
@WebServlet("/Login")
public class LoginOk extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String loginField = request.getParameter("LoginField");
        String passwordField = request.getParameter("PasswordField");
        int id = DBWorker.getUserId(loginField,passwordField);//which user we have now. If nothing, then 0.
        if (!(loginField == null||loginField=="") & !(passwordField == null||passwordField=="")) {
            if (id!=0){
                HttpSession session = request.getSession();//create session for new user.
                session.setAttribute("authorized", "ok");
                session.setAttribute("Searched",0);//How much we clicked on the search button, when we are online.
                session.setAttribute("UserId",id);//Which user
                System.out.println("вход выполнен");
                ArrayList<Integer> ar = DBWorker.dBFindco("SELECT u_id FROM users.userlog WHERE u_id='"+id+"'","u_id");//quantity of current user's strings in his/her log.
                ArrayList<String> arname = DBWorker.dBFind("SELECT nickname FROM users.user2_1 WHERE email='"+loginField+"'","nickname");//nickname.
                session.setAttribute("Created",ar.size());//Which pages he/she created.
                session.setAttribute("UserName",arname.get(0));//Which nickname of current user.
                RequestDispatcher dispatcher = request.getRequestDispatcher("/hello.jsp?UID="+id);
                dispatcher.include(request, response);}
            else{
                System.out.println("вход не выполнен");
                String varTextE = "Не удалось войти в систему!";
                request.setAttribute("textE", varTextE);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp?Error=search");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
