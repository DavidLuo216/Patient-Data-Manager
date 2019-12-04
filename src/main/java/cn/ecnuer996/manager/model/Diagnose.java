package cn.ecnuer996.manager.model;

import java.util.List;

public class Diagnose {
    private String date;
    private CheckInfo checkInfo;
    private DiagnoseResult diagnoseResult;
    private List<Medication> medication;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CheckInfo getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(CheckInfo checkInfo) {
        this.checkInfo = checkInfo;
    }

    public DiagnoseResult getDiagnoseResult() {
        return diagnoseResult;
    }

    public void setDiagnoseResult(DiagnoseResult diagnoseResult) {
        this.diagnoseResult = diagnoseResult;
    }

    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }
}
