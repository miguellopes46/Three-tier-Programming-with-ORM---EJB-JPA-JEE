package servlet;

import beans.IManageStudents;
import beans.IManageTrip;
import data.Client;
import data.Trip;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete_trip")
public class DeleteTrip extends HttpServlet {
    @Resource(mappedName="java:jboss/mail/Default")
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrip manageTrip;
    @EJB
    private IManageStudents manageStudents;
    private Trip trip = new Trip();
    private Session mailSession;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        session.getAttribute("auth");
        String current = (String) session.getAttribute("auth");
        Trip trip = manageTrip.getTrip(token);
        double aux = manageTrip.removeTrip(token);
        List<Client> refunded = manageTrip.PassengerFromTrip(trip.getId());
        for (Client c:refunded) {
            manageStudents.chargeWallet(c.getEmail(), aux);
        }


        session.setAttribute("wallet", manageStudents.getClientId(current).getWallet());

        List<Client> lista = manageTrip.PassengerFromTrip(Integer.parseInt(token));
        Address[] to = new InternetAddress[lista.size()];
        try {
            for(int i = 0; i<lista.size(); i++){
                try {
                    to[i] = new InternetAddress(lista.get(i).getEmail());
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            }
            MimeMessage m = new MimeMessage(mailSession);
            Address from = new InternetAddress("is2021miguel@gmail.com");
            m.setFrom(from);
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject("Bus Trip");
            m.setSentDate(new java.util.Date());
            m.setContent("Your Trip to " + trip.getDeparture() + " was cancelled!","text/plain");
            Transport.send(m);
        } catch (javax.mail.MessagingException e)
        {
            e.printStackTrace();
            System.out.println("Error in Sending Mail: "+e);
        }
        request.getRequestDispatcher("secured/MenuAdmin.jsp").forward(request, response);
    }
}
