package com.employee.service;

import com.employee.model.Employee;
import com.employee.vo.ResponseTemplateVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 55683
 */

/*
 * This is all method for Employee fetching, creating, deleting and updating.
 * There is all service method for Employee Class
 * */

public interface EmployeeService {

    public ResponseTemplateVO getEmployeeWithDependent(int employeeID);

    /**
     * This method is for getting list of employees
     *
     * @return - list of all employee
     */
    public abstract List<Employee> getAllEmployees();

    /**
     * This method is for getting employee by id only
     *
     * @param employeeID - employee id
     * @return - employee by id only
     */
    public abstract Employee getEmployeeByID(int employeeID);

    /**
     * This method is for creating employee
     *
     * @param employee - employee details
     * @return - employee
     * @throws Exception - when employee already in database
     */
    public abstract Employee createEmployee(@Valid Employee employee) throws Exception;

    /**
     * This method is for checking employee exist or not with there pan number details only
     *
     * @param panNumber - employee pan number
     * @return - employee by its pan number details
     */
    public abstract int isEmployeeExist(@Valid String panNumber);

    /**
     * This method is for checking employee exist by id only
     *
     * @param id - employee id
     * @return - check if employee present or not in database
     */
    public abstract boolean isEmployeeExistById(int id);

    /**
     * This method is for deleting employee by there Id only
     *
     * @param employeeID - employee id
     */
    public abstract void deleteEmployee(int employeeID);

    /**
     * This method is for updating employee
     *
     * @param employee - employee details
     */
    public abstract void updateEmployee(Employee employee);

    /**
     * This method is for fetching list of all employee with there reporting manager specific only
     *
     * @param reportingManager - employee with reporting manager
     * @return -  employee by specific reporting manager
     * @throws Exception -  when employee not assign any reporting manager
     */
    public abstract List<Employee> getEmployeeByReportingManager(String reportingManager) throws Exception;

    /**
     * This method is for checking employee exist by reporting manager only
     *
     * @param reportingManager - employee reprting manager
     * @return - employee by its reporting manager
     */
    public abstract int isEmployeeExistByReportingManager(@Valid String reportingManager);
}