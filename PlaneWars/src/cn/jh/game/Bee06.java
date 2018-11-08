package cn.jh.game;

import java.awt.image.BufferedImage;

public class Bee06 extends FlyingObject06 implements Award06 {
    private static BufferedImage[] images;
    static {
        images=new BufferedImage[5];
        for (int i = 0; i < images.length; i++) {
            images[i] = LoadImage("image/bee" + i + ".png");
        }
    }
    private  int xStep;
    private  int yStep;
    private int awardType;
    int deadIndex=1;
    public Bee06(){
        width =60;
        height=50;
        x = (int) (Math.random() * (World06.WIDTH - this.width));
        y= -this.height;
        xStep=1;
        yStep=2;
        awardType = (int) (Math.random() * 2);
    }
        @Override
    public int getType() {
        return awardType;
    }
        @Override
    public void step(){
        x+=xStep;
        y+=yStep;
            if (x >= World06.WIDTH - this.width || x <= 0) {
                xStep*=-1;
            }
    }
    @Override
    public  boolean outOfBounds(){
        return  this.y>=World06.HEIGHT;
    }
    @Override
    public  BufferedImage getImage() {
        if (isLife()) {
            return images[0];
        } else if (isDead()) {
            BufferedImage image=images[deadIndex++];
            if (deadIndex == images.length) {
                state=REMOVE;
            }
            return  image;
        }
        return  null;
    }
}
