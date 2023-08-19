package com.empmng.service;

import com.empmng.data.dto.EmpLoginResultDto;
import com.empmng.data.dto.EmpRegDto;
import com.empmng.data.dto.EmpRegResultDto;
import com.empmng.data.entity.Employee;

public interface EmpRegLoginService {

    Employee testRegister(Employee employee);

    public EmpRegResultDto empRegister(EmpRegDto empRegDto, String role);

    public EmpLoginResultDto empLogin(Long empNum, String password);
}
