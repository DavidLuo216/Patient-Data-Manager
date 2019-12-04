package cn.ecnuer996.manager.model;

import java.util.List;

public class CheckFile {
    private List<String> CT;
    private List<String> CTImage;
    private List<String> CTPAImage;
    private List<String> CTPAMp4;
    private List<String> CTPA;

    public List<String> getCT() {
        return CT;
    }

    public void setCT(List<String> CT) {
        this.CT = CT;
    }

    public List<String> getCTImage() {
        return CTImage;
    }

    public void setCTImage(List<String> CTImage) {
        this.CTImage = CTImage;
    }

    public List<String> getCTPAImage() {
        return CTPAImage;
    }

    public void setCTPAImage(List<String> CTPAImage) {
        this.CTPAImage = CTPAImage;
    }

    public List<String> getCTPAMp4() {
        return CTPAMp4;
    }

    public void setCTPAMp4(List<String> CTPAMp4) {
        this.CTPAMp4 = CTPAMp4;
    }

    public List<String> getCTPA() {
        return CTPA;
    }

    public void setCTPA(List<String> CTPA) {
        this.CTPA = CTPA;
    }
}
