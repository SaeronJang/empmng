package com.empmng.data.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Log4j2
@Getter @Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "emp_key")
public class EmpKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empKeyId; //순차적으로 임의 번호 부여

    private String password;

    private String role;

    @OneToOne
    @JoinColumn(name = "emp_num")
    private Employee employee;

}
