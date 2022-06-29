package servlet;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.IManageStudents;
import data.Client;

@WebServlet("/edit_profile")
public class EditProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageStudents manageStudents;
    private Client client = new Client();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("Register.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String current = (String) session.getAttribute("auth");
        if (manageStudents.updateUser(current, username, phone, address, email, password)){
            session.setAttribute("auth", email);
            request.getRequestDispatcher("secured/Menu.jsp").forward(request, response);
        }
        else {
            request.setAttribute("Message", "Email already existing!");
            request.getRequestDispatcher("secured/EditProfile.jsp").forward(request, response);
        }
    }
}
