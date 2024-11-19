package com.whf.message.queue.controller;

import com.whf.message.queue.model.User;
import com.whf.message.queue.provider.RocketProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/rocket/")
public class RocketController {

    @Resource
    private RocketProvider rocketProvider;

    public AtomicInteger id = new AtomicInteger(0);

    @RequestMapping("sync")
    public String sync() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("sync");
        rocketProvider.sendMessage(user);
        return "success";
    }

    @RequestMapping("async")
    public String async() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("async");
        rocketProvider.sendAsyncMessage(user);
        return "success";
    }

    @RequestMapping("oneway")
    public String oneway() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("oneway");
        rocketProvider.sendOneWayMessage(user);
        return "success";
    }

    @RequestMapping("delay")
    public String delay() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("delay");
        rocketProvider.sendDelayMessage(user);
        return "success";
    }

    @RequestMapping("order")
    public String order() {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(id.getAndIncrement());
            user.setName("whf");
            user.setAge(18);
            user.setAddress("order" + i);
            rocketProvider.sendOrderMessage(user);
        }
        return "success";
    }

    @RequestMapping("transaction")
    public String transaction() {
        User user = new User();
        user.setId(id.getAndIncrement());
        user.setName("whf");
        user.setAge(18);
        user.setAddress("transaction");
        rocketProvider.sendTransactionMessage(user);
        return "success";
    }
}
