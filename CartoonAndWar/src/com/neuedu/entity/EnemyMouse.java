package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: EnemyMouse
* @Description:�з�������
* @author neuedu_jnn
* @date 2019��8��20�� ����11:03:21
*
*/
public class EnemyMouse extends Mouse implements ActionAble{
    private Integer enemyType;
    private Integer speed;
    //�õ��ͻ���
    private GameClient gc;
    
    

    public static Image[] imgs1 = {
    		GetImageUtil.getImg("enemy/enemyplane_02_1.png"),
    		GetImageUtil.getImg("enemy/enemyplane_02_2.png"),
    		GetImageUtil.getImg("enemy/enemyplane_02_3.png"),
    		GetImageUtil.getImg("enemy/enemyplane_02_4.png"),
    		GetImageUtil.getImg("enemy/enemyplane_02_5.png"),
    		GetImageUtil.getImg("enemy/enemyplane_02_6.png"),
    		GetImageUtil.getImg("enemy/enemyplane_02_7.png"),
    		GetImageUtil.getImg("enemy/enemyplane_02_8.png")
    };
    
	
	public EnemyMouse() {
		
	}
    public EnemyMouse(int x,int y,int enemyType,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.enemyType = this.enemyType;
		this.gc = gc;
		this.speed = 1;
		this.isGood = isGood;
	}
    @Override
    public void move() {
    	x -= speed;
    }
    int count = 0;
    @Override
    public void draw(Graphics g) {
    	if (count > 6) {
			count = 0;
		}
    	g.drawImage(imgs1[count++], x, y, null);
    	move();
    	
    	
    	if (random.nextInt(500)>490) {
			fire();
		}
    
    }
    //�����
    Random random = new Random();
    
    
    //���˷���
    public void fire() {
		Bullet bullet = new Bullet(x, y+imgs1[0].getHeight(null)/2+20, "bullet/enemyplane_03_bullet.png", gc,false);
	    gc.bullets.add(bullet);
	    
    }

  //��ȡ���ӵ��ľ���
  		public Rectangle getRec() {
  			return new Rectangle(x,y,this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
  		}
}
