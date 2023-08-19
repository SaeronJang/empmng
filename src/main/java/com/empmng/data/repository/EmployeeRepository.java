package com.empmng.data.repository;

import com.empmng.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getByEmpNum(Long empNum);

}
