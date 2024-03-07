package com.whf.fileupload.controller;

import com.whf.fileupload.model.FileUploadDTO;
import com.whf.fileupload.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * 文件上传
 *
 * @author whf
 * @date 2024/3/7
 */
@RestController
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService;

    @RequestMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(MultipartFile file) {
        String path = fileUploadService.upload(file);
        return ResponseEntity.ok().body(Collections.singletonMap("path", path));
    }

    /**
     * 检查文件上传
     * @param dto
     * @return
     */
    public ResponseEntity<Map<String, Object>> checkUpload(@RequestBody FileUploadDTO dto) {
        Map<String, Object> result = fileUploadService.checkUpload(dto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/chunkUpload")
    public ResponseEntity<Map<String, Object>> chunkUpload(@RequestBody FileUploadDTO dto) {
        Boolean result = fileUploadService.chunkUpload(dto);
        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }
}
