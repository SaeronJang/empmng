package com.empmng.controller;

import com.empmng.data.dto.EmpInfoDto;
import com.empmng.data.dto.EmpRegisterDto;
import com.empmng.data.entity.Employee;
import com.empmng.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Log4j2
@RequestMapping(value = "/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping(value = "/login")
    public String loginGet(String error, String logout){
        log.info("login get......");
        log.info("logout : " + logout);

        return "employee/login";
    }

    @GetMapping(value = "/register")
    public String registerGet(Model model){
        model.addAttribute("empRegisterDto", new EmpRegisterDto());
        return "employee/empRegisterForm";
    }

    @PostMapping(value = "/register")
    public String registerPost(EmpRegisterDto empRegisterDto) {

        empService.empRegister(empRegisterDto);

        return "employee/empRegisterForm";
    }

    @GetMapping(value = "/list")
    public String listGet(Model model){

        List<EmpInfoDto> empInfoDtoList = new ArrayList<>();

        for(int i=11; i<=70; i++) {
            EmpInfoDto empInfoDto = new EmpInfoDto();

            empInfoDto.setEmpNum("150701"+i);
            empInfoDto.setEmpName("테스트성명"+i);
            empInfoDto.setJoinDate(Employee.parseDate("150701"));
            empInfoDto.setAddress("테스트 주소"+i);
            empInfoDto.setDuty("팀원");
            empInfoDto.setBirthday(Employee.parseDate("890119"));
            empInfoDto.setEmail("test"+i+"@saeron.com");
            empInfoDto.setTeam("IT팀");
            empInfoDto.setCellphone("000-0000-0000");
            empInfoDto.setJobLevel("대리");

            if(i>60){
                empInfoDto.setResignDate(Employee.parseDate("230808"));
            }

            empInfoDtoList.add(empInfoDto);
        }

        model.addAttribute("empInfoDtoList", empInfoDtoList);
        return "employee/empList";
    }



}
