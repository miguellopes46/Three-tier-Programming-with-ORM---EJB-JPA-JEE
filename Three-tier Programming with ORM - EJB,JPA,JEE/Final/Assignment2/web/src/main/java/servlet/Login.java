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

@WebServlet("/login")
public class Login extends HttpServlet {
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

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        client.setMail(email);
        client.setPassword(password);

        if (manageStudents.validate(email, password)) {
            boolean auth = email.equals(email) && password.equals(password);
            if(auth){
                request.getSession(true).setAttribute("auth", email);
                double value = manageStudents.getClientId(email).getWallet();
                request.getSession(true).setAttribute("wallet", value);
                if(manageStudents.getRole(email).equals("Admin")) {
                    request.getRequestDispatcher("secured/MenuAdmin.jsp").forward(request, response);
                }
                else{
                    request.getRequestDispatcher("secured/Menu.jsp").forward(request, response);
                }
            }
            else{
                request.getSession(true).removeAttribute("auth");
                request.setAttribute("errorMessage", "Invalid email or password");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
