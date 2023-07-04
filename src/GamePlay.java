import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private int trudnosc;
    private boolean play = false;
    private int score = 0;

    private int totalBricks;

    private Timer timer;
    private int delay = 0;

    private int playerX = 310;

    private int ballposX = (int)(Math.random() * 650 + 50);
    private int ballposY = 350;
    private int ballXdir = 1;
    private int ballYdir = 2;

    private MapGenerator map;

    public GamePlay(int trudnosc){
        this.trudnosc = trudnosc;
        if(trudnosc == 1){
            totalBricks = 21;
            map = new MapGenerator(3,7, this.trudnosc);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(delay, this);
            timer.start();
        }
        if(trudnosc == 2){
            totalBricks = 45;
            map = new MapGenerator(5,9, this.trudnosc);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(delay, this);
            timer.start();
        }
        if(trudnosc == 3){
            totalBricks = 77;
            ballposY = 400;
            map = new MapGenerator(7,11, this.trudnosc);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(delay, this);
            timer.start();
        }
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);

        //drawing map
        map.draw((Graphics2D)g);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100,8);

        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20,20);

        if(ballposY > 570){
            play = false;
            ballYdir = 0;
            ballXdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Your Score: "+score,190,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to Restart",225,350);
        }
        if(totalBricks == 0){
            play = false;
            ballYdir = 0;
            ballXdir = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Congratulations, You Won! Score: "+score,100,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to Restart",225,350);
        }

        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdir = -ballYdir;
            }
            A: for(int i=0; i<map.map.length; i++){
                for(int j = 0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j* map.brickWidht + 80;
                        int brickY = i* map.brickHeight + 50;
                        int brickWidth = map.brickWidht;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            if(map.map[i][j] == 1) {
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                            }
                            else if(map.map[i][j] == 2){
                                map.setBrickValue(1,i,j);
                                score += 5;
                            }
                            else if(map.map[i][j] == 3){
                                map.setBrickValue(2, i, j);
                                score += 5;
                            }

                            if(ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }
                            else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0){
                ballXdir = -ballXdir;
            }

            if(ballposY < 0){
                ballYdir = -ballYdir;
            }
            if(ballposX > 670){
                ballXdir = -ballXdir;
            }

        }

        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7, this.trudnosc);

                repaint();
            }
        }
    }

    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }

}
