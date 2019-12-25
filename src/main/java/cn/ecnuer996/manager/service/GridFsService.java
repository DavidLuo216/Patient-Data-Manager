package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.model.FileInfo;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface GridFsService {

    void init();

    /**
     * 插入文件
     * @param file
     * @return
     */
    GridFSInputFile save(MultipartFile file);

    /**
     * 上传文件
     * 对save的进一步封装 用于不同的服务层
     * @param file
     * @return
     */
    Object uploadData(MultipartFile file);

    /**
     * 上传文件组
     * @param files
     */
    List<Object> uploadFiles(List<MultipartFile> files);


    GridFSDBFile getById(ObjectId id);

    /**
     * 下载和显示文件 返回的格式均为数据流 （View可能不是？）
     * @param id
     * @param response
     */
    void getFile(String id, HttpServletResponse response);

    void viewFile(String id, HttpServletResponse response);

    void remove(String id);

    List<FileInfo> getFileInfoList();
}
