package cn.ecnuer996.manager.service;


import cn.ecnuer996.manager.model.Diagnose;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    /**
     * 添加病人信息(单条）
     * @param patient
     */
    public Patient savePatient(Patient patient);

    /**
     * 批量导入（csv）
     */
    JSONObject insertByCsv(MultipartFile file) throws IOException;
    /**
     * 根据ID删除病人信息
     * @param id
     */
    public void deletePatient(String id);

    /**
     * 根据ID get病人
     * @param id
     * @return id所指病人
     */
    public Patient getPatientByID(String id);

    /**
     * 查询病人信息
     * @param id
     * @param name
     * @param birthday
     * @param gender
     * @param phone
     * @param address
     * @return
     */
    public List<Patient> findPatient(String id,String name,String birthday,String gender,String phone,String address);

    /**
     * 无条件分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Patient> findByExample(int pageIndex, int pageSize);

    /**
     * 分页查询，包含出生年份的范围查询
     * @param beginYear
     * @param endYear
     * @param gender
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Patient> findByExample(String beginYear, String endYear, String gender, int pageIndex, int pageSize);

    /**
     * 修改病人信息
     * @param patient
     */
    public int updatePatient(Patient patient);

    /**
     * 列出所有病人
     * @return
     */
    public  List<Patient> findAll();

    void addDiagnose(String id, Diagnose diagnose);

    void updateDignose(String id,Diagnose diagnose);

    void addHistory(String id, History history);

    void addFileIdToLists(String fileId,String type,Diagnose diagnose);

    /**
     * 根据id和日期返回唯一diagnose
     * @param id
     * @param date
     * @return
     */
    Diagnose findDiagnoseByIdAndDate(String id,String date);

}
