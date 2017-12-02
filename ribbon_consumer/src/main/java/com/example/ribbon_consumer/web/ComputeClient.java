package com.example.ribbon_consumer.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>ClassName:ComputeClient</p>
 * <p>Description:Feign是一个声明式的Web Service客户端 </p>
 *
 * @Author Gyo
 * @Date 2017/9/25 9:31
 */
@FeignClient("compute-service")
public interface ComputeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String add();

}
