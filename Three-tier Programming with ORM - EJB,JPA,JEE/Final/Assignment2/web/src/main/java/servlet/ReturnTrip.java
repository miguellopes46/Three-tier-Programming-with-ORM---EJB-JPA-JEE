package servlet;

import beans.IManageStudents;
import beans.IManageTrip;
import data.Client;
import data.Ticket;
import data.Trip;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/return_trip")
public class ReturnTrip extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrip manageTrip;
    @EJB
    private IManageStudents manageStudents;
    private Trip trip = new Trip();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        Trip aux = manageTrip.getTrip(token);
        HttpSession session = request.getSession();
        session.getAttribute("auth");
        String current = (String) session.getAttribute("auth");
        manageStudents.chargeWallet(current, aux.getPrice());
        double value = manageStudents.getClientId(current).getWallet();
        request.getSession(true).setAttribute("wallet", value);
        manageTrip.removeTicket(current, aux.getId());
        request.getRequestDispatcher("secured/Menu.jsp").forward(request, response);
    }
}
