package cn.ecnuer996.manager.service.Impl;


import cn.ecnuer996.manager.dao.LogDao;
import cn.ecnuer996.manager.model.Log;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;


    @Override
    public List<Log> getAllLogs() {
        return logDao.getAllLogs();
    }

    @Override
    public Page<Log> getAllLogsPage(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        List<Log> logList = logDao.getAllLogs();
        int count = logList.size();
        List<Log> logListPage  = logDao.getAllLogsPage(pageIndex,pageSize);
        return  PageableExecutionUtils.getPage(logListPage,pageable,()->count);

    }

    @Override
    public Page<Log> getLogsByUsername(String username, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        List<Log> logList = logDao.getLogsByUsername(username);
        int count = logList.size();
        List<Log> logListPage  = logDao.getLogsByUsernamePage(username,pageIndex,pageSize);
        return  PageableExecutionUtils.getPage(logListPage,pageable,()->count);
    }


}
