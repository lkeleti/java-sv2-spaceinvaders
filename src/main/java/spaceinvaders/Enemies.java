package spaceinvaders;

import java.util.ArrayList;
import java.util.List;

public class Enemies {
    private int minX = 65536;
    private int maxX = 0;
    private int direction = 1;
    private int velocityX = 5;
    private int tileSize;
    private List<Spirit> enemyList = new ArrayList<>();

    public Enemies(int tileSize) {
        this.tileSize = tileSize;
    }

    public List<Spirit> getEnemyList() {
        return enemyList;
    }

    public void addEnemy(Spirit enemy) {
        enemyList.add(enemy);
    }

    public void removeEnemy(Spirit enemy) {
        enemyList.remove(enemy);
    }

    private void findMinMaxX(){
        minX = 65536;
        maxX = 0;
        for (Spirit enemy: enemyList) {
            if (enemy.getPosX() < minX) {
                minX = enemy.getPosX();
            }
            if (enemy.getPosX() + tileSize > maxX) {
                maxX = enemy.getPosX() + tileSize ;
            }
        }
    }

    private void moveDown() {
        for (Spirit enemy: enemyList) {
            enemy.setPosY(enemy.getPosY() + 5);
        }
    }

    public void changePosX(int panelWidth) {
        findMinMaxX();
        if (direction == 1 && maxX + (velocityX*direction) > panelWidth) {
            direction *= -1;
            moveDown();
        }
        if (direction == -1 && minX + (velocityX*direction) < 0) {
            direction *= -1;
            moveDown();
        }
        for (Spirit enemy: enemyList) {
            enemy.setPosX(enemy.getPosX()+(velocityX*direction));
        }
    }
}
