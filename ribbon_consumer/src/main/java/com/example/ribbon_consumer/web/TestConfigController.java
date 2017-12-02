package com.example.ribbon_consumer.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ClassName:TestConfigController</p>
 * <p>Description: </p>
 *
 * @Author Gyo
 * @Date 2017/9/26 12:29
 */
@RestController
@RefreshScope
public class TestConfigController {

    @Value("${from}")
    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

}
