package com.gec.game;

import com.gec.game.bulid.Base;
import com.gec.game.bulid.FeWall;
import com.gec.game.bulid.Wall;

import java.awt.*;
import java.util.List;

public class Bullet extends AbstractGameObject{
    //速度会比坦克快一些
    private final int speed=1;
    //子弹发射方向 与坦克方向一致
    DirectionEnum direction;

    //有参构造
    public Bullet(String imgUrl, int x, int y,DirectionEnum direction, TankPanel tankPanel) {
        super(imgUrl, x, y, tankPanel);
        this.direction = direction;
    }

    //调用该方法通过方向移动子弹
    public void go() {
        switch (direction) {
            case UP:
                upWard();
                break;
            case DOWN:
                downWard();
                break;
            case LEFT:
                leftWard();
                break;
            case RIGHT:
                rightWard();
                break;
        }
    }


    //子弹运动坐标改变
    private void rightWard() {
        x+=speed;
    }
    private void downWard() {
        y+=speed;
    }
    private void leftWard() {
        x-=speed;
    }
    private void upWard() {
        y-=speed;
    }

    //我方子弹击中敌方 坦克
    public void hitEnemyBot(){
        //获取敌方坦克集合
        List<EnemyBot> enemyBotList = this.tankPanel.enemyBotList;
        //遍历集合判断敌方坦克与我方子弹是否相交
        for (EnemyBot enemyBot : enemyBotList) {
            if(this.getRec().intersects(enemyBot.getRec())){
                for(int i=0;i<7;i++) {
                    Boom boom=new Boom("image/boom/"+(i+1)+".gif", x-34, y-14, this.tankPanel);
                    this.tankPanel.boomlist.add(boom);
                }
//                Music.boomPlay();
                Music.jiPlay();
                //我方子弹击中敌方坦克 移除该坦克
                enemyBotList.remove(enemyBot);
                //将该子弹添加到 待移除集合中
                this.tankPanel.bulletsRemoveList.add(this);
                break; //结束当前循环
            }
        }
    }
    //子弹击中围墙
    public void hitWall() {
        //获取当前子弹坐标对象
        Rectangle next=this.getRec();
        //获取所有围墙对象集合
        List<Wall>walls=this.tankPanel.wallList;
        //循环围墙检测是否与当前子弹发生碰撞
        for(Wall w:walls) {
            if(w.getRec().intersects(next)) {
                //发生碰撞 将围墙移除
                this.tankPanel.wallList.remove(w);
                //将子弹放入待移除子弹集合中
                this.tankPanel.bulletsRemoveList.add(this);
                break;//子弹击中该围墙后消失 可以停止循环比较

            }
        }
    }

    //子弹移动到边界 加入待删除集合中
    public void moveOutOfBorder() {
        if(x<0||x>this.tankPanel.getWidth()) {
            this.tankPanel.bulletsRemoveList.add(this);
        }
        if(y<0||y>this.tankPanel.getHeight()) {
            this.tankPanel.bulletsRemoveList.add(this);
        }
    }

    //子弹击中铁墙
    public void hitFeWall() {
        Rectangle next=this.getRec();
        List<FeWall> fes = this.tankPanel.feWallList;
        for(FeWall f:fes) {
            if(f.getRec().intersects(next)) {
                //击中铁墙仅删除子弹
                this.tankPanel.bulletsRemoveList.add(this);
                break;
            }
        }
    }
    //子弹击中基地
    public void hitBase() {
        Rectangle next=this.getRec();
        for(Base base:tankPanel.baseList) {
            if(base.getRec().intersects(next)) {
                //绘制爆炸效果
                for(int i=0;i<7;i++) {
                Boom boom=new Boom("image/boom/"+(i+1)+".gif", x-34, y-14, this.tankPanel);
                this.tankPanel.boomlist.add(boom);

                }
                Music.boomPlay();
                Music.ggPlay();
                Music.bgmStop();
                this.tankPanel.baseList.remove(base);//删除基地
                this.tankPanel.bulletsRemoveList.add(this);//删除子弹

                //0.5秒后设置游戏状态为 失败
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tankPanel.state=3;
                }).start();
                break;
            }
        }
    }
    @Override
    public void paintSelf(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(image,x,y,null);
        go();
        hitEnemyBot();//每次重绘都执行碰撞检测
        hitWall();//调用子弹击中围墙检测
        moveOutOfBorder();
        hitFeWall();
        hitBase();
    }
    @Override
    public Rectangle getRec() {
        //长宽
        int width = 10;
        int height = 10;
        return new Rectangle(x,y, width, height);
    }

}