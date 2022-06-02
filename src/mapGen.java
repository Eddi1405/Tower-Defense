import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


public class mapGen {
    UI_gamepanel gp;
    Tile[] tile;
    int[][] mapTileNum;


    public mapGen(UI_gamepanel gp) {
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileimage();
    }

    public void getTileimage() {
        //Tiles werden defieniert
        //Abfrage ob das grid true ist.
        if (gp.grid) {
            setup(0, "grid", false);
        } else {
            setup(0, "grass", false);
        }
        setup(1, "grass1.2",    true);
        setup(2, "grass2.5",    true);
        setup(3, "grass3.7",    true); //up
        setup(4, "grass3.8",    true); //up
        setup(5, "grass3.9",    true); //up
        setup(6, "grass3.10",   true); //up
        setup(9, "grass3.7",    true); //down
        setup(10, "grass3.8",   true); //down
        setup(11, "grass3.9",   true); //down
        setup(12, "grass3.10",  true); //down
        setup(7, "grass4.11",   true);
        setup(8, "baum.12",     true);
    }
    // Die Daten von tile werden gesetzt und Scaliert.
    public void setup(int index, String path, boolean collision) {

        scaling sc = new scaling();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/pictures_map/" + path + ".png")));
            tile[index].image = sc.scale(tile[index].image, gp.w_TileSize, gp.h_TileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Map wird geladen
    public void loadMap(int map) {

        try {
            //Textdatei wird eingelesen
            InputStream is = getClass().getResourceAsStream("map/map" + map + ".txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error Map laden");
        }
    }
    //Die Map wird gezeichnet durch ausgeklügelte und komplexe Berechnungen.
    public void drawMap(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.w_TileSize, gp.h_TileSize, null);
            col++;
            x += gp.w_TileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.h_TileSize;
            }
        }
    }

}

