package employee.payroll.model;

public class Allowanceemployees {
    private int employeeId;
    private double allowanceAmount;

    // Constructor
    public Allowanceemployees(int employeeId, double overtime, double medical, double bonus, double other) {
        this.employeeId = employeeId;
        this.allowanceAmount = overtime + medical + bonus + other;
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAllowanceAmount() {
        return allowanceAmount;
    }

    public void setAllowanceAmount(double allowanceAmount) {
        this.allowanceAmount = allowanceAmount;
    }
}
