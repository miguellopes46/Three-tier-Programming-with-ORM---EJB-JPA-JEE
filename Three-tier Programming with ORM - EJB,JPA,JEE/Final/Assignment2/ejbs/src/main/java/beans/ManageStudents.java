package beans;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;

import data.Client;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
@Stateless
public class ManageStudents implements IManageStudents {
    Logger logger = LoggerFactory.logger(Client.class);
    @PersistenceContext(unitName = "playAula")
    EntityManager em;

    @Transactional
    public boolean addUser(Client client) {

        Query q = em.createQuery("From cliente s " +
                "WHERE email =:currentUser");
        q.setParameter("currentUser", client.getEmail());
        List<Client> cliente = q.getResultList();
        if (cliente.isEmpty())  {
            logger.info("Added user");
            em.merge(client);
            return true;
        }

        else {
            logger.warn("Email already exists");
            return false;
        }
    }
    public Client getClientId(String email){
        logger.debug("Getting CientId");
        TypedQuery<Client> q = em.createQuery("from cliente s where s.email =:email", Client.class);
        q.setParameter("email", email);
        Client client = q.getSingleResult();

        return client;
    }

    public double getWallet(String email){
        logger.debug("Getting CientId");
        TypedQuery<Client> q = em.createQuery("from cliente s where s.email =:email", Client.class);
        q.setParameter("email", email);
        Client client = q.getSingleResult();

        return client.getWallet();
    }

    public String getRole(String email){
        TypedQuery<Client> q = em.createQuery("from cliente s where s.email =:email", Client.class);
        q.setParameter("email", email);
        Client client = q.getSingleResult();
        return client.getRole();
    }
    public boolean updateUser(String currentUser, String username, String phone, String address, String email, String password){
        logger.debug("Updating User");
        Query t = em.createQuery("From cliente s " +
                "WHERE email =:email AND :email <> :currentUser ");
        t.setParameter("email", email);
        t.setParameter("currentUser", currentUser);
        List<Client> cliente = t.getResultList();

        if (cliente.isEmpty() || currentUser == email)  {
            Query q = em.createQuery("UPDATE cliente s " +
                    "SET name =:username," +
                    "phone =:phone," +
                    "address =:address," +
                    "email =:email," +
                    "password =:password " +
                    "WHERE email =:currentUser");
            System.out.println(currentUser);
            q.setParameter("currentUser", currentUser);
            q.setParameter("username", username);
            q.setParameter("phone", phone);
            q.setParameter("address", address);
            q.setParameter("email", email);
            q.setParameter("password", password);
            q.executeUpdate();
            logger.info("User updated! with email:" + email);
            return true;
        }
        else {
            logger.warn("Email already exists!");
            return false;
        }
    }
    public boolean chargeWallet(String currentUser, double value){
        if (value > 0) {

            Query q = em.createQuery("UPDATE cliente s " +
                    "SET wallet = wallet +:value " +
                    "WHERE email =:currentUser");
            q.setParameter("currentUser", currentUser);
            q.setParameter("value", value);
            q.executeUpdate();
            logger.warn("Wallet for " + currentUser + "updated!");
            return true;
        }

        return false;
    }
    public boolean pay(String email, double value){
        Client cliente = getClientId(email);
        if(cliente.getWallet() >= value){
            cliente.setWallet(cliente.getWallet() - value);
            logger.warn("Payed trip!" + value + " removed from" + email);
            return true;
        }
        else {
            logger.warn("No money for trip!" + (value-cliente.getWallet()) + " missing from" + email);
            return false;
        }
    }
    public List<Client> listStudents() {
        System.out.println("Retrieving students from the database...");
        TypedQuery<Client> q = em.createQuery("from cliente s", Client.class);
        List<Client> list = q.getResultList();
            logger.debug("Listing clients");
        return list;
    }
    public boolean validate(String email, String password) {
        logger.debug("validating client");
        Client client = null;

        TypedQuery<Client> q = em.createQuery("from cliente s where s.email =:email", Client.class);
        q.setParameter("email", email);
        try {
            client = q.getSingleResult();
        }catch (NoResultException a){}

        if (client != null && client.getPassword().equals(password)) {
            logger.info("Client validated");
            return true;
        }
        else {
            logger.warn("Email doesn't exist or incorrect password");
            //System.out.println("Email não existe ou password incorrent");
            return false;
        }
    }
    public boolean remove_account(String email){

        Client client_id = getClientId(email);
        logger.debug("Removing account");
        logger.warn("Removing account! With email:" + email);
        Query q = em.createQuery("delete from bilhete b where b.client =:client");
        q.setParameter("client", client_id);
        q.executeUpdate();
        Query c = em.createQuery("delete from cliente c where c.id =:client");
        c.setParameter("client", client_id.getId());
        c.executeUpdate();
        return true;
    }
}
