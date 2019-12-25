package cn.ecnuer996.manager.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Patient {
    @NotBlank(message = "输入病人ID！")
    private String id;
    @NotBlank(message = "输入病人姓名！")
    private String name;
    @NotBlank(message = "输入生日！")
    private String birthday;
    @NotBlank(message = "输入性别！")
    private String gender;
    @NotBlank(message = "输入联系方式！")
    private String phone;
    @NotBlank(message = "输入联系地址！")
    private String address;
    private List<History> history;
    private List<Diagnose> diagnose;

    public Patient() {
    }

    public Patient(String id, String name, String birthday, String gender, String phone, String address, List<History> history, List<Diagnose> diagnose) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.history = history;
        this.diagnose = diagnose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<Diagnose> getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(List<Diagnose> diagnose) {
        this.diagnose = diagnose;
    }
}
