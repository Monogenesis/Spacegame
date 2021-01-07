package spacegame.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.awt.Font;
import spacegame.controller.Controller;
import spacegame.controller.helper.Score;
import spacegame.gameobjects.Player;

public class ScoreMenu extends Menu {

    public static String labelText = "SCORE";
    public final static String menuText = "MENU";
    public final static String restartText = "RESTART";
    public final static Font lastScoreFont = new Font("arial", Font.BOLD, 18);
    public final static Font highscoreHeaderFont = new Font("arial", Font.BOLD, 25);
    public final static Font highscoreTableFont = new Font("arial", Font.BOLD, 19);

    private MenuButton restartButton;
    private MenuButton menuButton;

    public ScoreMenu(String label, Controller controller) {
        super(label, controller, MainMenu.menuLabelFont, MainMenu.labelColor);
        // menuButtons.add(restartButton = new MenuButton(0, 380, restartText, true));
        menuButtons.add(menuButton = new MenuButton(0, 430, menuText, true));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.orange);
        g.setFont(lastScoreFont);
        String lastScoreText = "last score: " + String.valueOf(Player.score);
        g.drawString(lastScoreText, MainMenu.getTextWorldCenterXPos(lastScoreFont, lastScoreText), 130);

        g.setFont(highscoreHeaderFont);
        g.setColor(Color.WHITE);
        g.drawString("Highscores", MainMenu.getTextWorldCenterXPos(highscoreHeaderFont, "Highscores"), 160);

        g.setFont(highscoreTableFont);
        int startHeight = 200;
        for (int i = 0; i < Score.scores.size(); i++) {
            if (i == 10)
                break;
            if (Score.scores.get(i).isLastPlayerScore()) {
                if (i == 0)
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.drawString((i + 1) + ".", 240, startHeight);
            g.drawString(String.valueOf(Score.scores.get(i).value), 270, startHeight);
            g.drawString(Score.scores.get(i).date, 330, startHeight);
            startHeight += 22;
        }

    }

}