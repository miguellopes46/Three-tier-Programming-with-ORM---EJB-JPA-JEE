package servlet;

import data.Client;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value="/mail")
public class MailServlet extends HttpServlet {
    @Resource(mappedName="java:jboss/mail/Default")
    private Session mailSession;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {{

            PrintWriter out=response.getWriter();
            try
            {
                MimeMessage m = new MimeMessage(mailSession);
                Address from = new InternetAddress("is2021miguel@gmail.com");
              //  Address[] to = new InternetAddress[] {new InternetAddress("miguel.pimenta.13@gmail.com") };
                Address[] to = new InternetAddress[10];
               // to[0] = new InternetAddress("miguel.pimenta.13@gmail.com");
                m.setFrom(from);
                m.setRecipients(Message.RecipientType.TO, to);
                m.setSubject("WildFly Mail");
                        m.setSentDate(new java.util.Date());
                m.setContent("Mail sent from WildFly","text/plain");
                Transport.send(m);
                out.println("Mail sent!");
                request.getRequestDispatcher("secured/MenuAdmin.jsp").forward(request, response);
            }
            catch (javax.mail.MessagingException e)
            {
                e.printStackTrace();
                out.println("Error in Sending Mail: "+e);
            }
        }
    }
}