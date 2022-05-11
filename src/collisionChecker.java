public class collisionChecker {
    UI_gamepanel gp;
    boolean[][] checkTower;

    public collisionChecker(UI_gamepanel gp) {
        this.gp = gp;
        checkTower = new boolean[gp.maxScreenCol][gp.maxScreenRow];
    }

    public void check(Entity entity, int index) {

        int entityLX = (int) gp.ui.imageCorner[index].getX() + entity.solidarea.x;
        int entityRX = (int) gp.ui.imageCorner[index].getX() + entity.solidarea.x + entity.solidarea.width;
        int entityLY = (int) gp.ui.imageCorner[index].getY() + entity.solidarea.y;
        int entityRY = (int) gp.ui.imageCorner[index].getY() + entity.solidarea.y + entity.solidarea.height;

        int entityLC = entityLX / gp.w_tileSize;
        int entityRC = entityRX / gp.w_tileSize;
        int entityTR = entityLY / gp.h_tileSize;
        int entityBR = entityRY / gp.h_tileSize;
        int tileNum1, tileNum2, tileNum3, tileNum4;

        //Überprüft ob in dem gezeichneten bereich befindet
        if (entityLC < 17 && entityRC < 17 && entityTR < 12 && entityBR < 12) {
            tileNum1 = gp.tileM.mapTileNum[entityLC][entityTR];
            tileNum2 = gp.tileM.mapTileNum[entityRC][entityTR];
            tileNum3 = gp.tileM.mapTileNum[entityLC][entityBR];
            tileNum4 = gp.tileM.mapTileNum[entityRC][entityBR];

            //Die ersten 4 Abfrgagen sind für die gezeichnenten Tiles, die nächsten 4 sind für die Tower die aufgestellt wurden.
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || gp.tileM.tile[tileNum3].collision || gp.tileM.tile[tileNum4].collision
                    || checkPosition(entityLC, entityTR) || checkPosition(entityRC, entityTR) || checkPosition(entityLC, entityBR) || checkPosition(entityRC, entityBR)) {
                entity.collisionOn = true;
            }
        }
    }

    //Wenn ein neu aufgestellt wird, wird eine die Position auf true gesetzt.
    public void addPosition(int col, int row) {
        checkTower[col][row] = true;
    }

    //Die Position wird überprüft.
    public boolean checkPosition(int col, int row) {
        return checkTower[col][row];
    }

    //Wenn ein gelöscht wird, wird eine die Position auf false gesetzt.
    public void delPosition(int col, int row) {
        checkTower[col][row] = false;
    }
}

