/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class KnockKnockSkeleton implements Runnable {

    private Socket clientSocket;
    private ILlamadaMetodos llamada = null;

    public KnockKnockSkeleton(Socket clientSocket, ILlamadaMetodos llamada) {
        this.clientSocket = clientSocket;
        this.llamada = llamada;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){ 
            String inputLine;
            String outputLine = llamada.LlamarMetodo(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = llamada.LlamarMetodo(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye.")) {
                    break;
                }
            }
        } catch (IOException e) {
            Logger.getLogger(KnockKnockSkeleton.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                Logger.getLogger(KnockKnockSkeleton.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}


