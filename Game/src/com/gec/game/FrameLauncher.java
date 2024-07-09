package com.gec.game;

import javax.swing.*;

public class FrameLauncher extends JFrame{

    public void launch(){

        TankPanel panel = new TankPanel();

        add(panel);

//        标题
        setTitle("坦克大战");
//        窗口大小
        setSize(1200,800);
//        窗口居中
        setLocationRelativeTo(null);
//        关闭事件
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        设置窗口不能调整
        setResizable(false);
//        窗口可见
        setVisible(true);
//        键盘监听器
        addKeyListener(panel);
        panel.addBuild();

        new Thread(panel::run).start();


    }

    public static void main(String[] args){
        FrameLauncher frameLauncher = new FrameLauncher();
        frameLauncher.launch();
    }

}
