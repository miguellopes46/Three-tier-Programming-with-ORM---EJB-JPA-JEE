package data;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity(name = "viagem")
public class Trip {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String departure;
    private String arrival;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departure_time ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrival_time;
    private int capacity;

    private double price;

    @OneToMany(mappedBy="trip", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Ticket> ticket;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDeparture() {
        return departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public String getArrival() {
        return arrival;
    }
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    public Date getDeparture_time() {
        return departure_time;
    }
    public void setDeparture_time(Date  departure_time) {
        this.departure_time = departure_time;
    }
    public Date  getArrival_time() {
        return arrival_time;
    }
    public void setArrival_time(Date  arrival_time) {
        this.arrival_time = arrival_time;
    }
    public List<Ticket> getTicket() {
        return ticket;
    }
    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}