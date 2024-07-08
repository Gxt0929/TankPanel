package com.gec.game.bulid;

import com.gec.game.AbstractGameObject;
import com.gec.game.*;

import java.awt.*;

public class Base extends AbstractGameObject {
    public int width=60;
    public int height=60;

    public Base(String imgUrl, int x, int y, TankPanel tankPanel) {
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