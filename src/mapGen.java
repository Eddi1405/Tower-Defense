import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class mapGen {

    UI_start s1;
    UI_gamepanel gp;
    Tile[] tile;
    int mapTileNum[][];
    int map = 1;

    public mapGen(UI_gamepanel gp) {

        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileimage();
        loadMap(map);

    }

    public void getTileimage() {
        //Tiles werden defieniert
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass1.1.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass1.2.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass1.3.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass2.1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass2.2.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass2.3.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass3.1.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass3.2.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass3.3.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass3.4.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/grass4.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/baum.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Map wird geladen
    public void loadMap(int map){
        //int map = s1.getMap();
        //int map = 1;
        //Problem s1 ist null variabel muss übergeben werden
        System.out.println("asd"+map);
        try {
            //text datei wird eingelesen
            //TODO map unterscheidliche
            InputStream is = getClass().getResourceAsStream("map/map"+map+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while (col < gp.maxScreenCol ){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            System.out.println("Error Map laden");
        }
    }

    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    }

