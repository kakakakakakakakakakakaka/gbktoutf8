package todemo;

import java.io.File;

import static todemo.GbkToUtf8.fileList;

/**
 * @author Juch
 * @date 2019/4/10 7:03
 */
class FileList {
    static void fileList(File file) {
        File[] fs = file.listFiles();
        for (File f : fs != null ? fs : new File[0]) {
            if (f.isDirectory()) {
                fileList(f);
                //若是目录，则递归打印该目录下的文件
            }
            if (f.isFile()) {
                fileList.add(f.toString());
                //若是文件，直接打印
            }
        }
    }

}
