/*
This is the executable class with the main method for the tetris game project
Alfred Okonkwo
Chiamaka Onwude
Oluwatobiloba Onimole
CSC 282
11/28/2022
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisWindow extends JFrame {
    private JMenuBar topMenubar;
    private int win_width = 400;
    private int win_height = 600;
    private int game_rows = 20;
    private int game_cols = 12;
    private TetrisGame game;
    private TetrisDisplay display;
    public TetrisWindow() {

        game = new TetrisGame(game_rows, game_cols);
        display = new TetrisDisplay(game);

        menu_items();
        this.setTitle("Tetris Game");
        this.setSize(win_width, win_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(display);
        this.setVisible(true);
    }
    private void menu_items(){
        topMenubar = new JMenuBar();

        JMenu file_menu = new JMenu("File");
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                String file_Name = JOptionPane.showInputDialog("Enter the file "
                        + "name to save as \n");
                game.saveToFile(file_Name+".dat");
            }
        });
        JMenuItem retrieveGame = new JMenuItem("Retrieve Game ");
        retrieveGame.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                game.retrieveFromFile();
            }
        });
        JMenuItem new_game = new JMenuItem("New Game");
        new_game.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                game.newGame();
            }
        });

        file_menu.add(new_game);
        file_menu.add(saveGame);
        file_menu.add(retrieveGame);

        JMenu score_menu = new JMenu("Score");
        JMenuItem highScore = new JMenuItem("High Score");
        highScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, game.score_board("Score.dat"));
            }
        });
        JMenuItem clearScores = new JMenuItem("Clear Scores");
        clearScores.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                game.clearScore();
            }
        });
        score_menu.add(highScore);
        score_menu.add(clearScores);
        topMenubar.add(file_menu);
        topMenubar.add(score_menu);
        this.setJMenuBar(topMenubar);
        JPanel high_score = new JPanel();
        this.add(high_score, BorderLayout.WEST);
    }
    public static void main(String[] args) {
        new TetrisWindow();
    }
}
