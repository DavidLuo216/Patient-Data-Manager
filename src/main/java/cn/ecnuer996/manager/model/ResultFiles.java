package cn.ecnuer996.manager.model;

import java.util.List;

public class ResultFiles {
    private List<String> txt;
    private List<String> igs;
    private List<String> stl;

    public List<String> getTxt() {
        return txt;
    }

    public void setTxt(List<String> txt) {
        this.txt = txt;
    }

    public List<String> getIgs() {
        return igs;
    }

    public void setIgs(List<String> igs) {
        this.igs = igs;
    }

    public List<String> getStl() {
        return stl;
    }

    public void setStl(List<String> stl) {
        this.stl = stl;
    }
}
