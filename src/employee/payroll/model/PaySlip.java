package employee.payroll.model;

public class PaySlip {
    private int employeeId;
    private String firstName;
    private String surname;
    private String jobTitle;
    private double basicSalary;
    private double medical;
    private double overtime;
    private double other;
    private double bonus;
    private double totalAllowance;
    private double loan;
    private double tax;
    private double etf;
    private double epf;
    private double totalDeduction;
    private double netSalary;
    private String pdfPath;

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getMedical() {
        return medical;
    }

    public void setMedical(double medical) {
        this.medical = medical;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getEtf() {
        return etf;
    }

    public void setEtf(double etf) {
        this.etf = etf;
    }

    public double getEpf() {
        return epf;
    }

    public void setEpf(double epf) {
        this.epf = epf;
    }

    public double getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }
}
