public class path {
    //Instanzen
    UI_gamepanel gp;
    Gegner gg;
    //In Konstruktor wird eine Matrix erstellt.
    public int tileNum1, tileNum2, tileNum3, tileNum4;
    public path(UI_gamepanel gp) {
        this.gp = gp;
    }
    //Hier wird überprüft ob der Mauscursor über den jeweiligen Bereich gleitet
    public void check(Entity entity,int x,int y) {

        int entityLX = (int) x + entity.solidArea.x;
        int entityRX = (int) x + entity.solidArea.x + entity.solidArea.width;
        int entityLY = (int) y + entity.solidArea.y;
        int entityRY = (int) y + entity.solidArea.y + entity.solidArea.height;

        int entityLC = entityLX / gp.w_TileSize;
        int entityRC = entityRX / gp.w_TileSize;
        int entityTR = entityLY / gp.h_TileSize;
        int entityBR = entityRY / gp.h_TileSize;


        //Überprüft, ob in dem gezeichneten bereich befindet
        if (entityLC < 17 && entityRC < 17 && entityTR < 12 && entityBR < 12) {
            tileNum1 = gp.tileM.mapTileNum[entityLC][entityTR];
            tileNum2 = gp.tileM.mapTileNum[entityRC][entityTR];
            tileNum3 = gp.tileM.mapTileNum[entityLC][entityBR];
            tileNum4 = gp.tileM.mapTileNum[entityRC][entityBR];
            System.out.println(tileNum1+":1-"+tileNum2+":2-"+tileNum3+":3-"+tileNum4+":4");

        }


    }
}

