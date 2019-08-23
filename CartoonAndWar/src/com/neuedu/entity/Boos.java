package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: Boos
* @Description: boos类
* @author neuedu_jnn
* @date 2019年8月20日 下午6:08:49
*
*/
public class Boos extends Mouse implements ActionAble{
	
	private boolean up = true;
	
	private int speed = 5;
	

	
	public Boos() {
		
	}
	
	public Boos(int x,int y,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 100000;
		
	}
	//动态定义图片数组
	private static Image[] imgs = new Image[8];
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i]  =GetImageUtil.getImg("boos/0"+(i+1)+".png");
		}
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if (count>7) {
			count = 0;
		}
		g.drawImage(imgs[count++], x, y, null);
		move();
		g.drawString("当前血量"+blood, x, y);
	}
	
	@Override
	public void move() {
		x -= speed;
		
		if (x < 1100) {
			x = 1100;

			if (up) {
				y -= speed;
			}
			if (!up) {
				y += speed;
			}
			
			
			if (y>Constant.GAME_HEIGHT-imgs[0].getHeight(null)) {
				up = true;
			}
			if (y<30) {
				up = false;
			}
		}
		if (random.nextInt(500)>450) {
			fire();
		}
		
	}
	//生成随机数
	Random random = new Random();
	
	//获取到boos所在的矩形
		public Rectangle getRec() {
			return new Rectangle(x,y,imgs[0].getWidth(null),imgs[0].getHeight(null));
		}
		@Override
		public void fire() {
			singlePlayer.play("com/neuedu/sound/SOUND_BUYSCCUCESS_01.mp3");
			Bullet b = new Bullet(x, y+imgs[0].getHeight(null)/2-10, "bullet/enemyplane_03_bullet.png",gc,false);
			gc.bullets.add(b);
		}

}
