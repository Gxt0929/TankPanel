package com.gec.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GamerOne extends Tank {

    //设置四方向默认 未按下
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;
    /*
     * 按下键盘时坦克持续运动
     */
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A -> {
                left = true;
                Music.movePlay();
            }
            case KeyEvent.VK_D -> {
                right = true;
                Music.movePlay();
            }
            case KeyEvent.VK_W -> {
                up = true;
                Music.movePlay();
            }
            case KeyEvent.VK_S -> {
                down = true;
                Music.movePlay();
            }
            case KeyEvent.VK_SPACE -> {
                Music.attackPlay();
                this.attack();
            }
            default -> {
            }
        }
    }
    /*
     * 松开键盘时，坦克停止运动
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A -> {
                left = false;
                Music.moveStop();
            }
            case KeyEvent.VK_S -> {
                down = false;
                Music.moveStop();
            }
            case KeyEvent.VK_D -> {
                right = false;
                Music.moveStop();
            }
            case KeyEvent.VK_W -> {
                up = false;
                Music.moveStop();
            }
            default -> {
            }
        }
    }

    /*
     * 坦克移动
     */
    public void move() {
        if(left) {
            leftWard();
        }else if(right) {
            rightWard();
        }else if (up) {
            upWard();
        }else if (down) {
            downWard();
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        move();//坦克移动
    }


    public GamerOne(String imgUrl, int x, int y, TankPanel tankPanel, String upPic, String downPic, String rightPic, String leftPic) {
        super(imgUrl, x, y, tankPanel, upPic, downPic, rightPic, leftPic);
    }


}