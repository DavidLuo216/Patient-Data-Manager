package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Researcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class ResearcherDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // insert
    public void saveResearcher(Researcher researcher){
        mongoTemplate.save(researcher);
    }

    //query
    public Researcher findResearcherByName(String name){
        Query query=new Query(Criteria.where("name").is(name));
        Researcher researcher=mongoTemplate.findOne(query,Researcher.class);
        return researcher;
    }

    //update
    public void updateResearcher(Researcher researcher){
        Query query=new Query(Criteria.where("name").is(researcher.getName()));
        Update update=new Update().set("name",researcher.getName())
                .set("password",researcher.getPassword()).set("roles",researcher.getRoles());
        mongoTemplate.updateFirst(query,update,Researcher.class);
    }

    //delete
    public void deleteResearcher(Integer id){
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Researcher.class);
    }
}
