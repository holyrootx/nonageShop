package com.nonage.admin.controller.dto;

import java.sql.Timestamp;
import lombok.Data;
@Data
public class AdminQnaVO {
    // qseq,subject,content,reply,id,rep,indate
    private int qseq;
    private String subject;
    private String content;
    private String reply;
    private String id;
    private String rep;
    private Timestamp indate;


}
