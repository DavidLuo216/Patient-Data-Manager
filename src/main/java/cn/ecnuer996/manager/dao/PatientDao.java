package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    //update
    public void updatePatient(Patient patient){
        Query query = new Query(Criteria.where("_id").is(patient.getId()));
        Update update = new Update().set("_id",patient.getId())
                .set("name",patient.getName())
                .set("birthday",patient.getBirthday())
                .set("gender",patient.getGender())
                .set("phone",patient.getPhone())
                .set("address",patient.getAddress())
                .set("history",patient.getHistory())
                .set("diagnose",patient.getDiagnose());
        mongoTemplate.updateFirst(query,update,Patient.class);
    }

    //delete
    public void deletePatient(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Patient.class);
    }
}
