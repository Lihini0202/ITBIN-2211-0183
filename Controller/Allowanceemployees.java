package employee.payroll.controller;

import employee.payroll.model.Allowanceemployees;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Allowanceemployees")
public class AllowanceController extends HttpServlet {
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
                    addAllowance(request, response);
                    break;
                case "update":
                    updateAllowance(request, response);
                    break;
                case "delete":
                    deleteAllowance(request, response);
                    break;
                case "search":
                    searchAllowance(request, response);
                    break;
                case "clear":
                    clearForm(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addAllowance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        double allowanceAmount = Double.parseDouble(request.getParameter("allowanceAmount"));

        String query = "INSERT INTO allowances (employee_id, amount) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.setDouble(2, allowanceAmount);
        statement.executeUpdate();

        response.sendRedirect("allowanceemployees.jsp");
    }

    private void updateAllowance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        double allowanceAmount = Double.parseDouble(request.getParameter("allowanceAmount"));

        String query = "UPDATE allowances SET amount = ? WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, allowanceAmount);
        statement.setInt(2, employeeId);
        statement.executeUpdate();

        response.sendRedirect("allowanceemployees.jsp");
    }

    private void deleteAllowance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        String query = "DELETE FROM allowances WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.executeUpdate();

        response.sendRedirect("allowanceemployees.jsp");
    }

    private void searchAllowance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        String query = "SELECT * FROM allowances WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Allowance allowance = new Allowance();
            allowance.setEmployeeId(resultSet.getInt("employee_id"));
            allowance.setAllowanceAmount(resultSet.getDouble("amount"));
            request.setAttribute("allowance", allowance);
        }

        request.getRequestDispatcher("Allowanceemployees.jsp").forward(request, response);
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("Allowanceemployees.jsp");
    }
}
