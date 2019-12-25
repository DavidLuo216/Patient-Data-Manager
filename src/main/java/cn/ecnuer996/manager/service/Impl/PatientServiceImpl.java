package cn.ecnuer996.manager.service.Impl;

import cn.ecnuer996.manager.dao.LogDao;
import cn.ecnuer996.manager.dao.PatientDao;
import cn.ecnuer996.manager.error.BusinessException;
import cn.ecnuer996.manager.error.ErrorEm;
import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.*;
import cn.ecnuer996.manager.service.PatientService;
import com.alibaba.fastjson.JSONObject;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private LogDao logDao;


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
        Patient tmp = patientDao.savePatient(patient);

        String username = "吴忠明";//暂未实现用户切换
        logDao.addLog(username,patient.getId(),"addPatientInfo");//更新操作记录

        return tmp;
    }

    @Override
    public JSONObject insertByCsv(MultipartFile file) throws IOException {
        CsvReader csvReader = new CsvReader();
        if(file==null||file.getOriginalFilename()==null||file.getOriginalFilename().equals("")){
            throw new ProdProcessOrderException("文件(名)不可为空");
        }
        File fileUse = new File(file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), fileUse);

        List<Patient> patientList = new ArrayList<>();

        CsvContainer csv = csvReader.read(fileUse, StandardCharsets.UTF_8);
        for(CsvRow row : csv.getRows()){
            if(row.getOriginalLineNumber()!=1) {
                Patient patient =new Patient();
                patient.setId(row.getField(0));
                patient.setName(row.getField(1));
                patient.setBirthday(row.getField(2));
                patient.setGender(row.getField(3));
                patient.setPhone(row.getField(4));
                patient.setAddress(row.getField(5));
                List<History> histories = new ArrayList<>();

                String histroyString = row.getField(6);
                String historyString1 = histroyString.substring(1,histroyString.indexOf("]"));
                String [] histroyStringArr = historyString1.split("},");
                for(int i =0;i<histroyStringArr.length-1;i++){
                    histroyStringArr[i] = histroyStringArr[i] + "}";
                }
                for(String historyItem : histroyStringArr){
                    JSONObject tmp = JSONObject.parseObject(historyItem);
                    History history = new History();
                    history.setDisease((String) tmp.get("disease"));
                    history.setDetails((String)tmp.get("details"));
                    histories.add(history);
                }
                patient.setHistory(histories);
                patientList.add(patient);
            }
        }


        JSONObject response = new JSONObject();
        patientDao.insertPatientList(patientList);
        response.put("CountOfInsertion",patientList.size());
        response.put("patientList",patientList);
        response.put("code",200);
        response.put("message","成功导入"+patientList.size()+"条记录！");

        logDao.addLog("吴忠明","-1",String.valueOf(patientList.size()));

        File del = new File(fileUse.toURI()); //删除项目产生的临时文件
        del.delete();
        return response;
     }

    @Override
    public void deletePatient(String id) {
        if(id==null||id.equals("")){
            throw new ProdProcessOrderException("ID为空");
        }
        patientDao.deletePatient(id);

        String username = "吴忠明";//暂未实现用户切换
        logDao.addLog(username,id,"deletePatientInfo");//更新操作记录
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

    /**
     * 分页查询
     * @param patient
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Patient> findByExample(Patient patient, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        List<Patient> patientList = patientDao.findByExample(patient);
        int count = patientList.size();
        List<Patient> patientListPage  = patientDao.findPageable(patient,pageIndex,pageSize);
        return  PageableExecutionUtils.getPage(patientListPage,pageable,()->count);

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
        if(birthday == null||birthday.equals("")){
            throw new ProdProcessOrderException("生日为空");
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"生日不可为空");
        }
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
    public void addDiagnose(String id, Diagnose diagnose) {
        Patient patient = patientDao.findPatientByID(id);
        List<Diagnose> diagnoseList = patient.getDiagnose();
        diagnoseList.add(diagnose);
        patientDao.updateDiagnoseList(patient,diagnoseList);

        String username = "吴忠明";//暂未实现用户切换
        logDao.addLog(username,id,"AddPatientDiagnose");//更新操作记录

//        if(disease == null||disease.equals("")){
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"请填写病症");
//        }
//        if(details == null||details.equals("")){
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"请描述细节");
//        }
//        patientDao.addHistory(patient,history);
    }

    @Override
    public void addHistory(String id, History history) {
        Patient patient = patientDao.findPatientByID(id);
        List<History> historyList = patient.getHistory();
        historyList.add(history);
        patientDao.updateHistoryList(patient,historyList);

//        if(disease == null||disease.equals("")){
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"请填写病症");
//        }
//        if(details == null||details.equals("")){
//            throw new BusinessException(ErrorEm.PARAMETER_VALIDATION_ERROR,"请描述细节");
//        }
//        patientDao.addHistory(patient,history);
    }

    /**
     * 根据上传的文件类型在正确列表中传入文件ID号
     * @param fileId
     * @param type
     * @param diagnose
     */
    @Override
    public void addFileIdToLists(String fileId, String type,Diagnose diagnose) {
        CheckInfo checkInfo = diagnose.getCheckInfo();
        CheckFile diagnoseFiles = diagnose.getCheckInfo().getCheckFile();
        List<String> CTFiles = diagnoseFiles.getCT();
        List<String> CTImages = diagnoseFiles.getCTImage();
        List<String> CTPAImages = diagnoseFiles.getCTPA();
        List<String> CTPAMp4 = diagnoseFiles.getCTPAMp4();
        List<String> CTPAs = diagnoseFiles.getCTPA();

        switch(type){
            case "CT":
                CTFiles.add(fileId);
                diagnoseFiles.setCT(CTFiles);
                break;
            case "CTImage":
                CTImages.add(fileId);
                diagnoseFiles.setCT(CTImages);
                break;
            case "CTPAImage":
                CTPAImages.add(fileId);
                diagnoseFiles.setCT(CTPAImages);
                break;
            case "CTPAMp4":
                CTPAMp4.add(fileId);
                diagnoseFiles.setCT(CTPAMp4);
                break;
            case "CTPA":
                CTPAs.add(fileId);
                diagnoseFiles.setCT(CTPAs);
                break;

            default:
                throw new ProdProcessOrderException("类型不正确");
        }

        checkInfo.setCheckFile(diagnoseFiles);
        diagnose.setCheckInfo(checkInfo);
    }
}
