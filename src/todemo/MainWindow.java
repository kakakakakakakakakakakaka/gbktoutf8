package todemo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Juch
 * @date 2019/4/10 10:35
 */

public class MainWindow extends JFrame implements ActionListener {

    public static void main(String[] args) {
        MainWindow window = new MainWindow("GBK to UTF-8");
        //设置整个窗口居中显示
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((screenSize.width - 450) / 2, (screenSize.height - 300) / 2, 450, 300);
    }

    private JButton jButton1, jButton2;
    private JTextField textField = new JTextField("未选择路径", 50);
    static JTextArea fileArea = new JTextArea("日志记录：", 5, 20);


    private MainWindow(String s) {
        Box baseBox, boxV1, boxV2;

        setTitle(s);

        boxV1 = Box.createVerticalBox();
        boxV1.add(Box.createVerticalStrut(8));

        jButton1 = new JButton("浏览");
        jButton2 = new JButton("转码");
        jButton2.setBounds(400, 500, 300, 200);
        boxV2 = Box.createVerticalBox();
        boxV2.add(textField);

        baseBox = Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(boxV2);
        baseBox.add(jButton1);
        jButton1.addActionListener(this);

        add(jButton2);
        jButton2.addActionListener(this);
        add(baseBox, BorderLayout.NORTH);
        add(fileArea, BorderLayout.CENTER);
        add(jButton2, BorderLayout.SOUTH);
        validate();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


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
            //点击转换按钮的响应处理，转换gbk的内容写粘贴在下面的空白区
            try {
                new GbkToUtf8(new File(textField.getText()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
