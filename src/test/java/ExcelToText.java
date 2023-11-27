import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelToText {

    public static void main(String[] args) throws IOException {

        // 创建一个 FileInputStream 对象来读取 Excel 电子表格
        FileInputStream inputStream = new FileInputStream("images/zyt.xls");

        // 创建一个 Workbook 对象来表示 Excel 电子表格
        Workbook workbook = WorkbookFactory.create(inputStream);

        // 获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);

        // 创建一个 FileOutputStream 对象来写入文本文件
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        // 将 Excel 电子表格中的内容写入文本文件
        for (Row row : sheet) {
            for (Cell cell : row) {
                outputStream.write(cell.getStringCellValue().getBytes());
                outputStream.write("\n".getBytes());
            }
        }

        // 关闭输入和输出流
        inputStream.close();
        outputStream.close();
    }
}
