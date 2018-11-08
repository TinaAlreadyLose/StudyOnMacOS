package cn.jh.game;

import java.awt.image.BufferedImage;
/**
 * bullet16.java(子弹类)
 * @author LFQ16
 *
 */
public class Bullet06 extends FlyingObject06 {
    private static BufferedImage image;
    static{
        image = LoadImage("image/bullet.png");
    }
    private int step;
    public Bullet06(int x,int y){
        width = 8;
        height = 14;
        this.x = x;
        this.y = y;
        step = 3;
    }
    @Override
    public void step() {

        y -= step;
    }

    @Override
    public boolean outOfBounds() {

        return this.y<=this.height;
    }

    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return image;
        }
        if(isDead()){
            state = REMOVE;
        }
        return null;
    }

}
