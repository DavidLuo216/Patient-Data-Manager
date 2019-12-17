package cn.ecnuer996.manager.service;


import cn.ecnuer996.manager.model.Patient;
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
     * 修改病人信息
     * @param patient
     */
    public void updatePatient(Patient patient);

    /**
     * 列出所有病人
     * @return
     */
    public  List<Patient> findAll();



}
