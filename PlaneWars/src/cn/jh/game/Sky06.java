package cn.jh.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sky06 extends FlyingObject06{
    private static BufferedImage image;
    static{
        image = LoadImage("image/background.png");
    }

    private int step;
    private int y1;
    public Sky06(){
        width=World06.WIDTH;
        height=World06.HEIGHT;
        x=0;
        y=0;
        step=1;
        y1=-this.height;

    }


    @Override
    public void step() {
        // TODO Auto-generated method stub
        y+=step;
        y1+=step;
        if(y>=this.height){
            y=-this.height;
        }
        if(y1>=this.height){
            y1=-this.height;
        }
    }

    @Override
    public boolean outOfBounds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BufferedImage getImage() {
        // TODO Auto-generated method stub
        return image;
    }
    public void paint(Graphics g){
        g.drawImage(getImage(),x,y,null);
        g.drawImage(getImage(),x,y1,null);
    }


}
