package employee.payroll.controller;

import employee.payroll.model.SalaryUpdate;
import employee.payroll.model.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/updateSalaryOfStaff")
public class UpdateSalaryOfStaffController extends HttpServlet {
    private Connection connection;

    public void init() {
        connection = DBConnection.java_DBConnection();
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
        int id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String dateOfBirth = request.getParameter("dateOfBirth");
        double basicSalary = Double.parseDouble(request.getParameter("basicSalary"));
        String department = request.getParameter("department");
        double amount = Double.parseDouble(request.getParameter("amount"));
        double percentage = Double.parseDouble(request.getParameter("percentage"));

        String query = "UPDATE employees SET firstname = ?, surname = ?, date_of_birth = ?, basic_salary = ?, department = ?, amount = ?, percentage = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, firstname);
        statement.setString(2, surname);
        statement.setString(3, dateOfBirth);
        statement.setDouble(4, basicSalary);
        statement.setString(5, department);
        statement.setDouble(6, amount);
        statement.setDouble(7, percentage);
        statement.setInt(8, id);
        statement.executeUpdate();

        response.sendRedirect("updateSalaryOfStaff.jsp");
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("updateSalaryOfStaff.jsp");
    }
}
