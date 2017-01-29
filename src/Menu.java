import java.awt.*;
import java.util.*;

/**
 * Created by lovealmgren on 2017-01-28.
 */
public class Menu {

    public static LinkedList<MenuButton> buttonList = new LinkedList<MenuButton>();
    public static MenuButton currentButton;

    public static int BTNWIDTH = (int)Math.floor(Mazeblox.WIDTH*0.5), BTNHEIGHT = 100;

    public static MenuButton playButton = new MenuButton(new Rectangle(BTNWIDTH, BTNHEIGHT),"Play Game");
    public static MenuButton browseMazeButton = new MenuButton(new Rectangle(BTNWIDTH, BTNHEIGHT),"Browse Mazes");
    public static MenuButton createMazeButton = new MenuButton(new Rectangle(BTNWIDTH, BTNHEIGHT),"Create Maze");
    public static MenuButton quitButton = new MenuButton(new Rectangle(BTNWIDTH, BTNHEIGHT),"Quit Game");

    Menu(){
        buttonList.add(playButton);
        buttonList.add(browseMazeButton);
        buttonList.add(createMazeButton);
        buttonList.add(quitButton);
        currentButton = buttonList.getFirst();

    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0, 0, Mazeblox.WIDTH, Mazeblox.HEIGHT);
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.green);
        g.drawString("MazeBlox", (int)Math.floor(Mazeblox.WIDTH*0.25), 100);
        drawButtons(g2d);
    }

    static void drawButtons(Graphics2D g) {
        int x = (int)Math.floor(Mazeblox.WIDTH*0.25);
        int y = 200;
        Font fnt1 = new Font("arial", Font.BOLD, 25);
        g.setFont(fnt1);

        for (MenuButton b : buttonList) {
            if (b == currentButton) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.green);
            }
            g.drawString(b.buttonText,x+20,y+50);
            b.rectangle.x = x;
            b.rectangle.y = y;
            g.draw(b.rectangle);
            y += 100;
        }
    }

    static void getNextButton(int i) {
        int index = buttonList.indexOf(currentButton);
        if (index + i >= buttonList.size()) {
            currentButton = buttonList.getFirst();
        } else if (index + i < 0) {
            currentButton = buttonList.getLast();
        } else {
            currentButton = buttonList.get(index + i);
        }
    }

    static void buttonFunction() {
        if (currentButton == playButton) {
            Mazeblox.state = Mazeblox.STATE.GAME;
        }
        if (currentButton == quitButton) {
            System.exit(0);
        }
    }
}

class MenuButton {

    Rectangle rectangle;
    String buttonText;

    MenuButton(Rectangle rectangle, String buttonText){
        this.rectangle = rectangle;
        this.buttonText = buttonText;
    }


}
