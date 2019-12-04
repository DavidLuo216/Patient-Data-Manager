package cn.ecnuer996.manager.model;

import java.util.List;

public class Researcher {
    private String name;
    private String password;
    private List<String> roles;
    private boolean prohibited;
    private String registerTime;
    private String lastLoginTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isProhibited() {
        return prohibited;
    }

    public void setProhibited(boolean prohibited) {
        this.prohibited = prohibited;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
