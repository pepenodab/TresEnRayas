package net.salesianos.server.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import net.salesianos.utils.Game;

public class ClientHandler extends Thread {

  private Socket playerOne;
  private Socket playerTwo;
  private Game GAME;
  private boolean turn = true;

  public ClientHandler(Socket socketOne, Game game, Socket socketTwo) {
    this.playerOne = socketOne;
    this.playerTwo = socketTwo;
    this.GAME = game;
  }

  /**
   * This function manage the game
   */

  @Override
  public void run() {
    try {
      DataOutputStream outputStream1 = new DataOutputStream(new BufferedOutputStream(playerOne.getOutputStream()));
      DataOutputStream outputStream2 = new DataOutputStream(new BufferedOutputStream(playerTwo.getOutputStream()));
      DataInputStream inputStream1 = new DataInputStream(new BufferedInputStream(playerOne.getInputStream()));
      DataInputStream inputStream2 = new DataInputStream(new BufferedInputStream(playerTwo.getInputStream()));

      outputStream1.writeUTF("Bienvenido al Tres En Rayas :), eres el jugador X");
      outputStream1.flush();
      outputStream2.writeUTF("Bienvenido al Tres En Rayas :), eres el jugador O");
      outputStream2.flush();

      while (GAME.getKeepPlaying()) {
        if (turn) {
          // playerOne
          outputStream1.writeUTF("Tu turno. \n" + GAME.boardToString());
          outputStream1.flush();
          outputStream2.writeUTF("Espera el turno del otro jugador...");
          outputStream2.flush();

          int playerOption = inputStream1.readInt();
          if (!(0 < playerOption && playerOption < 10)) {
            outputStream1.writeUTF("Mi colega por listo perdiste el turno, SOLO PUEDES ENTRE 1 Y 9 !!!");
            outputStream1.flush();
          } else {
            GAME.play(Integer.toString(playerOption), 1);
          }

          String result = GAME.checkPosition();
          if (!GAME.getKeepPlaying()) {
            outputStream1.writeUTF(result);
            outputStream1.flush();
            outputStream2.writeUTF(result);
            outputStream2.flush();
            break;
          }

          turn = false;
        } else {
          // playerTwo
          outputStream2.writeUTF("Tu turno. \n" + GAME.boardToString());
          outputStream2.flush();
          outputStream1.writeUTF("Espera el turno del otro jugador...");
          outputStream1.flush();

          int playerOption = inputStream2.readInt();
          if (!(0 < playerOption && playerOption < 10)) {
            outputStream2.writeUTF("Mi colega por listo perdiste el turno, SOLO PUEDES ENTRE 1 Y 9 !!!");
            outputStream2.flush();
          } else {
            GAME.play(Integer.toString(playerOption), 2);
          }

          String result = GAME.checkPosition();
          if (!GAME.getKeepPlaying()) {
            outputStream1.writeUTF(result);
            outputStream1.flush();
            outputStream2.writeUTF(result);
            outputStream2.flush();
            break;
          }

          turn = true;
        }
      }
      outputStream1.close();
      outputStream2.close();
      inputStream1.close();
      inputStream2.close();
      playerOne.close();
      playerTwo.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}