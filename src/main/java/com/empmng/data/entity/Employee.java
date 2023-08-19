package com.empmng.data.entity;

import com.empmng.data.dto.EmpRegDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "employee")
public class Employee extends BaseEntity implements UserDetails {

    /** 테이블 칼럼 */

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long empNum; //사번 (JWT 토큰 내 정보)

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Json결과로 출력하지 않음
    private String password; //passwordEncoding

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();




    /** UserDetails 메서드 오버라이딩 */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /** security 에서 사용하는 회원 구분 id
     * 토큰 생성용
     * @return empNum(String으로) */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return Long.toString(this.empNum);
    }

    // 계정 만료 여부 체크
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 체크
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정의 패스워드 만료 여부 체크
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 체크
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }


    /** Employee 객체 생성용 (제작 완료 후 삭제 예정) */
    public Employee createEmployeeRegister(EmpRegDto empRegDto){
        Employee employee = new Employee();

        employee.setEmpNum(empRegDto.getEmpNum());
        employee.setEmpName(empRegDto.getEmpName());
        employee.setBirthday(parseDate(empRegDto.getBirthday()));
        employee.setAddress(empRegDto.getAddress());
        employee.setCellphone(empRegDto.getCellphone());
        employee.setEmail(empRegDto.getEmail());
        employee.setTeam(empRegDto.getTeam());
        employee.setJobLevel(empRegDto.getJobLevel());
        employee.setDuty(empRegDto.getDuty());
        employee.setJoinDate(parseDate(empRegDto.getJoinDate()));
        employee.setResignDate(parseDate(empRegDto.getResignDate()));
        employee.setPassword(String.valueOf(empRegDto.getEmpNum()));

        return employee;
    }

    /**
     * html Form에 String으로 입력된 날짜필드를
     * Date 로 형변환
     */
    public Date parseDate(String strDate) {
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
