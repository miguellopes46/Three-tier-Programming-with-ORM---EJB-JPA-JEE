package beans;
import data.Client;
import data.Ticket;
import data.Trip;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class ManageTrip implements IManageTrip {
    Logger logger = LoggerFactory.logger(ManageTrip.class);
    @PersistenceContext(unitName = "playAula")
    EntityManager em;

    @Transactional
    public void addTrip(Trip trip) {
        System.out.println("aqui");
        em.merge(trip);
        logger.info("Created Trip");
        logger.debug("Debug!! Created Trip with route: " + trip.getArrival() + " -> " + trip.getArrival());
    }

    @Transactional
    public void addTicket(Ticket ticket){
        em.merge(ticket);
        logger.info("Created Ticket");
        logger.debug("Debug!! Created Ticket to trip: " + ticket.getTrip().getDeparture() + "->" + ticket.getTrip().getArrival());
    }

    public double removeTrip(String trip_id){
        TypedQuery<Trip> q = em.createQuery("from viagem v where v.id =:trip_id ", Trip.class);
        q.setParameter("trip_id", Integer.parseInt(trip_id));

        Trip trip = q.getSingleResult();
        Double price = trip.getPrice();
        TypedQuery<Client> a = em.createQuery( "select b.client " +
                                                  "FROM bilhete b " +
                                                  "WHERE b.trip.id =:trip_id ", Client.class);
        a.setParameter("trip_id", Integer.parseInt(trip_id));
        List<Client> list = a.getResultList();

        for (Client client : list) {
            client.setWallet(client.getWallet() + price);
        }
        em.remove(trip);
        logger.info("Removed Trip");
        logger.warn("Removed trip! With id" + trip_id);
        return price;
    }

    public List<Integer> removeSeats(String trip_id){
        logger.debug("Removing seats for:" + trip_id);
        Query q = em.createQuery("select b.seat from bilhete b where b.trip.id=:trip_id ");
        q.setParameter("trip_id", Integer.parseInt(trip_id));
        return q.getResultList();
    }

    public void removeTicket(String email, int trip_id){
        TypedQuery<Ticket> q = em.createQuery( "from bilhete b where b.trip.id =:trip_id " +
                                                "and b.client.email =:email", Ticket.class);
        q.setParameter("trip_id", trip_id);
        q.setParameter("email", email);
        List<Ticket> list = q.getResultList();
        for(Ticket t : list) {
            em.remove(t);
            logger.info("Removed ticket:" + t);
        }
    }

    public List<Trip> listTrips() {
        System.out.println("Retrieving trips from the database...");
        TypedQuery<Trip> q = em.createQuery("from viagem s", Trip.class);
        List<Trip> list = q.getResultList();
        logger.debug("Listing all trips");
        return list;
    }

    public List<Trip> listTripsToDelete(){
        TypedQuery<Trip> q = em.createQuery("from viagem s where s.departure_time > current_timestamp ", Trip.class);
        List<Trip> list = q.getResultList();
        return list;
    }

    public List<Ticket> listTicketToReturn(String email, int id){
        System.out.println("1");
        TypedQuery<Ticket> q = em.createQuery( "from bilhete b " +
                                     "where b.trip.departure_time > current_timestamp " +
                                     "and b.client.email =:email ", Ticket.class);
        q.setParameter("email", email);
        System.out.println("2");
        List<Ticket> list = q.getResultList();
        System.out.println("3");
        return list;
    }

    public Trip getTrip(String trip_id){
        TypedQuery<Trip> q = em.createQuery( "from viagem t " +
                                                "where t.id =:trip_id ", Trip.class);

        q.setParameter("trip_id", Integer.parseInt(trip_id));
        Trip trip = q.getSingleResult();
        logger.debug("Get trip with id: " + trip_id);
        return trip;
    }

    public List<Trip> searchBetween(Date begin, Date end){
        logger.debug("Searching for a trip between dates" + begin + "->" + end);
        Query q = em.createQuery("FROM viagem t WHERE t.departure_time >= :b AND t.departure_time <= :e ");
        q.setParameter("b", begin);
        q.setParameter("e", end);
        return q.getResultList();
    }

    public List<Trip> searchTripsByDate(Date begin){
        logger.debug("Searching for a trip with beginning: " + begin);
        Query q = em.createQuery("FROM viagem t WHERE t.departure_time = :b ");
        q.setParameter("b", begin);
        return q.getResultList();
    }

    public List<Trip> listMyTrips(String email){
        logger.debug("Trips of user with email:" + email);
        System.out.println("Entrei na funcao");

        Query q = em.createQuery("select distinct b.trip FROM  cliente c, bilhete b " +
                                    "WHERE c.email =:email " +
                                    "AND b.client.id = c.id ");
        q.setParameter("email", email);

        List<Trip> list= q.getResultList();
        logger.info("Listing trips for email:" + email);
        return list;
    }

    public List<Client> PassengerFromTrip(int trip_id){
        Query q = em.createQuery("select b.client FROM bilhete b WHERE b.trip.id =:trip_id");
        q.setParameter("trip_id", trip_id);
        List<Client> list = q.getResultList();
        logger.debug("Listed passengers of the trip:" + trip_id);

        return list;
    }

    public List<Object[]> top5() {
        System.out.println("query mal");
        TypedQuery<Object[]> q = em.createQuery("Select c.name , count(distinct b.trip) " +
                                    "from cliente c, bilhete b, viagem v where b.client.id = c.id and v.arrival_time < current_timestamp " +
                                    "group by c.name order by 2 desc", Object[].class);
        System.out.println("query bem");
        List<Object[]> lista = q.getResultList();

        logger.debug("Listing top 5 clients");
        return lista;
    }
}
