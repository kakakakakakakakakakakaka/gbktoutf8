
package todemo;

import info.monitorenter.cpdetector.io.*;

import java.io.File;

/**
 * @author Juch
 * @date 2019/4/10 7:01
 */
class GetEncodingByPath {
    static String getEncodingByPath(String fileName) {
        String fileEncoding = null;
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
         * 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        detector.add(JChardetFacade.getInstance());
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset;
        File f = new File(fileName);
        try {
            charset = detector.detectCodepage(f.toURI().toURL());
            if (charset != null) {
                fileEncoding = charset.name();
            } else {
                fileEncoding = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileEncoding;
    }
}
