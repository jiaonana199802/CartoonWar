package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

public class EnemyMouse2 extends Mouse implements ActionAble{
	
	
	 private Integer enemyType;
	    private Integer speed;
	    //拿到客户端
	    private GameClient gc;
	    
	    

	    public static Image[] imgs2 = {
	    		GetImageUtil.getImg("enemy/img_01.png"),
	    		GetImageUtil.getImg("enemy/img_02.png"),
	    		GetImageUtil.getImg("enemy/img_03.png"),
	    		GetImageUtil.getImg("enemy/img_04.png"),
	    		GetImageUtil.getImg("enemy/img_05.png"),
	    		GetImageUtil.getImg("enemy/img_06.png"),
	    };
	    
		
		public EnemyMouse2() {
			
		}
	    public EnemyMouse2(int x,int y,int enemyType,GameClient gc,boolean isGood) {
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
	    	if (count > 5) {
				count = 0;
			}
	    	g.drawImage(imgs2[count++], x, y, null);
	    	move();
	    	
	    	
	    	if (random.nextInt(500)>490) {
				fire();
			}
	    
	    }
	    //随机数
	    Random random = new Random();
	    
	    
	    //敌人发火
	    public void fire() {
			Bullet bullet = new Bullet(x, y+imgs2[0].getHeight(null)/2+20, "bullet/enemyplane_03_bullet.png", gc,false);
		    gc.bullets.add(bullet);
		    
	    }

	  //获取到子弹的矩形
	  		public Rectangle getRec() {
	  			return new Rectangle(x,y,this.imgs2[0].getWidth(null),this.imgs2[0].getHeight(null));
	  		}
	

}
