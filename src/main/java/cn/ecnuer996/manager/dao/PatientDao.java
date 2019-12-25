package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Diagnose;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public class PatientDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // insert单条
    public Patient savePatient(Patient patient){
        return mongoTemplate.save(patient);
    }

    //列表导入
    public List<Patient> insertPatientList(List<Patient> patientList){
        return (List<Patient>)mongoTemplate.insert(patientList,Patient.class);
    }
    // get by id
    public Patient findPatientByID(String id){
        Query query=new Query(Criteria.where("_id").is(id));
        Patient patient=mongoTemplate.findOne(query,Patient.class);
        return patient;
    }

    //query模糊查找
    public List<Patient> findPatient(String name,String birthday,String gender,String phone,String address){
//        Criteria criteria = Criteria.where("name").regex(name).and("birthday").regex(birthday)
//                .and("gender").regex(gender).and("phone").regex(phone).and("address").regex(address);
        Criteria criteria = Criteria.where("name").is(name).and("birthday").is(birthday)
                .and("gender").is(gender).and("phone").is(phone).and("address").is(address);
        Query query = Query.query(criteria);//.limit(limit).skip(skip) 构建查询条件 并设置分页和至多返回limit条（后面的没设置）
        List<Patient> patientList = mongoTemplate.find(query, Patient.class);
        return patientList;
    }

    /**
     * 分页多条件查询，以一个Patient对象作为查询条件
     * 对参数patient不为null的字段进行相等查询
     * @param patient
     * @return
     */
    public List<Patient> findPageable(Patient patient,int pageIndex,int pageSize){
        Criteria criteria=Criteria.byExample(patient);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Query query=Query.query(criteria).with(pageable);
        return mongoTemplate.find(query,Patient.class,"patient");
    }

    public List<Patient> findByExample(Patient patient){
        Criteria criteria=Criteria.byExample(patient);
        Query query=Query.query(criteria);
        return mongoTemplate.find(query,Patient.class,"patient");
    }

    // update
    public int updatePatient(Patient patient){
        Query query = new Query(Criteria.where("_id").is(patient.getId()));
        Update update = new Update()
                .set("name",patient.getName())
                .set("birthday",patient.getBirthday())
                .set("gender",patient.getGender())
                .set("phone",patient.getPhone())
                .set("address",patient.getAddress())
                .set("history",patient.getHistory())
//                .set("diagnose",patient.getDiagnose())
        ;
        mongoTemplate.updateFirst(query,update,Patient.class);
        return 1;
    }

    // delete
    public void deletePatient(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Patient.class);
    }

    //find all
    public List<Patient> findAllPatients(){
        return mongoTemplate.findAll(Patient.class);
    }

    //更新诊疗信息列表
    public void updateDiagnoseList(Patient patient, List<Diagnose> diagnoseList){
        Query query = new Query(Criteria.where("_id").is(patient.getId()));
        Update update = new Update().set("diagnose",diagnoseList);
        mongoTemplate.updateFirst(query,update,Patient.class);
    }

    //添加单条病史进病史列表
    public void updateHistoryList(Patient patient, List<History> historyList){
        Query query = new Query(Criteria.where("_id").is(patient.getId()));
        Update update = new Update().set("history",historyList);
        mongoTemplate.updateFirst(query,update,Patient.class);
    }

    public void addFileIdIntoCheckFileListByType(String id,String fileId,String type){
        Query query=new Query(Criteria.where("_id").is(id));
        Patient patient=mongoTemplate.findOne(query,Patient.class);
        switch(type){
//            case "CT": List<String> =
        }

    }

}

