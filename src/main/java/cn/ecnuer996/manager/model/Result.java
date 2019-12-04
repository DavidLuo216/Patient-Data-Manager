package cn.ecnuer996.manager.model;

public class Result {
    private String researcher;
    private String patientId;
    private String checkDate;
    private String processDate;
    private Algorithm algorithm;
    private ResultData shrink;
    private ResultData stretch;

    public String getResearcher() {
        return researcher;
    }

    public void setResearcher(String researcher) {
        this.researcher = researcher;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public ResultData getShrink() {
        return shrink;
    }

    public void setShrink(ResultData shrink) {
        this.shrink = shrink;
    }

    public ResultData getStretch() {
        return stretch;
    }

    public void setStretch(ResultData stretch) {
        this.stretch = stretch;
    }
}
