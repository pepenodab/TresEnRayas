package net.salesianos.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import net.salesianos.utils.Constant;

public class ClientApp {
  public static Scanner SC = new Scanner(System.in);

  public static void main(String[] args) throws UnknownHostException, IOException {
    Socket socket = new Socket("localhost", Constant.SERVER_PORT);
    DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    while (true) {
      try {

        System.out.println(inputStream.readUTF());

        while (true) {
          String serverMessage = inputStream.readUTF();

          if (serverMessage.contains("Tu turno")) {
            System.out.println(serverMessage);
            System.out.println("Elige una casilla: ");
            int opcion = SC.nextInt();
            outputStream.writeInt(opcion);
            outputStream.flush();
          } else if (serverMessage.contains("Ganador") || serverMessage.contains("Empate")) {
            System.out.println(serverMessage);
            break;
          } else {
            System.out.println(serverMessage);
          }
        }

        inputStream.close();
        outputStream.close();
        socket.close();
        SC.close();
      } catch (InputMismatchException e) {
        System.out.println("Mi colega debes introducir un numero");
        outputStream.writeInt(10);
        outputStream.flush();
      }
    }

  }
}
