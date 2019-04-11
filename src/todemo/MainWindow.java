package todemo;

import javax.swing.*;
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
        window.setBounds((screenSize.width - 450) / 2, (screenSize.height - 350) / 2, 450, 350);
        window.setResizable(false);
    }

    private JButton jButton1, jButton2;
    private JTextField textField = new JTextField("未选择路径", 50);
    static JTextArea fileArea = new JTextArea("");

    private MainWindow(String s) {
        setTitle(s);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认退出
        JPanel contentPane = new JPanel();//初始化面板
        contentPane.setLayout(null);//设置布局NULL
        this.jButton1 = new JButton("浏览");//给按钮名字
        this.jButton1.setBounds(335, 5, 94, 30);//设置按钮规格
        jButton1.setBackground(Color.WHITE);
        this.textField.setText("未选择路径");
        this.textField.setBounds(10, 5, 320, 30);
        contentPane.add(textField);
        JScrollPane scroll = new JScrollPane(fileArea);
        scroll.setBounds(10, 45, 420, 200);
        contentPane.add(scroll);
        fileArea.setText("\t\t警告：\n本次转码将覆盖原文件，转码前请做好原文件的备份，请勿重复转码！！\n\n已转换的文件：");
        fileArea.setBounds(10, 45, 420, 200);
        fileArea.setLineWrap(true);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(jButton1);//加入面板中
        this.jButton1.addActionListener(this);
        this.jButton2 = new JButton("转码");
        jButton2.setBackground(Color.WHITE);
        this.jButton2.addActionListener(this);
        contentPane.add(jButton2);
        this.jButton2.setBounds(137, 260, 150, 40);
        this.add(contentPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            textField.setText(null);
            JFileChooser fc = new JFileChooser("C:\\");
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int val = fc.showOpenDialog(null);
            if (val == JFileChooser.APPROVE_OPTION) {
                textField.setText(fc.getSelectedFile().toString());
            } else {
                textField.setText("未选择路径");
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
