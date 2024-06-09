package employee.payroll.controller;

import employee.payroll.model.PaySlip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/paySlippdf")
public class PaySlipController extends HttpServlet {
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
                    addPaySlip(request, response);
                    break;
                case "update":
                    updatePaySlip(request, response);
                    break;
                case "delete":
                    deletePaySlip(request, response);
                    break;
                case "search":
                    searchPaySlip(request, response);
                    break;
                case "clear":
                    clearForm(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addPaySlip(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String pdfPath = request.getParameter("pdfPath");

        String query = "INSERT INTO payslips (employee_id, pdf_path) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.setString(2, pdfPath);
        statement.executeUpdate();

        response.sendRedirect("paySlippdf.jsp");
    }

    private void updatePaySlip(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
Let's continue completing the MVC architecture for the remaining interfaces. We'll focus on `Salarydeduction` and `UpdateSalaryOfStaff`, following the same pattern as before.

### 5. Salarydeduction

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
