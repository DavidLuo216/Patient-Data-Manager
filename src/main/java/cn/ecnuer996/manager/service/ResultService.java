package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.model.Result;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ResultService  {
    /**
     * 指定ID返回结果
     * @param id
     * @return
     */
    public Result getResultByID(String id);

    /**
     * 添加结果信息
     * @param result
     * @return
     */
    public Result saveResult(Result result);

    /**
     * 返回指定病人的结果
     * @param patientId
     * @return
     */
    public Result getResultByPatientID(String patientId);

    public void deleteResult(String id);

    public List<Result> findAll();

    public void updateResult(Result result);

    Page<Result> findResult(Result result, int pageIndex, int pageSize);


}
