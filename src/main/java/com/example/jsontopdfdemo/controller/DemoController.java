package com.example.jsontopdfdemo.controller;

import com.example.jsontopdfdemo.util.pdfUtil;
import com.example.jsontopdfdemo.vo.DemoVo;
import com.lowagie.text.DocumentException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author l
 * @Date 2021/11/09
 */
@Controller
@CrossOrigin
public class DemoController {

    @PostMapping(value = "getjson", produces = MediaType.APPLICATION_PDF_VALUE)
//    @ResponseBody
    public void getJson(@RequestBody DemoVo mapList, HttpServletResponse resp) throws IOException, DocumentException {
        System.out.println(mapList);
        OutputStream os = resp.getOutputStream();
//        resp.setHeader("Content-Type","application/pdf");

        pdfUtil.toPdf(os,mapList);
//        resp.getWriter().write();
    }
}
