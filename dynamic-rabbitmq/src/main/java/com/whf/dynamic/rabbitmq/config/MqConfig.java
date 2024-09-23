package com.whf.dynamic.rabbitmq.config;

import lombok.Data;

@Data
public class MqConfig {

    private String host;

    private Integer port;

    private String username;

    private String password;
}
