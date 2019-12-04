package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class PatientDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // insert
    public void saveAccount(Patient patient){
        mongoTemplate.save(patient);
    }

    //query
    public Patient findAccountByName(String id){
        Query query=new Query(Criteria.where("_id").is(id));
        Patient patient=mongoTemplate.findOne(query,Patient.class);
        return patient;
    }
}
