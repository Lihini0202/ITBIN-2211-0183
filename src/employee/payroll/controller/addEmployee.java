package employee.payroll.controller;

import employee.payroll.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

@WebServlet("/addEmployee")
public class AddEmployeeController extends HttpServlet {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String name = request.getParameter("name");
        String dateOfBirth = dateFormat.format(java.sql.Date.valueOf(request.getParameter("dateofbirth")));
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String ahno = request.getParameter("ahno");
        String postalCode = request.getParameter("postalCode");
        String department = request.getParameter("department");
        String designation = request.getParameter("designation");
        String dateOfHire = dateFormat.format(java.sql.Date.valueOf(request.getParameter("dateofhire")));
        String basicSalary = request.getParameter("basicSalary");
        String jobTitle = request.getParameter("jobTitle");
        String status = request.getParameter("status");
        byte[] personImage = request.getParameter("personImage").getBytes();
        String gender = request.getParameter("gender");

        String query = "INSERT INTO employees (name, date_of_birth, email, contact, address1, address2, ahno, postal_code, department, designation, date_of_hire, basic_salary, job_title, status, person_image, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, dateOfBirth);
        statement.setString(3, email);
        statement.setString(4, contact);
        statement.setString(5, address1);
        statement.setString(6, address2);
        statement.setString(7, ahno);
        statement.setString(8, postalCode);
        statement.setString(9, department);
        statement.setString(10, designation);
        statement.setString(11, dateOfHire);
        statement.setString(12, basicSalary);
        statement.setString(13, jobTitle);
        statement.setString(14, status);
        statement.setBytes(15, personImage);
        statement.setString(16, gender);
        statement.executeUpdate();

        response.sendRedirect("addEmployee.jsp");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
                String name = request.getParameter("name");
                String dateOfBirth = dateFormat.format(java.sql.Date.valueOf(request.getParameter("dateofbirth")));
                String email = request.getParameter("email");
                String contact = request.getParameter("contact");
                String address1 = request.getParameter("address1");
                String address2 = request.getParameter("address2");
                String ahno = request.getParameter("ahno");
                String postalCode = request.getParameter("postalCode");
                String department = request.getParameter("department");
                String designation = request.getParameter("designation");
                String dateOfHire = dateFormat.format(java.sql.Date.valueOf(request.getParameter("dateofhire")));
                String basicSalary = request.getParameter("basicSalary");
                String jobTitle = request.getParameter("jobTitle");
                String status = request.getParameter("status");
                byte[] personImage = request.getParameter("personImage").getBytes();
                String gender = request.getParameter("gender");

                String query = "INSERT INTO employees (name, date_of_birth, email, contact, address1, address2, ahno, postal_code, department, designation, date_of_hire, basic_salary, job_title, status, person_image, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, dateOfBirth);
                statement.setString(3, email);
                statement.setString(4, contact);
                statement.setString(5, address1);
                statement.setString(6, address2);
                statement.setString(7, ahno);
                statement.setString(8, postalCode);
                statement.setString(9, department);
                statement.setString(10, designation);
                statement.setString(11, dateOfHire);
                statement.setString(12, basicSalary);
                statement.setString(13, jobTitle);
                statement.setString(14, status);
                statement.setBytes(15, personImage);
                statement.setString(16, gender);
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
