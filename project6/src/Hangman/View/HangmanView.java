package Hangman.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * This class represents the Hangman game view
 */
public class HangmanView extends JFrame implements HView {

  private JButton guessButton;
  private HangmanGraphics hangmanGraphics;
  private JTextField input;
  private JLabel gameLogDisplay, displayWord, wrongGuesses;
  private String gameLog;
  private JScrollBar vertical;

  /**
   * Constructs the basic layouts of the view
   */
  public HangmanView() {
    super();
    this.setTitle("Hangman");
    this.setSize(580, 750);
    this.setMinimumSize(new Dimension(580, 750));
    this.setMaximumSize(new Dimension(580, 750));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.gameLog = "";
    //set up the title text
    JLabel title = new JLabel("<html><h1 style='color:#1400FF'>Hangman</h1></html>");
    title.setBorder(new EmptyBorder(15,25,15,15));
    this.getContentPane().setBackground(Color.white);
    this.add(title, BorderLayout.NORTH);
    //set up the center items
    setupCenterPanel();
    //set up the bottom panel
    JPanel bottomPanel = new JPanel();
    JLabel instructionText = new JLabel("<html><div style='font-size:14'>Make your guess below:</div></html>");
    JPanel buttonPanel = buttonPanel();
    bottomPanel.setBackground(Color.white);
    bottomPanel.setBorder(new EmptyBorder(10,25,25,25));
    bottomPanel.setLayout(new BorderLayout());
    bottomPanel.add(instructionText, BorderLayout.CENTER);
    bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
    this.add(bottomPanel, BorderLayout.SOUTH);
  }

  /**
   * Helper method to set up the layout in the center
   */
  private void setupCenterPanel() {
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout());
    centerPanel.setBackground(Color.white);
    this.add(centerPanel, BorderLayout.CENTER);

    JScrollPane logScroll = logScrollPanel();
    centerPanel.add(logScroll);

    JPanel graphicsPanel = graphicsPanel();
    centerPanel.add(graphicsPanel);
  }

  /**
   * Helper method that sets up and returns the scroll panel of game log
   * @return the scroll panel of game log
   */
  private JScrollPane logScrollPanel() {
    gameLogDisplay = new JLabel();
    JScrollPane logScroll = new JScrollPane(gameLogDisplay);
    vertical = logScroll.getVerticalScrollBar();

    logScroll.setBorder(new EmptyBorder(15,15,15,15));
    logScroll.setPreferredSize(new Dimension(300, 450));

    return logScroll;
  }

  /**
   * Helper method that sets up all the graphics part and returns the graphics panel on the right
   * @return the graphics panel on the right
   */
  private JPanel graphicsPanel() {
    JPanel graphicsPanel = new JPanel();
    graphicsPanel.setLayout(new BorderLayout());
    graphicsPanel.setBackground(Color.white);
    graphicsPanel.setBorder(new EmptyBorder(15,15,15,15));

    hangmanGraphics = new HangmanGraphics();
    hangmanGraphics.setPreferredSize(new Dimension(200, 320));
    displayWord = new JLabel();
    wrongGuesses = new JLabel();

    graphicsPanel.add(hangmanGraphics, BorderLayout.NORTH);
    graphicsPanel.add(displayWord, BorderLayout.CENTER);
    graphicsPanel.add(wrongGuesses, BorderLayout.SOUTH);
    return graphicsPanel;
  }

  /**
   * Sets up and returns the button and input
   * @return the button panel with input and guess button
   */
  private JPanel buttonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BorderLayout());
    buttonPanel.setBackground(Color.white);
    buttonPanel.setBorder(new EmptyBorder(10,0,0,0));

    input = new JTextField();
    Dimension size = new Dimension(400, 40);
    input.setBorder(new LineBorder(new Color(112,112,112), 2));
    input.setPreferredSize(size);

    input.setFont(new Font("SansSerif", Font.PLAIN, 14));
    buttonPanel.add(input, BorderLayout.CENTER);

    guessButton = new JButton("<html><h2 style='color:#505050'>Guess</h2></html>");
    guessButton.setBorder(new LineBorder(new Color(112,112,112), 2));
    guessButton.setPreferredSize(new Dimension(90,40));
    buttonPanel.add(guessButton, BorderLayout.EAST);

    return buttonPanel;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }


  @Override
  public String getGuess() {
    String guess = this.input.getText();
    this.input.setText("");
    return guess;
  }

  @Override
  public void appendGameLog(String log) {
    gameLog += String.format("<div>%s</div>",log) ;
    String htmlText = String.format("<html><div style='font-size:11px'>%s</div></html>", gameLog);
    gameLogDisplay.setText(htmlText);
    vertical.setValue( vertical.getMaximum() -1 );
  }

  @Override
  public void updateWordDisplay(String word) {
    String htmlText = String.format("<html><h1 style='marginBottom:10px; color:#1400FF'>%s</h1></html>", word);
    displayWord.setText(htmlText);
  }

  @Override
  public void updateCanvas(int stage) {
    hangmanGraphics.setWrongGuesses(stage);
    hangmanGraphics.repaint();
  }

  @Override
  public void updateMissedDisplay(List<Character> missedChars) {
    StringBuilder result = new StringBuilder("Misses: ");
    for (Character missedChar : missedChars) {
      result.append(missedChar.toString().toUpperCase()).append(" ");
    }
    wrongGuesses.setText(result.toString());
  }

  @Override
  public void setGuessListener(ActionListener listener) {
    guessButton.addActionListener(listener);
  }
}
