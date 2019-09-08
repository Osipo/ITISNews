package Servlets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.HttpJspPage;
import java.io.IOException;

/**
 * Created by Олег on 16.10.2016.
 */
public abstract class SecureJSP extends HttpServlet implements HttpJspPage{
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
        HttpSession session = request.getSession();
        //System.out.println(session);
        boolean isPassed = false;

        if (session != null) {
            System.out.println(session.getAttribute("authorized"));
            if (session.getAttribute("authorized") != null)
                isPassed = true;
        }

        if (!isPassed) {
            forward("/login.jsp", request, response);
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
