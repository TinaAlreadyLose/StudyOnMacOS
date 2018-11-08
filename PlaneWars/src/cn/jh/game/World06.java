package cn.jh.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class  World06 extends JPanel {
    public static  final  int WIDTH=400;
    public  static  final  int HEIGHT=700;
    public static BufferedImage start ;
    public  static  BufferedImage pause;
    public  static  BufferedImage gameover;

    public  static  final  int START=0;
    public  static  final  int RUNNING=1;
    public  static  final  int PAUSE=2;
    public static  final  int GAME_OVER=3;
    public  int state =START;

    private   Bullet06[] bullets ={};
    private  Sky06 sky06 =new Sky06();
    private  FlyingObject06[] enemies={};
    private  Hero06 hero06 =new Hero06();


    static {/*静态代码块*/
        start =FlyingObject06.LoadImage("image/start.png");
        pause =FlyingObject06.LoadImage("image/pause.png");
        gameover =FlyingObject06.LoadImage("image/gameover.png");
    }
    public  FlyingObject06 nextOne(){
        Random random =new Random();/*随机数的种子*/
        int type = random.nextInt(20);
        if (type < 5) {
            return new Bee06();
        } else if (type < 12) {
            return new Airplane06();
        } else {
            return new BigAirplane06();
        }
    }
    int flyEnterIndex =0;
    int shootIndex =0;
    private  int score=0;
    int num=1;

    public void enterAction() {
        flyEnterIndex++;
        if (flyEnterIndex % 40 == 0) {
            FlyingObject06 flyingObject06 =nextOne();
            enemies = Arrays.copyOf(enemies, enemies.length+1);
            enemies[enemies.length-1]=flyingObject06;
        }
    }

    public void stepAction() {
        sky06.step();
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].step();
        }
        for (int i = 0; i < bullets.length; i++) {
            bullets[i].step();
        }
    }
    public void shootAction() {
        shootIndex++;
        if (shootIndex % 30 == 0) {
            Bullet06[] bs=hero06.shoot();
            bullets =Arrays.copyOf(bullets,bullets.length+bs.length);
            System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
        }
    }

    public void outOfBoundsAction() {
        int index = 0;
        FlyingObject06[] enemyLives = new FlyingObject06[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            FlyingObject06 e = enemies[i];
            /**
             * ？？？？  e.isRemove()少了一个! /dk
             */
            if (!e.outOfBounds() && !e.isRemove()) {
                enemyLives[index++]=e;
            }
        }
        enemies = Arrays.copyOf(enemyLives, index);
        index =0;
        Bullet06[] bulletlives = new Bullet06[bullets.length];
        for (int i = 0; i < bullets.length; i++) {
            Bullet06 b = bullets[i];
            if (!b.outOfBounds() && !b.isRemove()) {
                bulletlives[index++]=b;
            }
        }
        bullets = Arrays.copyOf(bulletlives, index);
    }

    public void bangAction() {
        for (int i = 0; i < bullets.length; i++) {
            Bullet06 b = bullets[i];
            for (int j = 0; j < enemies.length; j++) {
                FlyingObject06 f = enemies[j];
                if (b.isLife() && f.isLife() && b.hit(f)) {
                    b.goDead();
                    f.goDead();
                    if (f instanceof Enemy06) {
                        Enemy06 e=(Enemy06)f;
                        score += e.getScore();
                    }
                    if (f instanceof Award06) {
                        Award06 a=(Award06)f;
                        int type = a.getType();
                        switch (type) {
                            case  Award06.DOUBLK_FIRE:
                                hero06.addDoubleFire();
                                break;
                            case  Award06.LIFE:
                                hero06.addLife();
                                break;
                        }
                    }
                }
            }
        }
    }
    public  void hitAction() {
        for (int i = 0; i < enemies.length; i++) {
            FlyingObject06 f = enemies[i];
            if (f.isLife() && hero06.hit(f)) {
                f.goDead();
                hero06.subtractLife();
                hero06.clearDoubleFire();
                break;
            }
        }
    }
    public void  checkGameoverAction() {
        if (hero06.getLife() <= 0) {
            state=GAME_OVER;
        }
    }
    public  void  action(){
        MouseAdapter l=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (state){
                    case START:
                        state=RUNNING;
                        break;
                    case GAME_OVER:
                        score=0;
                        hero06=new Hero06();
                        enemies=new FlyingObject06[0];
                        bullets = new Bullet06[0];
                        state =START;
                        break;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (state == PAUSE) {
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (state == RUNNING) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (state == RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    hero06.moveTo(x, y);
                }
            }
        };
        this.addMouseListener(l);
        this.addMouseMotionListener(l);

        Timer timer = new Timer();
        int intervel =10;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) {
                    enterAction();
                    stepAction();
                    shootAction();
                    outOfBoundsAction();
                    bangAction();
                    hitAction();
                    checkGameoverAction();
                }
                repaint();
            }
        },intervel,intervel);/*？？？*/
    }
    public  void paint(Graphics graphics){
        sky06.paint(graphics);/*画出天空*/
        hero06.paint(graphics);/*画出战斗机*/
        /*添加敌人*/
        for (int i = 0; i < enemies.length; i++)
            enemies[i].paint(graphics);
        for(int i=0;i<bullets.length;i++)
            bullets[i].paint(graphics);
        /*打印英雄熟悉值*/
        graphics.drawString("SCORE:"+score,10,25);
        graphics.drawString("LIFE:"+hero06.getLife(),10,45);
        /*不同的状态，画出不同的图片*/
        switch (state) {
            case START:
                graphics.drawImage(start,0,0,null);
                break;
            case PAUSE:
                graphics.drawImage(pause,0,0,null);
                break;
            case  GAME_OVER:
                graphics.drawImage(gameover,0,0,null);
                break;
        }
    }
    public  static  void main(String[] args){
        JFrame jFrame = new JFrame();
        World06 world06 =new World06();
        jFrame.add(world06);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH,HEIGHT);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        world06.action();
    }
}
