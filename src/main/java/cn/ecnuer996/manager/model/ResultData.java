package cn.ecnuer996.manager.model;

import java.util.List;
import java.util.Map;

public class ResultData {
    public Map<String, List<String>> files;
    public List<PlaqueInfo> plaqueInfos;

    public Map<String, List<String>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, List<String>> files) {
        this.files = files;
    }

    public List<PlaqueInfo> getPlaqueInfos() {
        return plaqueInfos;
    }

    public void setPlaqueInfos(List<PlaqueInfo> plaqueInfos) {
        this.plaqueInfos = plaqueInfos;
    }
}
