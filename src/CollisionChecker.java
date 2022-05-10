public class CollisionChecker {

    UI_gamepanel gp;
    boolean[][] checkTower;

    public CollisionChecker(UI_gamepanel gp){
    this.gp = gp;
    checkTower = new boolean[gp.maxScreenCol][gp.maxScreenRow];
    }

    public void check(Entity entity,int index){

        int entityLX = (int)gp.ui.imageCorner[index].getX()+entity.solidarea.x;
        int entityRX = (int)gp.ui.imageCorner[index].getX()+entity.solidarea.x + entity.solidarea.width;
        int entityLY = (int)gp.ui.imageCorner[index].getY()+entity.solidarea.y;
        int entityRY = (int)gp.ui.imageCorner[index].getY()+entity.solidarea.y + entity.solidarea.height;

        int entityLC = entityLX/gp.w_tileSize;
        int entityRC = entityRX/gp.w_tileSize;
        int entityTR = entityLY/gp.h_tileSize;
        int entityBR = entityRY/gp.h_tileSize;

        int actualX = (int)gp.ui.imageCorner[index].getX()/gp.w_tileSize;
        int acutalY = (int)gp.ui.imageCorner[index].getY()/gp.h_tileSize;

        int tileNum1,tileNum2,tileNum3,tileNum4;

        if(entityLC < 17 && entityRC < 17 && entityTR < 12  && entityBR < 12){
        tileNum1 = gp.tileM.mapTileNum[entityLC][entityTR];
        tileNum2 = gp.tileM.mapTileNum[entityRC][entityTR];
        tileNum3 = gp.tileM.mapTileNum[entityLC][entityBR];
        tileNum4 = gp.tileM.mapTileNum[entityRC][entityBR];

        if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true || gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true
                || checkPosition(entityLC,entityTR) == true || checkPosition(entityRC,entityTR) == true || checkPosition(entityLC,entityBR) == true || checkPosition(entityRC,entityBR) == true){
            entity.collisionOn = true;
        }
    }
    }


    public void addPosition(int col,int row) {

        checkTower[col][row] = true;

    }

    public boolean checkPosition(int col, int row){

        return checkTower[col][row];

    }
}

