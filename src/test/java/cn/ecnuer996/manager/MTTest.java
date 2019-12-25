package cn.ecnuer996.manager;

import cn.ecnuer996.manager.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

public class MTTest extends PatientDataManagerApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void  insertTest(){
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        patient1.setId("11221");
        patient1.setName("www");
        patient2.setId("11122");
        patient2.setName("wwwwww");
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient2);
        mongoTemplate.insert(patientList,Patient.class);
    }
}
