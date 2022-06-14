public class path {
    //Instanzen
    UI_gamepanel gp;
    //In Konstruktor wird eine Matrix erstellt.
    public int entityLC,entityRC,entityTR,entityBR,mitteX,mitteY,mitte,savei;
    public path(UI_gamepanel gp) {
        this.gp = gp;
    }
    //Hier wird überprüft ob der Mauscursor über den jeweiligen Bereich gleitet
    public void check(Entity entity,int x,int y) {

        int entityLX = (int) x + entity.solidArea.x;
        int entityRX = (int) x + entity.solidArea.x + entity.solidArea.width;
        int entityLY = (int) y + entity.solidArea.y;
        int entityRY = (int) y + entity.solidArea.y + entity.solidArea.height;

        entityLC = entityLX / gp.w_TileSize;
        entityRC = entityRX / gp.w_TileSize;
        entityTR = entityLY / gp.h_TileSize;
        entityBR = entityRY / gp.h_TileSize;
        mitteX = (x + entity.solidArea.x + entity.solidArea.width/2) / gp.w_TileSize ;
        mitteY = (y + entity.solidArea.y + entity.solidArea.height/2) / gp.h_TileSize ;

        //Überprüft, ob in dem gezeichneten bereich befindet
        if (entityLC < 17 && entityRC < 17 && entityTR < 12 && entityBR < 12) {
            mitte = gp.tileM.mapTileNum[mitteX][mitteY];
        }
    }

    public int checkstart(){
        for(int i = 0;i<=gp.maxScreenRow;i++){
            if(gp.tileM.mapTileNum[0][i] == 2){
                savei = i;
                break;
            }
        }
        return savei;
    }
}

