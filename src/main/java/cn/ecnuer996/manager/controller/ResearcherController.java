package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.ResearcherDao;
import cn.ecnuer996.manager.error.ExceptionResponse;
import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.Researcher;
import cn.ecnuer996.manager.service.ResearcherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ResearcherController extends ExceptionResponse {

    @Autowired
    private ResearcherService researcherService;

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


    @GetMapping(value = "/researchers")
    public JSONObject findAll(Researcher researcher){
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        List<Researcher> researcherList = researcherService.findAll();
        data.put("researcherList",researcherList);
        response.put("data",data);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }


    /**
     * 多字段查询
     * @param researcher
     * @return
     */
    @GetMapping(value = "/find-researcher")
    public JSONObject findResearchers(Researcher researcher){
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        List<Researcher> researcherList = researcherService.findByExample(researcher);
//        List<Researcher> researcherList = researcherService.findAll();
        data.put("researcherList",researcherList);
        response.put("data",data);
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
        researcherService.updateResearcher(researcher);
        response.put("code",200);
        response.put("message","更新成功");
        return response;
    }

}
