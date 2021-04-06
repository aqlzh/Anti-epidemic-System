package com.example.epi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "epidemic_info")
public class EpidemicInfo implements Serializable {
    /*@Id
    @Column(name = "id")
    private int id;*/
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "age")
    private int age;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "identy")
    private String identy;
    @Basic
    @Column(name= "temp")
    private String temp;
    @Basic
    @Column(name="inNative")
    private String inNative;
    @Basic
    @Column(name = "adress")
    private String adress;
    @Basic
    @Column(name = "regtime")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date regtime;
    @Basic
    @Column(name = "id_type")
    private String id_type;
    @Basic
    @Column(name = "id_card")
    private long id_card;
    @Basic
    @Column(name = "phone")
    private long phone;
    /*    @Basic
        @Column(name = "status")
        private int status;*/

}
