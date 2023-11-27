package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        try {
            // 打开PDF文件
            PDDocument document = PDDocument.load(new File("images/二手车合同.pdf"));
            // 创建PDF渲染器
            PDFRenderer renderer = new PDFRenderer(document);

            // 创建一个新的Word文档
            XWPFDocument wordDoc = new XWPFDocument();

            // 遍历PDF的每一页
            for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); pageNumber++) {
                // 渲染PDF页面为图像
                BufferedImage image = renderer.renderImage(pageNumber);

                // 创建一个段落
                XWPFParagraph paragraph = wordDoc.createParagraph();

                // 创建一个运行
                XWPFRun run = paragraph.createRun();

                // 执行OCR
                String text = performOCR(image);

                // 设置文本内容为识别结果
                run.setText(text);

                // 添加换行符
                run.addBreak();
            }

            // 保存Word文档
            FileOutputStream out = new FileOutputStream(new File("output.docx"));
            wordDoc.write(out);
            out.close();

            // 关闭PDF文档
            document.close();

            System.out.println("OCR结果已保存到Word文档中。");
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    private static String performOCR(BufferedImage image) throws IOException {
        // 设置 Tesseract 数据目录
        File tessdataDir = new File("tessdata");
        // 创建一个 Tesseract 实例
        Tesseract tesseract = new Tesseract();
        // 设置语言为中文
        tesseract.setLanguage("chi_sim");
        // 设置 Tesseract 数据目录
        tesseract.setDatapath(tessdataDir.getAbsolutePath());

        // 将图像保存为临时文件
        File tempImageFile = File.createTempFile("ocr-image-", ".png");
        ImageIO.write(image, "png", tempImageFile);

        // 执行OCR
        String text = null;
        try {
            text = tesseract.doOCR(tempImageFile);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }

        // 删除临时图像文件
        tempImageFile.delete();

        return text;
    }
}
