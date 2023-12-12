package com.whf.preview.controller;

import com.whf.preview.service.PreviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author whf
 * @date 2022/6/2 14:35
 */
@RestController
public class PreviewController {

    @Resource
    private PreviewService previewService;

    @GetMapping("/preview")
    public void onlinePreview(@RequestParam(value = "url", required = false) String url, HttpServletResponse response) {
        url = "D:\\test\\1653285241016.xlsx";
        previewService.onlinePreview(url,response);
    }
}
