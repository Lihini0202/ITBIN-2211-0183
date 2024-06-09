package employee.payroll.controller;

import employee.payroll.model.SalaryUpdate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/updateSalaryOfStaff")
public class UpdateSalaryOfStaffController extends HttpServlet {
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
                case "update":
                    updateSalary(request, response);
                    break;
                case "clear":
                    clearForm(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateSalary(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        double newSalary = Double.parseDouble(request.getParameter("newSalary"));

        String query = "UPDATE employees SET salary = ? WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, newSalary);
        statement.setInt(2, employeeId);
        statement.executeUpdate();

        response.sendRedirect("updateSalaryOfStaff.jsp");
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("updateSalaryOfStaff.jsp");
    }
}
