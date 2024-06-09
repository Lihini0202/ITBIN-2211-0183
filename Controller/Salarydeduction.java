package employee.payroll.controller;

import employee.payroll.model.Salarydeduction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/salarydeduction")
public class SalaryDeductionController extends HttpServlet {
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
                    addSalaryDeduction(request, response);
                    break;
                case "update":
                    updateSalaryDeduction(request, response);
                    break;
                case "delete":
                    deleteSalaryDeduction(request, response);
                    break;
                case "search":
                    searchSalaryDeduction(request, response);
                    break;
                case "clear":
                    clearForm(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addSalaryDeduction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        double deductionAmount = Double.parseDouble(request.getParameter("deductionAmount"));

        String query = "INSERT INTO salary_deductions (employee_id, amount) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.setDouble(2, deductionAmount);
        statement.executeUpdate();

        response.sendRedirect("salarydeduction.jsp");
    }

    private void updateSalaryDeduction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        doubleTo complete the MVC architecture for the `Salarydeduction` interface, here is the continuation of the model and controller code, including the view:

#### Model: `SalaryDeduction.java`
```java
package employee.payroll.model;

public class SalaryDeduction {
    private int employeeId;
    private double deductionAmount;

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(double deductionAmount) {
        this.deductionAmount = deductionAmount;
    }
}
