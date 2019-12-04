package cn.ecnuer996.manager.model;

public class PlaqueInfo {
    private String name;    //斑块名
    private double value;   //斑块大小
    private String pointOne;//斑块位置点一
    private String pointTwo;//斑块位置点二
    private String type;    //类型
    private String location;//左右

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPointOne() {
        return pointOne;
    }

    public void setPointOne(String pointOne) {
        this.pointOne = pointOne;
    }

    public String getPointTwo() {
        return pointTwo;
    }

    public void setPointTwo(String pointTwo) {
        this.pointTwo = pointTwo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
