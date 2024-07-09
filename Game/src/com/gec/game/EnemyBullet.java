package com.gec.game;

import java.awt.*;
import java.util.List;

public class EnemyBullet extends Bullet{

    public EnemyBullet(String imgUrl, int x, int y, DirectionEnum direction, TankPanel tankPanel) {
        super(imgUrl, x, y, direction, tankPanel);
    }

    public void hitGamer(){
        //敌方子弹矩形类
        Rectangle bulletr=this.getRec();
        //获取玩家坦克集合
        List<Tank> gamerList = this.tankPanel.gamerList;
        //遍历集合判断玩家坦克与敌方子弹是否相交
        for (Tank tank : gamerList) {
            if(tank.getRec().intersects(bulletr)){
                for(int i=0;i<7;i++) {
                    Boom boom = new Boom("image/boom/"+(i+1)+".gif", x - 34, y - 14, this.tankPanel);
                    this.tankPanel.boomlist.add(boom);
                }
                Music.boomPlay();
                //敌方子弹击中玩家坦克 移除该坦克
                this.tankPanel.gamerList.remove(tank);
                //将该子弹添加到 待移除集合中
                this.tankPanel.bulletsRemoveList.add(this);
                //0.5秒后设置游戏状态为 失败
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tankPanel.state=3;
                }).start();
                break; //结束当前循环
            }
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(image,x,y,null);//子类自己绘制敌方子弹
        go();//子弹移动
        hitGamer();
        hitWall();//调用子弹击中围墙检测
        moveOutOfBorder();
        hitFeWall();//调用父类方法 检测子弹与铁墙碰撞
        hitBase();
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}