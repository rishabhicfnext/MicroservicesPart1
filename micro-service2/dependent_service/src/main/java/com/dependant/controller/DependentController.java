package com.dependant.controller;

import com.dependant.model.Dependent;
import com.dependant.service.DependentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is a Spring Rest controller for handling the API operations. This
 * controller exposes various end-points to perform the CRUD operations on the
 * Dependent repository.
 * <p>
 * All the APIs return response in a JSON format. Spring automatically
 * serializes the Java POJO classes into JSON.
 */

/**
 * @author 55683
 */

@RestController
@JsonFormat
@RequestMapping(value = "/dependent/")
public class DependentController {
    private static final Logger logger = LoggerFactory.getLogger(DependentController.class);

    @Autowired
    private DependentService dependentService;

    /**
     * End-point to return a list of dependants
     *
     * @return - List of all dependants
     * @throws Exception - when there is no dependant in database
     */

    /* ========== Get Mapping ========== */
    @GetMapping(value = "v1/getdependants")
    public List<Dependent> getAllDependants() throws Exception {

        logger.info("Getting all Dependent");

        List<Dependent> dependentList = dependentService.getAllDependants();
        if (dependentList.size() > 0) {
            logger.info("Getting all list of Dependent successfully !!");
            return dependentList;
        } else {
            logger.error("There is no dependant !!");
            throw new Exception("There is no Dependent");
        }

    }

    /**
     * End-point to Get details of a single dependant based on the dependant ID
     *
     * @param id - Dependent id
     * @return - Dependent by id
     * @throws Exception - when there is no dependant for this particular id
     */

    /* ========== Get Mapping ========== */
    @GetMapping(value = "v1/getdependant/{id}")
    public Dependent getdependant(@PathVariable Integer id) throws Exception {

        logger.info("Getting Dependent by ID : " + id);

        try {
            Dependent dependent = dependentService.getDependantByID(id);
            logger.info("Getting Dependent by ID successfully !!");
            //List<Dependent> deps = new ArrayList<Dependent>();
            //deps.add(dependent);
            return (Dependent) dependent;
        } catch (NoSuchElementException e) {
            logger.error("Dependent does not exist with this ID : " + id);
            throw new NoSuchElementException("Dependent does not exist with this ID : " + id);
        }

    }

    /**
     * End-point to update an dependent.
     *
     * @param dependantID - Dependent Id for updated dependent
     * @param dependent   - Dependent details
     * @return - updated dependent
     * @throws Exception - if Id not present in database for which we want updation
     */

    /* ========== Put Mapping ========== */
    @PutMapping(value = "v1/updatedependant/{dependantID}")
    public List<Dependent> updateDependant(@PathVariable("dependantID") int dependantID,
                                           @RequestBody Dependent dependent) throws Exception {

        logger.info("Dependent Updation...");

        boolean isDependantExist = dependentService.isDependantsExistById(dependantID);

        if (isDependantExist) {
            dependent.setDependentID(dependantID);
            dependentService.updateDependant(dependent);
            logger.info("Dependent update successfully !!");
            List<Dependent> updatedependant = new ArrayList<Dependent>();
            updatedependant.add(dependent);
            return updatedependant;
        } else {
            logger.error("Dependent does not exist with this ID : " + dependantID);
            throw new NoSuchElementException("Dependent does not exist with this ID : " + dependantID);
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
    @PostMapping(value = "v1/createdependant")
    public ResponseEntity<Object> createDependent(@Valid @RequestBody Dependent dependent) throws Exception {

        logger.info("Dependent Creation...");

        dependent = dependentService.createDependent(dependent);

        if (dependent != null) {
            logger.info("Dependent successfully created !!");
            return new ResponseEntity<Object>("Dependent created successfully : \n" + dependent.toString(),
                    HttpStatus.CREATED);
        } else {
            logger.error("Dependent already exist !!");
            throw new Exception("Dependent Already Exist");
        }
    }

}
