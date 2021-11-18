package spaceinvaders;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    private GamePanel gamePanel;
    public GameFrame() {
        gamePanel = new GamePanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                gamePanel.setLeftDown(true);
                break;
            }

            case KeyEvent.VK_RIGHT: {
                gamePanel.setRightDown(true);
                break;
            }

            case KeyEvent.VK_SPACE: {
                gamePanel.setSpaceDown(true);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                gamePanel.setLeftDown(false);
                break;
            }

            case KeyEvent.VK_RIGHT: {
                gamePanel.setRightDown(false);
                break;
            }

            case KeyEvent.VK_SPACE: {
                gamePanel.setSpaceDown(false);
                break;
            }
            default:
                break;
        }
    }
}
