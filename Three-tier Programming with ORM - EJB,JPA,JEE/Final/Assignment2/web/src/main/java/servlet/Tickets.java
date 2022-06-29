package servlet;

import beans.IManageStudents;
import beans.IManageTrip;
import data.Client;
import data.Ticket;
import data.Trip;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/tickets")
public class Tickets extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrip manageTrip;
    @EJB
    private IManageStudents manageStudents;
    private Ticket ticket = new Ticket();
    private Trip trip = new Trip();
    String trip_id;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Trip> listTrips = manageTrip.listTrips();
        if(listTrips.isEmpty()) {
            request.setAttribute("ErrorMessage", "No trips available!");
            request.getRequestDispatcher("secured/Menu.jsp").forward(request, response);
        }
        else {
            request.setAttribute("Trips", listTrips);
            System.out.println(listTrips.get(0).getArrival());
            request.getRequestDispatcher("secured/Tickets.jsp").forward(request, response);
        }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        String current = (String) session.getAttribute("auth");
        if(token.equals("seat_selected")) {

            trip = manageTrip.getTrip(trip_id);
            if ((trip.getPrice() <= manageStudents.getWallet(current)))  {


                ticket.setTrip(trip);

                int seat = Integer.parseInt(request.getParameter("seat_list"));
                System.out.println(seat);

                ticket.setSeat(seat);

                ticket.setClient(manageStudents.getClientId(current));
                manageStudents.pay(current, trip.getPrice());
                request.getSession(true).setAttribute("wallet", manageStudents.getClientId(current).getWallet());
                manageTrip.addTicket(ticket);
                request.getRequestDispatcher("secured/Menu.jsp").forward(request, response);

            } else {
                request.setAttribute("ErrorMessage", "No money for this trip!");
                request.getRequestDispatcher("secured/SelectSeat.jsp").forward(request, response);
            }

        }else if (token.equals("back")) {
           {request.getRequestDispatcher("secured/Tickets.jsp").forward(request, response);}
        }
        else {
            trip_id = token;
            List<Integer> aux = new ArrayList<Integer>();
            List<Integer> list = manageTrip.removeSeats(trip_id);
            for(int i = 0; i<manageTrip.getTrip(token).getCapacity(); i++){
                aux.add(i + 1);
            }
            for(int i = 0; i<list.size(); i++){
                if(aux.contains(list.get(i))){
                    aux.remove(list.get(i));
                }
            }

            request.setAttribute("seats", aux);
            request.getRequestDispatcher("secured/SelectSeat.jsp").forward(request, response);
        }
    }
}