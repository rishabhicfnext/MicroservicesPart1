package com.employee.vo;

import com.employee.model.Employee;

public class ResponseTemplateVO {

    private Employee employee;
    private Dependent dependent;

    public ResponseTemplateVO() {
    }

    public ResponseTemplateVO(Employee employee, Dependent dependent) {
        this.employee = employee;
        this.dependent = dependent;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Dependent getDependent() {
        return dependent;
    }

    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }
}