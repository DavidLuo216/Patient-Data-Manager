package cn.ecnuer996.manager.service;


import cn.ecnuer996.manager.model.Diagnose;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface PatientService {
    /**
     * 添加病人信息
     * @param patient
     */
    public Patient savePatient(Patient patient);

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
     * 根据参数patient中不为null的字段进行多条件相等查询
     * @param patient
     * @return
     */
    Page<Patient> findByExample(Patient patient, int pageIndex, int pageSize);

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

    void addHistory(String id, History history);

    void addFileIdToLists(String fileId,String type,Diagnose diagnose);


}
