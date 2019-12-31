package cn.ecnuer996.manager.service.Impl;

import cn.ecnuer996.manager.dao.ResultDao;
import cn.ecnuer996.manager.model.Researcher;
import cn.ecnuer996.manager.model.Result;
import cn.ecnuer996.manager.service.ResearcherService;
import cn.ecnuer996.manager.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultDao resultDao;

    @Override
    public Result getResultByID(String id) {
        return resultDao.findResultByID(id);
    }

    @Override
    public Result saveResult(Result result) {
        return resultDao.saveResult(result);
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
        return resultDao.findAllResult();
    }

    @Override
    public void updateResult(Result result) {

    }

    @Override
    public Page<Result> findResult(Result result,int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        List<Result> resultList = resultDao.findByExample(result);
        int count = resultList.size();
        List<Result> resultListPage =  resultDao.findPage(result,pageIndex,pageSize);
        return PageableExecutionUtils.getPage(resultListPage,pageable,()->count);
    }
}
