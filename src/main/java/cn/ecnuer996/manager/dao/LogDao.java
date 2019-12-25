package cn.ecnuer996.manager.dao;

import cn.ecnuer996.manager.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class LogDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 返回所有的操作记录
     * @return
     */
    public List<Log> getAllLogsPage(int pageIndex,int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Criteria criteria = new Criteria();
        Query query = new Query(criteria).with(pageable);
        return mongoTemplate.find(query, Log.class, "log");
    }

    public List<Log> getAllLogs() {

        return mongoTemplate.findAll(Log.class);
    }
    /**
     * 根据用户名返回
     * @param name
     * @return
     */
    public List<Log> getLogsByUsername(String name){
        Query query = new Query((Criteria.where("username").is(name)));
        return mongoTemplate.find(query,Log.class);
    }

    public List<Log> getLogsByUsernamePage(String name,int pageIndex,int pageSize){
        Pageable pageable = PageRequest.of(pageIndex,pageSize);
        Query query = new Query((Criteria.where("username").is(name))).with(pageable);
        return mongoTemplate.find(query,Log.class,"log");
    }
    /**
     * 增加一条操作记录
     */
    public void addLog(String username,String patientId,String type){
        String details = null;
        if(patientId.equals("-1")){
            int count = Integer.parseInt(type);
            details = username+"批量导入了"+type+"条病患基本信息";
        }
        else
        switch(type){
            case "addPatientInfo":
                details= username+"增加了病患(ID:"+patientId+")的病患信息";
                break;
            case "addPatientDiagnose":
                details = username+"增加了病患(ID:"+patientId+")的诊疗记录";
                break;
            case "deletePatientInfo":
                details= username+"删除了病患(ID:"+patientId+")的病患信息";
                break;
            case "deletePatientDiagnose":
                details = username+"删除了病患(ID:"+patientId+")的诊疗记录";
                break;
            case "updatePatientInfo":
                details= username+"更新了病患(ID:"+patientId+")的病患信息";
                break;
            case "updatePatientDiagnose":
                details= username+"更新了病患(ID:"+patientId+")的诊疗记录";
                break;
        }

        Log log = new Log();
        log.setUsername(username);
        log.setDetails(details);

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.setDate(df.format(date));
        mongoTemplate.save(log);
    }
}
