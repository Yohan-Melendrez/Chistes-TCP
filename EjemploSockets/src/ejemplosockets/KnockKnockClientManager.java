/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnockKnockClientManager implements Runnable {
    private Socket clientSocket;
    private KnockKnockProtocol kkp;
    
    public KnockKnockClientManager(Socket c){
        this.clientSocket = c;
        this.kkp = new KnockKnockProtocol();
    }
    
    @Override
    public void run() {
        try {
            PrintWriter out = null;
            BufferedReader in = null;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            String inputLine, outputLine;            
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }   out.close();
            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(KnockKnockClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
