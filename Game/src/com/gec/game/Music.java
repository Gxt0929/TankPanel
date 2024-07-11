package com.gec.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 游戏音乐、音效
 */
public class Music{
    private static Clip start; //开始游戏北京音乐
    private static Clip move;  //坦克移动音效
    private static Clip attack;//发射子弹音效
    private static Clip boom;  //爆炸音效
    private static Clip wall;  //击中墙体音效
    private static Clip ji;
    private static Clip ngmhhy;
    private static Clip gg;
    private static Clip zt;
    private static Clip nb;

    static {
        //获取文件对象 使用绝对路径
        File bgMusicStartFile = new File("music/bgm.wav");
        File bgMusicAttackFile = new File("music/attack.wav");
        File bgMusicMoveFile = new File("music/move.wav");
        File bgMusicExplodeFile = new File("music/boom.wav");
        File bgMusicWallFile = new File("music/wall.wav");
        File bgMusicJiFile = new File("music/ji.wav");
        File bgMusicngmhhyFile = new File("music/ngmhhy.wav");
        File bgMusicnggFile = new File("music/siwang.wav");
        File bgMusicnztFile = new File("music/zt.wav");
        File bgMusicnnbFile = new File("music/nb.wav");
        try {
            //获取音乐文件输入流
            AudioInputStream audioInputStreamStart = AudioSystem.getAudioInputStream(bgMusicStartFile);
            start = AudioSystem.getClip();
            start.open(audioInputStreamStart);
            AudioInputStream audioInputStreamAttack = AudioSystem.getAudioInputStream(bgMusicAttackFile);
            attack = AudioSystem.getClip();
            attack.open(audioInputStreamAttack);
            AudioInputStream audioInputStreamStartMove = AudioSystem.getAudioInputStream(bgMusicMoveFile);
            move = AudioSystem.getClip();
            move.open(audioInputStreamStartMove);
            AudioInputStream audioInputStreamStartExplode = AudioSystem.getAudioInputStream(bgMusicExplodeFile);
            boom = AudioSystem.getClip();
            boom.open(audioInputStreamStartExplode);
            AudioInputStream audioInputStreamStartWall = AudioSystem.getAudioInputStream(bgMusicWallFile);
            wall = AudioSystem.getClip();
            wall.open(audioInputStreamStartWall);
            AudioInputStream audioInputStreamStartJi = AudioSystem.getAudioInputStream(bgMusicJiFile);
            ji = AudioSystem.getClip();
            ji.open(audioInputStreamStartJi);
            AudioInputStream audioInputStreamStartngmhhy = AudioSystem.getAudioInputStream(bgMusicngmhhyFile);
            ngmhhy = AudioSystem.getClip();
            ngmhhy.open(audioInputStreamStartngmhhy);
            AudioInputStream audioInputStreamStartgg = AudioSystem.getAudioInputStream(bgMusicnggFile);
            gg = AudioSystem.getClip();
            gg.open(audioInputStreamStartgg);
            AudioInputStream audioInputStreamStartzt = AudioSystem.getAudioInputStream(bgMusicnztFile);
            zt = AudioSystem.getClip();
            zt.open(audioInputStreamStartzt);
            AudioInputStream audioInputStreamStartnb = AudioSystem.getAudioInputStream(bgMusicnnbFile);
            nb = AudioSystem.getClip();
            nb.open(audioInputStreamStartnb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //播放背景音乐
    public static void startPlay(){
        start.start();
        start.setFramePosition(0);
    }
    //播放攻击音效
    public static void attackPlay(){
        attack.start();
        //将进度条调为0
        attack.setFramePosition(0);
    }
    //播放坦克移动音效
    public static void movePlay(){
        move.start();
        move.setFramePosition(0);
    }
    //关闭坦克移动音效
    public static void moveStop(){
        move.stop();
    }
    public static void bgmStop(){
        start.stop();
    }

    //播放爆炸音效
    public static void boomPlay(){
        boom.start();
        boom.setFramePosition(0);
    }

    public static void jiPlay(){
        ji.start();
        ji.setFramePosition(0);
    }
    public static void ngmhhyPlay(){
        ngmhhy.start();
        ngmhhy.setFramePosition(0);
    }
    public static void ggPlay(){
        gg.start();
        gg.setFramePosition(0);
    }
    public static void ztPlay(){
        zt.start();
        zt.setFramePosition(0);
    }
    public static void nbPlay(){
        nb.start();
        nb.setFramePosition(0);
    }

    public static void main(String[] args) {
        Music.jiPlay();//测试
    }
}
