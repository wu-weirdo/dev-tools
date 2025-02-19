package com.whf.fileupload.service;

import com.whf.fileupload.model.FileUploadDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件上传服务
 * @author whf
 * @date 2024/3/7
 */
@Service
public interface FileUploadService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);

    /**
     * 检查上传
     * @param dto
     * @return
     */
    Map<String, Object> checkUpload(FileUploadDTO dto);

    /**
     * 分片上传
     * @param dto
     * @return
     */
    Boolean chunkUpload(FileUploadDTO dto);

    /**
     * 文件下载
     * @param fileName
     * @return
     */
    ResponseEntity<FileSystemResource> download(String fileName);
}
