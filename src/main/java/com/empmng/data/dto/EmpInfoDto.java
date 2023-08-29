package com.empmng.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class EmpInfoDto {

    private String empNum; //사번

    private String empName; //성명

    private Date birthday; //생년월일

    private String address; //주소

    private String cellphone; //연락처

    private String email; //이메일

    private String team; //소속

    private String jobLevel; //직급

    private String duty; //직책

    private Date  joinDate; //입사일

    private Date resignDate; //퇴사일

}
