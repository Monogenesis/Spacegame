package spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.SerializablePermission;

import spacegame.Game.STATE;
import spacegame.controller.Controller;
import spacegame.controller.helper.Score;
import spacegame.gameobjects.Player;

public class ScoreMenu extends Menu implements PropertyChangeListener {

    public static String labelText = "SCORE";
    public final static String menuText = "MENU";
    public final static String restartText = "RESTART";
    public final static Font lastScoreFont = new Font("arial", Font.BOLD, 18);
    public final static Font highscoreHeaderFont = new Font("arial", Font.BOLD, 25);
    public final static Font highscoreTableFont = new Font("arial", Font.BOLD, 19);

    private MenuButton restartButton;
    private MenuButton menuButton;
    String lastScoreText;
    int lastScoreY, highscoresY;
    int allHighscoresY;
    boolean lastScoreIsHighscore;
    int lastScoreIndex;
    String[] splitHighscores;

    public ScoreMenu(String label, Controller controller) {
        super(label, controller, MainMenu.menuLabelFont, MainMenu.labelColor);
        // menuButtons.add(restartButton = new MenuButton(0, 380, restartText, true));
        menuButtons.add(menuButton = new MenuButton(0, 430, menuText, true));
        updateTextLocations();

    }

    void updateTextLocations() {

        lastScoreText = "last score: " + String.valueOf(Player.scoreValue);
        lastScoreY = MainMenu.getTextWorldCenterXPos(lastScoreFont, lastScoreText);
        highscoresY = MainMenu.getTextWorldCenterXPos(highscoreHeaderFont, "Highscores");
        lastScoreIndex = -1;
        String highsccores = "";
        for (int i = 0; i < Score.scores.size(); i++) {
            Score score = Score.scores.get(i);
            if (i == 9)
                break;
            if (score.isLastPlayerScore()) {
                lastScoreIndex = i;
                if (i == 0) {
                    lastScoreIsHighscore = true;
                }
            }
            highsccores += (i + 1) + ".  ";
            highsccores += score.value;
            int spaceLength = 4;
            System.out.println((spaceLength - String.valueOf(score.value).length()));
            for (int j = 0; j < (spaceLength - String.valueOf(score.value).length()); j++) {
                highsccores += "  ";
            }
            highsccores += score.date;
            highsccores += "\n";
            // highsccores += String.format("%s. %s %s\n", score.id, score.value,
            // score.date);

        }
        splitHighscores = highsccores.split("\n");
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {

        if (e.getNewValue().equals(STATE.Score)) {
            System.out.printf("Property '%s': '%s' -> '%s'%n", e.getPropertyName(), e.getOldValue(), e.getNewValue());
            updateTextLocations();
        }

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
        g.drawString(lastScoreText, lastScoreY, 130);

        g.setFont(highscoreHeaderFont);
        g.setColor(Color.WHITE);
        g.drawString("Highscores", highscoresY, 160);

        g.setFont(highscoreTableFont);
        allHighscoresY = 170;

        for (int i = 0; i < splitHighscores.length; i++) {
            if (i == lastScoreIndex) {
                if (i == 0 && lastScoreIsHighscore) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GREEN);
                }
            } else {
                g.setColor(Color.BLUE);
            }
            g.drawString(splitHighscores[i], 240, allHighscoresY += g.getFontMetrics().getHeight());
        }

    }

}