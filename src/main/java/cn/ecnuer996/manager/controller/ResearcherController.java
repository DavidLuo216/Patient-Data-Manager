package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.ResearcherDao;
import cn.ecnuer996.manager.model.Researcher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResearcherController {
    @Autowired
    private ResearcherDao researcherDao;

    @GetMapping(value="/get-researcher")
    public JSONObject getAccount(@RequestParam(value = "name") String name){
        JSONObject response=new JSONObject();
        Researcher researcher =researcherDao.findResearcherByName(name);
        response.put("account",researcher);
        return response;
    }

//    @DeleteMapping(value = "delete-researcher")
//    public JSONObject deleteAccount(@RequestParam String name){
//        JSONObject response=new JSONObject();
//        Researcher researcher = researcherDao.findResearcherByName(name);
//    }

}
