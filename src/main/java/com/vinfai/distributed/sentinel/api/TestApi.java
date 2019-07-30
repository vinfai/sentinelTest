package com.vinfai.distributed.sentinel.api;

import com.vinfai.distributed.sentinel.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestApi {

    @Autowired
    TestService testService;

    @GetMapping(value = "/sayhello")
    public String sayHello(@RequestParam String name) {

        String s = testService.sayHello(name);
        return s;
    }

    @GetMapping(value = "/sayhello/{name}/{cost}")
    public String sayHelloByTime(@PathVariable String name, @PathVariable Integer cost) {

//


        String s = testService.sayHello2(name,cost);
        return s;
    }
}
