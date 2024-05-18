//Karlos Miguel P. Semilla

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends Frame implements ActionListener {
    private Button[][] buttons;
    private Label statusLabel;
    private Label scoreLabel;
    private int player1Score;
    private int player2Score;
    private boolean playerXTurn;
    private boolean gameEnded;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());
        setBackground(new Color(230, 230, 250));

        Panel gridPanel = new Panel(new GridLayout(3, 3));
        buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(new Color(255, 255, 204)); 
                gridPanel.add(buttons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        Panel statusPanel = new Panel(new FlowLayout());
        statusLabel = new Label("Player X's turn");
        statusPanel.add(statusLabel);

        Button newGameButton = new Button("New Game");
        newGameButton.addActionListener(e -> resetGame());
        statusPanel.add(newGameButton);

        add(statusPanel, BorderLayout.NORTH);

        Panel scorePanel = new Panel(new FlowLayout());
        scoreLabel = new Label("Player 1: 0  Player 2: 0");
        scorePanel.add(scoreLabel);

        add(scorePanel, BorderLayout.SOUTH);

        setSize(300, 300);
        setVisible(true);

        player1Score = 0;
        player2Score = 0;
        playerXTurn = true;
        gameEnded = false;
    }

    private void resetGame() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setEnabled(true);
                button.setLabel("");
            }
        }
        playerXTurn = true;
        gameEnded = false;
        statusLabel.setText("Player X's turn");
    }

    private void checkWinner() {
        
        String winner = "";
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getLabel().isEmpty() &&
                    buttons[i][0].getLabel().equals(buttons[i][1].getLabel()) &&
                    buttons[i][0].getLabel().equals(buttons[i][2].getLabel())) {
                winner = buttons[i][0].getLabel();
            }
            if (!buttons[0][i].getLabel().isEmpty() &&
                    buttons[0][i].getLabel().equals(buttons[1][i].getLabel()) &&
                    buttons[0][i].getLabel().equals(buttons[2][i].getLabel())) {
                winner = buttons[0][i].getLabel();
            }
        }
        if (!buttons[0][0].getLabel().isEmpty() &&
                buttons[0][0].getLabel().equals(buttons[1][1].getLabel()) &&
                buttons[0][0].getLabel().equals(buttons[2][2].getLabel())) {
            winner = buttons[0][0].getLabel();
        }
        if (!buttons[0][2].getLabel().isEmpty() &&
                buttons[0][2].getLabel().equals(buttons[1][1].getLabel()) &&
                buttons[0][2].getLabel().equals(buttons[2][0].getLabel())) {
            winner = buttons[0][2].getLabel();
        }

        if (!winner.isEmpty()) {
            gameEnded = true;
            statusLabel.setText("Player " + winner + " wins!");
            updateScore(winner);
            for (Button[] row : buttons) {
                for (Button button : row) {
                    button.setEnabled(false);
                }
            }
        } else {
            
            boolean draw = true;
            for (Button[] row : buttons) {
                for (Button button : row) {
                    if (button.getLabel().isEmpty()) {
                        draw = false;
                        break;
                    }
                }
            }
            if (draw) {
                gameEnded = true;
                statusLabel.setText("DRAW");
            }
        }
    }

    private void updateScore(String winner) {
        if (winner.equals("X")) {
            player1Score++;
        } else {
            player2Score++;
        }
        scoreLabel.setText("Player 1: " + player1Score + "  Player 2: " + player2Score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded) {
            return;
        }

        Button clickedButton = (Button) e.getSource();
        if (!clickedButton.getLabel().isEmpty()) {
            return;
        }

        clickedButton.setLabel(playerXTurn ? "X" : "O");
        playerXTurn = !playerXTurn;
        statusLabel.setText("Player " + (playerXTurn ? "X" : "O") + "'s turn");

        checkWinner();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}