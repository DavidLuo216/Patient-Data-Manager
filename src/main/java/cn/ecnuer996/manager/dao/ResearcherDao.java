package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Researcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    /**
     * update
     */
    public void updateResearcherInfo(Researcher researcher){
        Query query=new Query(Criteria.where("name").is(researcher.getName()));
        Update update = new Update();
        if(researcher.getPassword()!=null&&!researcher.getPassword().equals("")){
            update.set("password",researcher.getPassword());
        }
        if(researcher.getRoles()!=null&&!researcher.getRoles().isEmpty()){
            update.set("roles",researcher.getRoles());
        }
        if(researcher.getLastLoginTime()!=null&&!"".equals(researcher.getLastLoginTime())){
            update.set("lastLoginTime",researcher.getLastLoginTime());
        }
        update.set("isProhibited",researcher.isProhibited());
        mongoTemplate.updateFirst(query,update,Researcher.class);
    }

    public void updateIsProhibited(Researcher researcher,boolean bool){
        Query query=new Query(Criteria.where("name").is(researcher.getName()));
        Update update = new Update().set("isProhibited",bool);
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

    /**
     * 不分页
     * @param researcher
     * @return
     */
    public List<Researcher> findResearcher(Researcher researcher){
        Criteria criteria = new Criteria();
        Query query = new Query();

        String name = researcher.getName();
        List<String> roles = researcher.getRoles();
        Boolean isProhibited = researcher.isProhibited();
        String registerTime = researcher.getRegisterTime();
        String lastLoginTime = researcher.getLastLoginTime();
        if(name!=null&&!name.equals("")){
            criteria.and("name").is(name);
        }
        if(roles!=null){
            criteria.and("roles").is(roles);
        }
//        if(isProhibited!=null){
//            criteria.and("name").is(name);
//        }
        if(registerTime!=null&&!registerTime.equals("")){
            criteria.and("registerTime").is(registerTime);
        }
        if(lastLoginTime!=null&&!lastLoginTime.equals("")){
            criteria.and("lastLoginTime").is(lastLoginTime);
        }
        criteria.and("isProhibited").is(isProhibited);
        query.addCriteria(criteria);


        return mongoTemplate.find(query,Researcher.class,"researcher");

    }

    public List<Researcher> findResearcherPageable(Researcher researcher,int pageIndex,int pageSize){
        Criteria criteria = new Criteria();
        Query query = new Query();
        Pageable pageable = PageRequest.of(pageIndex,pageSize);
        String name = researcher.getName();
        List<String> roles = researcher.getRoles();
        Boolean isProhibited = researcher.isProhibited();
        String registerTime = researcher.getRegisterTime();
        String lastLoginTime = researcher.getLastLoginTime();
        if(name!=null&&!name.equals("")){
            criteria.and("name").is(name);
        }
        if(roles!=null){
            criteria.and("roles").is(roles);
        }
//        if(isProhibited!=null){
//            criteria.and("name").is(name);
//        }
        if(registerTime!=null&&!registerTime.equals("")){
            criteria.and("registerTime").is(registerTime);
        }
        if(lastLoginTime!=null&&!lastLoginTime.equals("")){
            criteria.and("lastLoginTime").is(lastLoginTime);
        }
        criteria.and("isProhibited").is(isProhibited);
        query.addCriteria(criteria);

        query.with(pageable);


        return mongoTemplate.find(query,Researcher.class,"researcher");

    }


}


