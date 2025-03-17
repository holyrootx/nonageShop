package com.nonage.admin.controller.dto;

import java.sql.Timestamp;
import lombok.Data;
@Data
public class AdminVO {

    private String id;
    private String pwd;
    private String name;
    private String email;
    private String zipNum;
    private String address;
    private String phone;
    private String useyn;
    private Timestamp indate;
}
