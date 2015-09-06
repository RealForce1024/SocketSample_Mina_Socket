package com.jahx.jsonutil;

import com.jahx.protocol.GPRSEntity;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by fqc on 15/8/11.
 */
public class JsonToGPRSEntityUtil {




    public static void main(String[] args) throws Exception{
        String json = "{\"uuid\":\"1\",\"flag\":\"0\",\"glu\":0.1,\"head\":{\"id\":\"0\",\"flag2\":\"0\",\"flag1\":\"0\"},\"tm\":{\"year\":2015,\"date\":11,\"mon\":7,\"hr\":2,\"min\":25,\"sec\":16}}";
        JsonToGPRSEntity(json);

    }

    public static GPRSEntity JsonToGPRSEntity(String json) throws java.io.IOException {
        System.out.println(json);
        ObjectMapper objectMapper = new ObjectMapper();
        GPRSEntity gprsEntity = objectMapper.readValue(json, GPRSEntity.class);
        System.out.println(gprsEntity.getHead());
        System.out.println(gprsEntity.getTm());
        System.out.println(gprsEntity);
        return gprsEntity;
    }











}
