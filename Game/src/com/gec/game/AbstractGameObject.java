package com.gec.game;

import java.awt.*;

/**
 * @author cr
 * @description  每个组件都需要 生成画面 将共有的参数 方法提炼出来
 */
public abstract class AbstractGameObject {
    //面板图片对象
    public Image image;
    //生成组件坐标
    public int x;
    public int y;

    //生成面板
    TankPanel tankPanel;

    //有参构造方法
    public AbstractGameObject(String imgUrl, int x, int y, TankPanel tankPanel) {
        this.image = Toolkit.getDefaultToolkit().getImage(imgUrl);
        this.x = x;
        this.y = y;
        this.tankPanel = tankPanel;
    }

    //提供抽象方法 绘制组件到面板
    public abstract void paintSelf(Graphics g);

    //获取该组件 矩形大小(长宽) 坐标(x、y轴)对象
    public abstract Rectangle getRec();
//    坦克方向枚举
    public enum DirectionEnum {
        UP,LEFT,RIGHT,DOWN
    }

}