package data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity(name = "cliente")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String address;
    private String role;
    private double wallet;

    @OneToMany(mappedBy="client", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Ticket> ticket;

    public Client() {
        super();
    }
    public Client(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return email;
    }
    public void setMail(String mail) {
        this.email = mail;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}