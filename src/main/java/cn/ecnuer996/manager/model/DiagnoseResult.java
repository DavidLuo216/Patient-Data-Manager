package cn.ecnuer996.manager.model;

public class DiagnoseResult {
    private boolean haveCoronaryDisease;
    private String diseaseName;
    private String severity;

    public boolean isHaveCoronaryDisease() {
        return haveCoronaryDisease;
    }

    public void setHaveCoronaryDisease(boolean haveCoronaryDisease) {
        this.haveCoronaryDisease = haveCoronaryDisease;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
