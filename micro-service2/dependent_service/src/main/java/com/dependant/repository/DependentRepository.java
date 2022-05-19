package com.dependant.repository;

import com.dependant.model.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author 55683 This is a Dependent Repository for intracting Dependent table
 * in database.
 */

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Integer> {

    /**
     * This is a custom query for fetching list of dependant instead of using
     * hibernate method findAll()
     *
     * @return - list of dependant
     */
    @Query(value = "select * from dependent", nativeQuery = true)
    List<Dependent> findAllDependants();

    /**
     * This is a custom query for fetching dependant by ID
     *
     * @param dependentid - dependant id
     * @return - all dependant with id only
     */
    @Query(value = "select * from dependent d where d.dependentid=:dependentid", nativeQuery = true)
    Optional<Dependent> findByDependantId(@Param("dependentid") int dependentid);

}
