package com.example.jsontopdfdemo.util;

import com.example.jsontopdfdemo.vo.DemoO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author l
 * @Date 2021/11/10
 */
public class POIUtil {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/template.xls");
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
//        workbook.write(os);
        os.close();


//        FileOutputStream out = new FileOutputStream("src/main/resources/template.docx");

        DemoO temp = new DemoO();
        String x = "2019-09-09";
        temp.setDjh(x);
        temp.setSj(x);
        List<DemoO> demoOS = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoOS.add(temp);
        }

        FileInputStream in = new FileInputStream("src/main/resources/template.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(in);
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        paragraphs.forEach(xwpfParagraph -> {
            List<XWPFRun> runs = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : runs) {
            }
        });

        List<XWPFTable> xwpfTables = xwpfDocument.getTables();
        for (XWPFTable xwpfTable : xwpfTables) {
            List<String> secondRowName = new ArrayList<>();
            XWPFTableRow secondRow = xwpfTable.getRow(1);
            XWPFTableRow firstRow = xwpfTable.getRow(0);

            List<XWPFTableCell> xwpfTableCells = secondRow.getTableCells();
            if (null == xwpfTableCells) {
                continue;
            }
            for (XWPFTableCell xwpfTableCell : xwpfTableCells) {
                List<XWPFParagraph> xwpfParagraphs = xwpfTableCell.getParagraphs();
                for (XWPFParagraph xwpfParagraph : xwpfParagraphs) {
                    List<XWPFRun> runs = xwpfParagraph.getRuns();
                    StringBuilder stringBuilder = new StringBuilder();
                    //ignore spaces in runs,and append those content to the stringBuilder
                    runs.stream().forEach(xwpfRun -> {
                        stringBuilder.append(xwpfRun.toString());
                    });
                    String rs = stringBuilder.toString();
                    if (isContainsDynamicParameter(rs)) {
                        List<String> rsList = getKeyListByContent(rs);
                        secondRowName.addAll(rsList);
                    }
                }
            }

            int dsSize = demoOS.size();
            for (int j = 0; j < dsSize; j++) {
                XWPFTableRow dynamicRow = xwpfTable.insertNewTableRow(2 + j);
                // copy attr of tr
                dynamicRow.getCtRow().setTrPr(secondRow.getCtRow().getTrPr());
                int srnSize = secondRowName.size();
                for (int i = 0; i < srnSize; i++) {
                    XWPFTableCell dynamicCell = dynamicRow.addNewTableCell();
                    dynamicCell.getCTTc().setTcPr(xwpfTableCells.get(i).getCTTc().getTcPr());
                    String value = String.valueOf(getAttrValue(demoOS.get(j), secondRowName.get(i)));
                    dynamicCell.setText(value);
                }
            }
            CTPPr headerCTPPr = firstRow.getTableCells().get(0).getParagraphs().get(0).getCTP().getPPr();
            XWPFTableRow yyxjCRow = xwpfTable.insertNewTableRow(xwpfTable.getRows().size());
            XWPFTableCell yyxjCell = yyxjCRow.addNewTableCell();
            yyxjCell.setText("月初小结");
            yyxjCell.getParagraphs().forEach(jp -> {
                jp.getCTP().setPPr(headerCTPPr);
            });
            XWPFTableCell yyxjValueCell = yyxjCRow.addNewTableCell();
            yyxjValueCell.getCTTc().setTcPr(xwpfTableCells.get(1).getCTTc().getTcPr());
            yyxjValueCell.setText("1000");
            XWPFTableRow nczjRow = xwpfTable.insertNewTableRow(xwpfTable.getRows().size());
            XWPFTableCell nczjCell = nczjRow.addNewTableCell();
//            nczjCell.getCTTc().setTcPr(fistCtPr);
            nczjCell.setText("年末总计");
            nczjCell.getParagraphs().forEach(yp -> {
                yp.getCTP().setPPr(headerCTPPr);
            });
            XWPFTableCell nczjValueCell = nczjRow.addNewTableCell();
            nczjValueCell.getCTTc().setTcPr(xwpfTableCells.get(1).getCTTc().getTcPr());
            nczjValueCell.setText("12000");


            xwpfTable.removeRow(1);
        }

        System.out.println("export start!");

        OutputStream docxOs = new FileOutputStream("src/main/resources/result.docx");
        xwpfDocument.write(docxOs);
//        FileInputStream tx = new FileInputStream("src/main/resources/template.docx");
//        XWPFDocument ty = new XWPFDocument(tx);
//        ty.write(docxOs);
        docxOs.close();
//        out.close();
        System.out.println("createdocument.docx written successully");
//        firstRow.stream().forEach(matchedRow -> {
//            System.out.println(matchedRow);
//        });


//        runs.stream().forEach(xwpfRun -> {
//            stringBuilder.append(xwpfRun.toString());
//        });
//        System.out.println(firstRow.toArray());

    }

    /**
     * 根据属性，使用getter获取值
     *
     * @param object
     * @param name
     * @return
     */
    private static Object getAttrValue(Object object, String name) {
        Class<?> clazz = object.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        String initials = name.substring(0, 1).toUpperCase(Locale.ROOT);
        String other = name.substring(1);
        stringBuilder.append("get").append(initials).append(other);
        Object value = null;
        try {
            Method method = clazz.getDeclaredMethod(stringBuilder.toString());
            value = ReflectionUtils.invokeMethod(method, object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static Pattern dynamicLimitCount = Pattern.compile("\\$\\{([a-z]+)\\}");

    /**
     * 判断内容中是否包含动态参数(${key}形式的)
     *
     * @param content 要判断的内容
     * @return
     */
    public static boolean isContainsDynamicParameter(String content) {
        return dynamicLimitCount.matcher(content).matches();
    }

    /**
     * 按照动态内容的参数出现顺序,将参数放到List中
     *
     * @param content
     * @return
     */
    public static List<String> getKeyListByContent(String content) {
        Set<String> paramSet = new LinkedHashSet<>();
        Matcher m = dynamicLimitCount.matcher(content);
        while (m.find()) {
            paramSet.add(m.group(1));
        }
        return new ArrayList<>(paramSet);
    }
}
