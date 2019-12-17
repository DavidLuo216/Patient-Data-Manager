package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.dao.PatientDao;
import cn.ecnuer996.manager.model.Patient;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao = new PatientDao();


    @Override
    public Patient savePatient(Patient patient) {
        return patientDao.savePatient(patient);
    }

    @Override
    public void deletePatient(String id) {
        patientDao.deletePatient(id);
    }

    @Override
    public Patient getPatientByID(String id) {
        Patient patient = patientDao.findPatientByID(id);
        return patient;
    }

    @Override
    public List<Patient> findPatient(String id, String name, String birthday, String gender, String phone, String address) {

        return patientDao.findPatient(id,name,gender,phone,address);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientDao.updatePatient(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientDao.findAllPatients();
    }
}
