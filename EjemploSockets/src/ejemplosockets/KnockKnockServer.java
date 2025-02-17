/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilberto.borrego
 */
public class KnockKnockServer {

    private static final int PORT = 4444; 
    private static final int pool = 5; 

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(pool);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor KnockKnock iniciado en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Acepte a un cliente: " + clientSocket.getInetAddress());

                executor.execute(new KnockKnockClientManager(clientSocket));
            }
        } catch (IOException ex) {
            Logger.getLogger(KnockKnockServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            executor.shutdown();
        }
    }
}
