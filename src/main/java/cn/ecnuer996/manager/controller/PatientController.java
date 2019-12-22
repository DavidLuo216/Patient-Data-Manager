package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.error.ExceptionResponse;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.service.PatientService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class PatientController extends ExceptionResponse {

    @Autowired
    private PatientService patientService;

    @GetMapping(value="/patient/{id}")
    public JSONObject getPatient(@PathVariable(value = "id") String id){
        JSONObject response=new JSONObject();
        Patient patient =patientService.getPatientByID(id);
        response.put("patient",patient);
        return response;
    }

//参数要到body中传入 (mongo中会多一个_class字段)
    @PostMapping(value="/patientAdd")
    public String addPatient(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        patientService.savePatient(patient);
        return"Added successfully!";
    }

//    删除
    @PostMapping(value = "/patientDelete")
    public String deletePatient(@RequestParam("id") String id){
        patientService.deletePatient(id);
        return "Deleted successfully!";
    }

    //
    @PutMapping(value = "/patient")
    public String updatePatient (Patient patient){
        patientService.updatePatient(patient);
//        if(result==1)
        return "Updated successfully!";
//            return"Failed";
        }

    @GetMapping(value="/patients")
    public JSONArray getAllPatients(){
        List<Patient> patientList = patientService.findAll();
        JSONArray response = new JSONArray();
        for(Patient p : patientList){
            JSONObject tempJO= new JSONObject();
            tempJO.put("patient",p);
            response.add(tempJO);
        }
//        response.parseArray(JSON.toJSONString(patientList));
//        JSONArray response = JSONArray.parseArray(JSON.toJSONString(patientList));
        return response;
    }

    /**
     * 多字段查询
     * @param patient
     * @return
     */
    @GetMapping(value="/find-patient")
    public JSONObject findPatient(Patient patient){
        //response是这个api返回给前端的整个JSON对象
        JSONObject response=new JSONObject();
        //data也是一个JSON对象，保存的是数据库的查询结果，这里data中保存一个Patient对象列表
        JSONObject data=new JSONObject();
        response.put("data",data);
        List<Patient> patientList=patientService.findByExample(patient);
        data.put("patientList",patientList);
        //状态码，200是成功，其他可自定义（如500表示查询失败）
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    //添加病史
    @PostMapping(value = "/patientHistory")
    public String addHistory(@RequestParam("id") String id,@RequestParam History history){
        Patient patient = patientService.getPatientByID(id);
        return"Success";
    }




}
