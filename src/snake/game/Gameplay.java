/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class Gameplay extends JPanel implements KeyListener , ActionListener {
    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private ImageIcon leftmouth;
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon TitleImage;
    
    private int lengthofsnake = 3;
    private int move = 0;
    private Timer timer;
    private int delay =100;
    private ImageIcon snakeimage;
    
    private int[] enemyxpos ={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
                              525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyypos ={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
                              525,550,575,600,625};
    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    private int score, c = 0;;
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
        
    }
    public void paint(Graphics g){
        
        if(move == 0){
            
            snakexlength[2]=50;
            snakexlength[1]=75;
            snakexlength[0]=100;
            
            snakeylength[2]=100;
            snakeylength[1]=100;
            snakeylength[0]=100;
        }
        
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        
        TitleImage = new ImageIcon("src/res/snaketitle.jpg");
        TitleImage.paintIcon(this, g, 25, 11);
        
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 576);
        
        g.setColor(Color.ORANGE);
        g.setFont(new Font("arial",Font.BOLD,14));
        g.drawString("Score : "+score, 780, 30);
        
        g.setColor(Color.ORANGE);
        g.setFont(new Font("arial",Font.BOLD,14));
        g.drawString("Length : "+lengthofsnake, 780, 50);
        
        rightmouth = new ImageIcon("src/res/rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        
        for(int a =0;a<lengthofsnake;a++){
            if(a==0 && right){
                rightmouth = new ImageIcon("src/res/rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a==0 && left){
                leftmouth = new ImageIcon("src/res/leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a==0 && up){
                upmouth = new ImageIcon("src/res/upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a==0 && down){
                downmouth = new ImageIcon("src/res/downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a!=0){
                
                snakeimage = new ImageIcon("src/res/snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
                // g.setColor(Color.CYAN);
                //g.drawString("x : "+snakexlength[a]+" y : "+snakeylength[a], 100, 500);
            }
        }
        
        enemyimage = new ImageIcon("src/res/enemy.png");
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        
        //score code
        if(enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0] ){
            lengthofsnake++;
            score++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        //game over
        for(int b = 1; b < lengthofsnake ; b++){
            if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]){
                right = false;
                left = false;
                up = false;
                down = false;
                
                g.setColor(Color.YELLOW);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("GAME OVER", 300, 300);
                
                g.setFont(new Font("arial",Font.BOLD,25));
                g.drawString("Enter SPACE to restart", 300, 340);
            }
        }
        
        g.dispose();
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
           timer.start();
           if(right){
             for (int r = lengthofsnake-1;r>=0;r--){
                 snakeylength[r+1]= snakeylength[r];
             } 
             for(int r = lengthofsnake; r>=0 ; r-- ){
                 if(r==0){
                     snakexlength[r]=snakexlength[r]+25;
                 }
                 else{
                     snakexlength[r]=snakexlength[r-1];
                 }
                 if(snakexlength[r]>850){
                     snakexlength[r] = 25;
                 }
             }
             repaint();
           }
           if(left){
             for (int r = lengthofsnake-1;r>=0;r--){
                 snakeylength[r+1]= snakeylength[r];
             } 
             for(int r = lengthofsnake; r>=0 ; r-- ){
                 if(r==0){
                     snakexlength[r]=snakexlength[r]-25;
                 }
                 else{
                     snakexlength[r]=snakexlength[r-1];
                 }
                 if(snakexlength[r]<25){
                     snakexlength[r] = 850;
                 }
             }
             repaint();
           }
           if(up){
             for (int r = lengthofsnake-1;r>=0;r--){
                 snakexlength[r+1]= snakexlength[r];
             } 
             for(int r = lengthofsnake; r>=0 ; r-- ){
                 if(r==0){
                     snakeylength[r]=snakeylength[r]-25;
                 }
                 else{
                     snakeylength[r]=snakeylength[r-1];
                 }
                 if(snakeylength[r]<75){
                     snakeylength[r] = 625;
                 }
             }
             repaint();  
           }
           if(down){
             for (int r = lengthofsnake-1;r>=0;r--){
                 snakexlength[r+1]= snakexlength[r];
             } 
             for(int r = lengthofsnake; r>=0 ; r-- ){
                 if(r==0){
                     snakeylength[r]=snakeylength[r]+25;
                 }
                 else{
                     snakeylength[r]=snakeylength[r-1];
                 }
                 if(snakeylength[r]>625){
                     snakeylength[r] = 75;
                 }
             }
            repaint();
           }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            score = 0;
            move = 0;
            lengthofsnake = 3;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            move=1;
            right = true;
            if(!left){
                right = true;
            }
            else{
                right = false;
                left = true;
            }
            
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            move++;
            left = true;
            if(!right){
                left = true;
            }
            else{
                left = false;
                right = true;
            }
            
            up = false;
            down = false;
        }    
        if(e.getKeyCode() == KeyEvent.VK_UP){
            move++;
            up = true;
            if(!down){
                up = true;
            }
            else{
                up = false;
                down = true;
            }
            
            left = false;
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            move++;
            down = true;
            if(!up){
                down = true;
            }
            else{
                down = false;
                up = true;
            }
            
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
