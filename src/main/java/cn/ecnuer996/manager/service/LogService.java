package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.model.Log;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LogService {

    List<Log> getAllLogs();

    Page<Log> getAllLogsPage(int pageIndex, int pageSize);

    Page<Log> getLogsByUsername(String username,int pageIndex,int pageSize);

}
