import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ImageToText {

    public static void main(String[] args) {
        // 指定Tesseract OCR引擎的安装路径
        // 设置 Tesseract 数据目录
        File tessdataDir = new File("tessdata");
        // 创建一个 Tesseract 实例
        Tesseract tesseract = new Tesseract();
        // 设置语言为中文
        tesseract.setLanguage("chi_sim");
        // 设置 Tesseract 数据目录
        tesseract.setDatapath(tessdataDir.getAbsolutePath());

        try {
            // 读取图片文件
            File imageFile = new File("images/img_2.png");
            // 使用Tesseract进行图片识别
            String text = tesseract.doOCR(imageFile);
            // 将识别结果保存到txt文件
            File outputFile = new File("output.txt");
            FileUtils.writeStringToFile(outputFile, text, "UTF-8");
            System.out.println("图片转文本成功！");
        } catch (TesseractException e) {
            System.err.println("图片转文本失败：" + e.getMessage());
        } catch (IOException e) {
            System.err.println("保存文本文件失败：" + e.getMessage());
        }
    }
}
