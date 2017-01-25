import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

/**
 * Created by lovealmgren on 2017-01-24.
 */
public class Mazeblox implements KeyListener {

    public static Mazeblox mazeblox;

    public final int WIDTH = 700, HEIGHT = 700;

    public char[][] level1 = {
            {'1','P','1','1','1','1','1'},
            {'1','0','0','0','1','1','1'},
            {'1','0','0','X','0','0','1'},
            {'1','0','1','X','1','0','0'},
            {'1','0','0','X','0','1','1'},
            {'1','1','1','1','1','1','1'}};

    public char[][] level2 = {
            {'1','0','1','1','1','1','P','1'},
            {'1','0','0','0','1','1','0','1'},
            {'1','0','0','X','0','0','0','1'},
            {'1','0','1','X','1','0','0','1'},
            {'1','0','0','X','0','1','1','1'},
            {'1','0','0','X','0','1','1','1'},
            {'1','1','1','1','1','1','1','1'}};

    public char[][] board;
    private static int playerX = 0;
    private static int playerY = 1;
    private static final int winX = 3;
    private static final int winY = 6;


    public static Renderer renderer;

    public Mazeblox() {
        JFrame jFrame = new JFrame();

        jFrame.addKeyListener(this);
        renderer = new Renderer();
        jFrame.add(renderer);
        jFrame.setTitle("Mazeblox");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH,HEIGHT);
        jFrame.setResizable(false);
        jFrame.setVisible(true);


        this.board = this.level1;

        renderer.repaint();
    }

    public static void repaint(Graphics g) {


        g.setColor(Color.BLUE);
        int x = 0;
        int y = 0;


        for (int i = 0; i < mazeblox.board.length; i++) {
            for (int j = 0; j < mazeblox.board[0].length; j++) {
                if (mazeblox.board[i][j] == '1') {
                    //WALL
                    g.setColor(new Color(87,59,12));
                } else if (mazeblox.board[i][j] == 'P') {
                    //PLAYER
                    g.setColor(Color.GREEN);
                } else if (mazeblox.board[i][j] == 'X') {
                    //ROCK
                    g.setColor(new Color(133,132,119));
                } else if (mazeblox.board[i][j] == '0') {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x, y, 100, 100);

                x += 100;
            }
            x = 0;
            y += 100;
        }
        if (playerX == winX && playerY == winY) {
            //g.drawString("YOU WIN",100,100);
            mazeblox.board = mazeblox.level2;
            return;
        }

    }

    public static void main(String[] args) {

        mazeblox = new Mazeblox();

    }

    static boolean move(int x1, int y1, int x2, int y2) {

        char object1 = mazeblox.board[x1][y1];
        char object2 = mazeblox.board[x2][y2];

        System.out.println(object1+":"+object2);

        if (object2 == '0') {
            mazeblox.board[x1][y1] = '0';
            mazeblox.board[x2][y2] = object1;
            renderer.repaint();
            if (object1 == 'P') {
                playerX = x2;
                playerY = y2;
            }
            return true;
        } else if (object2 == 'X') {
            if (move(x2, y2, x2 + (x2-x1), y2 + (y2-y1))) {
                mazeblox.board[x1][y1] = '0';
                mazeblox.board[x2][y2] = object1;
                renderer.repaint();
                if (object1 == 'P') {
                    playerX = x2;
                    playerY = y2;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move(playerX,playerY,playerX,playerY+1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move(playerX,playerY,playerX,playerY-1);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            move(playerX,playerY,playerX+1,playerY);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            move(playerX,playerY,playerX-1,playerY);
        }
    }

}