package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.model.Result;
import cn.ecnuer996.manager.service.ResultService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ResultController {

    private ResultService resultService;

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping(value="/result/{id}")
    public JSONObject getResult(@PathVariable("id") String id){
        JSONObject response=new JSONObject();
        Result result =resultService.getResultByID(id);
        response.put("result",result);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    /**
     * result导入 （body中传JSON）
     * headers：Content_type : application/json
     * @param result
     * @param bindingResult
     * @return
     */
    @PostMapping(value="/resultAdd")
    public JSONObject addResult(@Valid @RequestBody Result result, BindingResult bindingResult){
        JSONObject response = new JSONObject();
        if(bindingResult.hasErrors()){
            response.put("code",500);
            response.put("message",bindingResult.getFieldError().getDefaultMessage());
            return response;
        }
        resultService.saveResult(result);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }

    @PostMapping(value = "/resultDelete")
    public  JSONObject deleteResult(@RequestParam("_id")String id){
        JSONObject response = new JSONObject();
        resultService.deleteResult(id);
        response.put("code",200);
        response.put("message","删除成功");
        return response;
    }

//    @GetMapping(value = "/results")
//    public JSONObject findAllResults(){
//        JSONObject response = new JSONObject();
//        JSONObject data = new JSONObject();
//        List<Result> resultList = resultService.findAll();
//        data.put("resultList",resultList);
//        response.put("data",data);
//        response.put("code",200);
//        response.put("message","请求成功");
//        return response;
//    }

    /**
     * 多条件查询/也可填空参数来获取所有结果
     * @param result
     * @return
     */

    @GetMapping(value = "/find-result")
    public JSONObject findResults(Result result,@RequestParam("index") int pageIndex){
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();

        Page page = resultService.findResult(result,pageIndex,10);
        List<Result> resultList = page.getContent();
        if(resultList.isEmpty()){
            response.put("code",500);
            response.put("message","无结果");
            return response;
        }
        int totalPages = page.getTotalPages();
        int pagesize = page.getSize();
        Long totalElements = page.getTotalElements();

        data.put("totalPages",totalPages);
        data.put("pageIndex",pageIndex);
        data.put("totalElements",totalElements);
        data.put("pageSize",pagesize);
        data.put("resultList",resultList);

        response.put("data",data);
        response.put("code",200);
        response.put("message","请求成功");
        return response;
    }

    @PostMapping(value = "/resultUpdate")
    public JSONObject updateResult(@Valid @RequestBody Result result,BindingResult bindingResult){
        JSONObject response = new JSONObject();
        if(bindingResult.hasErrors()){
            response.put("code",500);
            response.put("message",bindingResult.getFieldError().getDefaultMessage());
            return response;
        }
        resultService.saveResult(result);
        response.put("code",200);
        response.put("message","添加成功");
        return response;
    }
}
