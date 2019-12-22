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
        Researcher researcher =researcherService.getResearcher(name);
        response.put("name",researcher);
        return response;
    }


    @GetMapping(value = "/researchers")
    public JSONArray getAllResearchers(){
        List<Researcher> researcherList = researcherService.findAll();
        JSONArray response = new JSONArray();
        for(Researcher r : researcherList){
            JSONObject tempJO = new JSONObject();
            tempJO.put("researcher",r);
            response.add(tempJO);
        }
        return response;
    }


    /**
     * 关于权限相关的限制还未实现
     */
    @PostMapping(value = "/researcherAdd")
    public String addResearcher(@Valid Researcher researcher, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        researcherService.saveResearcher(researcher);
        return "Success!";
    }


    /**
     * 关于权限相关的限制还未实现
     */
    @PostMapping(value = "researcherDelete")
    public String deleteAccount(@RequestParam String name){
        researcherService.getResearcher(name);//先测试用户是否存在
        researcherService.deleteResearcher(name);
        return "Success";
    }

    @PutMapping(value = "/researcher")
    public String updateResearcher(Researcher researcher){
        researcherService.updateResearcher(researcher);
        return "Success";
    }


}
