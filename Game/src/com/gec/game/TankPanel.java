package com.gec.game;

import com.gec.game.bulid.Base;
import com.gec.game.bulid.FeWall;
import com.gec.game.bulid.Grass;
import com.gec.game.bulid.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankPanel extends JPanel implements KeyListener {

//    设置窗口大小
    final static int x = 1200;
//    设置游戏状态
    int state = 0;
//    临时变量
    private int a = 1;
//     0 未开始，1 开始，2 暂停，3 失败，4 胜利 6双人模式
    Image choose = Toolkit.getDefaultToolkit().getImage("image/player1/p1tankR.gif");
//    定义初始y指针
    public int y1 = 475;
//    创建玩家1对象 填入默认图片、生成坐标、四方向图片
    GamerOne gamerOne = new GamerOne("image/player1/p1tankU.gif", 500, 700,
            this,"image/player1/p1tankU.gif",
            "image/player1/p1tankD.gif",
            "image/player1/p1tankR.gif",
            "image/player1/p1tankL.gif");
//    子弹集合
    public List<Bullet> bulletList=new ArrayList<>();
//    子弹 待删除集合
    public List<Bullet> bulletsRemoveList = new ArrayList<>();
//    敌方坦克集合
    public List<EnemyBot> enemyBotList = new ArrayList<>();
//    玩家坦克待删除集合
    public List<Tank> gamerList = new ArrayList<>();
//    关卡围墙集合
    public List<Wall> wallList = new ArrayList<>();
//    爆炸集合
    public List<Boom> boomlist = new ArrayList<>();
    public List<Base> baseList = new ArrayList<>();
    public List<Grass> grassList = new ArrayList<>();
    public List<FeWall> feWallList = new ArrayList<>();
    //控制是否重绘
    private boolean run = true;

//    子弹方向

    //     重绘次数
    public int repaintCount=0;
//     敌方坦克人机数量
    private int enemyRobotCount=0;
    private final int wallCount = 100;
    private final int[] xrr;
    private final int[] yrr;
    public TankPanel() {
        this.xrr = new int[this.wallCount];
        this.yrr = new int[this.wallCount];
    }

    @Override
    public void paint(Graphics g){
        //绘制实心矩形 填充整个画布
        g.fillRect(0, 0, 1200, 800);
        switch (state) {
            case 0 -> {
                g.drawImage(Toolkit.getDefaultToolkit().getImage("image/interface.png"), 0, 0, this);
                g.drawImage(choose,300,y1,this);
            }
            case 1 -> {
                g.setFont(new Font("宋体",Font.BOLD,66));
                g.setColor(Color.PINK);
//                g.drawString("单人模式",400,400);
                //绘制我方坦克
                for (Tank tank : gamerList) {
                    tank.paintSelf(g);
                }
                //绘制子弹
                for(Bullet bullet:bulletList) {
                    bullet.paintSelf(g);
                }
                //绘制敌方坦克
                for (EnemyBot enemyBot : enemyBotList) {
                    enemyBot.paintSelf(g);
                }
                for (Wall wall : wallList) {
                    wall.paintSelf(g);
                }
                for (FeWall feWall :feWallList) {
                    feWall.paintSelf(g);
                }
                for (Base base : baseList) {
                    base.paintSelf(g);
                }
                for (Grass grass : grassList) {
                    grass.paintSelf(g);
                }
                for (Boom boom : boomlist) {
                    boom.paintSelf(g);
                }
                //删除已经击中敌方坦克的我方子弹
                bulletList.removeAll(bulletsRemoveList);
                //移除后清空
                bulletsRemoveList.clear();
            }

            case 2 -> {
                g.setFont(new Font("宋体",Font.BOLD,66));
                g.setColor(Color.PINK);
                g.drawString("双人模式",400,400);
            }
            case 3 -> {
                g.fillRect(0, 0, 1200, 800);
                g.drawImage(Toolkit.getDefaultToolkit().getImage("image/fail.png"), 300,200,this);
            }
            case 4 -> g.drawImage(Toolkit.getDefaultToolkit().getImage("image/success.jpg"),0,0,this);
            case 5 -> {
                //设置 不能重绘
                run = false;
                //最后一次绘制关卡内容
                for (Tank tank : gamerList) {
                    tank.paintSelf(g);
                }
                for (Bullet bullet : bulletList) {
                    bullet.paintSelf(g);
                }
                //重绘敌方坦克
                for (EnemyBot enemyBot : enemyBotList) {
                    enemyBot.paintSelf(g);
                }
                //删除坦克子弹
                bulletList.removeAll(bulletsRemoveList);
                //移除后清空
                bulletsRemoveList.clear();
                for (Wall wall : wallList) {
                    wall.paintSelf(g);
                }
                //绘制基地
                for (Base base1 : baseList) {
                    base1.paintSelf(g);
                }
                //绘制铁墙
                for (FeWall feWall : feWallList) {
                    feWall.paintSelf(g);
                }
                //绘制草地
                for (Grass grass : grassList) {
                    grass.paintSelf(g);
                }
                //绘制爆炸效果
                for (Boom boom : boomlist) {
                    boom.paintSelf(g);
                }
                //绘制 游戏暂停在屏幕中间
                g.setColor(Color.WHITE);//设置画笔颜色
                g.setFont(new Font("黑体", Font.BOLD, 100));//设置字体
                g.drawString("游戏暂停", 450, 500);
            }
            default -> System.out.println("状态码无效！");
        }
        if(run){
            repaint();
        }

    }

    //生成建筑: 围墙、铁墙、草地、基地
    public void addBuild() {
        random();
        //生成长条围墙
        for (int i = 0; i < 21; i++) {
            wallList.add(new Wall("image/walls.gif", i * 60, 120, this));
        }
        //生成基地包围围墙
        wallList.add(new Wall("image/walls.gif", 550, 720, this));
        wallList.add(new Wall("image/walls.gif", 550, 660, this));
        wallList.add(new Wall("image/walls.gif", 610, 660, this));
        wallList.add(new Wall("image/walls.gif", 670, 660, this));
        wallList.add(new Wall("image/walls.gif", 670, 720, this));
        baseList.add(new Base("image/base.gif", 610, 720, this));
        for(int i=0;i<wallCount;i++) {
            Random a=new Random();
            int num=a.nextInt(4);//随机生成0~3
            if(num<2) {//随机数为0,1
                Wall wall=new Wall("image/walls.gif",xrr[i], yrr[i], null);
                wallList.add(wall);
            }else if(num<3){//随机数为2
                Grass g=new Grass("image/cao.gif",xrr[i], yrr[i], null);
                grassList.add(g);
            }else {//随机数为3
                FeWall f=new FeWall("image/fe.gif",xrr[i], yrr[i], null);
                feWallList.add(f);
            }
        }
    }

    private void initEnemyBots() { // 添加初始敌方坦克
        for (int i = 0; i < 2; i++) {
            addEnemyBot();
        }
    }

    private void addEnemyBot() {
        Random r = new Random();
        int x = r.nextInt(TankPanel.x - 50);
        int y = r.nextInt(10); // 限制在一定范围内生成
        EnemyBot enemyBot = new EnemyBot("image/enemy/enemy1D.gif", x, y, this,
                "image/enemy/enemy1U.gif", "image/enemy/enemy1D.gif",
                "image/enemy/enemy1R.gif", "image/enemy/enemy1L.gif");
        //默认方向向下
        enemyBot.direction = AbstractGameObject.DirectionEnum.DOWN;
        enemyBotList.add(enemyBot);
        enemyRobotCount++;//生成敌方坦克+1

    }


    //键盘按下和抬起事件
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //添加键盘监听
    @Override
    public void keyPressed(KeyEvent e) {
        gamerOne.keyPressed(e);
        int key = e.getKeyChar();
        if(key == 87 || key == 119) {
            a = 1;
            y1 = 475;
        } else if (key == 83 || key == 115) {
            a = 2;
            y1 = 570;
        } else if (key == KeyEvent.VK_ENTER){
            state = a;
            if(state == 1)
            {
                //添加一辆己方坦克
                gamerList.add(gamerOne);
            }
            Music.startPlay();
        } else if(key == 'P' || key == 'p') {
            if(state!=5) { //未暂停状态
                a=state;  //将原状态赋值给 临时变量a
                state=5;  //设置为暂停
            }else {
                state=a; //设置原状态
                run=true; //重绘 继续游戏
                if(a==0){ //state 之前值为0
                    a=1;//该临时变量a值为默认1
                }
        }

        }
        System.out.println(e.getKeyChar());
        switch (key) {
            case KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S:
            default:
                break;
        }
    }
    ////键盘抬起事件
    @Override
    public void keyReleased(KeyEvent e) {
        gamerOne.keyReleased(e);
    }

    //更新、渲染场景
    public void run (){
        // 初始化敌方坦克
        initEnemyBots();
        while (true) {
            //每绘制100次并且 敌方坦克小于10
            if (repaintCount % 100 == 1 && enemyRobotCount < 10) {
                addEnemyBot();
            }
            if(!boomlist.isEmpty()) {//爆炸集合不为空，则删除集合中第一个元素
                boomlist.remove(0);
            }
            if (run) { //未暂停 执行重绘  <-----------------------
                repaint();
                repaintCount++;//重绘次数+1
            }
            try {
                Thread.sleep(15);//刷新休眠时间
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }
            if(enemyBotList.isEmpty() &&enemyRobotCount==10){
                //敌方坦克消灭并生成过10个坦克
                state=4;//状态为胜利

            }
        }
    }
    //随机生成坐标方法
    public void random() {
        Random r = new Random();
        for (int i = 0; i < wallCount; ) {
            int x = r.nextInt(21);//生成0~20的随机整数
            int y = r.nextInt(8) + 3;//生成3~10的随机整数
            if (i > 0) { //判断是否为第一次生成
                boolean repeat = false;
                //先遍历判断是否重复
                for (int j = 0; j < i; j++) {
                    if (xrr[j] == x * 60 && yrr[j] == y * 60) {
                        repeat = true;
                        break;
                    }
                }
                //不重复记录坐标
                if (!repeat) {
                    xrr[i] = x * 60;
                    yrr[i] = y * 60;
                    i++;
                }  //重复则跳过当前循环 再次生成随机

            } else {
                //第一次生成坐标
                xrr[i] = x * 60;
                yrr[i] = y * 60;
                i++;
            }
        }
    }

}



