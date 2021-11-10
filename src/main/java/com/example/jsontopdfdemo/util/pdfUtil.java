package com.example.jsontopdfdemo.util;

import com.example.jsontopdfdemo.vo.DemoO;
import com.example.jsontopdfdemo.vo.DemoVo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author l
 * @Date 2021/11/08
 */
@Component
public class pdfUtil {
    public static void toPdf(OutputStream os, DemoVo demoVo) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4.rotate());
        BaseFont baseFontChinese = BaseFont.createFont("src/main/resources/font/msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
//        File file = new File("D://hahaha.pdf");
//        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
        pdfWriter.setPageEvent(new TitleEvent());
        Font contentFontSecond = new Font(baseFontChinese, 10, Font.NORMAL);

        document.open();

        PdfPTable baseInfoTable = new PdfPTable(6);
        int[] width = {6, 14, 8, 50, 8, 14};
        baseInfoTable.setWidths(width);
        baseInfoTable.setWidthPercentage(100);
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setHorizontalAlignment(PdfPCell.LEFT);
        pdfPCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        pdfPCell.setBorder(0);

        //清单
        PdfPTable presListTable = new PdfPTable(10);
        int presWidth[] = {8, 10, 20, 8, 10, 10, 8, 8, 8, 8};
        presListTable.setWidths(presWidth);
        presListTable.setWidthPercentage(100);
        pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pdfPCell.setPaddingTop(6);
        pdfPCell.setPaddingBottom(8);
        pdfPCell.setBorder(Rectangle.BOX);
        for (DemoO d : demoVo.getTotol()) {
            String goodId = d.getDjh();
            String time = d.getSj();
            String orderType = d.getDjlx();
            String remark = d.getZy();
            String num = String.valueOf(d.getSl());
            String receivable = String.valueOf(d.getYsje());
            String discount = String.valueOf(d.getZjzk());
            String collected = String.valueOf(d.getSkje());
            String other = String.valueOf(d.getZjzk());
            String restructuring = String.valueOf(d.getZwcz());
//            String arrears = String.valueOf(d.getBnqk());
            pdfPCell.setPhrase(new Phrase(goodId, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(time, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(orderType, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(remark, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(num, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(receivable, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(discount, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(collected, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(other, contentFontSecond));
            presListTable.addCell(pdfPCell);
            pdfPCell.setPhrase(new Phrase(restructuring, contentFontSecond));
            presListTable.addCell(pdfPCell);
//            pdfPCell.setPhrase(new Phrase(arrears, contentFontSecond));
//            presListTable.addCell(pdfPCell);
            presListTable.completeRow();
        }
        document.add(presListTable);
//        PdfStamper pdfStamper = new PdfStamper()
        document.close();
        pdfWriter.flush();
        pdfWriter.close();

    }
}
