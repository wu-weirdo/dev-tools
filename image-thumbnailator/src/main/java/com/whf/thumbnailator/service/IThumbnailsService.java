package com.whf.thumbnailator.service;

import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

public interface IThumbnailsService {

    String changeSize(MultipartFile resource, int width, int height, String toFile);

    String changeScale(MultipartFile resource, double scale, String toFile);

    String watermark(MultipartFile resource, Positions positions, MultipartFile watermark, float opacity, String toFile);

    String rotate(MultipartFile resource, double rotate, String toFile);

    String region(MultipartFile resource, Positions positions, int width, int height, String toFile);
}
