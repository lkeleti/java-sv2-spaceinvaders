package spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static spaceinvaders.GameDatas.*;

public class GamePanel extends JPanel implements ActionListener {
    static final int PANEL_WIDTH = 1024;
    static final int PANEL_HEIGHT = 768;
    boolean leftDown = false;
    boolean rightDown = false;
    boolean spaceDown = false;

    private Timer timer;
    private Enemies enemies;
    private final Spirit player;
    private Bullets bullets;
    private int spaceRepeat = 10;
    private boolean endOfGame = false;

    GamePanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        enemies = new Enemies();
        player = new Spirit(PANEL_WIDTH/2,PANEL_HEIGHT-TILE_SIZE,0);
        bullets = new Bullets();

        for (int i = 0; i < PANEL_WIDTH-150; i += 70) {
            for (int j = 0; j < 8; j++) {
                enemies.addEnemy(new Spirit(i, j*70, j+1));
            }
        }

        timer = new Timer(10,this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

            Graphics2D g2d = (Graphics2D) g;
        if (!endOfGame) {
            for (Spirit enemy : enemies.getEnemyList()) {
                g2d.drawImage(enemy.getImage(), enemy.getPosX(), enemy.getPosY(), null);
            }

            for (Bullet bullet : bullets.getBulletList()) {
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(bullet.getPosX(), bullet.getPosY(), bullet.getPosX(), bullet.getPosY() - 10);
            }

            g2d.drawImage(player.getImage(), player.getPosX(), player.getPosY(), null);
        }
        else {
            g2d.setColor(Color.YELLOW);
            Font font = new Font("Serif", Font.BOLD, 86);
            g2d.setFont(font);
            FontMetrics metrics = g.getFontMetrics(font);
            String winText = "YOU WIN!!!";
            int stringX = (PANEL_WIDTH - metrics.stringWidth(winText)) / 2;
            int stringY = ((PANEL_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();

            g2d.drawString(winText, stringX, stringY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        enemies.changePosX(PANEL_WIDTH);
        if (enemies.getEnemyList().isEmpty()) {
            if (leftDown) {
                if (player.getPosX() - 5 > 0) {
                    player.setPosX(player.getPosX() - 5);
                } else {
                    player.setPosX(0);
                }
            }
            if (rightDown) {
                if (player.getPosX() + 5 < PANEL_WIDTH - TILE_SIZE) {
                    player.setPosX(player.getPosX() + 5);
                } else {
                    player.setPosX(PANEL_WIDTH - 50);
                }
            }

            bullets.moveUp(5);
            //check collision
            checkCollision();

            if (spaceDown) {
                spaceRepeat -= 1;
                if (spaceRepeat == 0) {
                    bullets.addBullet(new Bullet(player.getPosX() + TILE_SIZE / 2, player.getPosY()));
                    spaceRepeat = 10;
                }
            }
        }
        else{
            endOfGame = true;
        }
        repaint();
    }

    public void checkCollision(){
        List<Spirit> enemiesToRemove = new ArrayList<>();
        List<Bullet> bulletsToRemove = new ArrayList<>();

        for (Bullet bullet: bullets.getBulletList()){
            for (Spirit enemy: enemies.getEnemyList()){
                if (enemy.checkCollision(bullet)) {
                    enemiesToRemove.add(enemy);
                    bulletsToRemove.add(bullet);
                }
            }

            if (bullet.getPosY() < 0) {
                bulletsToRemove.add(bullet);
            }
        }
        removeEnemiesAndBullets(enemiesToRemove, bulletsToRemove);
    }

    public void removeEnemiesAndBullets( List<Spirit> enemiesToRemove, List<Bullet> bulletsToRemove) {
        for (Spirit spirit: enemiesToRemove)
        {
            enemies.removeEnemy(spirit);
        }

        for (Bullet bullet: bulletsToRemove)
        {
            bullets.removeBullet(bullet);
        }
    }

    public Spirit getPlayer() {
        return player;
    }

    public boolean isLeftDown() {
        return leftDown;
    }

    public void setLeftDown(boolean leftDown) {
        this.leftDown = leftDown;
    }

    public boolean isRightDown() {
        return rightDown;
    }

    public void setRightDown(boolean rightDown) {
        this.rightDown = rightDown;
    }

    public boolean isSpaceDown() {
        return spaceDown;
    }

    public void setSpaceDown(boolean spaceDown) {
        this.spaceDown = spaceDown;
    }
}
