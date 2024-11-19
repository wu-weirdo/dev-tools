package com.whf.message.queue.controller;

import com.whf.message.queue.model.User;
import com.whf.message.queue.provider.RabbitProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/rabbit/")
public class RabbitController {

    @Resource
    private RabbitProvider rabbitProvider;

    public AtomicInteger id = new AtomicInteger(0);

    @RequestMapping("direct")
    public String direct() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("direct");
        rabbitProvider.sendDirectMessage(user);
        return "success";
    }

    @RequestMapping("fanout")
    public String fanout() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("fanout");
        rabbitProvider.sendFanoutMessage(user);
        return "success";
    }

    @RequestMapping("topic")
    public String topic() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("topic");
        rabbitProvider.sendTopicMessage(user);
        return "success";
    }

    @RequestMapping("delay")
    public String delay() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("delay");
        rabbitProvider.sendDelayMessage(user);
        return "success";
    }
}
