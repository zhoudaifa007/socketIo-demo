package com.example.controller;

import com.example.model.AddFileBean;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ZHOUDF2 on 2016-12-26.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        System.out.println("Controller hello");
        return "Hello " + name;
    }

    @RequestMapping(value = "/token", method=RequestMethod.POST)
    @ResponseBody
    public String checkToken(@RequestBody AddFileBean addFileBean){
        System.out.println(addFileBean.getFileNname());
        return "token";
    }
}