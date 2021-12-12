import Hangman.Control.HController;
import Hangman.Control.HangmanController;
import Hangman.Model.HModel;
import Hangman.Model.HangmanModel;
import Hangman.View.HView;
import Hangman.View.HangmanView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runner for the Hangman game
 */
public class HangmanRunner {
  public static void main(String[] args) {
    ArrayList<String> wordList = new ArrayList<>();

    try {
      Scanner sc = new Scanner( new File("./res/words.txt"));
      while (sc.hasNext()) {
        wordList.add(sc.next());
      }
      sc.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    HModel model = new HangmanModel.Builder().generateSecretWord(wordList).build();
    HView view = new HangmanView();
    HController controller = new HangmanController(model, view);

    controller.play();
  }

}
