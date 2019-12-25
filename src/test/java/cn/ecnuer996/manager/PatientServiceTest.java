package cn.ecnuer996.manager;

import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.History;
import cn.ecnuer996.manager.model.Patient;
import com.alibaba.fastjson.JSONObject;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PatientServiceTest {

    @Test
    public void insertByCsv() throws IOException {
        File file =  new File("/Users/ihsingchang/Desktop/patient.csv");
        CsvReader csvReader = new CsvReader();

        List<Patient> patientList = new ArrayList<>();

        CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
        for (CsvRow row : csv.getRows()) {
            if (row.getOriginalLineNumber() != 1) {
                Patient patient = new Patient();
                patient.setId(row.getField(0));
                patient.setName(row.getField(1));
                patient.setBirthday(row.getField(2));
                patient.setGender(row.getField(3));
                patient.setPhone(row.getField(4));
                patient.setAddress(row.getField(5));
                List<History> histories = new ArrayList<>();

                String histroyString = row.getField(6);
                String historyString1 = histroyString.substring(1, histroyString.indexOf("]"));
                String[] histroyStringArr = historyString1.split("},");
                for (int i = 0; i < histroyStringArr.length - 1; i++) {
                    histroyStringArr[i] = histroyStringArr[i] + "}";
                }
                for (String historyItem : histroyStringArr) {
                    JSONObject tmp = JSONObject.parseObject(historyItem);
                    History history = new History();
                    history.setDisease((String) tmp.get("disease"));
                    history.setDetails((String) tmp.get("details"));
                    histories.add(history);
                }
                patient.setHistory(histories);
                patientList.add(patient);
            }
        }
    }
}
