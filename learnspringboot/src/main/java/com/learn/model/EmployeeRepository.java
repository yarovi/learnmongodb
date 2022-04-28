package com.learn.model;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.Employee;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String>,ReactiveQueryByExampleExecutor<Employee> {

}
