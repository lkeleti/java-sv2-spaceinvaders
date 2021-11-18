package spaceinvaders;

import javax.swing.*;
import java.awt.*;

public class Spirit {
    private int posX;
    private int posY;
    private Image image;
    private int tileSize;

    public Spirit(int posX, int posY, int typeOfSpirit, int tileSize) {
        this.posX = posX;
        this.posY = posY;
        this.tileSize = tileSize;
        switch (typeOfSpirit) {
            case 0: {
                image = new ImageIcon("src/main/resources/spaceinvaders/spaceship.png").getImage();
                break;
            }
            case 1: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy01.png").getImage();
                break;
            }
            case 2: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy02.png").getImage();
                break;
            }
            case 3: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy03.png").getImage();
                break;
            }
            case 4: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy04.png").getImage();
                break;
            }
            case 5: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy05.png").getImage();
                break;
            }
            case 6: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy06.png").getImage();
                break;
            }
            case 7: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy07.png").getImage();
                break;
            }
            case 8: {
                image = new ImageIcon("src/main/resources/spaceinvaders/enemy08.png").getImage();
                break;
            }
            default:
                break;
        }
    }

    public Image getImage() {
        return image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean checkCollision(Bullet bullet) {
        return (bullet.getPosX() - 1 >= posX && bullet.getPosX() + 1 <= posX + tileSize && bullet.getPosY() - 10 <= posY + tileSize && bullet.getPosY()-10 >= posY);
    }
}
