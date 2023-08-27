package com.empmng.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "employee")
public class Employee extends BaseEntity {
    //  implements UserDetails 삭제

    /** 테이블 칼럼 */

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long empNum; //사번

    @Column(name = "name")
    private String empName;

    private Date birthday;

    private String address;

    private String cellphone;

    private String email;

    private String team; //소속

    @Column(name = "joblevel")
    private String jobLevel; //직급

    private String duty; //직책

    private Date joinDate; //입사일

    private Date resignDate; //퇴사일




    /**
     * html Form에 String으로 입력된 날짜필드를
     * Date 로 형변환
     */
    public static Date parseDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            SimpleDateFormat exFormatter = new SimpleDateFormat("yyyyMMdd");
            String exceptionDate = "19010101";
            date = exFormatter.parse(exceptionDate);
        } finally {
            return date;
        }
    }




}
