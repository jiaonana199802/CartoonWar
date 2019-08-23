package com.neuedu.entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlayer;

import javazoom.jl.player.Player;

/**
* @ClassName: Mouse
* @Description: 创建一个仓鼠类
* @author neuedu_jnn
* @date 2019年8月19日 下午1:08:30
*
*/
public class Mouse extends GameObj implements ActionAble{
	
	//
	SinglePlayer singlePlayer = new SinglePlayer();
	
	//是否存活
	public boolean isLive;
	
	//速度
	private int speed;
	
	//方向布尔变量
	private boolean left,up,right,down;
	
	//客户端
	public GameClient gc;
	
	//判断是否为我军还是敌军
	public boolean isGood;
	
	//添加飞机子弹等级变量
	public int bulletLevel = 1;
	
	//添加血值
	public int blood;
	
	public Mouse() {
		
	}
	public Mouse(int x,int y,String imgName,GameClient gc,boolean isGood) {
		
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 20;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 100;
		this.isLive = true;
	}
	//移动的方法
	@Override
	public void move() {
		if (left) {
			x -= speed;
		}
		if (up) {
			y -= speed;
		}
		if (right) {
			x += speed;
		}
		if (down) {
			y += speed;
		}
		outOfBound();
	}
	//画一个仓鼠
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(img, x, y, null);
		move();
		g.drawString("当前血量"+blood, 10, 200);
//		bloodBar.draw(g);
	}
	//处理仓鼠的边界问题
	public void outOfBound() {
		if (y<=30) {
			y = 20;
		}
		if (x<=5) {
			x = 0;
		}
		if (x>=Constant.GAME_WIDTH-img.getWidth(null)) {
			x = Constant.GAME_WIDTH-img.getWidth(null);
		}
		if (y>=Constant.GAME_HEIGHT-img.getHeight(null)) {
			y = Constant.GAME_HEIGHT-img.getHeight(null);
		}
	}
	
	
	//鼠标按下
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
        case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		

		default:
			break;
		}
		
	}
	//鼠标释放
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
        case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_M:
			if (isLive==true) {
				fire();
			} 
			break;
		
		default:
			break;
		}	
	}
	//我方飞鼠开火
	public void fire() {
		singlePlayer.play("com/neuedu/sound/SOUND_BUYSCCUCESS_01.mp3");
		Bullet b = new Bullet(x+this.img.getWidth(null), y+this.img.getHeight(null)/2-10, "bullet/myPlane_bullet_0"+bulletLevel+".png",gc,true);
		gc.bullets.add(b);
		
	}
	//获取到当前的矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
	//检测我方角色碰撞道具
	public void containItem (Prop prop) {
		if (this.getRec().intersects(prop.getRect())) {
			//道具消失
			gc.props.remove(prop);
			if (bulletLevel>4) {
				bulletLevel = 8;
				return;
			}
			//更改子弹等级
			this.bulletLevel +=1;
			
		}
	}
	//检测我方角色碰撞到多个道具
	public void containItems(List<Prop> props) {
		if (props==null) {
			return;
		}else {
			for (int i = 0; i < props.size(); i++) {
				containItem(props.get(i));
			}
		}
	}
	
//	private BloodBar bloodBar = new BloodBar();
    
//    //画血条
//    class BloodBar{
//        
//        public void draw(Graphics g) {
//            Color c = g.getColor();
//            if(blood>75) {
//                g.setColor(Color.GREEN);
//            }else if(blood<75&&blood>30){
//                g.setColor(Color.ORANGE);
//            }else {
//                g.setColor(Color.RED);
//            }
//            g.drawRect(x, y-20, img.getWidth(null), 10);
//            g.fillRect(x, y-20, img.getWidth(null)*blood/100, 10);
//            
//            
//        }
//    }
	
}
