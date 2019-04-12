package todemo;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Juch
 * @date 2019/4/10 10:35
 */

public class MainWindow extends JFrame implements ActionListener {

    public static void main(String[] args) {
        MainWindow window = new MainWindow("GBK to UTF-8");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((screenSize.width - 450) / 2, (screenSize.height - 350) / 2, 445, 350);
        window.setResizable(false);
    }

    private JButton jButton1 = new JButton("浏览"), jButton2 = new JButton("转码");
    private JTextField textField = new JTextField("转换路径可以设置为\"目录\"或者\"文件\"", 50);
    static JTextArea textArea = new JTextArea("本次转码将覆盖原文件，转码前请做好原文件的备份，请勿重复转码！！\n\n已转换的文件：");

    private MainWindow(String s) {
        setTitle(s);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //默认退出
        JPanel contentPane = new JPanel();
        //初始化面板
        contentPane.setLayout(null);
        //设置布局NULL
        jButton1.setBounds(335, 10, 94, 30);
        jButton2.setBounds(137, 260, 150, 40);
        textField.setBounds(10, 10, 320, 30);
        textArea.setBounds(10, 55, 420, 200);
        textField.setFont(new Font("", Font.BOLD, 12));
        textArea.setFont(new Font("", Font.PLAIN, 15));
        textField.setEditable(false);
        textArea.setEditable(false);
//        禁止用户编辑文本框
        jButton1.setBackground(Color.WHITE);
        jButton2.setBackground(Color.WHITE);
        textArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(10, 45, 420, 200);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(textField);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
        contentPane.add(scroll);
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        add(contentPane);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//            默认打开路径为桌面
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int val = fc.showOpenDialog(null);
            if (val == JFileChooser.APPROVE_OPTION) {
                textField.setText(fc.getSelectedFile().toString());
                textArea.setText("本次转码将覆盖原文件，转码前请做好原文件的备份，请勿重复转码！！\n\n已转换的文件：");
            }
        }
        if (e.getSource() == jButton2) {
            if (!Pattern.matches("^\\w", textField.getText().substring(0, 1))) {
                JOptionPane.showMessageDialog(null, "错误的路径");
            } else {
                try {
                    new GbkToUtf8(new File(textField.getText()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "转码完成，请勿重复转码！！");
            }
        }
    }
}
