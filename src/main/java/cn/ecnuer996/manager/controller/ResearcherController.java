package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.LogDao;
import cn.ecnuer996.manager.error.ExceptionResponse;
import cn.ecnuer996.manager.model.Log;
import cn.ecnuer996.manager.model.Researcher;
import cn.ecnuer996.manager.service.LogService;
import cn.ecnuer996.manager.service.ResearcherService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ResearcherController extends ExceptionResponse {

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private LogService logService;

    @GetMapping(value="/researcher/{name}")
    public JSONObject getResearcher(@PathVariable(value = "name") String name){
        JSONObject response=new JSONObject();
        JSONObject data = new JSONObject();
        response.put("data",data);
        Researcher researcher =researcherService.getResearcher(name);
        data.put("name",name);
        data.put("roles",researcher.getRoles());
        data.put("registerTime",researcher.getRegisterTime());
        data.put("lastLoginTime",researcher.getLastLoginTime());
        data.put("isProhibited",researcher.isProhibited());
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }


//    @GetMapping(value = "/researchers")
//    public JSONArray getAllResearchers(){
//        List<Researcher> researcherList = researcherService.findAll();
//        JSONArray response = new JSONArray();
//        for(Researcher r : researcherList){
//            JSONObject tempJO = new JSONObject();
//            tempJO.put("researcher",r);
//            response.add(tempJO);
//        }
//        return response;
//    }


//    @GetMapping(value = "/researchers")
//    public JSONObject findAll(){
//        JSONObject response = new JSONObject();
//        JSONObject data = new JSONObject();
//        List<Researcher> researcherList = researcherService.findAll();
//        data.put("researcherList",researcherList);
//        response.put("data",data);
//        response.put("code",200);
//        response.put("message","请求成功");
//        return response;
//    }


    /**
     * 多字段查询
     * @param researcher
     * @return
     */
    @GetMapping(value = "/find-researcher")
    public JSONObject findResearchers(Researcher researcher,@RequestParam("index") int pageIndex){
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        response.put("data",data);
        Page page = researcherService.findByExample(researcher,pageIndex,10);
        List<Researcher> researcherList = page.getContent();
        int totalPages = page.getTotalPages();
        int pagesize = page.getSize();
        Long totalElements = page.getTotalElements();

        if(researcherList.isEmpty()){
            response.put("code",500);
            response.put("message","无结果");
            return response;
        }
        List<Object> researcherListBack = new ArrayList<>();
//        List<Researcher> researcherList = researcherService.findAll();
        for(Researcher r : researcherList){
            JSONObject tmp = new JSONObject();
            String string = r.getName();
            tmp.put("name",r.getName());
            tmp.put("roles",r.getRoles());
            tmp.put("registerTime",r.getRegisterTime());
            tmp.put("lastLoginTime",r.getLastLoginTime());
            tmp.put("isProhibited",r.isProhibited());
            researcherListBack.add(tmp);
//            data.put(string,tmp);
        }
        data.put("researcherList",researcherListBack);
        data.put("totalPages",totalPages);
        data.put("pageIndex",pageIndex);
        data.put("totalElements",totalElements);
        data.put("pageSize",pagesize);
//        data.put("researcherList",researcherList);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    /**
     * 关于权限相关的限制还未实现
     */
    @PostMapping(value = "/researcherAdd")
    public JSONObject addResearcher(@Valid Researcher researcher, BindingResult bindingResult){
        JSONObject response = new JSONObject();
        if(bindingResult.hasErrors()){
            response.put("code",500);
            response.put("message",bindingResult.getFieldError().getDefaultMessage());
            return response;
        }
        researcherService.saveResearcher(researcher);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }


    /**
     * 关于权限相关的限制还未实现
     */
    @PostMapping(value = "/researcherDelete")
    public JSONObject deleteAccount(@RequestParam String name){
        JSONObject response = new JSONObject();
        researcherService.getResearcher(name);//先测试用户是否存在
        researcherService.deleteResearcher(name);
        response.put("code",200);
        response.put("message","删除成功");
        return response;
    }

    @PutMapping(value = "/researcher")
    public JSONObject updateResearcher(Researcher researcher){
        JSONObject response = new JSONObject();
        researcherService.updateResearcherInfo(researcher);
        response.put("code",200);
        response.put("message","更新成功");

        return response;

    }

    @GetMapping(value = "/allLogs")
    public JSONObject getAllLogs(@RequestParam("index")int pageIndex){
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        Page page = logService.getAllLogsPage(pageIndex,10);
        List<Log>logList = page.getContent();
        int totalPages = page.getTotalPages();
        int pagesize = page.getSize();
        Long totalElements = page.getTotalElements();

        data.put("LogList",logList);
        data.put("totalPages",totalPages);
        data.put("pageIndex",pageIndex);
        data.put("totalElements",totalElements);
        data.put("pageSize",pagesize);

        response.put("data",data);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    @GetMapping(value = "/logs")
    public JSONObject getAllLogsByName(@RequestParam("name") String name,
                                       @RequestParam("index")int pageIndex){
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        Page page = logService.getLogsByUsername(name,pageIndex,10);
        List<Log>logList = page.getContent();
        int totalPages = page.getTotalPages();
        int pagesize = page.getSize();
        Long totalElements = page.getTotalElements();

        data.put("LogList",logList);
        data.put("totalPages",totalPages);
        data.put("pageIndex",pageIndex);
        data.put("totalElements",totalElements);
        data.put("pageSize",pagesize);

        response.put("data",data);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

//    /**
//     * 解禁某用户
//     */
//    @PatchMapping(value = "/researcher/unban")
//    public JSONObject deProhibited(@RequestParam String name){
//        JSONObject response = new JSONObject();
//        Researcher researcher = researcherService.getResearcher(name);
//        if(!researcher.isProhibited()){
//            response.put("code",500);
//            response.put("message","用户未被封禁");
//            return response;
//        }
//        researcherService.updateIsProhibited(researcher,false);
//        response.put("code",200);
//        response.put("message","解禁成功");
//        return response;
//    }
//
//    /**
//     * 封禁某用户
//     */
//    @PatchMapping(value = "/researcher/ban")
//    public JSONObject prohibited(@RequestParam String name){
//        JSONObject response = new JSONObject();
//        Researcher researcher = researcherService.getResearcher(name);
//        if(researcher.isProhibited()){
//            response.put("code",500);
//            response.put("message","用户已经被封禁");
//            return response;
//        }
//        researcherService.updateIsProhibited(researcher,true);
//        response.put("code",200);
//        response.put("message","封禁成功");
//        return response;
//    }



}
