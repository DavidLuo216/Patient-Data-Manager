package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.ResultDao;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.model.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ResultController {

    @Autowired
    private ResultDao resultDao;

    @GetMapping(value="/result/{_id}")
    public JSONObject getResult(@PathVariable("_id") String id){
        JSONObject response=new JSONObject();
        Result result =resultDao.findResultByID(id);
        response.put("result",result);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    @PostMapping(value="/resultAdd")
    public JSONObject addResult(@Valid Result result, BindingResult bindingResult){
        JSONObject response = new JSONObject();
        if(bindingResult.hasErrors()){
            response.put("code",500);
            response.put("message",bindingResult.getFieldError().getDefaultMessage());
            return response;
        }
        resultDao.saveResult(result);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }

    @PostMapping(value = "/resultDelete")
    public  JSONObject deleteResult(@RequestParam("_id")String id){
        JSONObject response = new JSONObject();
        resultDao.deleteResultByID(id);
        response.put("code",200);
        response.put("message","删除成功");
        return response;
    }

    @GetMapping(value = "/find-result")
    public JSONObject findResults(Result result){
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        List<Result> resultList = resultDao.findByExample(result);
        if(resultList.isEmpty()){
            response.put("code",500);
            response.put("message","无结果");
            return response;
        }
        data.put("resultList",resultList);
        response.put("data",data);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    @PutMapping(value = "/result")
    public JSONObject updateResult(Result result){
        return null;
    }
}
