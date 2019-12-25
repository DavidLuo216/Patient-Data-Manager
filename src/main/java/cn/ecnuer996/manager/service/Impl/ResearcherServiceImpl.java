package cn.ecnuer996.manager.service.Impl;

import cn.ecnuer996.manager.dao.ResearcherDao;
import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.model.Researcher;
import cn.ecnuer996.manager.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearcherServiceImpl implements ResearcherService {
    @Autowired
    private ResearcherDao researcherDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Researcher getResearcher(String name) {
        Researcher researcher =researcherDao.findResearcherByName(name);
        if(researcher==null)
            throw new ProdProcessOrderException("找不到该用户");
        else
            return researcher;
    }

    @Override
    public void deleteResearcher(String name) {
        if(name==null||name.equals(""))
            throw new ProdProcessOrderException("输入要删除的用户名称！");
        researcherDao.deleteResearcherByName(name);
    }

    @Override
    public void updateResearcherInfo(Researcher researcher) {
        String name = researcher.getName();
        String password = researcher.getPassword();
        List<String> roles = researcher.getRoles();
//        boolean isProhibited;

        if (name == null || name.equals("")) {
            throw new ProdProcessOrderException("用户名为空");
        }
//        if (password == null || password.equals("")) {
//            throw new ProdProcessOrderException("密码为空");
//        }
            researcherDao.updateResearcherInfo(researcher);
    }

    @Override
    public void updateIsProhibited(Researcher researcher,Boolean bool){
        researcherDao.updateIsProhibited(researcher,bool);
    }

    @Override
    public Researcher saveResearcher(Researcher researcher) {
        return researcherDao.saveResearcher(researcher);
    }

    @Override
    public List<Researcher> findResearchersByRoles(List<String> roles) {
        return null;
    }

    @Override
    public List<Researcher> findAll() {
        return researcherDao.findAllResearchers();
    }

    @Override
    public Page<Researcher> findByExample(Researcher researcher, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex,pageSize);
        List<Researcher> researcherList =researcherDao.findResearcher(researcher);
        int count = researcherList.size();
        List<Researcher> researcherListPage = researcherDao.findResearcherPageable(researcher,pageIndex,pageSize);
        return  PageableExecutionUtils.getPage(researcherListPage,pageable,()->count);
//        return researcherDao.findByExample(researcher);
    }
}
