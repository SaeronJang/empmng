package com.empmng.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpRegDto {

    private Long empNum;

    private String empName;

    private String birthday;

    private String address;

    private String cellphone;

    private String email;

    private String team; //소속

    private String jobLevel; //직급

    private String duty; //직책

    private String joinDate; //입사일

    private String resignDate; //퇴사일

    //private String role; //ADMIN,USER



//    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
//    @Email(message = "이메일 형식으로 입력해주세요")
//    private String email;
//
//    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
//    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
//    private String password;
//
//    @NotEmpty(message = "주소는 필수 입력 값입니다.")
//    private String address;

}
