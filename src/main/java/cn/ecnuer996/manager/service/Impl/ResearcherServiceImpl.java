package cn.ecnuer996.manager.service.Impl;

import cn.ecnuer996.manager.dao.ResearcherDao;
import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.model.Researcher;
import cn.ecnuer996.manager.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearcherServiceImpl implements ResearcherService {
    @Autowired
    private ResearcherDao researcherDao;

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
    public void updateResearcher(Researcher researcher) {
        String name = researcher.getName();
        String password = researcher.getPassword();
        List<String> roles = researcher.getRoles();
        boolean isProhibited;

        if (name == null || name.equals("")) {
            throw new ProdProcessOrderException("用户名为空");
        }
        if (password == null || password.equals("")) {
            throw new ProdProcessOrderException("密码为空");
        }
            researcherDao.updateResearcher(researcher);
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
    public List<Researcher> findByExample(Researcher researcher) {
        return researcherDao.findResearcher(researcher);
//        return researcherDao.findByExample(researcher);
    }
}
