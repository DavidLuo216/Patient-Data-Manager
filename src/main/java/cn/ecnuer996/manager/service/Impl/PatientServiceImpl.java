package cn.ecnuer996.manager.service.Impl;

import cn.ecnuer996.manager.dao.PatientDao;
import cn.ecnuer996.manager.error.BusinessException;
import cn.ecnuer996.manager.error.ErrorEm;
import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.service.PatientService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;


    @Override
    public Patient savePatient(Patient patient) {
//        String id = patient.getId();
//        String name = patient.getName();
//        if(id == null||id.equals("")){
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"ID不可为空");
//        }
//        if(name == null||name.equals("")){
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"姓名不可为空");
//        }
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
    public int updatePatient(Patient patient) {
        String id = patient.getId();
        String name = patient.getName();
        String birthday = patient.getBirthday();
        String gender = patient.getGender();
        String phone = patient.getPhone();
        String address = patient.getAddress();
        if(id == null||id.equals("")){
            throw new ProdProcessOrderException("ID为空");
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"ID不可为空");
        }
        if(name == null||name.equals("")){
            throw new ProdProcessOrderException("姓名为空");
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"姓名不可为空");
        }
//        if(birthday == null||birthday.equals("")){
//            throw new ProdProcessOrderException("生日为空");
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"生日不可为空");
//        }
        if(gender == null||gender.equals("")){
            throw new ProdProcessOrderException("性别为空");
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"性别不可为空");
        }
        if(phone == null||phone.equals("")){
            throw new ProdProcessOrderException("联系方式为空");
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"联系方式不可为空");
        }
        if(address == null||address.equals("")){
            throw new ProdProcessOrderException("联系地址为空");
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"联系地址不可为空");
        }
        int result = 0;
        result = patientDao.updatePatient(patient);

        return result;
    }

    @Override
    public List<Patient> findAll() {
        return patientDao.findAllPatients();
    }

    @Override
    public void addHistory(Patient patient, History history) {
        String disease = history.getDisease();
        String details = history.getDetails();
        if(disease == null||disease.equals("")){
            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"请填写病症");
        }
        if(details == null||details.equals("")){
            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"请描述细节");
        }
        patientDao.addHistory(patient,history);
    }
}
