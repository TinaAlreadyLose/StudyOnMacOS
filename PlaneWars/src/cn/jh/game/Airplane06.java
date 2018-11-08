package cn.jh.game;

import java.awt.image.BufferedImage;

public class Airplane06 extends FlyingObject06 implements Enemy06{
    private static BufferedImage[] images;
    static {
        images=new BufferedImage[5];
        for (int i = 0; i < images.length; i++) {
            images[i] = LoadImage("image/airplane" + i + ".png");
        }
    }
    private  int step;
    private  int deadIndex=1;
    public  Airplane06(){
        width =49;
        height=36;
        x = (int) (Math.random() * (World06.WIDTH - this.width));
        y = -this.height;
        step=2;
    }
    @Override
    public void step() {
    y+=step;
    }

    @Override
    public boolean outOfBounds() {
        return this.y>=World06.HEIGHT;
    }

    @Override
    public BufferedImage getImage() {
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

    @Override

    public int getScore() {
      return  1;
    }

    /**
     * add
     * @param graphics
     */
}


