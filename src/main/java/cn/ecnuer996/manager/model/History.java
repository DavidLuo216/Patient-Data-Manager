package cn.ecnuer996.manager.model;

import javax.validation.constraints.NotBlank;

public class History {
    @NotBlank(message = "不能为空")
    private String disease;
    @NotBlank(message = "不能为空")
    private String details;

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
