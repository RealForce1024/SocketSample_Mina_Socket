package com.jahx.protocol;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by fqc on 15/8/11.
 */
@Entity(name="Gprs_head")
public class  HeadEntity implements Serializable{

//    unsigned char FLAG1=0x55
//    unsigned char FLAG2=0xAA
//    unsigned char ID   //ID=0x82(表示血糖数据)  ID=0x90（表示请求时间同步）

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long head_id;

    private String flag1;
    private String flag2;
    private String id; //ID=0x82(表示血糖数据)  ID=0x90（表示请求时间同步） char=0x82->x

    public HeadEntity() {

    }

    public HeadEntity(String flag1, String flag2, String id) {
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.id = id;
    }

    public long getHead_id() {
        return head_id;
    }

    public void setHead_id(long head_id) {
        this.head_id = head_id;
    }

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
