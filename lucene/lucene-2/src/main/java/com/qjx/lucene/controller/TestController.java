package com.qjx.lucene.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author: qinjiaxing
 * @Date: 2022/11/4 21:33
 * @Description:
 */
@RestController
public class TestController {
    /**
     * @param type 1 走正常逻辑，2 thread 1min，2 ...
     * @return
     */
    @GetMapping("/test/{type}")
    public String test(@PathVariable Integer type) {
        switch (type) {
            case 1:
                test1();
                break;
            case 2:
                test2();
                break;
            default:
        }
        return "hello world";
    }

    private void test2() {
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void test1() {
        System.out.println("11111");
    }

}
