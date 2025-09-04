package com.whf.oshi.controller;

import com.whf.oshi.common.R;
import com.whf.oshi.entity.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {
    @GetMapping()
    public R getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return R.success(server);
    }
}
