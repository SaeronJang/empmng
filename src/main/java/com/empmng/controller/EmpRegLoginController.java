package com.empmng.controller;

import com.empmng.data.dto.EmpLoginDto;
import com.empmng.data.dto.EmpLoginResultDto;
import com.empmng.data.dto.EmpRegDto;
import com.empmng.data.dto.EmpRegResultDto;
import com.empmng.data.entity.Employee;
import com.empmng.service.EmpRegLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/emp")
public class EmpRegLoginController {

    private final EmpRegLoginService empRegLoginService;

    @Autowired
    public EmpRegLoginController(EmpRegLoginService empRegLoginService) {
        this.empRegLoginService = empRegLoginService;
    }


    /** 직원 등록 API */
    @GetMapping(value="/register")
    public String employeeRegisterGet(Model model) {
        model.addAttribute("empRegDto", new EmpRegDto());
        return "employee/employeeRegisterForm";
    }
    @PostMapping(value = "/register")
    public String empRegisterPost(@Valid EmpRegDto empRegDto,
                                  Model model) {
        EmpRegResultDto empRegResultDto =
                empRegLoginService.empRegister(empRegDto,"USER");

        return "employee/employeeRegisterForm";
    }


    /** 로그인 API */
    @GetMapping(value = "/login")
    public String loginGet(Model model) {
        model.addAttribute("empLoginDto", new EmpLoginDto());
        return "loginForm";
    }

    @PostMapping(value = "/login")
    public String loginPost(@Valid EmpLoginDto empLoginDto, Model model) {
        //EmpLoginResultDto empLoginResultDto =
                empRegLoginService.empLogin(empLoginDto.getEmpNum(),  empLoginDto.getPassword());
        return "employee/employeeRegisterForm";
    }



    /*@PostMapping(value = "/register")
    public String employeeRegisterPost(@Valid EmpRegDto empRegDto,
                                       Model model) {
        Employee employee = new Employee();
        employee = employee.createEmployeeRegister(empRegDto);
        empRegLoginService.testRegister(employee);
        return "employee/employeeRegisterForm";
    }*/

    /** MASTER 계정 생성용(임시) */
    @GetMapping(value = "/master")
    public String masterTest(Model model){
        model.addAttribute("empRegDto", new EmpRegDto());
        return "fragments/header";
    }

    @PostMapping(value = "/master")
    public String createMasterEmployee(@Valid EmpRegDto empRegDto,
                                       Model model){
        Employee employee = new Employee();

        {
            empRegDto.setEmpNum(99999999L);
            empRegDto.setEmpName("MASTER");
            empRegDto.setBirthday("19010101");
            empRegDto.setAddress("서울시");
            empRegDto.setCellphone("99999999999");
            empRegDto.setEmail("master@saeron.com");
            empRegDto.setTeam("IT팀");
            empRegDto.setJobLevel("지원");
            empRegDto.setDuty("IT Master");
            empRegDto.setJoinDate("19010101");
        }

        employee = employee.createEmployeeRegister(empRegDto);

        Employee savedEmployee = empRegLoginService.testRegister(employee);
        System.out.println(savedEmployee.toString());
        return "employee/employeeRegisterForm";
    }


}
