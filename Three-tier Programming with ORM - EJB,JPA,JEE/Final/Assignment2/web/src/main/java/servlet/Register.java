package servlet;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.IManageStudents;
import data.Client;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageStudents manageStudents;
    private Client client = new Client();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String username = request.getParameter("username");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if(username.equals("admin")){
        client.setRole("Admin");
    }else{
        client.setRole("User");
    }
    client.setName(username);
    client.setPhone(phone);
    client.setAddress(address);
    client.setMail(email);
    client.setPassword(password);

  if ( manageStudents.addUser(client)){
      request.getSession(true).setAttribute("auth", email);
      if(client.getRole().equals("Admin")) {
          request.getRequestDispatcher("secured/MenuAdmin.jsp").forward(request, response);
      }
      else{
          request.getRequestDispatcher("secured/Menu.jsp").forward(request, response);
      }
  }
  else {
      System.out.println("erroooo");
      request.setAttribute("errorMessage", "Email already exists");
      request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }
}
