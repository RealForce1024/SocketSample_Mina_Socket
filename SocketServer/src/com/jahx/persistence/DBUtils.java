package com.jahx.persistence;

import com.jahx.jsonutil.JsonToGPRSEntityUtil;
import com.jahx.protocol.DateUtil;
import com.jahx.protocol.GPRSEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by fqc on 15/8/19.
 */
public class DBUtils {

    private static EntityManagerFactory entityManagerFactory;
    public static EntityManager getEntityManager() {
         entityManagerFactory =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = entityManagerFactory.createEntityManager();
        return em;
    }




    public static void main(String[] args) throws Exception{
        String json = "{\"uuid\":\"2\",\"flag\":\"0\",\"glu\":0.1,\"head\":{\"id\":\"2\",\"flag2\":\"0\",\"flag1\":\"0\"},\"tm\":{\"year\":2015,\"date\":11,\"mon\":7,\"hr\":2,\"min\":25,\"sec\":16}}";
        save_gprs(json);
    }

    public static void save_gprs(String json) throws java.io.IOException {
        EntityManager em = getEntityManager();
        EntityTransaction userTransaction = em.getTransaction();

        userTransaction.begin();


        GPRSEntity gprsEntity =  new JsonToGPRSEntityUtil().JsonToGPRSEntity(json);


        em.persist(gprsEntity);
        userTransaction.commit();
        em.close();
        entityManagerFactory.close();
    }


    public static void save_gprs(GPRSEntity gprsEntity) throws java.io.IOException {
        EntityManager em = getEntityManager();
        EntityTransaction userTransaction = em.getTransaction();

        userTransaction.begin();


        em.persist(gprsEntity);
        userTransaction.commit();
        em.close();
        entityManagerFactory.close();
    }

}
