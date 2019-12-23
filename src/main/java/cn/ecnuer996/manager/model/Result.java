package cn.ecnuer996.manager.model;

import javax.validation.constraints.NotBlank;

public class Result {
    @NotBlank(message = "请填写信息")
    private  String _id;
    @NotBlank(message = "请填写信息")
    private String researcher;
    @NotBlank(message = "请填写信息")
    private String patientId;
    @NotBlank(message = "请填写信息")
    private String checkDate;
    @NotBlank(message = "请填写信息")
    private String processDate;
    private Algorithm algorithm;
    private ResultData shrink;
    private ResultData stretch;

    public Result() {
    }

    public Result(String _id,String researcher, String patientId, String checkDate, String processDate, Algorithm algorithm, ResultData shrink, ResultData stretch) {
        this._id = _id;
        this.researcher = researcher;
        this.patientId = patientId;
        this.checkDate = checkDate;
        this.processDate = processDate;
        this.algorithm = algorithm;
        this.shrink = shrink;
        this.stretch = stretch;
    }

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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
