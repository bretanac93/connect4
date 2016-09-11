package com.bretana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testExec() {
        Assert.assertEquals("Hello World", "Hello World");
    }
}
