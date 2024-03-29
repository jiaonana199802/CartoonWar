package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.CountDownLatch;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: Boom
* @Description: 爆炸效果类
* @author neuedu_jnn
* @date 2019年8月19日 下午5:50:03
*
*/
public class Boom extends GameObj implements ActionAble{
	
	private boolean isLive;
	private GameClient gc;
	
    public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	//动态初始化
	private static Image[] boomImgs = new Image[9];
	static {
		for (int i = 0; i <9; i++) {
			boomImgs[i] = GetImageUtil.getImg("boom/"+(i+1)+".png");
		}
	}
			
	public Boom() {
		
	}
    public Boom(int x,int y,GameClient gc) {
		this.x  =x;
		this.y  =y;
		this.isLive = true;
		this.gc = gc;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if (isLive) {
		if (count>8) {
			
			gc.booms.remove(this);
			this.setLive(false);
			return;
		}
		g.drawImage(boomImgs[count++], x, y, null);
		}
	}

}
