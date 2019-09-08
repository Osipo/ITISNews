package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.HttpJspPage;
import java.io.IOException;

/**
 * Created by Олег on 31.10.2016.
 */
public abstract class SecureReg extends HttpServlet implements HttpJspPage {
    public void destroy() {
        jspDestroy();
    }

    public void init() throws ServletException {
        jspInit();
    }


    public void jspDestroy() { }

    public void jspInit() { }

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        //System.out.println(session);
        boolean isPassed = true;

        if (session != null) {
            System.out.println(session.getAttribute("authorized"));
            if (session.getAttribute("authorized") != null)
                isPassed = false;
        }
        if (!isPassed) {
            forward("/hello.jsp", request, response);
            return;
        }
        _jspService(request, response);
    }

    private void forward(String page, HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
