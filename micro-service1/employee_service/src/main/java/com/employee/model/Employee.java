package com.employee.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 55683
 */

@Entity
@Table(name = "Employee", indexes = @Index(name = "employee_index", columnList = "employeeID"))
public class Employee extends ErrorResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeID;
    @NotNull(message = "First Name can't null")
    @NotEmpty(message = "First Name can't empty")
    private String firstName;

    @NotNull(message = "Last Name can't null")
    @NotEmpty(message = "Last Name can't empty")
    private String lastName;
    @NotNull(message = "Gender can't null")
    @NotEmpty(message = "Gender can't empty")
    private String gender;
    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "Date of birth can't null")
    private Date dob;
    // It is also known as Joining Date
    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "Start Date can't null")
    private Date startDate;
    @NotNull(message = "Qualification can't null")
    @NotEmpty(message = "Qualification can't empty")
    private String qualification;
    @NotNull(message = "Address can't null")
    @NotEmpty(message = "Address can't empty")
    private String address;
    @NotNull(message = "Pan number can't null")
    @NotEmpty(message = "Pan number can't empty")
    private String panNumber;

    @NotNull(message = "Designation can't null")
    @NotEmpty(message = "Designation can't empty")
    private String designation;
    @NotNull(message = "Dependent can't null")
    @NotEmpty(message = "Dependent can't empty")
    private String department;
    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "End Date can't null")
    private Date endDate;
    @NotNull(message = "Status can't null")
    @NotEmpty(message = "Status can't empty")
    private String status;
    @NotNull(message = "Reposrting Manager can't null")
    @NotEmpty(message = "Reposrting Manager can't empty")
    private String reportingManager;
    @NotNull(message = "Blood Group can't null")
    @NotEmpty(message = "Blood Group can't empty")
    private String bloodGroup;

    private int dependentID;

    public int getDependentID() {
        return dependentID;
    }

    public void setDependentID(int dependentID) {
        this.dependentID = dependentID;
    }
    /**
     * here OneToMany mapping created because every employee have more than one
     * dependent
     */

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "empID", referencedColumnName = "employeeID")
//    private List<Dependent> dependents = new ArrayList<Dependent>();

    /**
     * This is a default constructor with no argument
     */

    public Employee() {
    }

    /**
     * @param employeeID       - Integer
     * @param firstName        - String
     * @param lastName         - String
     * @param gender           - String
     * @param dob              - Date
     * @param startDate        - Date
     * @param qualification    - String
     * @param address          - String
     * @param panNumber        - String
     * @param designation      - String
     * @param department       - String
     * @param endDate          - Date
     * @param status           - String
     * @param reportingManager - String
     * @param bloodGroup       - String
     * @param dependentID      - Integer
     */

    public Employee(int employeeID, @NotNull(message = "First Name can't null") String firstName,
                    @NotNull(message = "Last Name can't null") String lastName,
                    @NotNull(message = "Gender can't null") String gender,
                    @NotNull(message = "Date of birth can't null") Date dob,
                    @NotNull(message = "Start Date can't null") Date startDate,
                    @NotNull(message = "Qualification can't null") String qualification,
                    @NotNull(message = "Address can't null") String address,
                    @NotNull(message = "Pan number can't null") String panNumber,
                    @NotNull(message = "Designation can't null") String designation,
                    @NotNull(message = "Dependent can't null") String department,
                    @NotNull(message = "End Date can't null") Date endDate,
                    @NotNull(message = "Status can't null") String status,
                    @NotNull(message = "Reporting Manager can't null") String reportingManager,
                    @NotNull(message = "Blood Group can't null") String bloodGroup, int dependentID) {
        super();
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.startDate = startDate;
        this.qualification = qualification;
        this.address = address;
        this.panNumber = panNumber;
        this.designation = designation;
        this.department = department;
        this.endDate = endDate;
        this.status = status;
        this.reportingManager = reportingManager;
        this.bloodGroup = bloodGroup;
        this.dependentID = dependentID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
                + ", gender=" + gender + ", dob=" + dob + ", startDate=" + startDate + ", qualification="
                + qualification + ", address=" + address + ", panNumber=" + panNumber + ", designation=" + designation
                + ", department=" + department + ", endDate=" + endDate + ", status=" + status + ", reportingManager="
                + reportingManager + ", bloodGroup=" + bloodGroup + ", dependentID=" + dependentID + "]";
    }

}