package Hangman.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * This class represents the hangman drawing part of the game
 */
public class HangmanGraphics extends JPanel {
  private int wrongGuesses = 0;

  /**
   * Constructs the graphic panel
   */
  public HangmanGraphics() {
    super();
    this.setBackground(Color.white);
  }

  /**
   * The paintComponent method to draw the hangman
   * @param g graphics
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(new Color(112,112,112));

    //the hanging tree
    g2d.fillRect(40,0,100, 15);
    g2d.fillRect(40, 0, 15, 50);
    g2d.fillRect(140, 0,15,280);
    g2d.fillRect(0,280, 170,15);

    g2d.setStroke(new BasicStroke(6));

    if (wrongGuesses >=1 ) {
      //head
      g2d.drawOval(25,48,45, 45);
    }

    if (wrongGuesses >=2) {
      //body
      g2d.drawLine(48, 93, 48, 160);
    }

    if (wrongGuesses >=4) {
      //left arm
      g2d.drawLine(48, 120,5, 110);
    }

    if (wrongGuesses >=5) {
      //right arm
      g2d.drawLine(48, 120, 90, 110);
    }

    if (wrongGuesses >=6 ) {
      //left leg
      g2d.drawLine(48,160,20, 220);
    }

    if (wrongGuesses >=8) {
      //right leg
      g2d.drawLine(48, 160, 70, 220);
    }
  }

  /**
   * Sets the current wrongGuesses of the hangman
   * @param wrongGuesses the wrongGuesses of the hangman
   */
  public void setWrongGuesses(int wrongGuesses) {
    this.wrongGuesses = wrongGuesses;
  }
}
