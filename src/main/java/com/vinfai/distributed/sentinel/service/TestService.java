package com.vinfai.distributed.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    @SentinelResource(value = "sayHello", blockHandler = "sayHelloBlockHandler", fallback = "sayHelloFallback")
    public String sayHello(String name) {
        /*long ms = 100 + (int) (Math.random() * 500);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int i = (int) (Math.random() * 100);
        System.out.println(i);
//        java.lang.ArithmeticException
        if (i % 2 == 0) {
            //exception test
            int i1 = i / 0;
        }
        return "hello," + name;
    }

    @SentinelResource(value = "sayHello2", blockHandler = "sayHelloBlockHandler2",fallback = "sayHelloFallback2")
    public String sayHello2(String name, Integer cost) {
        /*try {
            Thread.sleep(cost);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       if (cost >200) {
            throw  new IndexOutOfBoundsException();
       }
        return "hello," + name;
    }

    public String sayHelloBlockHandler2(String name,Integer cost, BlockException e){
        e.printStackTrace();
        return "blockException," + name + e.getRule().getResource()/**/;
    }


    public String sayHelloFallback2(String name, Integer cost, Throwable e) {
        e.printStackTrace();
        return "fallback exception," + name + cost;
    }

    //Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String sayHelloBlockHandler(String name, BlockException e) {
        e.printStackTrace();
        return "blockException," + name + e.getRule().getResource();
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String sayHelloFallback(String name/*, Throwable e*/) {
        return "fallback exception," + name;
    }




}
