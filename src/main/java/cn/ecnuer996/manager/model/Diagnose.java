package cn.ecnuer996.manager.model;

import java.util.List;

public class Diagnose {
    private String date;
    private String hospital;
    private CheckInfo checkInfo;
    private DiagnoseResult diagnoseResult;
    private List<Medication> medication;

    public String getDate() {
        return date;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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
