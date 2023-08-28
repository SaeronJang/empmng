package com.empmng.data.repository;

import com.empmng.data.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmpRepository extends JpaRepository<Employee, Long> {
    Employee getByEmpNum(String EmpNum);

}
