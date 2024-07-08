package com.gec.game.bulid;

import com.gec.game.*;

import java.awt.*;

public class Grass extends AbstractGameObject {
    public Grass(String img,int x,int y,TankPanel tankPanel) {
        super(img,x,y,tankPanel);
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