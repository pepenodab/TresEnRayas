package net.salesianos.utils;

import java.util.ArrayList;

public class Game {

  private ArrayList<String> board = new ArrayList<>();
  private boolean keepPlaying = true;
  private String message = "";

  /**
   * This function it's to play the game, receives a number that marks the
   * corresponding position
   * 
   * @param option This is the position on board to mark
   * @param id     This id its to control the player who plays
   * @throws InterruptedException
   */

  public synchronized void play(String option, int id) throws InterruptedException {
    if (emptyCell(Integer.parseInt(option))
        && 1 <= Integer.parseInt(option)
        && Integer.parseInt(option) <= 9) {
      String symbol = (id == 1) ? "X" : "O";
      board.set(Integer.parseInt(option) - 1, symbol);
    } else if (Integer.parseInt(option) == 10) {
      board.set(Integer.parseInt(option), "");
    }
  }

  /**
   * This function turn array value to a string with a board format
   * 
   * @return A string with a Tic-Tac-Toe board format
   * 
   */

  public String boardToString() {
    String message = "┌───╤───╤───┐ \n" +
        "│ " + this.board.get(0) + " │ " + this.board.get(1) + " │ " + this.board.get(2) + " │\n" +
        "│ " + this.board.get(3) + " │ " + this.board.get(4) + " │ " + this.board.get(5) + " │\n" +
        "│ " + this.board.get(6) + " │ " + this.board.get(7) + " │ " + this.board.get(8) + " │\n" +
        "└───┴───┴───┘";

    return message;
  }

  /**
   * This function initialize board to get number 1 to 9
   */

  public void initBoard() {
    for (int i = 0; i < 11; i++) {
      this.board.add(i, Integer.toString(i + 1));
    }

  }

  /**
   * This function check every position board
   * 
   * @return a boolean if its empty or not
   */

  public boolean emptyCells() {
    for (String cell : board) {
      if (cell.matches("[1-9]")) {
        return true;
      }
    }
    return false;
  }

  /**
   * This function check if the option selected its empty or not.
   * 
   * @param option the index of board to check
   * @return a boolean if its empty or not
   */

  public boolean emptyCell(int option) {
    if (!board.get(option - 1).matches("[1-9]")) {
      return false;
    }
    return true;
  }

  /**
   * This function check the positions board to know if the game end or keep
   * running
   * 
   * @return A message with the decision on whether the game continues or ends.
   */

  public String checkPosition() {
    if ((board.get(0).equals(board.get(1))) && (board.get(1).equals(board.get(2)))) {
      keepPlaying = false;
      return "Ganador " + board.get(1);
    }

    if ((board.get(3).equals(board.get(4))) && (board.get(4).equals(board.get(5)))) {
      keepPlaying = false;
      return "Ganador " + board.get(4);
    }

    if ((board.get(6).equals(board.get(7))) && (board.get(7).equals(board.get(8)))) {
      keepPlaying = false;
      return "Ganador " + board.get(7);
    }

    if ((board.get(0).equals(board.get(3))) && (board.get(3).equals(board.get(6)))) {
      keepPlaying = false;
      return "Ganador " + board.get(3);
    }

    if ((board.get(1).equals(board.get(4))) && (board.get(4).equals(board.get(7)))) {
      keepPlaying = false;
      return "Ganador " + board.get(4);
    }

    if ((board.get(2).equals(board.get(5))) && (board.get(5).equals(board.get(8)))) {
      keepPlaying = false;
      return "Ganador " + board.get(5);
    }

    if ((board.get(0).equals(board.get(4))) && (board.get(4).equals(board.get(8)))) {
      keepPlaying = false;
      return "Ganador " + board.get(4);
    }

    if ((board.get(2).equals(board.get(4))) && (board.get(4).equals(board.get(6)))) {
      keepPlaying = false;
      return "Ganador " + board.get(4);
    }

    if (emptyCells() == false) {
      keepPlaying = false;
      return "Empate mis kinkis";
    }

    return "La partida continua...";
  }

  /**
   * This functions return the boolean field
   * 
   * @return A boolean to know if game keep running
   */

  public boolean getKeepPlaying() {
    return this.keepPlaying;
  }

  /**
   * getMessage(): This functions return the message of game
   * 
   * @return A string with a message from game
   */

  public String getMessage() {
    return this.message;
  }

}
