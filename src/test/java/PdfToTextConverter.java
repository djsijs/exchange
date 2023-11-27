import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToTextConverter {
    public static void main(String[] args) {
        try {
            // 创建一个PDDocument对象，并使用FileInputStream将PDF文件加载到该对象中
            PDDocument document = PDDocument.load(Files.newInputStream(Paths.get("images/zyt.pdf")));

            // 创建一个PDFTextStripper对象，并设置其属性
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(document.getNumberOfPages());
            stripper.setSortByPosition(true);
            stripper.setLineSeparator("\n");

            // 使用PDFTextStripper对象将PDF文件转换为TXT文件
            String text = stripper.getText(document);

            // 将TXT文件写入文件系统
            FileOutputStream out = new FileOutputStream("output.txt");
            out.write(text.getBytes());
            out.close();

            // 关闭PDDocument对象
            document.close();

            System.out.println("PDF转换为TXT成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
