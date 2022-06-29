package servlet;

import beans.IManageTrip;
import beans.IManageStudents;
import data.Client;
import data.Ticket;
import data.Trip;
import javax.ejb.EJB;
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

@WebServlet("/admin")
public class AdminFunctions extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrip manageTrip;
    @EJB
    private IManageStudents manageStudents;
    private Trip trip = new Trip();




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token;

        if(request.getParameter("token") != null) {
            token = request.getParameter("token");

            //CREATE TRIP
            if (token.equals("create_trip")) {
                String departure = request.getParameter("departure");
                String arrival = request.getParameter("arrival");
                String departure_time = request.getParameter("departure_time");
                String arrival_time = request.getParameter("arrival_time");
                String capacity = request.getParameter("capacity");
                String price = request.getParameter("price");

                String arrival_aux = arrival_time.replace("T", " ");
                String departure_aux = departure_time.replace("T", " ");

                try {
                    Date arrivalf = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(arrival_aux);
                    Date departuref = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(departure_aux);

                    trip.setArrival_time(arrivalf);
                    trip.setDeparture_time(departuref);
                    trip.setPrice(Double.parseDouble(price));
                    trip.setDeparture(departure);
                    trip.setArrival(arrival);
                    trip.setCapacity(Integer.parseInt(capacity));
                    manageTrip.addTrip(trip);
                    request.getRequestDispatcher("secured/MenuAdmin.jsp").forward(request, response);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //GET SELECTED DATES
            else if (token.equals("get_data")) {
                String departure_time = request.getParameter("departure_time");
                String arrival_time = request.getParameter("arrival_time");
                String departure_aux = departure_time.replace("T", " ");
                String arrival_aux = arrival_time.replace("T", " ");
                try {
                    Date arrivalf = new SimpleDateFormat("yyyy-MM-dd").parse(arrival_aux);
                    Date departuref = new SimpleDateFormat("yyyy-MM-dd").parse(departure_aux);
                    List<Trip> list = manageTrip.searchBetween(departuref, arrivalf);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("secured/TripBetweenDate.jsp").forward(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //GET EXACT DATE
            else if (token.equals("get_exact_date")) {
                String departure_time = request.getParameter("departure_time");
                String departure_aux = departure_time.replace("T", " ");
                try {
                    Date departuref = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(departure_aux);
                    List<Trip> list = manageTrip.searchTripsByDate(departuref);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("secured/TripByDate.jsp").forward(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //GET MY TRIPS
            else if (token.equals("my_trips")) {
                HttpSession session = request.getSession();
                session.getAttribute("auth");
                String current = (String) session.getAttribute("auth");
                System.out.println(current);
                List<Trip> list = manageTrip.listMyTrips(current);
                request.setAttribute("my_list", list);
                request.getRequestDispatcher("secured/MyTrips.jsp").forward(request, response);
            }

            //DELETE USER ACCOUNT
            else if (token.equals("delete_account")) {
                HttpSession session = request.getSession();
                session.getAttribute("auth");
                String current = (String) session.getAttribute("auth");

                if (manageStudents.remove_account(current)) {
                    session.removeAttribute("auth");
                    System.out.println("Client Removed!");
                    request.getRequestDispatcher("InitialPage.jsp").forward(request, response);
                } else {
                    System.out.println("Error removing Client!");
                }
            }
            //DELETE TRIP
            else if(token.equals("delete_trip")){
                List<Trip> listTrips = manageTrip.listTripsToDelete();
                request.setAttribute("Trips", listTrips);
                request.getRequestDispatcher("secured/DeleteTrip.jsp").forward(request, response);
            }
            else if(token.equals("top5")){

                List<Object[]> list = manageTrip.top5();
                List<String> result = new ArrayList<>();

                for(Object[] a : list){
                    String aux = (String)a[0];
                    long aux1 = (long)a[1];
                    String aux1_final = String.valueOf(aux1);
                    String final_string = aux.concat("  ").concat(aux1_final);
                    result.add(final_string);
                }
                request.setAttribute("list", result);
                request.getRequestDispatcher("secured/Top5.jsp").forward(request, response);
            }
            //RETURN TRIP
            else if(token.equals("return_trip")){
                HttpSession session = request.getSession();
                session.getAttribute("auth");
                String current = (String) session.getAttribute("auth");
                int id = manageStudents.getClientId(current).getId();
                List<Ticket> listTickets = manageTrip.listTicketToReturn(current, id);
                request.setAttribute("Tickets", listTickets);
                request.getRequestDispatcher("secured/ReturnTrip.jsp").forward(request, response);
            }
        }
    }
}
