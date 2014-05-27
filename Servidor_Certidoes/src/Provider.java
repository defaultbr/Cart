import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Provider implements Runnable {
   // ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    String recebida = "1";
    ObjectInputStream in;
    ArrayList pedido;
    Provider(Socket s, receptor receptor){this.connection=s;}
    
   
   
    
    public void run() {
            try {
				out = new ObjectOutputStream(this.connection.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //out.flush();
           sendMessage("1");
            try {
				in = new ObjectInputStream(connection.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Certidão de: " + connection.getInetAddress().getHostName());
            	
             
                    try {
						pedido =  (ArrayList) in.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                   System.out.println("Certidão: " + pedido.get(1));
                   
                   receptor.lista(pedido);
                
                   
                   /*
                   try {
				Desktop.getDesktop().print(new File(caminhodoarquivo));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
*/
                   
                   
                  
                }
    
    

    
    


    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }


}