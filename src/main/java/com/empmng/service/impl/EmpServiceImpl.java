package com.empmng.service.impl;

import com.empmng.data.dto.EmpRegisterDto;
import com.empmng.data.entity.Employee;
import com.empmng.data.repository.EmpRepository;
import com.empmng.service.EmpService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Log4j2
public class EmpServiceImpl implements EmpService {

    public EmpRepository empRepository;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public EmpServiceImpl(EmpRepository empRepository, PasswordEncoder passwordEncoder) {
        this.empRepository = empRepository;
        this.passwordEncoder = passwordEncoder;
    }



    // 직원 등록
    @Override
    public void empRegister(EmpRegisterDto empRegisterDto) {
        log.info("[empRegister] 신규 직원 등록 정보 전달");
        Employee employee;

        employee = Employee.builder()
                .empNum(empRegisterDto.getEmpNum())
                .empName(empRegisterDto.getEmpName())
                .password(passwordEncoder.encode(empRegisterDto.getEmpNum()))
                .address(empRegisterDto.getAddress())
                .joinDate(Employee.parseDate(empRegisterDto.getJoinDate()) )
                .birthday(Employee.parseDate(empRegisterDto.getBirthday()))
                .cellphone(empRegisterDto.getCellphone())
                .duty(empRegisterDto.getDuty())
                .email(empRegisterDto.getEmail())
                .jobLevel(empRegisterDto.getJobLevel())
                .team(empRegisterDto.getTeam())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        Employee savedEmployee = empRepository.save(employee);

        log.info("[empRegister] 등록 확인");
        if(savedEmployee.getEmpNum().isEmpty()) {
            log.info("등록 오류 ㅠㅠ");
        } else {
            log.info("등록 완료 : " + savedEmployee.getEmpNum());
        }

    }

}
