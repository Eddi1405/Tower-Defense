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

        if (gp.rel) {
            setup(0, "grid",false);

        } else {
            setup(0, "grass",false);
        }

        setup(2, "grass1.2",true);
        setup(5, "grass2.5",true);
        setup(7, "grass3.7",true);
        setup(8, "grass3.8",true);
        setup(9, "grass3.9",true);
        setup(10, "grass3.10",true);
        setup(11, "grass4.11",true);
        setup(12, "baum.12",true);
    }
    public void setup(int index,String path,boolean collision){

      scaling sc = new scaling();
      try {
          tile[index]= new Tile();
          tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/pictures_map/" + path + ".png")));
          tile[index].image = sc.scale(tile[index].image, gp.w_tileSize, gp.h_tileSize);
          tile[index].collision = collision;
      }catch (IOException e){
          e.printStackTrace();
      }
    }

    //Map wird geladen
    public void loadMap(int map){

        try {
            //Textdatei wird eingelesen
            InputStream is = getClass().getResourceAsStream("map/map"+map+".txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while (col < gp.maxScreenCol ){
                    String[] numbers = line.split(" ");
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
            g2.drawImage(tile[tileNum].image, x, y, gp.w_tileSize, gp.h_tileSize, null);
            col++;
            x += gp.w_tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.h_tileSize;
            }
        }
    }

    }

