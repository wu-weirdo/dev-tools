package com.whf.thumbnailator.controller;

import com.whf.thumbnailator.service.IThumbnailsService;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class ThumbnailsController {
    @Resource
    private IThumbnailsService thumbnailsService;

    /**
     * 指定大小缩放
     */
    @GetMapping("/changeSize")
    public String changeSize(MultipartFile resource, int width, int height) {
        String toFile = "C:\\Users\\Administrator\\Desktop\\thumbnailator\\changeSize";
        return thumbnailsService.changeSize(resource, width, height, toFile);
    }

    /**
     * 指定比例缩放
     */
    @GetMapping("/changeScale")
    public String changeScale(MultipartFile resource, double scale) {
        String toFile = "C:\\Users\\Administrator\\Desktop\\thumbnailator\\changeScale";
        return thumbnailsService.changeScale(resource, scale, toFile);
    }

    /**
     * 添加水印 watermark(位置,水印,透明度)
     */
    @GetMapping("/watermark")
    public String watermark(MultipartFile resource, Positions center, MultipartFile watermark, float opacity) {
        String toFile = "C:\\Users\\Administrator\\Desktop\\thumbnailator\\watermark";
        return thumbnailsService.watermark(resource, Positions.CENTER, watermark, opacity, toFile);
    }

    /**
     * 图片旋转 rotate(度数),顺时针旋转
     */
    @GetMapping("/rotate")
    public String rotate(MultipartFile resource, double rotate) {
        String toFile = "C:\\Users\\Administrator\\Desktop\\thumbnailator\\rotate";
        return thumbnailsService.rotate(resource, rotate, toFile);
    }

    /**
     * 图片裁剪
     */
    @GetMapping("/region")
    public String region(MultipartFile resource, Positions center, int width, int height) {
        String toFile = "C:\\Users\\Administrator\\Desktop\\thumbnailator\\region";
        return thumbnailsService.region(resource, Positions.CENTER, width, height, toFile);
    }
}
