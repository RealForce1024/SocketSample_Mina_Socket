package com.jahx.jsonutil;

import com.jahx.protocol.DateEntity;
import com.jahx.protocol.DateUtil;
import com.jahx.protocol.GPRSEntity;
import com.jahx.protocol.HeadEntity;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by fqc on 15/8/11.
 */
public class GPRSEntityToJsonUtil {


    public static void main(String[] args) throws Exception{


        GPRSEntity gprsEntity = new GPRSEntity();


        Map map = new DateUtil().getTimeByCalendar();
//        Integer year = (Integer) map.get("year");
//        Integer month = (Integer) map.get("month");
//        Integer day = (Integer) map.get("day");
//        Integer hour = (Integer) map.get("hour");
//        Integer minute = (Integer) map.get("minute");
//        Integer second = (Integer) map.get("second");

        String year = (String) map.get("year");
        String month = (String) map.get("month");
        String day = (String) map.get("day");
        String hour = (String) map.get("hour");
        String minute = (String) map.get("minute");
        String second = (String) map.get("second");

        gprsEntity.setUuid("10000");
        gprsEntity.setGlu(0.1f);
        gprsEntity.setFlag('0');

        gprsEntity.setTm(new DateEntity(year, month, day, hour, minute, second));

        gprsEntity.setHead(new HeadEntity("0", "0","0x90"));

        System.out.println(gprsEntity);

        GPRSEntityToJson(gprsEntity);

    }

    public static void GPRSEntityToJson(GPRSEntity gprsEntity) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);

        System.out.println("jsonGenerator");
        //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
        jsonGenerator.writeObject(gprsEntity);
        System.out.println();

        System.out.println("ObjectMapper");
        //writeValue具有和writeObject相同的功能
        objectMapper.writeValue(System.out, gprsEntity);
    }


    public  String EntityToJson(Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);

        System.out.println("jsonGenerator");
        //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等

        jsonGenerator.writeObject(object);
        System.out.println();

        System.out.println("ObjectMapper");
        //writeValue具有和writeObject相同的功能
        objectMapper.writeValue(System.out, object);
        String json = objectMapper.writeValueAsString(object);
        return json;
    }

}
