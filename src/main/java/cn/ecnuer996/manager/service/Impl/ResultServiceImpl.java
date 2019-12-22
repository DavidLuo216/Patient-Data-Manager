package cn.ecnuer996.manager.service.Impl;

import cn.ecnuer996.manager.dao.ResultDao;
import cn.ecnuer996.manager.model.Researcher;
import cn.ecnuer996.manager.model.Result;
import cn.ecnuer996.manager.service.ResearcherService;
import cn.ecnuer996.manager.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultDao resultDaoDao;

    @Override
    public Result getResultByID(String id) {
        return null;
    }

    @Override
    public Result saveResult(Result result) {
        return null;
    }

    @Override
    public Result getResultByPatientID(String patientId) {
        return null;
    }

    @Override
    public void deleteResult(String id) {

    }

    @Override
    public List<Result> findAll() {
        return null;
    }

    @Override
    public void updateResult(Result result) {

    }
}
