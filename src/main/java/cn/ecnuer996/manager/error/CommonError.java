package cn.ecnuer996.manager.error;

public interface CommonError {
    int getErrorCode();

    String getErrorMessage();

    CommonError setErrorMessage(String message);
}