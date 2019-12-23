package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.error.ExceptionResponse;
import cn.ecnuer996.manager.model.Diagnose;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.service.PatientService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class PatientController extends ExceptionResponse {

    @Autowired
    private PatientService patientService;

    @GetMapping(value="/patient")
    public JSONObject getPatient(@RequestParam String id){
        JSONObject response=new JSONObject();
        JSONObject data=new JSONObject();
        Patient patient =patientService.getPatientByID(id);
        Diagnose firstDiagnose=null;
        List<JSONObject> checkDates=null;
        if(patient!=null){
            List<Diagnose> diagnoses=patient.getDiagnose();
            if(diagnoses!=null){
                firstDiagnose=patient.getDiagnose().get(0);
                checkDates=new ArrayList<>();
                for(int i=0;i<diagnoses.size();++i){
                    JSONObject date=new JSONObject();
                    date.put("value",diagnoses.get(i).getDate());
                    checkDates.add(date);
                }
            }
        }
        data.put("patient",patient);
        data.put("firstDiagnose",firstDiagnose);
        data.put("checkDates",checkDates);
        response.put("data",data);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

//参数要到body中传入 (mongo中会多一个_class字段)

    /**
     * 基本信息
     * @param patient
     * @param bindingResult
     * @return
     */
    @PostMapping(value="/patientAdd")
    public JSONObject addPatient(@Valid @RequestBody Patient patient, BindingResult bindingResult){
        JSONObject response = new JSONObject();
        if(bindingResult.hasErrors()){
            response.put("code",500);
            response.put("message",bindingResult.getFieldError().getDefaultMessage());
        }
        patientService.savePatient(patient);
        response.put("code","200");
        response.put("message","添加成功");
        return response;
    }

//    删除
    @PostMapping(value = "/patientDelete")
    public JSONObject deletePatient(@RequestParam("id") String id){
        JSONObject response = new JSONObject();
        if(id==null||id.equals("")) {
            response.put("code",500);
            response.put("message","用户不存在");
        }
        patientService.deletePatient(id);
        response.put("code",200);
        response.put("message","删除成功");
        return response;
    }

    /**
     * 更新基本信息
     * @param patient
     * @return
     */
    @PutMapping(value = "/patientUpdate")
    public JSONObject updatePatient (Patient patient){
        JSONObject response = new JSONObject();
        patientService.updatePatient(patient);
        response.put("code",200);
        response.put("message","更新成功");
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
        if(patientList.isEmpty()){
            response.put("code",500);
            response.put("message","无结果");
            return response;
        }
        data.put("patientList",patientList);
        //状态码，200是成功，其他可自定义（如500表示查询失败）
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    /**
     *  添加诊疗信息
     * @param id
     * @param diagnose
     * @return
     */
    @PatchMapping(value = "/patient/{id}/diagnose")
    public JSONObject addDiagnose(@PathVariable(value = "id")String id,Diagnose diagnose){
        JSONObject response = new JSONObject();
        patientService.addDiagnose(id,diagnose);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }

    @PatchMapping(value = "/patient/{id}/history")
    public JSONObject addDiagnose(@PathVariable(value = "id")String id,History history){
        JSONObject response = new JSONObject();
        patientService.addHistory(id,history);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }


}
