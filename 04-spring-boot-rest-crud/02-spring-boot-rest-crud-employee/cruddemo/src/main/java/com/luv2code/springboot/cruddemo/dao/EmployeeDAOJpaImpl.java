package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    //define field for entitymanager
    private EntityManager entityManager; //entitymanagers are used to create all types of queries with the database!!

    //set up constructor injection
    @Autowired //for injection annotation
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);

        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, id);

        //return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee employee) {
        //save employee
        Employee dbEmployee = entityManager.merge(employee); //save or update based on the id of the entity (insert if id==0)

        //return dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        //find employee by id
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
