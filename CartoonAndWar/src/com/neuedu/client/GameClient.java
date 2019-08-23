package com.neuedu.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.constant.Constant;
import com.neuedu.entity.BackGround;
import com.neuedu.entity.Boom;
import com.neuedu.entity.Boos;
import com.neuedu.entity.Bullet;
import com.neuedu.entity.EnemyMouse;
import com.neuedu.entity.EnemyMouse2;
import com.neuedu.entity.GameObj;
import com.neuedu.entity.Mouse;
import com.neuedu.entity.Prop;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SoundPlayer;

/**
* @ClassName: GameClient
* @Description: 游戏客户端
* @author neuedu_jnn
* @date 2019年8月17日 上午11:18:40
*
*/
public class GameClient extends Frame{
	
//	//创建一个mouse
//	Mouse mouse = new Mouse(100,200,"plane/myplane_02.png",this,true);
	
	//创建一个我方角色集合
	public List<Mouse> mouses = new ArrayList<>();
	
	//创建道具集合
	public List<Prop> props = new ArrayList<>();

	
	//创建一颗子弹
//	Bullet bullet = new Bullet(500,200,"bullet/BULLET_04.png");
	
	//创建子弹集合
	public List<Bullet> bullets = new ArrayList<>();
	
	//创建一个背景图
	BackGround backImg = new BackGround(0, 0, "plane/bg01.png");
	
	//创建敌方集合
	public List<Mouse> enemys = new ArrayList<>();
	
	//创建boos集合
	public List<Mouse> booses = new ArrayList<>();
	
	//创建爆炸集合
	public List<Boom> booms = new ArrayList<>();
	
	//解决图片闪烁问题
	@Override
	public void update(Graphics g) {
		Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		Graphics backg = backImg.getGraphics();
		Color color = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg, 0, 0, null);	
	}
	Mouse mouse = null;

	//生成客户端窗口的方法
	public void launchFrame() {
		
		SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sound/FEVER_BGM.mp3");
		soundPlayer.start();
		
		
		//设置窗口大小
		
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		//设置标题
		this.setTitle("卡通大作战");
		//设置是否能够显示
		this.setVisible(true);
		//禁止最大化
		this.setResizable(false);
		//窗口居中
		this.setLocationRelativeTo(null);
		
		//关闭窗口
		//使用监听器关闭
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
//		//添加鼠标监听
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("鼠标");
//			}
//			
//		});
		mouse = new Mouse(100,200,"plane/plane_01.png",this,true);
		//往我方容器中添加自己
		mouses.add(mouse);
		
		//添加键盘监听
		this.addKeyListener(new KeyAdapter() {
			//键盘按下情况
			@Override
			public void keyPressed(KeyEvent e) {
				mouse.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				mouse.keyReleased(e);
			}
		});
		
		//启动线程
		new paintThread().start();
		
		
		//往敌方容器中添加敌人
		EnemyMouse enemy1 = new EnemyMouse(1000,50,1,this,false);	
		EnemyMouse enemy2 = new EnemyMouse(1000,150,1,this,false);
		EnemyMouse enemy3 = new EnemyMouse(1000,250,1,this,false);
		EnemyMouse enemy4 = new EnemyMouse(1000,350,1,this,false);
		EnemyMouse enemy5 = new EnemyMouse(1000,450,1,this,false);
		EnemyMouse enemy6 = new EnemyMouse(1000,550,1,this,false);
		
//		EnemyMouse2 enemy7 = new EnemyMouse2(1100,50,1,this,false);	
//		EnemyMouse2 enemy8 = new EnemyMouse2(1100,150,1,this,false);
//		EnemyMouse2 enemy9 = new EnemyMouse2(1100,250,1,this,false);
//		EnemyMouse2 enemy10 = new EnemyMouse2(1100,350,1,this,false);
//		EnemyMouse2 enemy11 = new EnemyMouse2(1100,450,1,this,false);
//		EnemyMouse2 enemy12 = new EnemyMouse2(1100,550,1,this,false);
		
		
		

		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		enemys.add(enemy5);
		enemys.add(enemy6);
		
//		enemys.add(enemy7);
//		enemys.add(enemy8);
//		enemys.add(enemy9);
//		enemys.add(enemy10);
//		enemys.add(enemy11);
//		enemys.add(enemy12);

		
		//添加boos
		booses.add(new Boos(1400, 400, this, false));
		
	}
	//重写paint方法
	@Override
	public void paint(Graphics g) {
		
		backImg.draw(g);
		
		g.drawLine(1100, 0, 1100, 800);
		
		for (int i = 0; i < mouses.size(); i++) {
			Mouse mouse2 = mouses.get(i);
			mouse2.draw(g);
			mouse2.containItems(props);
		}
		//增强for循环中不能做任何操作
		//for循环画出每个子弹
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(mouses);
			bullet.hitMonsters(booses);
		}
		g.drawString("打印子弹当前的数量:"+bullets.size(), 10, 40);
		g.drawString("当前敌方的数量："+enemys.size(), 10, 70);
		g.drawString("当前爆炸的数量："+booms.size(), 10, 100);
		g.drawString("当前我方的数量："+mouses.size(), 10, 130);
		g.drawString("当前道具的数量："+props.size(), 10, 160);
		//循环画敌方
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).draw(g);
		}
		//循环爆炸
		for (int i = 0; i < booms.size(); i++) {
			if (booms.get(i).isLive()==true) {
				booms.get(i).draw(g);
			}
			
		}
		
		//循环画道具
				for (int i = 0; i < props.size(); i++) {			
						props.get(i).draw(g);
				}
			if (enemys.size()==0) {
				//循环遍历boos
			for (int i = 0; i < booses.size(); i++) {
				booses.get(i).draw(g);
			}
			}	
		
	}
	
	//内部类
	class paintThread extends Thread{
		@Override
		public void run() {
			while (true) {
				repaint();	
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
