package cn.ecnuer996.manager.model;

import java.util.List;

public class Patient {
    private String id;
    private String name;
    private String birthday;
    private String gender;
    private String phone;
    private String address;
    private List<History> history;
    private List<Diagnose> diagnose;

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
