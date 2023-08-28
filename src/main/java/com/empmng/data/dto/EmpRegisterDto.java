package com.empmng.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter @Setter
public class EmpRegisterDto {

    private String empNum; //사번

    private String empName; //성명

    private String birthday; //생년월일

    private String address; //주소

    private String cellphone; //연락처

    private String email; //이메일

    private String team; //소속

    private String jobLevel; //직급

    private String duty; //직책

    private String  joinDate; //입사일

    private String resignDate; //퇴사일

    // private String password; //초기패스워드=사번
}
