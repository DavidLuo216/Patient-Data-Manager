package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // insert
    public void saveAccount(Account account){
        mongoTemplate.save(account);
    }

    //query
    public Account findAccountByName(String name){
        Query query=new Query(Criteria.where("name").is(name));
        Account account=mongoTemplate.findOne(query,Account.class);
        return account;
    }

    //update
    public void updateAccount(Account account){
        Query query=new Query(Criteria.where("name").is(account.getName()));
        Update update=new Update().set("name",account.getName())
                .set("password",account.getPassword()).set("roles",account.getRoles());
        mongoTemplate.updateFirst(query,update,Account.class);
    }

    //delete
    public void deleteAccount(Integer id){
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Account.class);
    }
}
