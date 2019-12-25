package cn.ecnuer996.manager.service.Impl;

import cn.ecnuer996.manager.error.ProdProcessOrderException;
import cn.ecnuer996.manager.model.FileInfo;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import cn.ecnuer996.manager.service.GridFsService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GridFsServiceImpl implements GridFsService {
    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    private MongoTemplate mongoTemplate;


    GridFsTemplate gridFsTemplate = null;

    @Override
    @PostConstruct
    public void init() {
        gridFsTemplate = new GridFsTemplate(mongoDbFactory, mongoTemplate.getConverter());

    }

    /**
     * 插入文件
     * @param file
     * @return
     */
    @Override
    public GridFSInputFile save(MultipartFile file) {
        //mongofactory在springboot 2.x 被重写了 不能直接用
//        String fileName = file.getOriginalFilename();
//        String contentType = file.getContentType();
//        DBObject metaData = new BasicDBObject();
//        InputStream inputStream = null;
//        try
//        {
//            inputStream = file.getInputStream();
//            ObjectId objectId = gridFsTemplate.store(inputStream, fileName);
//            String id = objectId.toString();
//            return id;
//        }catch(IOException e){
//            throw new RuntimeException();mongodb://userName:password%40@localhost:27017/db?authSource=admin&authMechanism=SCRAM-SHA-1
//        }
//        MongoClientURI uri = new MongoClientURI("mongodb://david:password@118.25.158.194:27017/test?authSource=admin&authMechanism=SCRAM-SHA-1");
//        Mongo mongo = new Mongo(new MongoURI("mongodb://david:password@118.25.158.194:27017/test"));
//        Mongo mongo = new Mongo(uri);
//        DB db = mongo.getDB(uri.getDatabase());
//        GridFS gridFS = new GridFS(db);
        MongoClientURI uri = new MongoClientURI("mongodb://david:password@118.25.158.194:27017/test");
        MongoClient mongo = new MongoClient(uri);
        DB db = mongo.getDB(uri.getDatabase());
        GridFS gridFS = new GridFS(db);
        try {

            InputStream in = file.getInputStream();

            String name = file.getOriginalFilename();

            GridFSInputFile gridFSInputFile = gridFS.createFile(in);

            gridFSInputFile.setFilename(name);

            gridFSInputFile.setContentType(file.getContentType());

            gridFSInputFile.save();

            return gridFSInputFile;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public HashMap<String,Object> uploadData(MultipartFile file) {
        GridFSInputFile inputFile = this.save(file);

        if (inputFile == null) {
            throw new ProdProcessOrderException("上传文件为空");
        }
        String id = inputFile.getId().toString();
        String md5 = inputFile.getMD5();
        String name = inputFile.getFilename();
        long length = inputFile.getLength();


        HashMap<String, Object> dt = new HashMap<String, Object>();
        dt.put("id", id);
        dt.put("md5", md5);
        dt.put("name", name);
        dt.put("length", length);

        return dt;
//        return null;
    }

    /**
     * 上传文件组
     * @param files
     */
    @Override
    public List<Object> uploadFiles(List<MultipartFile> files){
        List<Object> allParams = new ArrayList<>();
        for(MultipartFile m : files)
        {
            Object params =  this.uploadData(m);
            allParams.add(params);
        }
        return allParams;
    }

    /**
     * 获取文件 根据id
     * @param id
     * @return
     */
    @Override
    public GridFSDBFile getById(ObjectId id) {
        MongoClientURI uri = new MongoClientURI("mongodb://david:password@118.25.158.194:27017/test");
        MongoClient mongo = new MongoClient(uri);
        DB db = mongo.getDB(uri.getDatabase());
        GridFS gridFS = new GridFS(db);

        return gridFS.findOne(new BasicDBObject("_id",id));
    }

    @Override
    public void getFile(String id, HttpServletResponse response) {

        GridFSDBFile file = this.getById(new ObjectId(id));

        if (file == null) {
            return;
        }

        OutputStream os = null;

        try {
            os = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + file.getFilename());
            response.addHeader("Content-Length", "" + file.getLength());
            response.setContentType("application/octet-stream");
            file.writeTo(os);
            os.flush();
            os.close();

        } catch (Exception e) {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e2) {
            }
            e.printStackTrace();
        }

    }

    @Override
    public void viewFile(String id, HttpServletResponse response) {
        GridFSDBFile file = this.getById(new ObjectId(id));

        if (file == null) {
            return;
        }

        OutputStream os = null;

        try {
            os = response.getOutputStream();
            response.addHeader("Content-Disposition", "inline;filename=" + file.getFilename());
            response.addHeader("Content-Length", "" + file.getLength());
            response.setContentType(file.getContentType());
            file.writeTo(os);
            os.flush();
            os.close();

        } catch (Exception e) {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e2) {
            }
            e.printStackTrace();
        }

    }


    /**
     * 移除文件
     * @param id
     */
    @Override
    public void remove(String id) {
        MongoClientURI uri = new MongoClientURI("mongodb://david:password@118.25.158.194:27017/test");
        MongoClient mongo = new MongoClient(uri);
        DB db = mongo.getDB(uri.getDatabase());
        GridFS gridFS = new GridFS(db);

        gridFS.remove(new ObjectId(id));

    }

    /**
     * gridFSDBFile的api改了
     * @return
     */
    @Override
    public List<FileInfo> getFileInfoList() {
//        GridFSFindIterable gridFSDBFiles = gridFsTemplate.find(null);
//
//        List<FileInfo> collect = gridFSDBFiles.stream().map(i -> {
//            FileInfo fileInfo = new FileInfo();
//            fileInfo.setFilename(i.getFilename());
//
//            fileInfo.setId((ObjectId) i.getId());
//            fileInfo.setLength(i.getLength());
//            fileInfo.setUploadData(i.getUploadDate());
//            return fileInfo;
//        }).collect(Collectors.toList());
//
//        return collect;
        return null;
    }
}
