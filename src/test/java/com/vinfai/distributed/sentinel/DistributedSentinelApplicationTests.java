package com.vinfai.distributed.sentinel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedSentinelApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        Integer.getInteger("11", 3);
    }

}
