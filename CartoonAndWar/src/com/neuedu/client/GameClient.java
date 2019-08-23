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
* @Description: ��Ϸ�ͻ���
* @author neuedu_jnn
* @date 2019��8��17�� ����11:18:40
*
*/
public class GameClient extends Frame{
	
//	//����һ��mouse
//	Mouse mouse = new Mouse(100,200,"plane/myplane_02.png",this,true);
	
	//����һ���ҷ���ɫ����
	public List<Mouse> mouses = new ArrayList<>();
	
	//�������߼���
	public List<Prop> props = new ArrayList<>();

	
	//����һ���ӵ�
//	Bullet bullet = new Bullet(500,200,"bullet/BULLET_04.png");
	
	//�����ӵ�����
	public List<Bullet> bullets = new ArrayList<>();
	
	//����һ������ͼ
	BackGround backImg = new BackGround(0, 0, "plane/bg01.png");
	
	//�����з�����
	public List<Mouse> enemys = new ArrayList<>();
	
	//����boos����
	public List<Mouse> booses = new ArrayList<>();
	
	//������ը����
	public List<Boom> booms = new ArrayList<>();
	
	//���ͼƬ��˸����
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

	//���ɿͻ��˴��ڵķ���
	public void launchFrame() {
		
		SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sound/FEVER_BGM.mp3");
		soundPlayer.start();
		
		
		//���ô��ڴ�С
		
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		//���ñ���
		this.setTitle("��ͨ����ս");
		//�����Ƿ��ܹ���ʾ
		this.setVisible(true);
		//��ֹ���
		this.setResizable(false);
		//���ھ���
		this.setLocationRelativeTo(null);
		
		//�رմ���
		//ʹ�ü������ر�
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
//		//���������
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("���");
//			}
//			
//		});
		mouse = new Mouse(100,200,"plane/plane_01.png",this,true);
		//���ҷ�����������Լ�
		mouses.add(mouse);
		
		//��Ӽ��̼���
		this.addKeyListener(new KeyAdapter() {
			//���̰������
			@Override
			public void keyPressed(KeyEvent e) {
				mouse.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				mouse.keyReleased(e);
			}
		});
		
		//�����߳�
		new paintThread().start();
		
		
		//���з���������ӵ���
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

		
		//���boos
		booses.add(new Boos(1400, 400, this, false));
		
	}
	//��дpaint����
	@Override
	public void paint(Graphics g) {
		
		backImg.draw(g);
		
		g.drawLine(1100, 0, 1100, 800);
		
		for (int i = 0; i < mouses.size(); i++) {
			Mouse mouse2 = mouses.get(i);
			mouse2.draw(g);
			mouse2.containItems(props);
		}
		//��ǿforѭ���в������κβ���
		//forѭ������ÿ���ӵ�
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(mouses);
			bullet.hitMonsters(booses);
		}
		g.drawString("��ӡ�ӵ���ǰ������:"+bullets.size(), 10, 40);
		g.drawString("��ǰ�з���������"+enemys.size(), 10, 70);
		g.drawString("��ǰ��ը��������"+booms.size(), 10, 100);
		g.drawString("��ǰ�ҷ���������"+mouses.size(), 10, 130);
		g.drawString("��ǰ���ߵ�������"+props.size(), 10, 160);
		//ѭ�����з�
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).draw(g);
		}
		//ѭ����ը
		for (int i = 0; i < booms.size(); i++) {
			if (booms.get(i).isLive()==true) {
				booms.get(i).draw(g);
			}
			
		}
		
		//ѭ��������
				for (int i = 0; i < props.size(); i++) {			
						props.get(i).draw(g);
				}
			if (enemys.size()==0) {
				//ѭ������boos
			for (int i = 0; i < booses.size(); i++) {
				booses.get(i).draw(g);
			}
			}	
		
	}
	
	//�ڲ���
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
