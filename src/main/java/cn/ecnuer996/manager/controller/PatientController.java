package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.error.ExceptionResponse;
import cn.ecnuer996.manager.model.*;
import cn.ecnuer996.manager.service.GridFsService;
import cn.ecnuer996.manager.service.PatientService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            if(diagnoses!=null && diagnoses.size()!=0){
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
    @CrossOrigin
    @PostMapping(value="/patientAdd")
    public JSONObject addPatient(@Valid @RequestBody Patient patient, BindingResult bindingResult){
        JSONObject response = new JSONObject();
        if(bindingResult.hasErrors()){
            response.put("code",500);
            response.put("message",bindingResult.getFieldError().getDefaultMessage());
        }
        patientService.savePatient(patient);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }

    /**
     * 通过csv批量导入
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/patientAddCsv")
    public JSONObject addPatientCsv(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        return patientService.insertByCsv(multipartFile);
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
     * 无条件分页查询
     * @param pageIndex
     * @return
     */
    @GetMapping(value="/find-patient")
    public JSONObject findPatient(@RequestParam("index") int pageIndex){
        //response是这个api返回给前端的整个JSON对象
        JSONObject response=new JSONObject();
        //data也是一个JSON对象，保存的是数据库的查询结果，这里data中保存一个Patient对象列表
        JSONObject data=new JSONObject();
        response.put("data",data);
        Page page = patientService.findByExample(pageIndex,10);
        int totalPages = page.getTotalPages();
        int pagesize = page.getSize();
        Long totalElements = page.getTotalElements();
        List<Patient> patientList=page.getContent();
        if(patientList.isEmpty()){
            response.put("code",500);
            response.put("message","无结果");
            return response;
        }
        data.put("patientList",patientList);
        //状态码，200是成功，其他可自定义（如500表示查询失败）
        data.put("totalPages",totalPages);
        data.put("pageIndex",pageIndex);
        data.put("totalElements",totalElements);
        data.put("pageSize",pagesize);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    /**
     * 多字段查询/包含年份范围，性别作为查询条件
     * @param pageIndex
     * @param beginYear
     * @param endYear
     * @param gender
     * @return
     */
    @GetMapping(value="/find-patient-with-condition")
    public JSONObject findPatientByCondition(@RequestParam("index") int pageIndex,
                                             @RequestParam(value = "beginYear",required = false) String beginYear,
                                             @RequestParam(value="endYear",required = false) String endYear,
                                             @RequestParam(value="gender",required = false) String gender){
        //response是这个api返回给前端的整个JSON对象
        JSONObject response=new JSONObject();
        //data也是一个JSON对象，保存的是数据库的查询结果，这里data中保存一个Patient对象列表
        JSONObject data=new JSONObject();
        response.put("data",data);
        Page page = patientService.findByExample(beginYear,endYear,gender,pageIndex,10);
        int totalPages = page.getTotalPages();
        int pagesize = page.getSize();
        Long totalElements = page.getTotalElements();
        List<Patient> patientList=page.getContent();
        data.put("patientList",patientList);
        //状态码，200是成功，其他可自定义（如500表示查询失败）
        data.put("totalPages",totalPages);
        data.put("pageIndex",pageIndex);
        data.put("totalElements",totalElements);
        data.put("pageSize",pagesize);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    /**
     *  添加诊疗信息的文字基本信息（单条）
     *  Header：Content-Type:application/json
     * @param id
     * @param diagnose
     * @return
     */
    @PostMapping(value = "/patient/{id}/diagnoseInfo")
    public JSONObject addDiagnoseInfo(@PathVariable(value = "id")String id,
                                  @RequestBody Diagnose diagnose){
        JSONObject response = new JSONObject();
        Diagnose searchDiagnose=patientService.findDiagnoseByIdAndDate(id,diagnose.getDate());
        if(searchDiagnose==null){
            //没有对应日期的诊疗记录，新增诊疗记录
            patientService.addDiagnose(id,diagnose);
        }else{
            //有对应日期的诊疗记录，更新诊疗记录
            patientService.updateDignose(id,diagnose);
        }
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }

    /**
     * 上传图片
     * @param id
     * @param date
     * @param file
     * @return
     */
    @PostMapping(value = "/patient/{id}/diagnoseImage")
    public JSONObject addDiagnoseImage(@PathVariable(value = "id")String id,
                                       @RequestParam(value = "date")String date,
                                       @RequestParam(value = "file") MultipartFile file){
        JSONObject response = new JSONObject();

//        List<> files = (List<MultipartFile>) filesWithType.get("files");
        HashMap<String,Object> upLoadResult = gridFsService.uploadData(file);


        String fileId = (String) upLoadResult.get("id");

        String type = "CTImage";

        Diagnose diagnose = patientService.findDiagnoseByIdAndDate(id,date);

        patientService.addFileIdToLists(fileId,type,diagnose);
            //  此时diagnose已经成功添加上文件
        patientService.updateDignose(id,diagnose);
        response.put("code",200);
        response.put("message","添加成功");
        return response;


        }


    @PostMapping(value = "/patient/{id}/removeImage")
    public JSONObject removeDiagnoseImage(@PathVariable(value = "id") String id,
                                          @RequestParam(value = "date")String date,
                                          @RequestParam(value = "fileId")String fileId){
        JSONObject response = new JSONObject();
        Diagnose diagnose = patientService.findDiagnoseByIdAndDate(id,date);
        patientService.removeFileFromLists(fileId,"CTImage",diagnose);
        patientService.updateDignose(id,diagnose);
        gridFsService.remove(fileId);
        response.put("code",200);
        response.put("message","删除成功");
        return response;
        }

    @PostMapping(value = "/patient/{id}/diagnoseMp4")
    public JSONObject addDiagnoseMp4(@PathVariable(value = "id")String id,
                                       @RequestParam(value = "date")String date,
                                       @RequestParam(value = "file") MultipartFile file){
        JSONObject response = new JSONObject();

//        List<> files = (List<MultipartFile>) filesWithType.get("files");
        HashMap<String,Object> upLoadResult = gridFsService.uploadData(file);


        String fileId = (String) upLoadResult.get("id");

        String type = "CTPAMp4";

        Diagnose diagnose = patientService.findDiagnoseByIdAndDate(id,date);

        patientService.addFileIdToLists(fileId,type,diagnose);
        //  此时diagnose已经成功添加上文件
        patientService.updateDignose(id,diagnose);
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
