package todemo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static todemo.FileList.fileList;
import static todemo.GetEncodingByPath.getEncodingByPath;
import static todemo.MainWindow.fileArea;
import static todemo.ToUTF8.toUTF8;

/**
 * @author Juch
 * @date 2019/4/10 7:04
 */
class GbkToUtf8 {

    static ArrayList<String> fileList = new ArrayList<String>();
    private static StringBuilder sb = new StringBuilder();

    private GbkToUtf8(String fileName) throws IOException {
        String keyPre = "GB2312";
        if (keyPre.equals(getEncodingByPath(fileName))) {
            sb.append(fileName).append("\n");
            String outFile = toUTF8(fileName);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            out.write(outFile);
            out.close();
        }
    }

    GbkToUtf8(File file) throws IOException {
        fileList(file);
        if (fileList.size() == 0) {
            new GbkToUtf8(file.toString());
        } else {
            for (String fileName : fileList) {
                new GbkToUtf8(fileName);
            }
        }
        if (sb.lastIndexOf("件") == sb.length() - 1) {
            sb.append("没有要转换的文件").append("\n");
        } else {
            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
        }
        fileArea.setText(sb.toString());
    }
}
