package cn.ecnuer996.manager.error;

public enum ErrorEm implements CommonError {
    PARAMETER_VALIDATION_ERROR(10001,"参数非法"),
    UNKNOWN_ERROR(20001,"未知错误"),
    PATIENT_UPDATE_FAIL(30001,"病人更新失败")
    ;




    private int errorCode;
    private String errorMessage;

    ErrorEm(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public CommonError setErrorMessage(String message) {
        this.errorMessage = message;
        return this;
    }
}
