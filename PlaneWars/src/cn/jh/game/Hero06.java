//debug 出来dawent
package cn.jh.game;
import  java.awt.*;
import java.awt.image.BufferedImage;

public  class Hero06 extends FlyingObject06 {
    private static BufferedImage[] images;
    static {
        images=new BufferedImage[2];
        for (int i = 1; i < images.length; i++) {
            images[i] = LoadImage("image/hero" + i + ".png");
        }
    }
    private int life;/*命*/
    private int doubleFires;/*双倍火力*/
    public Hero06(){
        super.width=97;
        super.height=124;
        super.x=140;
        super.y=400;
        this.life=3;
        this.doubleFires=0;
    }
    private  int index=0;
    int deadIndex=2;
    public void moveTo(int x, int y) {
        this.x=x-this.width/2;
        this.y = y - this.height / 2;
    }
    public Bullet06[] shoot(){
        int xStep = this.width/4;
        int yStep = 20;
        if(doubleFires>0){
            Bullet06[] bs = new Bullet06[2];
            bs[0] = new Bullet06(this.x+1*xStep,this.y-yStep);
            bs[1] = new Bullet06(this.x+3*xStep,this.y-yStep);
            doubleFires-= 2;
            return bs;
        }
        else
        {
            Bullet06[] bs = new Bullet06[1];
            bs[0] = new Bullet06(this.x+2*xStep,this.y-yStep);
            return bs;
        }

    }

    @Override
    public void step() {
    }

    @Override
    public boolean outOfBounds() {/*越界*/
        return false;
    }
    public int getLife(){/*获得当前生命值*/
        return this.life;
    }
    public  void addDoubleFire(){ /*双倍火力*/
        this.doubleFires+=40;
    }
    public void clearDoubleFire(){/*清楚双倍火力*/
        this.doubleFires=0;
    }
    public int getDoubleFires(){/*获取火力值*/
        return  this.doubleFires;
    }

    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return images[index++ % 2];
        }
        return null;
    }
    public  void addLife(){
        life++;
    }
    public  void subtractLife(){
        life--;
    }
}
