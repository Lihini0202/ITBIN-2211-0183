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
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String datehired = request.getParameter("datehired");
        double basicSalary = Double.parseDouble(request.getParameter("basicsalary"));
        String department = request.getParameter("department");
        double overtime = Double.parseDouble(request.getParameter("overtime"));
        double medical = Double.parseDouble(request.getParameter("medical"));
        double bonus = Double.parseDouble(request.getParameter("bonus"));
        double other = Double.parseDouble(request.getParameter("other"));

        String query = "INSERT INTO allowances (employee_id, firstname, surname, datehired, basicsalary, department, overtime, medical, bonus, other) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.setString(2, firstname);
        statement.setString(3, surname);
        statement.setString(4, datehired);
        statement.setDouble(5, basicSalary);
        statement.setString(6, department);
        statement.setDouble(7, overtime);
        statement.setDouble(8, medical);
        statement.setDouble(9, bonus);
        statement.setDouble(10, other);
        statement.executeUpdate();

        response.sendRedirect("allowanceemployees.jsp");
    }

    private void updateAllowance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String datehired = request.getParameter("datehired");
        double basicSalary = Double.parseDouble(request.getParameter("basicsalary"));
        String department = request.getParameter("department");
        double overtime = Double.parseDouble(request.getParameter("overtime"));
        double medical = Double.parseDouble(request.getParameter("medical"));
        double bonus = Double.parseDouble(request.getParameter("bonus"));
        double other = Double.parseDouble(request.getParameter("other"));

        String query = "UPDATE allowances SET firstname = ?, surname = ?, datehired = ?, basicsalary = ?, department = ?, overtime = ?, medical = ?, bonus = ?, other = ? WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, firstname);
        statement.setString(2, surname);
        statement.setString(3, datehired);
        statement.setDouble(4, basicSalary);
        statement.setString(5, department);
        statement.setDouble(6, overtime);
        statement.setDouble(7, medical);
        statement.setDouble(8, bonus);
        statement.setDouble(9, other);
        statement.setInt(10, employeeId);
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
            Allowanceemployees allowance = new Allowanceemployees();
            allowance.setEmployeeId(resultSet.getInt("employee_id"));
            allowance.setFirstname(resultSet.getString("firstname"));
            allowance.setSurname(resultSet.getString("surname"));
            allowance.setDatehired(resultSet.getString("datehired"));
            allowance.setBasicsalary(resultSet.getDouble("basicsalary"));
            allowance.setDepartment(resultSet.getString("department"));
            allowance.setOvertime(resultSet.getDouble("overtime"));
            allowance.setMedical(resultSet.getDouble("medical"));
            allowance.setBonus(resultSet.getDouble("bonus"));
            allowance.setOther(resultSet.getDouble("other"));
            request.setAttribute("allowance", allowance);
        }

        request.getRequestDispatcher("Allowanceemployees.jsp").forward(request, response);
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("allowanceemployees.jsp");
    }
}
