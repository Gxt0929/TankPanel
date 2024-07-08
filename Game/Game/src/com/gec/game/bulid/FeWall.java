package com.gec.game.bulid;

import com.gec.game.AbstractGameObject;
import com.gec.game.*;

import java.awt.*;

public class FeWall extends AbstractGameObject {
    private int width=60;
    private int height=60;

    public FeWall(String imgUrl, int x, int y, TankPanel tankPanel) {
        super(imgUrl, x, y, tankPanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(image,x,y,null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}