package com.jahx.protocol;


import org.apache.mina.core.buffer.IoBuffer;

import javax.persistence.*;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by fqc on 15/8/11.
 */
@Entity
@Table(name="GPRS")
public class GPRSEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long duid;

    private String uuid; //血糖仪设备标识
    private char flag; //=0餐前，=1餐后 =2随机（默认）
    private float glu; //浮点值
    private Time tim; // measure time.

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="head_id")
    private HeadEntity head; //请求头 逻辑标识

    //这个字段设计暂时保留
    @Embedded
    private DateEntity tm;   //时间

    private String uploadTime;

    public GPRSEntity() {
        String time = new DateUtil().getNowDateAndTime();
        this.setUploadTime(time);
    }
/*
    public GPRSEntity(IoBuffer ioBuffer) {
        // pointer = ioBuffer head.
        Byte[]  b = new Byte[ioBuffer.limit() ] ;
        int sizeofBuffer = ioBuffer.limit();
        short year = new short();
        System.arraycopy(year, 0, b, 3, 2); // sizeof short
        tim.setYear(year);
        tim.setMonth(b[4]);




//        String time = new DateUtil().getNowDateAndTime();
//        this.setUploadTime(time);
    }*/



    public GPRSEntity(String uuid, char flag, float glu, HeadEntity head, DateEntity tm) {
        this.uuid = uuid;
        this.flag = flag;
        this.glu = glu;
        this.head = head;
        this.tm = tm;

        String time = new DateUtil().getNowDateAndTime();
        this.setUploadTime(time);


    }


    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
    public long getDuid() {
        return duid;
    }

    public void setDuid(long duid) {
        this.duid = duid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }

    public float getGlu() {
        return glu;
    }

    public void setGlu(float glu) {
        this.glu = glu;
    }


    public HeadEntity getHead() {
        return head;
    }

    public void setHead(HeadEntity head) {
        this.head = head;
    }

    public DateEntity getTm() {
        return tm;
    }

    public void setTm(DateEntity tm) {
        this.tm = tm;
    }
}

/**
 *
 *
 {
 "body": {
 "flag": "",
 "GLU": "",
 "head": {
 "FLAG1": "0x55",
 "FLAG2": "0xAA",
 "ID": "0x82"
 },
 "tm": {
 "DATE": "",
 "HR": "",
 "MIN": "",
 "MON": "",
 "SEC": "",
 "YEAR": ""
 },
 "UUID": ""
 }
 }
 */
