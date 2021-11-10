package com.example.jsontopdfdemo.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author l
 * @Date 2021/11/08
 * 用于在页面右上角打印 日期页码信息
 * 适用于横向的A4模板
 * 效果如下：
 * <p>
 * ======================================
 * 打印时间：2021-09-17 22:24:55
 * 第 1 页  共 5 页
 * ======================================
 */
public class PrintInfoEvent extends PdfPageEventHelper {

    private PdfTemplate total;
    private BaseFont baseFont;

    private BaseFont getBaseFont() throws DocumentException, IOException {
        if (null == baseFont) {
            baseFont = BaseFont.createFont("src/main/resources/font/msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
        return baseFont;
    }

    @SneakyThrows
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        PdfContentByte pdfContentByte = writer.getDirectContent();
        PdfTemplate pdfTemplate = pdfContentByte.createTemplate(400, 400);
        pdfTemplate.setFontAndSize(getBaseFont(), 10);
        pdfTemplate.beginText();
        pdfTemplate.setTextMatrix(1, 0, 0, 1, 0, 4);
        pdfTemplate.showText("第 " + document.getPageNumber() + " 页  ");
        pdfTemplate.endText();
        pdfContentByte.addTemplate(pdfTemplate, 600, 520);
        pdfContentByte.addTemplate(total, 600, 520);
        super.onStartPage(writer, document);
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(500, 500);
        super.onOpenDocument(writer, document);
    }

    @SneakyThrows
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        total.setFontAndSize(getBaseFont(), 10);
        total.beginText();
        total.setTextMatrix(1, 0, 0, 1, 0, 40);
        total.showText("打印时间：" + now);
        total.endText();
        total.beginText();
        total.setTextMatrix(1, 0, 0, 1, 60, 4);
        total.showText("  共 " + (writer.getPageNumber() - 1) + " 页");
        total.endText();
        super.onCloseDocument(writer, document);
    }

}
