package com.gec.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * 游戏音乐、音效
 */
public class Music{
    private static Clip start; //开始游戏北京音乐
    private static Clip move;  //坦克移动音效
    private static Clip attack;//发射子弹音效
    private static Clip boom;  //爆炸音效
    private static Clip wall;  //击中墙体音效
    static {
        //获取文件对象 使用绝对路径
        File bgMusicStartFile = new File("music/bgm.wav");
        File bgMusicAttackFile = new File("music/attack.wav");
        File bgMusicMoveFile = new File("music/move.wav");
        File bgMusicExplodeFile = new File("music/boom.wav");
        File bgMusicWallFile = new File("music/wall.wav");
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

    //播放爆炸音效
    public static void boomPlay(){
        boom.start();
        boom.setFramePosition(0);
    }

    public static void main(String[] args) {
        Music.movePlay();//测试
    }
}
