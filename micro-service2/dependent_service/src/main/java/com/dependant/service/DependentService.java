package com.dependant.service;

import com.dependant.model.Dependent;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 55683
 */

/*
 * This is all method for Dependent fetching and updating.
 * There is all service method for Dependent Class
 * */

public interface DependentService {

    /**
     * This method is for creating employee
     *
     * @param dependent - dependent details
     * @return - dependent
     * @throws Exception - when dependent already in database
     */
    public abstract Dependent createDependent(@Valid Dependent dependent) throws Exception;

    /**
     * This method is for getting list of dependant
     *
     * @return - list of all dependants
     */
    public abstract List<Dependent> getAllDependants();

    /**
     * This method is for getting dependant by specific id only
     *
     * @param dependantID - dependant id
     * @return - dependant by id only
     */
    public abstract Dependent getDependantByID(int dependantID);

    /**
     * This method is for checking dependant exist or not exist with there id only
     *
     * @param id - employee id
     * @return - checking employee by id only
     */
    public abstract boolean isDependantsExistById(int id);

    /**
     * This method is for Updating Dependent
     *
     * @param dependent - dependants details
     */
    public abstract void updateDependant(Dependent dependent);

}