package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Researcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResearcherDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // insert
    public Researcher saveResearcher(Researcher researcher){
        return mongoTemplate.save(researcher);
    }

    //query
    public Researcher findResearcherByName(String name){
        Query query=new Query(Criteria.where("name").is(name));
        Researcher researcher=mongoTemplate.findOne(query,Researcher.class);
        return researcher;
    }


    //根据用户组来查找用户
//    public Researcher findResearcherByRole(String role){
//        Query query = new Query(Criteria.where("roles").is());
//    }

    public List<Researcher> findByExample(Researcher researcher){
        Criteria criteria = Criteria.byExample(researcher);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query,Researcher.class,"researcher");
    }

    //update
    public void updateResearcher(Researcher researcher){
        Query query=new Query(Criteria.where("name").is(researcher.getName()));
        Update update=new Update().set("name",researcher.getName())
                .set("password",researcher.getPassword()).set("roles",researcher.getRoles())
                .set("prohibited",String.valueOf(researcher.isProhibited()));
        mongoTemplate.updateFirst(query,update,Researcher.class);
    }

    //delete
    public void deleteResearcherByName(String name){
        Query query=new Query(Criteria.where("name").is(name));
        mongoTemplate.remove(query,Researcher.class);
    }

    //find all

    public List<Researcher> findAllResearchers(){
        List<Researcher> researcherList = mongoTemplate.findAll(Researcher.class);
        return researcherList;
    }

}


