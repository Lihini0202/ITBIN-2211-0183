package employee.payroll.controller;

import employee.payroll.model.AuthorizedUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/addAuthorizedUser")
public class AddAuthorizedUserController extends HttpServlet {
    private Connection connection;

    public void init() {
        conn=DBConnection.java_DBConnection();

        
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "add":
                    addAuthorizedUser(request, response);
                    break;
                case "update":
                    updateAuthorizedUser(request, response);
                    break;
                case "delete":
                    deleteAuthorizedUser(request, response);
                    break;
                case "search":
                    searchAuthorizedUser(request, response);
                    break;
                case "clear":
                    clearForm(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addAuthorizedUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String query = "INSERT INTO authorized_users (id, username, password, role) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setString(2, username);
        statement.setString(3, password);
        statement.setString(4, role);
        statement.executeUpdate();

        response.sendRedirect("addAuthorizedUser.jsp");
    }

    private void updateAuthorizedUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String query = "UPDATE authorized_users SET username = ?, password = ?, role = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, role);
        statement.setInt(4, userId);
        statement.executeUpdate();

        response.sendRedirect("addAuthorizedUser.jsp");
    }

    private void deleteAuthorizedUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        String query = "DELETE FROM authorized_users WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.executeUpdate();

        response.sendRedirect("addAuthorizedUser.jsp");
    }

    private void searchAuthorizedUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        String query = "SELECT * FROM authorized_users WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            AuthorizedUser user = new AuthorizedUser();
            user.setUserId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("addAuthorizedUser.jsp").forward(request, response);
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("addAuthorizedUser.jsp");
    }
}
