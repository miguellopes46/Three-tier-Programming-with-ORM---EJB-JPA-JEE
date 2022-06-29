package beans;
import data.Client;
import java.util.List;
public interface IManageStudents {
    boolean addUser(Client client);
    boolean updateUser(String currentUser, String username, String phone, String address, String email, String password);
    boolean chargeWallet(String currentUser, double value);
    List<Client> listStudents();
    boolean validate(String email, String password);
    Client getClientId(String email);
    boolean pay(String email, double value);
    boolean remove_account(String email);
    String getRole(String email);
    double getWallet(String email);
    }
