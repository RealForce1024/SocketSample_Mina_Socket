package persistence.jpa_test.test;


import com.jahx.jsonutil.JsonToGPRSEntityUtil;
import com.jahx.protocol.GPRSEntity;
import persistence.jpa_test.model.Address;
import persistence.jpa_test.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class Test {

    public static void main(String[] args) throws Exception{

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();

        userTransaction.begin();
//
//        Employee employee = new Employee();
//        employee.setName("frank");
//        employee.setSalary(2000);
//
//        Address address = new Address();
//        address.setCity("Beijing");
//        address.setState("BJ");
//        address.setStreet("Shuangying");
//        address.setZip("100000");
//        employee.setAddress(address);

        String json = "{\"uuid\":\"1\",\"flag\":\"0\",\"glu\":0.1,\"head\":{\"id\":\"0x90\",\"flag2\":\"0\",\"flag1\":\"0\"},\"tm\":{\"year\":2015,\"date\":11,\"mon\":7,\"hr\":2,\"min\":25,\"sec\":16}}";
        GPRSEntity gprsEntity =  new JsonToGPRSEntityUtil().JsonToGPRSEntity(json);


        em.persist(gprsEntity);
        userTransaction.commit();
        em.close();
        entityManagerFactory.close();
    }
}
