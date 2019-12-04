package cn.ecnuer996.manager.model;

public class CheckInfo {
    private String checkDate;
    private BasicCheck basicCheck;
    private CheckFile checkFile;

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public BasicCheck getBasicCheck() {
        return basicCheck;
    }

    public void setBasicCheck(BasicCheck basicCheck) {
        this.basicCheck = basicCheck;
    }

    public CheckFile getCheckFile() {
        return checkFile;
    }

    public void setCheckFile(CheckFile checkFile) {
        this.checkFile = checkFile;
    }
}
