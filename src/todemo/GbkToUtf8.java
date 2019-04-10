package todemo;

import java.io.*;
import java.util.ArrayList;

import static todemo.FileList.fileList;
import static todemo.GetEncodingByPath.getEncodingByPath;
import static todemo.ToUTF8.toUTF8;

/**
 * @author Juch
 * @date 2019/4/10 7:04
 */
public class GbkToUtf8 {

    public static ArrayList<String> fileList = new ArrayList<String>();

    public GbkToUtf8(String fileName) throws IOException {
        String keyPre ="GB2312";
        if (keyPre .equals(getEncodingByPath(fileName))) {
            System.out.println(fileName + "\t已转换");
            String outFile = toUTF8(fileName);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            out.write(outFile);
            out.close();
        }
    }

    public GbkToUtf8(File file) throws IOException {
        fileList(file);
        if (fileList.size() == 0) {
            new GbkToUtf8(file.toString());
        } else {
            for (String fileName : fileList) {
                new GbkToUtf8(fileName);
            }
        }
        System.out.println("编码转换完成切勿重复操作!!");
    }
}
