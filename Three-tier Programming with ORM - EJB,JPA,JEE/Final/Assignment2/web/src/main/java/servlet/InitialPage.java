package servlet;

import beans.IManageStudents;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/InitialPage")
public class InitialPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageStudents manageStudents;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("auth");
        request.getRequestDispatcher("InitialPage.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.getAttribute("auth");
        String current = (String) session.getAttribute("auth");
        String value = request.getParameter("valor");
        if (manageStudents.chargeWallet(current, Double.parseDouble(value))){
            double valor = manageStudents.getClientId(current).getWallet();
            request.getSession(true).setAttribute("wallet", valor);
            request.getRequestDispatcher("secured/Menu.jsp").forward(request, response);
        }
        else {
            request.setAttribute("ErrorMessage", "Introduce a positive amount!");
            request.getRequestDispatcher("secured/MyWallet.jsp").forward(request, response);
        }
    }
}
