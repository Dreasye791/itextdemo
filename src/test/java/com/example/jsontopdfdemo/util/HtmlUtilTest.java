package com.example.jsontopdfdemo.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HtmlUtilTest {

    @Autowired
    HtmlUtil htmlUtil;

    @BeforeEach
    void setUp() {
    }

    @Test
    void setHtmlResource(){
        File file = new File("D://a.html");
        try {
            htmlUtil.setHtmlSource(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}