package cards_certidao;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import mensagens.mensagens;
import bancodedados.conexao;


public class enviaCertidao {
	String ipOrigem;
	String protocolo;
	String numero;
	String tipo;
	String autor;
	String livro;
	String ipDestino;
	boolean conectado = false;
	public void enviacaminho(String protocolo, String numero, String tipo, String nomeUsuario, String livro) {
		this.protocolo = protocolo;
		this.ipOrigem = ipOrigem;
		this.numero = numero;
		this.tipo = tipo;
		this.autor = nomeUsuario;
    	this.livro = livro;
   	 Requester client = new Requester();

	        client.run();
    	

    }
    class Requester{
	    Socket requestSocket;
	    ObjectOutputStream out;
	    ObjectInputStream in;
	    String message;
	    String recebida;
	    Requester(){}
	    void run()
	    
	    
	    
	    {
	    	
	    	
   
	    	
	      
	            
					try {
						requestSocket = new Socket(ipDestino, 2014);

						//requestSocket = new Socket("192.168.0.22", 2014);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "ERRO: Verifique se o servidor de certidão está ligado, \n Se você está na rede, \n Se os IPs estão corretos, \n Tente novamente.");
						e.printStackTrace();
					}


	            try {
					out = new ObjectOutputStream(requestSocket.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();

				}
	            try {
					in = new ObjectInputStream(requestSocket.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
          
						try {
							recebida = (String)in.readObject();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						} catch (IOException e) {

							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// TODO Auto-generated catch block
					
					ArrayList dados = new ArrayList();
					dados.add(protocolo);
					dados.add(numero);
					dados.add(tipo);
					dados.add(autor);
					try {
						dados.add(InetAddress.getLocalHost().getHostAddress());
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dados.add(livro);

					sendMessage(dados);
					mensagens msg = new mensagens();

					if(recebida.matches("1")) {
						msg.info_enviada();
					//	JOptionPane.showMessageDialog(null, "Certidão Recebida.");
					} else {
						msg.error_conexao();
					//	JOptionPane.showMessageDialog(null, "ERRO: Verifique se o servidor de certidão está ligado, \n Se você está na rede, \n Tente novamente.");
					}
	      
              
	        
	   
	    }
	    
	    void sendMessage(ArrayList dados)
	    {
	        try{
	            out.writeObject(dados);
	            out.flush();
	            //System.out.println("client>" + msg);
	        }
	        catch(IOException ioException){

	            ioException.printStackTrace();
	        }
	    }
	    
	    
	    
	   
	}
	public void setDestino(String nome) {
		System.out.println(nome);
		conexao pegaip = new conexao();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet results = null;
		try {
			conn = pegaip.abreconexao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try {
			String sql = "SELECT * FROM IPCertidoes WHERE nome=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, nome);
				results = ps.executeQuery();
				while(results.next()) {
					ipDestino = results.getString("ip");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	} finally {
		
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

}
		
		System.out.println(ipDestino);
	}
	

}
