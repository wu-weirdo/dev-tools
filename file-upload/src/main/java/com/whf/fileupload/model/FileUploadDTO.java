package com.whf.fileupload.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author whf
 * @date 2024/3/7
 */
@Data
public class FileUploadDTO {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件md5
     */
    private String fileMd5;

    /**
     * 文件分片md5
     */
    private String chunkMd5;

    /**
     * 文件分片大小
     */
    private Integer chunkSize;

    /**
     * 当前分片数
     */
    private Integer chunkNumber;

    /**
     * 总分片数量
     */
    private Integer chunkTotal;

    /**
     * 文件
     */
    private MultipartFile file;
}
