package com.whf.preview.service.impl;

import com.whf.preview.service.PreviewService;
import com.whf.preview.utils.FileConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author whf
 * @date 2022/6/2 14:25
 */
@Service
@Slf4j
public class PreviewServiceImpl implements PreviewService {

    /**
     * @Description:系统文件在线预览接口
     * @Author: tarzan
     */
    @Override
    public void onlinePreview(String url, HttpServletResponse response) {
        //获取文件类型
        String[] str = url.split("\\.");

        if (str.length == 0) {
            log.error("文件格式不正确");
            return;
        }
        String suffix = str[str.length - 1];
        if (!suffix.equals("txt") && !suffix.equals("doc") && !suffix.equals("docx") && !suffix.equals("xls")
                && !suffix.equals("xlsx") && !suffix.equals("ppt") && !suffix.equals("pptx")) {
            log.error("文件格式不支持预览");
            return;
        }
        try {
            InputStream in = FileConvertUtil.convertLocaleFile(url, suffix);
            OutputStream outputStream = response.getOutputStream();
            //创建存放文件内容的数组
            byte[] buff = new byte[1024];
            //所读取的内容使用n来接收
            int n;
            //当没有读取完时,继续读取,循环
            while ((n = in.read(buff)) != -1) {
                //将字节数组的数据全部写入到输出流中
                outputStream.write(buff, 0, n);
            }
            //强制将缓存区的数据进行输出
            outputStream.flush();
            //关流
            outputStream.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
