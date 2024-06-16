package employee.payroll.controller;

import employee.payroll.model.SalaryDeduction;
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

@WebServlet("/salarydeduction")
public class SalaryDeductionController extends HttpServlet {
    private Connection connection;

    public void init() {
        connection = DBConnection.java_DBConnection();
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
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addSalaryDeduction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        SalaryDeduction salaryDeduction = new SalaryDeduction();
        salaryDeduction.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
        salaryDeduction.setDeductionAmount(Double.parseDouble(request.getParameter("deductionAmount")));
        salaryDeduction.setTax(Double.parseDouble(request.getParameter("tax")));
        salaryDeduction.setLoan(Double.parseDouble(request.getParameter("loan")));
        salaryDeduction.setEtf(Double.parseDouble(request.getParameter("etf")));
        salaryDeduction.setEpf(Double.parseDouble(request.getParameter("epf")));
        salaryDeduction.setTotalDeduction(Double.parseDouble(request.getParameter("totalDeduction")));
        salaryDeduction.setNetSalary(Double.parseDouble(request.getParameter("netSalary")));
        salaryDeduction.setBasicSalary(Double.parseDouble(request.getParameter("basicSalary")));
        salaryDeduction.setFirstName(request.getParameter("firstName"));
        salaryDeduction.setSurname(request.getParameter("surname"));

        String query = "INSERT INTO salary_deductions (employee_id, deduction_amount, tax, loan, etf, epf, total_deduction, net_salary, basic_salary, first_name, surname) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, salaryDeduction.getEmployeeId());
        statement.setDouble(2, salaryDeduction.getDeductionAmount());
        statement.setDouble(3, salaryDeduction.getTax());
        statement.setDouble(4, salaryDeduction.getLoan());
        statement.setDouble(5, salaryDeduction.getEtf());
        statement.setDouble(6, salaryDeduction.getEpf());
        statement.setDouble(7, salaryDeduction.getTotalDeduction());
        statement.setDouble(8, salaryDeduction.getNetSalary());
        statement.setDouble(9, salaryDeduction.getBasicSalary());
        statement.setString(10, salaryDeduction.getFirstName());
        statement.setString(11, salaryDeduction.getSurname());
        statement.executeUpdate();

        response.sendRedirect("salarydeduction.jsp");
    }

    private void updateSalaryDeduction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        double deductionAmount = Double.parseDouble(request.getParameter("deductionAmount"));

        String query = "UPDATE salary_deductions SET deduction_amount = ? WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, deductionAmount);
        statement.setInt(2, employeeId);
        statement.executeUpdate();

        response.sendRedirect("salarydeduction.jsp");
    }

    private void deleteSalaryDeduction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        String query = "DELETE FROM salary_deductions WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.executeUpdate();

        response.sendRedirect("salarydeduction.jsp");
    }

    private void searchSalaryDeduction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Logic for searching a salary deduction
        double totalDeduction = tax + epf + etf + loan;
        double netSalary = basicSalary - totalDeduction;
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("salarydeduction.jsp");
    }
}
