import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class WordToText {

    public static void main(String[] args) {
        try {
            // 获取输入文件路径。
            String inputFilePath = "images/zyt.doc";

            // 获取输出文件路径。
            String outputFilePath = "output.txt";

            // 创建一个 InputStream 对象用于读取输入文件。
            InputStream inputStream = Files.newInputStream(new File(inputFilePath).toPath());

            // 创建一个 HWPFDocument 对象用于解析输入文件。
            HWPFDocument document = new HWPFDocument(inputStream);

            // 创建一个 WordExtractor 对象用于提取文档中的文本内容。
            WordExtractor extractor = new WordExtractor(document);

            // 获取文档中的文本内容。
            String text = extractor.getText();

            // 创建一个 OutputStream 对象用于写入输出文件。
            OutputStream outputStream = Files.newOutputStream(new File(outputFilePath).toPath());

            // 将文本内容写入输出文件。
            outputStream.write(text.getBytes());

            // 关闭输入和输出流。
            inputStream.close();
            outputStream.close();
            System.out.println("成功");
        } catch (IOException e) {
            System.out.println("失败");
            throw new RuntimeException(e);
        }


    }
}
