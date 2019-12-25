package cn.ecnuer996.manager.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class FileInfo implements Serializable {

    @Id
    @JSONField
    private ObjectId id;

    private String filename;

    private Long length;

    private Date uploadDate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
