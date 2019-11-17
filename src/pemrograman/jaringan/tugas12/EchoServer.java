/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pemrograman.jaringan.tugas12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Somad
 */
public class EchoServer {
    public ServerSocket serverSocket;
    private Socket clienSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    
    
    public void start(int port){
        try{
            serverSocket = new ServerSocket(port);
            clienSocket = serverSocket.accept();
            out = new PrintWriter(clienSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
            
            String inputLine;
            while((inputLine = in.readLine()) != null){
                out.println(inputLine);
            }
        } catch (IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop(){
        try{
            in.close();
            out.close();
            serverSocket.close();
        }catch(IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        EchoServer server = new EchoServer();
        server.start(6666);
    }
}
