package com.gec.game;

import java.awt.*;
import java.util.Random;

public class EnemyBot extends Tank {


    public EnemyBot(String imgUrl, int x, int y, TankPanel tankPanel, String upPic, String downPic, String rightPic, String leftPic) {

        super(imgUrl, x, y, tankPanel, upPic, downPic, rightPic, leftPic);
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    //人机移动随机方向
    public DirectionEnum randomEnemyTankDirection() {
        Random r = new Random();
        int rnum = r.nextInt(4);//生成0 1 2 3 随机数
        switch (rnum) {
            case 0:
                return DirectionEnum.UP;
            case 1:
                return DirectionEnum.RIGHT;
            case 2:
                return DirectionEnum.LEFT;
            default:
                return DirectionEnum.DOWN;
        }
    }


    //人机移动次数
    int moveTime = 0;
    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        move();
        attack();//敌方坦克攻击
    }

    //人机移动
    public void move() {

        if (moveTime >= 100) {
            direction = randomEnemyTankDirection();
            moveTime = 0;
        } else {
            ++moveTime;
        }
        switch (direction) {
            case UP:
                upWard();
                break;
            case DOWN:
                downWard();
                break;
            case RIGHT:
                rightWard();
                break;
            case LEFT:
                leftWard();
                break;
        }
    }

    //射击方法
    @Override
    public void attack() {
        Point p = getHeadPoint();
        EnemyBullet enemyBullet = new EnemyBullet("image/bullet/bulletYellow.gif", p.x - 10, p.y - 10, direction, this.tankPanel);
        Random random = new Random();
        int i = random.nextInt(400);
        if (i < 1) {
            this.tankPanel.bulletList.add(enemyBullet);//将子弹添加至子弹集合
        }
    }
}

