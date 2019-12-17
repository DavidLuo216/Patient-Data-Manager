package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.ResultDao;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.model.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {

    @Autowired
    private ResultDao resultDao;

    @GetMapping(value="get-result")
    public JSONObject getPatient(@RequestParam String id){
        JSONObject response=new JSONObject();
        Result result =resultDao.findResultByID(id);
        response.put("result",result);
        return response;
    }
}
