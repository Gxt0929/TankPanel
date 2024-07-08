package com.gec.game;

import java.awt.*;

public class Boom extends AbstractGameObject{


    public Boom(String imgUrl, int x, int y, TankPanel tankPanel) {
        super(imgUrl, x, y, tankPanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(image,x,y,null);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}