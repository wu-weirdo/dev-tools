package com.whf.preview.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author whf
 * @date 2022/6/2 14:24
 */
public interface PreviewService {

    void onlinePreview(String url, HttpServletResponse response);
}
