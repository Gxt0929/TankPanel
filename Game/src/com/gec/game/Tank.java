package com.gec.game;

import com.gec.game.bulid.FeWall;
import com.gec.game.bulid.Wall;

import java.awt.*;
import java.util.List;

public class Tank extends AbstractGameObject{
    //向上移动时的图片
    private final String upPic;
    //向下移动时的图片
    private final String downPic;
    //向右移动时的图片
    private final String rightPic;
    //向左移动时的图片
    private final String leftPic;
    //坦克占用大小
    int width=50;
    int height=50;
    //坦克初始方向
    DirectionEnum direction= DirectionEnum.UP;
    //坦克速度
    private final int speed=1;

    //是否可以发射子弹
    boolean attackCooling=true;

    //有参构造 玩家和
    public Tank(String imgUrl, int x, int y, TankPanel tankPanel,
                String upPic, String downPic, String rightPic, String leftPic) {
        super(imgUrl, x, y, tankPanel);
        this.upPic = upPic;
        this.downPic = downPic;
        this.rightPic = rightPic;
        this.leftPic = leftPic;

    }
    //返回当前坦克 坐标 大小矩形对象
    @Override
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }

    //绘制图像
    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    /**
     * 坦克移动
     * 1.改变坦克坐标
     * 2.记录坦克方向
     * 3.更新坦克不同方向图片
     */
    //坦克移动的下一个坐标与墙体碰撞检测
    public boolean tankHitWall(int x,int y){
        //获取所有墙体 集合
        List<Wall> wallList = this.tankPanel.wallList;
        //获取所有铁墙集合
        List<FeWall> feWalls =this.tankPanel.feWallList;
        //获取坦克移动的下一个坐标
        Rectangle next = new Rectangle(x, y, this.width, this.height);
        //循环墙体
        for (Wall wall : wallList) {
            if(wall.getRec().intersects(next)){
                return false;// 相交
            }
        }
        //进行铁墙碰撞检测
        for (FeWall feWall : feWalls) {
            if(feWall.getRec().intersects(next)){
                return false;// 相交
            }
        }
        return true;//不想交
    }

    //坦克与边界碰撞
    public boolean tankHitBorder(int x,int y) {
        if(x<0) {
            //地图左边界检测
            return false;
        }else if(x>this.tankPanel.getWidth()-width) {
            //地图右边界检测
            return false;
        }else //地图下边界检测
            if(y<0) {
            //地图上边界检测
            return false;
        }else return y <= this.tankPanel.getHeight() - height;
    }
    public void leftWard() {
        if(tankHitWall(x - speed, y) && tankHitBorder(x - speed, y)){
            x -= speed;
            direction= DirectionEnum.LEFT;
            setImg(leftPic);
        }
    }

    public void rightWard() {
        if(tankHitWall(x + speed, y) && tankHitBorder(x + speed, y)) {
            x += speed;
            direction = DirectionEnum.RIGHT;
            setImg(rightPic);
        }
    }
    public void upWard() {
        if(tankHitWall(x, y - speed) && tankHitBorder(x, y - speed)) {
            y -= speed;
            direction = DirectionEnum.UP;
            setImg(upPic);
        }
    }
    public void downWard() {
        if(tankHitWall(x, y + speed) && tankHitBorder(x, y + speed)) {
            y += speed;
            direction = DirectionEnum.DOWN;
            setImg(downPic);
        }
    }

    public void setImg(String img) {
        this.image=Toolkit.getDefaultToolkit().getImage(img);
    }
    //根据方向确定头部位置,x和y是左上角的点
    public Point getHeadPoint(){
        switch (direction){
            case UP:
                return new Point(x+width/2,y);
            case LEFT:
                return new Point(x,y+height/2);
            case DOWN:
                return new Point(x+width/2,y+height);
            case RIGHT:
                return new Point(x+width,y+height/2);
            default:
                return null;
        }
    }
    //射击方法
    public void attack() {
        Point p=getHeadPoint();
        Bullet bullet=new Bullet("image/bullet/bulletGreen.gif",p.x-10,p.y-10, direction, this.tankPanel);
        //启动线程 设置1秒攻击间隔
//        new Thread(new AttackCD()).start();
        if(attackCooling){
            this.tankPanel.bulletList.add(bullet);//将子弹添加至子弹集合
        }
    }
//    //攻击间隔线程
//    class AttackCD implements Runnable {
//        @Override
//        public void run() {
//            //设置坦克不可射击
//            attackCooling = false;
//            try {//线程休眠1秒
//                //子弹发射间隔500ms
//                int attackIntervalTime = 500;
//                Thread.sleep(attackIntervalTime);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //休眠结束 设置坦克可以射击
//            attackCooling = true;
//        }
//    }

}
