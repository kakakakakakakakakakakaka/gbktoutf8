package uidmeo;

import todemo.GbkToUtf8;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Juch
 * @date 2019/4/10 7:06
 */
public class PathChoice {
    public static void pathChoice() throws IOException {
        JFileChooser fileChooser = new JFileChooser("C:\\");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            new GbkToUtf8(new File(fileChooser.getSelectedFile().getAbsolutePath()));
        }
    }
}
