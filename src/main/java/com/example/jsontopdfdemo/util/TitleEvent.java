package com.example.jsontopdfdemo.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author l
 * @Date 2021/11/08
 */
public class TitleEvent extends PdfPageEventHelper {


    @SneakyThrows
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        BaseFont baseFontChinese = BaseFont.createFont("src/main/resources/font/msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font headFont = new Font(baseFontChinese, 16, Font.BOLD);
        Font tableTitleFont = new Font(baseFontChinese, 12, Font.BOLD);
        Font contentFontSecond = new Font(baseFontChinese, 12);

        Paragraph head1 = new Paragraph("XX市XX饲料有限公司对账单", headFont);
        head1.setAlignment(1);
        head1.setSpacingAfter(30);
        head1.setLeading(0);
        document.add(head1);

        PdfPTable baseInfoTable = new PdfPTable(6);
        int[] width = {10, 14, 10, 8, 8, 10};
        baseInfoTable.setWidths(width);
        baseInfoTable.setWidthPercentage(100);
        //设置表格中单元格的属性,所有单元格都可以使用
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setHorizontalAlignment(PdfPCell.LEFT);
        pdfPCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        pdfPCell.setBorder(0);

        pdfPCell.setPhrase(new Phrase("编码/客户：:", contentFontSecond));
        baseInfoTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("10020620011", contentFontSecond));
        baseInfoTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("聂明娟", contentFontSecond));
        baseInfoTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase(""));
        baseInfoTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("业务员：", contentFontSecond));
        baseInfoTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("何森", contentFontSecond));
        baseInfoTable.addCell(pdfPCell);
        baseInfoTable.completeRow();
        baseInfoTable.setSpacingAfter(10);
        document.add(baseInfoTable);

        PdfPTable baseInfoTable3 = new PdfPTable(1);
        int[] width3 = {1};
        baseInfoTable3.setWidths(width3);
        baseInfoTable3.setWidthPercentage(100);
        pdfPCell.setPhrase(new Phrase("单位：元", contentFontSecond));
        baseInfoTable3.addCell(pdfPCell);

        PdfPTable baseInfoTable2 = new PdfPTable(6);
        int[] width2 = {10,20,10,20,10,20};
        baseInfoTable2.setWidths(width2);
        baseInfoTable2.setWidthPercentage(100);
        pdfPCell.setPhrase(new Phrase("当年（饲料）（金额：元 数量：吨）", contentFontSecond));
        baseInfoTable2.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("当年（饲料）（金额：元 数量：吨）", contentFontSecond));
        baseInfoTable2.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("当年（饲料）（金额：元 数量：吨）", contentFontSecond));
        baseInfoTable2.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("当年（饲料）（金额：元 数量：吨）", contentFontSecond));
        baseInfoTable2.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("当年（饲料）（金额：元 数量：吨）", contentFontSecond));
        baseInfoTable2.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("当年（饲料）（金额：元 数量：吨）", contentFontSecond));
        baseInfoTable2.addCell(pdfPCell);
        baseInfoTable2.completeRow();
        baseInfoTable2.setSpacingAfter(10);
        document.add(baseInfoTable2);

        PdfPTable presListTable = new PdfPTable(10);
        int[] presWidth = {8, 10, 20, 8, 10, 10, 8, 8, 8, 8};
        presListTable.setWidths(presWidth);
        presListTable.setWidthPercentage(100);
        pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        pdfPCell.setPaddingTop(6);
        pdfPCell.setPaddingBottom(8);
        pdfPCell.setBorder(Rectangle.BOX);

        pdfPCell.setPhrase(new Phrase("日期", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("单据号", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("摘要", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("数量", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("应收金额", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("收款金额", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("预收款", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("折扣额", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("其他折扣额", tableTitleFont));
        presListTable.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("债务重组", tableTitleFont));
        presListTable.addCell(pdfPCell);
        presListTable.completeRow();
        document.add(presListTable);

        super.onStartPage(writer, document);
    }





}
