package com.example.jsontopdfdemo.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author l
 * @Date 2021/11/10
 */
public class POIUtil {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/new.xls");
        OutputStream os = new FileOutputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("name");
        row.createCell(2).setCellValue("pass");
        row.createCell(3).setCellValue("role");
        row.createCell(4).setCellValue("resource");
        row.setHeightInPoints(30);
        workbook.setActiveSheet(0);
        workbook.write(os);
        os.close();

        XWPFDocument xwpfDocument = new XWPFDocument();
        FileOutputStream out = new FileOutputStream("D:/createdocument.docx");
        xwpfDocument.write(out);
        out.close();
        System.out.println("createdocument.docx written successully");

    }
}
