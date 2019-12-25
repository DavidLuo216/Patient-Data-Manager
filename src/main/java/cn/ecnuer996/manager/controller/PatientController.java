package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.error.ExceptionResponse;
import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.CheckFile;
import cn.ecnuer996.manager.model.Diagnose;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.service.GridFsService;
import cn.ecnuer996.manager.service.PatientService;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class PatientController extends ExceptionResponse {

    @Autowired
    private PatientService patientService;
    @Autowired
    private GridFsService gridFsService;

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Resource
    private MongoDbFactory mongoDbFactory;

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
     *  添加诊疗信息（单条）
     *  Header：Content-Type:application/json
     * @param id
     * @param diagnose
     * @return
     */
    @PostMapping(value = "/patient/{id}/diagnose")
    public JSONObject addDiagnose(@PathVariable(value = "id")String id,
                                  @RequestBody Diagnose diagnose,
                                  @RequestParam("files") List<Map<String,Object>> filesWithType){
        JSONObject response = new JSONObject();


//        List<> files = (List<MultipartFile>) filesWithType.get("files");
        for(Map<String,Object> m : filesWithType ){
            MultipartFile file = (MultipartFile) m.get("file");
            Object upLoadResult = gridFsService.uploadData(file);
            if(!upLoadResult.toString().equals("upload fail")){
                Map params = (Map)upLoadResult;
                String fileId = (String) params.get("id");

                String type = (String) m.get("type");

                patientService.addFileIdToLists(fileId,type,diagnose);

            }
            throw new ProdProcessOrderException("文件上传失败");
        }

        patientService.addDiagnose(id,diagnose);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }

//    /**
//     * 添加病史（单条）
//     * @param id
//     * @param history
//     * @return
//     */
//    @PostMapping(value = "/patient/{id}/history")
//    public JSONObject addHistory(@PathVariable(value = "id")String id,@Valid History history){
//        JSONObject response = new JSONObject();
//        patientService.addHistory(id,history);
//        response.put("code",200);
//        response.put("message","添加成功");
//        return response;
//    }

    /**
     * 上传文件
     */

    @PostMapping(value = "/file/upload")
    public Object uploadFile(@RequestParam("files") List<MultipartFile> files){
        return gridFsService.uploadFiles(files);
    }

    /**
     * 查看文件接口
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/file/view/{id}")
    public void viewFile(@PathVariable(value = "id") String id, HttpServletResponse response) throws IOException {
//        return gridFsService.uploadData(file);
        gridFsService.viewFile(id, response);
//        // 获得SpringBoot提供的mongodb的GridFS对象
//        DBObject metaData = new BasicDBObject();
//
//        // 获得提交的文件名
//        String fileName = file.getOriginalFilename();
//        // 获得文件输入流
//        InputStream ins = file.getInputStream();
//        // 获得文件类型
//        String contentType = file.getContentType();
//        // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
//        ObjectId objectId = gridFsTemplate.store(ins,fileName,metaData);
//        //将文件信息保存到关系型数据库中进行维护
////           FileInfoAO fileInfo = new FileInfoAO();
////           fileInfo.setContentType(contentType);
////           fileInfo.setFileName(fileName);
////           fileInfo.setLastUpdateBy(user != null ? user.getId() : null);
////           fileInfo.setMongoFileId(objectId.toString());
//         String id = objectId.toString();
    }


}
