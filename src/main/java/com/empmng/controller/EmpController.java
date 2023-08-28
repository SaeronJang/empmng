package com.empmng.controller;

import com.empmng.data.dto.EmpRegisterDto;
import com.empmng.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String listGet(){
        return "employee/empList";
    }



}
