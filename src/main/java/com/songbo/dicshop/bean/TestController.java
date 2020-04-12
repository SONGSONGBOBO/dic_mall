package com.songbo.dicshop.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/23 下午4:26
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    @ResponseBody
    public String test1(){
        return "test";
    }
    @RequestMapping("/testpay")
    public void test(@RequestBody Object o) {
        System.out.println(o);
    }
}
