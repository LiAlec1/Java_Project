import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int ORIGINAL_TILE_SIZE = 16;
    final int SCALE = 3;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    // Define screen as 4:3 ratio
    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    final int WINDOW_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    final int WINDOW_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    int FPS = 60;

    Player player;

    public GamePanel() {

        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improve render performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        player = new Player(this, keyHandler);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            System.out.println("game loop");

            update();

            repaint();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Convert nanoseconds to miliseconds

                remainingTime = Math.max(0, remainingTime);

                nextDrawTime += drawInterval;

                Thread.sleep((long) remainingTime);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics graphic) {

        super.paintComponent(graphic);

        Graphics2D altGraphic = (Graphics2D) graphic;

        player.draw(altGraphic);

        altGraphic.dispose();
    }
}
