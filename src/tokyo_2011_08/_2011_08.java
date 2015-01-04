package tokyo_2011_08;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class _2011_08 extends JFrame{
	JLabel label_a = new JLabel();
	JLabel label_b = new JLabel();
	JLabel label_c = new JLabel();
	JLabel label_d = new JLabel();
	JLabel label_x = new JLabel();
	JLabel label_score = new JLabel();
	String score_text = "Score:0";
	
	private int ball_x, ball_y, ball_flag;//ÇòµÄ×ø±ê
	private int gun_x = 172, gun_y = 372, gun_count = 0;//µ¯ÍèµÄ×ø±ê
	boolean move_gun = false;
	JPanel pane = new JPanel(){
		public void paint(Graphics g){
			super.paint(g);
			//ÅÌÃæ
			for(int i=0;i<9;i++){
				g.drawLine(100+20*i, 100, 100+20*i, 380);
			}
			for(int i=0;i<15;i++){
				g.drawLine(100, 100+20*i, 260, 100+20*i);
			}
			g.drawOval(ball_x, ball_y, 16, 16);
			g.fillOval(gun_x, gun_y, 16, 16);
	    }
	};
	
	public void init() throws InterruptedException{
		this.setTitle("2011_08");
		this.setBounds(400, 100, 360, 500);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initBall();
		
		pane.setBounds(0, 0, 300, 500);
		
		label_a.setText("A");
		label_a.setBounds(175, 70, 80, 20);
		label_b.setText("B");
		label_b.setBounds(80, 240, 80, 20);
		label_c.setText("C");
		label_c.setBounds(175, 400, 80, 20);
		label_d.setText("D");
		label_d.setBounds(270, 240, 80, 20);
		
		label_x.setText("X");
		label_x.setBounds(gun_x+4, gun_y+12, 80, 20);
		
		label_score.setText(score_text);
		label_score.setBounds(140, 30, 80, 20);

		this.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_I){
					move_gun = true;
				}
				if(e.getKeyCode()==e.VK_J&&gun_x>92&&move_gun==false){
					gun_x -= 20;
					label_x.setBounds(gun_x+4, gun_y+12, 80, 20);
					repaint();
				}
				if(e.getKeyCode()==e.VK_L&&gun_x<252&&move_gun==false){
					gun_x += 20;
					label_x.setBounds(gun_x+4, gun_y+12, 80, 20);
					repaint();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});
		
		this.add(label_a);
		this.add(label_b);
		this.add(label_c);
		this.add(label_d);
		this.add(label_x);
		this.add(label_score);
		this.add(pane);
		while(true){
			Thread.sleep(500);
			if(ball_x==gun_x&&ball_y==gun_y){
				score_text = "Score:"+(Integer.parseInt(score_text.substring(6))+1);
				label_score.setText(score_text);
				
				initBall();
				
				gun_y = 372;
				gun_count = 0;
				move_gun = false;
			} else {
				moveBall();
				if(move_gun){
					moveGun();
				}
			}
			repaint();
		}
	}
	
	//³õÊ¼»¯Çò
	public void initBall(){
		int ran_ball = (int) (Math.random()*9);
		ball_x = 92 + ran_ball*20;
		ball_y = 92;
		ball_flag = 0;
		if(ran_ball==8){
			ball_flag = 1;
		}
	}
	
	//ÒÆ¶¯Çò
	public void moveBall(){
		//0 ÓÒÒÆ 1 ×óÒÆ 2Í£Ö¹
		if(ball_flag==0){
			ball_x += 20;
			ball_y += 20;
			if(ball_x==252){
				ball_flag = 1;
			}
			if(ball_y==352){
				ball_flag = 2;
			}
		} else if(ball_flag==1){
			ball_x -= 20;
			ball_y += 20;
			if(ball_x==92){
				ball_flag = 0;
			}
			if(ball_y==352){
				ball_flag = 2;
			}
		} else if(ball_flag==2){
			initBall();
		}
	}
	
	//ÒÆ¶¯µ¯Íè
	public void moveGun(){
		if(gun_count<14){
			gun_y -= 20;
			gun_count++;
		} else {
			gun_y = 372;
			gun_count = 0;
			move_gun = false;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		_2011_08 main = new _2011_08();
		main.init();
	}
	
}
