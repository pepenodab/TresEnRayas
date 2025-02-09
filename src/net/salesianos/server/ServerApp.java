package net.salesianos.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import net.salesianos.server.thread.ClientHandler;
import net.salesianos.utils.Constant;
import net.salesianos.utils.Game;

public class ServerApp {
  public static Game GAME = new Game();

  public static void main(String[] args) throws IOException, InterruptedException {
    try (ServerSocket serverSocket = new ServerSocket(Constant.SERVER_PORT)) {
      GAME.initBoard();

      Socket clientSocket = serverSocket.accept();
      Socket clientSocket2 = serverSocket.accept();

      ClientHandler clientHandler = new ClientHandler(clientSocket, GAME, clientSocket2);

      clientHandler.start();
    }

  }
}
