package com.employee.controller;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.vo.ResponseTemplateVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is a Spring Rest controller for handling the API operations. This
 * controller exposes various end-points to perform the CRUD operations on the
 * Employee repository.
 * <p>
 * All the APIs return response in a JSON format. Spring automatically
 * serializes the Java POJO classes into JSON.
 */

/**
 * @author 55683
 */

@RestController
@JsonFormat
@RequestMapping(value = "/employee/")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * End-point to delete an employee.
     *
     * @param id - employee id
     * @return employee - employee delete with id specific
     * @throws Exception - if employee not present with this particular id in
     *                   database
     */

    /* ========== Delete Mapping ========== */
    @DeleteMapping(value = "v1/deleteEmployee/{id}")
    public List<Employee> deleteEmployeeData(@PathVariable String id) throws Exception {

        logger.info("Deleting employee with ID : " + id);

        try {
            Employee employee = new Employee();
            employee = employeeService.getEmployeeByID(Integer.parseInt(id));
            logger.trace("Deleting employee - employee get successfully !!");
            employeeService.deleteEmployee(Integer.parseInt(id));
            logger.info("Employee Deleted successfully !!");
            List<Employee> emp = new ArrayList<Employee>();
            emp.add(employee);
            return emp;
        } catch (NoSuchElementException e) {
            logger.error("Employee does not found with this id : " + id + " !!");
            throw new NoSuchElementException("Employee does not exist with this ID : " + id);
        }

    }

    /**
     * End-point to return a list of employees
     *
     * @return - List of employee
     * @throws Exception - if there is no employee present in database
     */

    /* ========== Get Mapping ========== */
    @GetMapping(value = "v1/getemployees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployee() throws Exception {

        logger.info("Getting List of all Employee !!");

        List<Employee> employeeList = employeeService.getAllEmployees();
        if (employeeList.size() > 0) {
            logger.debug("Getting employee list !!");
            logger.info("Getting list of employee successfully !!");
            return employeeList;
        } else {
            logger.error("There is no employee !!");
            throw new Exception("There is no Employee");
        }
    }

    /**
     * End-point to Get details of a single employee based on the employee ID
     *
     * @param id - Getting Employee by Id
     * @return - specific employee by id
     * @throws Exception - if employee not present for this particular id in
     *                   database
     */

    /* ========== Get Mapping ========== */
    @GetMapping(value = "v1/getemployee/{id}")
    public List<Employee> getemployee(@PathVariable Integer id) throws Exception {

        logger.info("Getting Employee by ID : " + id);

        try {
            Employee employee = employeeService.getEmployeeByID(id);
            logger.info("Getting Employee successfully with ID : " + id);
            List<Employee> emp = new ArrayList<Employee>();
            emp.add(employee);
            return emp;
        } catch (NoSuchElementException e) {
            logger.error("Employee does not exist with Id : " + id);
            throw new NoSuchElementException("Employee does not exist with this ID : " + id);
        }

    }

    /**
     * End-point to Get details of a single employee based on the reporting manager
     *
     * @param reportingManager - Get employee by Reporting manager
     * @return - employee by specific reporting manager
     * @throws Exception -if employee not present for this reporting manager
     */

    /* ========== Get Mapping ========== */
    @GetMapping(value = "v1/getemployeeByReportingManager/{reportingManager}")
    public List<Employee> getemployeeByReportingManager(@PathVariable String reportingManager) throws Exception {

        logger.info("Getting Employee by Reporting Manager : " + reportingManager);

        try {
            List<Employee> employee = employeeService.getEmployeeByReportingManager(reportingManager);
            logger.info("Getting Employee succcessfully with there Reporting Manager : " + reportingManager);
            return employee;
        } catch (NoSuchElementException e) {
            logger.error("Employee does not exist for this Reporting Manager : " + reportingManager);
            throw new NoSuchElementException(
                    "Employee does not exist with this Reporting Manager : " + reportingManager);
        }

    }

    /**
     * End-point to create an employee. Create a new employee record by passing the
     * employee details in the request body as JSON pay-load
     *
     * @param employee - JSON data of the employee details
     * @return - Details of the employee if successfully created, an error message
     * if failed to create the employee
     * @throws Exception - If employee already in database
     */

    /* ========== Post Mapping ========== */
    @PostMapping(value = "v1/createemployee")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) throws Exception {

        logger.info("Employee Creation...");

        employee = employeeService.createEmployee(employee);

        if (employee != null) {
            logger.info("Employee successfully created !!");
            return new ResponseEntity<Object>("Employee created successfully : \n" + employee.toString(),
                    HttpStatus.CREATED);
        } else {
            logger.error("Employee already exist !!");
            throw new Exception("Employee Already Exist");
        }
    }

    /**
     * End-point to update an employee.
     *
     * @param employeeID - update employee by specific id only
     * @param employee   - Employee details needed
     * @return - updated employee
     * @throws Exception - if id was not present in database so employee not updated
     */

    /* ========== Put Mapping ========== */
    @PutMapping(value = "v1/updateemployee/{employeeID}")
    public List<Employee> updateEmployee(@PathVariable("employeeID") int employeeID,
                                         @Valid @RequestBody Employee employee) throws Exception {

        logger.info("Employee Updation...");

        boolean isEmployeeExist = employeeService.isEmployeeExistById(employeeID);

        if (isEmployeeExist) {
            employee.setEmployeeID(employeeID);
            employeeService.updateEmployee(employee);
            logger.info("Employee updated successfully !!");
            List<Employee> updatedemployee = new ArrayList<Employee>();
            updatedemployee.add(employee);
            return updatedemployee;
        } else {
            logger.error("Employee updation failed because employee does not exist with ID :" + employeeID);
            throw new NoSuchElementException("Employee does not exist with this ID : " + employeeID);
        }
    }

    @GetMapping(value = "v1/getemployeewithdependent/{dependentID}")
    public ResponseTemplateVO getEmployeeWithDependent(@PathVariable("dependentID") int dependentID) {
        logger.info("Inside getEmployeeWithDependent of Employee Controller");
        return employeeService.getEmployeeWithDependent(dependentID);
    }

}