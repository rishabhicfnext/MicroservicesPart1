package com.dependant.service.impl;

import com.dependant.model.Dependent;
import com.dependant.repository.DependentRepository;
import com.dependant.service.DependentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 55683
 */

/*
 * This is a dependant service implimentation class
 * here we override all method of dependantservice interface
 * */

@Service
public class DependentServiceImpl implements DependentService {
    private static final Logger logger = LoggerFactory.getLogger(DependentServiceImpl.class);

    @Autowired
    private DependentRepository dependentRepository;

    /**
     * This method is for creating dependent
     */

    @Override
    public Dependent createDependent(Dependent dependent) throws Exception {
        logger.info("Dependent Creation method called !!");
        if (dependentRepository.existsById(dependent.getDependentID())) {
            logger.debug("Dependent checking with dependentID !!");
            logger.error("Dependent already exist !!");
            throw new Exception("Dependent already exist");
        } else {
            return dependentRepository.save(dependent);
        }
    }

    /**
     * This method is for getting list of all dependants
     */

    @Override
    public List<Dependent> getAllDependants() {
        logger.info("Getting list of all dependants method called !!");
        List<Dependent> allDependents = dependentRepository.findAllDependants();
        return allDependents;
    }

    /**
     * This method is for getting dependant by ID only
     */

    @Override
    public Dependent getDependantByID(int dependantID) {
        logger.info("Getting Dependent by ID method called !!");
        Optional<Dependent> optionalDependant = dependentRepository.findByDependantId(dependantID);
        Dependent dependent = optionalDependant.get();
        return dependent;
    }

    /**
     * This method is for checking dependant whether dependant is exist or not in
     * our database by using dependantsID
     */

    @Override
    public boolean isDependantsExistById(int id) {
        logger.info("Checking is dependant exist by Id method called !!");
        return dependentRepository.existsById(id);
    }

    /**
     * This method is for updating dependent
     */

    @Override
    public void updateDependant(Dependent dependent) {
        dependentRepository.saveAndFlush(dependent);
        logger.info("Update Dependent method called !!");
    }

}
