package employee.payroll.controller;

import employee.payroll.model.Employee;
import employee.payroll.model.DBConnection;
import employee.payroll.model.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/paySlippdf")
public class PaySlipController extends HttpServlet {
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
                default:
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
        String pdfPath = request.getParameter("pdfPath");

        String query = "UPDATE payslips SET pdf_path = ? WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, pdfPath);
        statement.setInt(2, employeeId);
        statement.executeUpdate();

        response.sendRedirect("paySlippdf.jsp");
    }

    private void deletePaySlip(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        String query = "DELETE FROM payslips WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.executeUpdate();

        response.sendRedirect("paySlippdf.jsp");
    }

    private void searchPaySlip(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String empId = request.getParameter("empId");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.getEmployeeDetails(empId);

        request.setAttribute("employee", employee);
        request.getRequestDispatcher("paySlippdf.jsp").forward(request, response);
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("paySlippdf.jsp");
    }
}
