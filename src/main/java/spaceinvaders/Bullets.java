package spaceinvaders;

import java.util.ArrayList;
import java.util.List;

public class Bullets {
    private List<Bullet> bulletList = new ArrayList<>();

    public void addBullet(Bullet bullet) {
        bulletList.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        bulletList.remove(bullet);
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void moveUp(int value) {
        for (Bullet bullet: bulletList) {
            bullet.setPosY(bullet.getPosY()-value);
        }
    }
}
