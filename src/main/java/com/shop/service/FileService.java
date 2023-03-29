package com.shop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
        UUID uuid = UUID.randomUUID(); //1
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension; //2
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl); //3
        fos.write(fileData); //4
        fos.close();
        return savedFileName; //5
    }

    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath); //6

        if(deleteFile.exists()){ //7
            deleteFile.delete();
            log.info("파일을 삭제하였습니다");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }

}
