import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Connect to your database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password");

            // Insert user data into the database
            PreparedStatement stmt = con.prepareStatement("INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                response.sendRedirect("success.html"); // Redirect to a success page
            } else {
                response.sendRedirect("error.html"); // Redirect to an error page
            }

            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
