package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.PatientDao;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.service.PatientService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(value="/get-patient/{id}")
    public JSONObject getPatient(@PathVariable(value = "id") String id){
        JSONObject response=new JSONObject();
        Patient patient =patientService.getPatientByID(id);
        response.put("patient",patient);
        return response;
    }

    @PostMapping(value="/add-patient")
    public Patient addPatient(Patient patient){
        return  patientService.savePatient(patient);
    }

    @DeleteMapping(value = "/delete-patient")
    public void deletePatient(@RequestParam String id){
        patientService.deletePatient(id);
//        return "Deleted successfully!";
    }

    @PostMapping(value = "/update-patient")
    public void updatePatient(@RequestParam Patient patient){
        patientService.updatePatient(patient);
//        return "Updated successfully!";
    }

    @GetMapping(value="findAllPatient")
    public JSONArray findAllPatient(){
//        JSONObject response= new JSONObject();
        List<Patient> patientList = patientService.findAll();
        JSONArray response = new JSONArray();
        response.parseArray(JSON.toJSONString(patientList));
        return response;
    }

    @GetMapping(value="find-Patient")
    public JSONArray findPatient(@RequestParam Patient patient){
        List<Patient> patientList = patientService.findPatient(patient.getId(),patient.getName(),patient.getBirthday(),
                patient.getGender(),patient.getPhone(),patient.getAddress());
        JSONArray response = new JSONArray();
        response.parseArray(JSON.toJSONString(patientList));
        return response;
    }




}
