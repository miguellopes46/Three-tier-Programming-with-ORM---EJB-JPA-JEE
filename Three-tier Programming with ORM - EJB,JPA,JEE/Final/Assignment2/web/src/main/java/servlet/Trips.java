package servlet;

import beans.IManageTrip;
import data.Client;
import data.Ticket;
import data.Trip;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/trips")
public class Trips extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrip manageTrip;
    private Trip trip = new Trip();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Trip> listTrips = manageTrip.listTrips();
        request.setAttribute("Trips", listTrips);
        request.getRequestDispatcher("secured/Trips.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        List<Client> passenger = manageTrip.PassengerFromTrip(Integer.parseInt(token));
        request.setAttribute("list", passenger);
        request.getRequestDispatcher("secured/ListOfPassengers.jsp").forward(request, response);

    }
}
