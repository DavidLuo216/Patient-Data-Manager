package cn.ecnuer996.manager.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Researcher {
    @NotNull(message = "用户不能为空")
    private String name; //唯一标识

    @NotNull(message = "密码不能为空")
    private String password;


    private List<String> roles;
    private boolean isProhibited;
    private String registerTime;
    private String lastLoginTime;

    public Researcher() {
    }

    public Researcher(String name,String password, List<String> roles, boolean isProhibited, String registerTime, String lastLoginTime) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.isProhibited = isProhibited;
        this.registerTime = registerTime;
        this.lastLoginTime = lastLoginTime;
    }

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
        return isProhibited;
    }

    public void setProhibited(boolean prohibited) {
        this.isProhibited = prohibited;
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
