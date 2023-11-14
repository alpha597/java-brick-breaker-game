import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
public class gameplay extends JPanel implements KeyListener,ActionListener {
    private boolean play = false;
    private int score =0;
    private Timer timer;
    private int delay =8;
    
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int totalbricks = 48;
    private mapgenerator map;
    public gameplay(){
        map = new mapgenerator(4, 12);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        //background properties
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        //draw the map
        map.draw((Graphics2D) g);
        // set borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3,592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // the scores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(" "+score, 590, 30);
        // the paddle
        g.setColor(Color.DARK_GRAY);
        g.fillRect(playerX,550,100,8);
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
        //you win the game
        if(totalbricks<=0){
            play = false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You won", 260, 300);
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press (enter) to restart", 230, 350);

        }
        if(ballposY > 570){
            play = false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over", 200, 300);
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press (enter) to restart",  230,350);

        }
        g.dispose();
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX <= 600){
                playerX=600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <10){
                playerX =10;
            }
            else{
                moveLeft();
            }
        }
        //starting a new game
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
        }	
    }
    public void keyReleased(KeyEvent e){

    }
    public void keyTyped(KeyEvent e){

    }
    public void moveRight(){
        play = true;
        playerX += 10;
    }
    public void moveLeft(){
        play = true;
        playerX -= 10;
    }
    public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{	// changeing direction of ball if it collides at boundary	
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYdir = -ballYdir;
			}
			
			// check map collision with the ball		
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						//scores++;
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						//if ball hits the bricks then score increases
						if(ballRect.intersects(brickRect))
						{					
							map.setBrickValue(0, i, j);
							score+=5;	
							totalBricks--;
							
							// when ball hit right or left of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}		
			
			repaint();		
		}
	}
}
