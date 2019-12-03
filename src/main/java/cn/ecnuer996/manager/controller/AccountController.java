package cn.ecnuer996.manager.controller;

import cn.ecnuer996.manager.dao.AccountDao;
import cn.ecnuer996.manager.model.Account;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    @GetMapping(value="get-account")
    public JSONObject getAccount(@RequestParam String name){
        JSONObject response=new JSONObject();
        Account account=accountDao.findAccountByName(name);
        response.put("name",account.getName());
        response.put("password",account.getPassword());
        response.put("roles",account.getRoles());
        return response;
    }

    @GetMapping(value="test")
    public JSONObject test(@RequestParam String name){
        JSONObject response=new JSONObject();
        response.put("name",name);
        return response;
    }

}
