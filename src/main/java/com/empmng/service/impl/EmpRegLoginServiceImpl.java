package com.empmng.service.impl;

import com.empmng.common.CommonResponse;
import com.empmng.config.security.JwtTokenProvider;
import com.empmng.data.dto.EmpLoginDto;
import com.empmng.data.dto.EmpLoginResultDto;
import com.empmng.data.dto.EmpRegDto;
import com.empmng.data.dto.EmpRegResultDto;
import com.empmng.data.entity.Employee;
import com.empmng.data.repository.EmployeeRepository;
import com.empmng.service.EmpRegLoginService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmpRegLoginServiceImpl implements EmpRegLoginService {


    public EmployeeRepository employeeRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public EmpRegLoginServiceImpl(EmployeeRepository employeeRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }


    /** 직원 등록 */
    public EmpRegResultDto empRegister(EmpRegDto empRegDto, String role) {

        Employee employee = new Employee();

        //초기 password : empNum
        if(role.equalsIgnoreCase("admin")) {
            employee = Employee.builder()
                    .empNum(empRegDto.getEmpNum())
                    .empName(empRegDto.getEmpName())
                    .birthday(employee.parseDate(empRegDto.getBirthday()))
                    .address(empRegDto.getAddress())
                    .cellphone(empRegDto.getCellphone())
                    .email(empRegDto.getEmail())
                    .team(empRegDto.getTeam())
                    .jobLevel(empRegDto.getJobLevel())
                    .duty(empRegDto.getDuty())
                    .joinDate(employee.parseDate(empRegDto.getJoinDate()))
                    .resignDate(employee.parseDate(empRegDto.getResignDate()))
                    .password(passwordEncoder.encode(String.valueOf(empRegDto.getEmpNum())))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            employee = Employee.builder()
                    .empNum(empRegDto.getEmpNum())
                    .empName(empRegDto.getEmpName())
                    .birthday(employee.parseDate(empRegDto.getBirthday()))
                    .address(empRegDto.getAddress())
                    .cellphone(empRegDto.getCellphone())
                    .email(empRegDto.getEmail())
                    .team(empRegDto.getTeam())
                    .jobLevel(empRegDto.getJobLevel())
                    .duty(empRegDto.getDuty())
                    .joinDate(employee.parseDate(empRegDto.getJoinDate()))
                    .resignDate(employee.parseDate(empRegDto.getResignDate()))
                    .password(passwordEncoder.encode(String.valueOf(empRegDto.getEmpNum())))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        /** DTO 재확인 할것!! */
        Employee savedEmployee = employeeRepository.save(employee);
        EmpRegResultDto empRegResultDto = new EmpRegResultDto();

        if(!savedEmployee.getEmpName().isEmpty()) {
            setSuccessResult(empRegResultDto);
        } else {
            setFailResult(empRegResultDto);
        }

        return empRegResultDto;

    }


    /** 로그인 */
    public EmpLoginResultDto empLogin(Long empNum, String password){
        Employee employee = employeeRepository.getByEmpNum(empNum);
        if(!passwordEncoder.matches(password, employee.getPassword())) {
            throw new RuntimeException();
        }

        EmpLoginResultDto empLoginResultDto = EmpLoginResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(empNum), employee.getRoles()))
                .build();

        setSuccessResult(empLoginResultDto);

        return empLoginResultDto;
    }


    private void setSuccessResult(EmpRegResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(EmpRegResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }













    /** 쇼핑몰 방식, 추후 삭제해야함 */
    public Employee testRegister(Employee employee){
        employeeRepository.save(employee);
        return employee;
    }


}
