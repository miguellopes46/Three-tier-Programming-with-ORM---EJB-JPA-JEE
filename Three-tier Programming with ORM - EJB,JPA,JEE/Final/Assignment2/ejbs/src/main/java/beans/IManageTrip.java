package beans;
import data.Client;
import data.Ticket;
import data.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public interface IManageTrip {
    void addTrip(Trip trip);
    void addTicket(Ticket ticket);
    List<Trip> listTrips();
    Trip getTrip(String trip_id);
    List<Trip> searchBetween(Date begin, Date end);
    List<Trip> searchTripsByDate(Date begin);
    List<Trip> listMyTrips(String email);
    List<Client> PassengerFromTrip(int trip_id);
    double removeTrip(String trip_id);
    List<Object[]> top5();
    List<Trip> listTripsToDelete();
    List<Integer> removeSeats(String trip_id);
    List<Ticket> listTicketToReturn(String email, int id);
    void removeTicket(String email, int trip_id);
    }
