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
 * Created by Олег on 24.10.2016.
 */
@WebServlet("/Reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Login = req.getParameter("LoginField");
        String Pass = req.getParameter("PasswordField");
        String Nick = req.getParameter("NickField");
        Boolean b1 = DBWorker.corlogin(Login);//FALSE IF IT IS EQUAL.
        Boolean b2 = DBWorker.corpassword(Pass);
        if((Login!=null&&Login!="")&&(Pass!=null&&Pass!="")&&(Nick!=null&&Nick!="")){
            if(DBWorker.regUser(Login,Pass,Nick)) {
                System.out.println("Регистрация прошла успешно.");
                HttpSession session = req.getSession();//сразу идём на свою страницу пользователей.
                session.setAttribute("authorized", "ok");
                session.setAttribute("UserId", DBWorker.dBFindco("SELECT u_id FROM users.user2 WHERE email='" + Login + "'", "u_id").get(0));
                session.setAttribute("Created", 0);
                session.setAttribute("Searched",0);//How much we clicked on the search button.
                session.setAttribute("UserName", Nick);// положить в сессию что то
                System.out.println("вход выполнен");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp?UID="+session.getAttribute("UserId"));
                dispatcher.forward(req, resp);
            }else if((!b1)&&(!b2)){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=regist");
                dispatcher.forward(req,resp);
            }
            else if(b1&&b2){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=regist3");
                dispatcher.forward(req,resp);
            }
            else if((!b1)){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=regist1");
                dispatcher.forward(req,resp);
            }
            else if(!(b2)){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp?Error=regist2");
                dispatcher.forward(req,resp);
            }

            }
            //IF empty.
            else {
                System.out.println("Ошибка. Поля заполнены не правильно!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/regist.jsp");
                dispatcher.forward(req, resp);
            }
    }
}
