package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.dao.ResearcherDao;
import cn.ecnuer996.manager.model.Researcher;

import java.util.List;

public class ResearcherServiceImpl implements ResearcherService {

    private ResearcherDao researcherDao = new ResearcherDao();

    @Override
    public Researcher getResearcher(String name) {
        return null;
    }

    @Override
    public void deleteResearcher(String name) {

    }

    @Override
    public void updateResearcher(Researcher researcher) {

    }

    @Override
    public Researcher saveResearcher(Researcher researcher) {
        return null;
    }

    @Override
    public List<Researcher> findResearcher(String name, List<String> roles) {
        return null;
    }

    @Override
    public List<Researcher> findAll() {
        return researcherDao.findAllResaerchers();
    }
}
