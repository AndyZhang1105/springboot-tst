package com.zz.tst.designmode;

import com.zz.util.FileUtil;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Horse implements Animal {

    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");

    @SneakyThrows
    public Horse() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：马"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon(FileUtil.getResourcePath("static/A_Horse.jpg")));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //用户点击窗口关闭
    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}
