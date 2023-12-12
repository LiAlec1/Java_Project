import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.io.*;
import javax.imageio.ImageIO;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    String[][] map;

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        tile = new Tile[10];
        map = new String[gamePanel.MAX_SCREEN_ROW][gamePanel.MAX_SCREEN_COL];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("tiles/001.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("tiles/019.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream("maps/map1.txt")));

            for (int i = 0; i < gamePanel.MAX_SCREEN_ROW; i++) {
                map[i] = reader.readLine().split(" ");
                System.out.println(Arrays.toString(map[i]));
            }

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void draw(Graphics2D graphic) {

        for (int i = 0; i < gamePanel.MAX_SCREEN_ROW; i++) {
            for (int j = 0; j < gamePanel.MAX_SCREEN_COL; j++) {
                graphic.drawImage(tile[Integer.parseInt(map[i][j])].image, j * gamePanel.TILE_SIZE,
                        i * gamePanel.TILE_SIZE,
                        gamePanel.TILE_SIZE,
                        gamePanel.TILE_SIZE, null);
            }
        }
    }
}
