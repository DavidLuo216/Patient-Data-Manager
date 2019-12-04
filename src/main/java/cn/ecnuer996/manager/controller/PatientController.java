package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.PatientDao;
import cn.ecnuer996.manager.model.Patient;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientDao patientDao;

    @GetMapping(value="get-patient")
    public JSONObject getPatient(@RequestParam String id){
        JSONObject response=new JSONObject();
        Patient patient =patientDao.findAccountByName(id);
        response.put("patient",patient);
        return response;
    }
}
