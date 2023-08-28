package com.empmng.data.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@ToString(callSuper = true, exclude = "roleSet")
@EqualsAndHashCode(callSuper = true)
@Table(name = "employee")
public class Employee extends BaseEntity implements UserDetails {
    //  implements UserDetails 삭제

    /** 테이블 칼럼 */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String empNum; //사번

    @Column(name = "name")
    private String empName; //성명

    private Date birthday; //생년월일

    private String address; //주소

    private String cellphone; //연락처

    private String email; //이메일

    private String team; //소속

    private String jobLevel; //직급

    private String duty; //직책

    private Date joinDate; //입사일

    private Date resignDate; //퇴사일

    private String password; //패스워드

    // [230828 핵심]
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


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


    /** UserDetails Override */
    // 권한목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    // 사번(id)리턴
    @Override
    public String getUsername() {
        return this.empNum;
    }

    // 계정 만료 유무 리턴
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 유무 리턴
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    // 패스워드 만료 유무 리턴
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    // 계정 활성화 유무 리턴
    @Override
    public boolean isEnabled() {
        if(resignDate != null) return false;
        else return true;
    }
}
