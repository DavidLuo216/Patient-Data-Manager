package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Researcher;
import cn.ecnuer996.manager.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // insert
    public void saveResult(Result result){
        mongoTemplate.save(result);
    }

    //query
    public Result findResultByID(String id){
        Query query=new Query(Criteria.where("_id").is(id));
        Result result=mongoTemplate.findOne(query,Result.class);
        return result;
    }

    public List<Result> findAllResult(){
        List<Result> resultList = mongoTemplate.findAll(Result.class);
        return resultList;
    }

    //delete
    public void deleteResultByID(String id){
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Result.class);
    }
}
