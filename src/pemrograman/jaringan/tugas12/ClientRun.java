/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pemrograman.jaringan.tugas12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Somad
 */
public class ClientRun implements Runnable{

    private Client client;
    private TampilanPort portView;
    private TampilanChat view;
    private String mess="";
    
    public ClientRun(){
        this.client = new Client();
        this.portView = new TampilanPort();
        this.view = new TampilanChat();
        this.portView.setTitle("Port Input");
        this.portView.setVisible(true);
        
        this.portView.getBtnOk().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(String.valueOf(portView.getPort().getText()).equals("6666")){
                    client.startConnection("127.0.0.1", Integer.valueOf(portView.getPort().getText()));
                    portView.setVisible(false);
                    view.setTitle("chat");
                    view.setVisible(true);
                }else {
                    portView.getPort().setText("");
                }
            }
            
        });
        
        this.view.getKirim().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mess += time()+"=>"+String.valueOf(view.getTextChat().getText()+" ");
                String response = client.sendMessage(time()+"<=" +String.valueOf(view.getTextChat().getText()));
                mess += response +" \n";
                System.out.println("\n");
            }
        });
    }
    public String time(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = sdf.format(date);
        return time;
    }
        
        @Override
        public void run(){
            do{
                if(this.view.getChat().getText().equals(mess) == false){
                    this.view.getChat().setText(mess);
                }
            }while(true);
        }
        public static void main(String[] args){
            new Thread(new ClientRun()).start();
        }
}
