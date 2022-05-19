package com.employee.service.impl;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import com.employee.vo.Dependent;
import com.employee.vo.ResponseTemplateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 55683
 */

/*
 * This is a employee service implimentation class
 * here we override all method of employeeservice interface
 * */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseTemplateVO getEmployeeWithDependent(int employeeID) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Employee employee = employeeRepository.findByEmployeeId(employeeID);
        Dependent dependent = restTemplate.getForObject("http://dependent-service/dependent/v1/getdependant/" + employee.getDependentID(), Dependent.class);
        responseTemplateVO.setEmployee(employee);
        responseTemplateVO.setDependent(dependent);
        return responseTemplateVO;
    }

    /**
     * This method is for getting list of all employee and sorted by joining date
     */

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Getting List of all Employee method called !!");
        List<Employee> allEmployee = employeeRepository.findAllEmployee();

        Collections.sort(allEmployee, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getStartDate().compareTo(e2.getStartDate());
            }
        });

        return allEmployee;
    }

    /**
     * This method is for getting employee by ID only
     */

    @Override
    public Employee getEmployeeByID(int employeeID) {
        logger.info("Getting employee by id method called !!");
        Optional<Employee> op = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeID));
        Employee employee = op.get();
        return employee;
    }

    /**
     * This method is for creating employee
     */

    @Override
    public Employee createEmployee(Employee employee) throws Exception {
        logger.info("Employee Creation method called !!");
        if (employeeRepository.existsByPanNumber(employee.getPanNumber()) > 0) {
            logger.debug("Employee checking with pan number !!");
            logger.error("Employee already exist !!");
            throw new Exception("Employee already exist");
        } else {
            return employeeRepository.save(employee);
        }
    }

    /**
     * This method is for checking employee whether employee is exist or not in our
     * database by using employee pan number detail
     */

    @Override
    public int isEmployeeExist(@Valid String panNumber) {
        logger.info("Checking Employee with pan number method called !!");
        return employeeRepository.existsByPanNumber(panNumber);
    }

    /**
     * This method is for deleting employee by ID
     */

    @Override
    public void deleteEmployee(int employeeID) {
        logger.info("Delete Employee method called !!");
        employeeRepository.deleteById(employeeID);
    }

    /**
     * This method is for checking employee whether employee is exist or not in our
     * database by using employeeID
     */

    @Override
    public boolean isEmployeeExistById(int id) {
        logger.info("Checking Employee by id method called !!");
        return employeeRepository.existsById(id);
    }

    /**
     * This method is for updating employee by ID
     */

    @Override
    public void updateEmployee(Employee employee) {
        logger.info("Update Employee method called !!");
        employeeRepository.saveAndFlush(employee);
    }

    /**
     * checking employee exist with reporting manager or not
     */

    @Override
    public int isEmployeeExistByReportingManager(@Valid String reportingManager) {
        logger.info("Checking Employee exist By Reporting Manager method called !!");
        return employeeRepository.existsByReportingManager(reportingManager);
    }

    /**
     * Getting list for all employee by reporting manager only
     */

    @Override
    public List<Employee> getEmployeeByReportingManager(String reportingManager) throws Exception {
        logger.info("Getting Employee by Reporting Manager method called !!");
        int employee = employeeRepository.existsByReportingManager(reportingManager);
        if (employee == 0) {
            logger.error("There is no employee for this Reporting Manager :" + reportingManager + " !!");
            throw new Exception("There is no employee for this Reporting Manager : " + reportingManager);
        } else {
            List<Employee> specificEmployee = employeeRepository.findAll();
            List<Employee> newEmployeeList = specificEmployee.stream()
                    .filter(c -> c.getReportingManager().equals(reportingManager)).collect(Collectors.toList());
            return newEmployeeList;
        }
    }

}