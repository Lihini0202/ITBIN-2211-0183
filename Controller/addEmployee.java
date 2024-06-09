package employee.payroll.controller;

import employee.payroll.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/addEmployee")
public class AddEmployeeController extends HttpServlet {
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
                    addEmployee(request, response);
                    break;
                case "update":
                    updateEmployee(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                case "search":
                    searchEmployee(request, response);
                    break;
                case "clear":
                    clearForm(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));

        String query = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, department);
        statement.setDouble(3, salary);
        statement.executeUpdate();

        response.sendRedirect("addEmployee.jsp");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));

        String query = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, department);
        statement.setDouble(3, salary);
        statement.setInt(4, employeeId);
        statement.executeUpdate();

        response.sendRedirect("addEmployee.jsp");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        String query = "DELETE FROM employees WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        statement.executeUpdate();

        response.sendRedirect("addEmployee.jsp");
    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        String query = "SELECT * FROM employees WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt("employee_id"));
            employee.setName(resultSet.getString("name"));
            employee.setDepartment(resultSet.getString("department"));
            employee.setSalary(resultSet.getDouble("salary"));
            request.setAttribute("employee", employee);
        }

        request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
    }

    private void clearForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("addEmployee.jsp");
    }
}
