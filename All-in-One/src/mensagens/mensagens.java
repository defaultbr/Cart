package mensagens;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class mensagens {
	
	String INFO_ENVIADA = "Certid�o Recebida com Sucesso.";
	String ERROR_CONEXAO = "ERRO: Verifique se o servidor de certid�o est� ligado, \n Se voc� est� na rede, \n Tente novamente.";
	

	
	public void info_enviada() {
		new Thread(new Runnable(){  
			@Override  
			public void run(){  
				
				JOptionPane.showMessageDialog(null, "Certid�o Recebida.");

			
			}  
			}).start();
	}	
	public void error_conexao() {
		new Thread(new Runnable(){  
			@Override  
			public void run(){  
				JOptionPane.showMessageDialog(null, "ERRO: Verifique se o servidor de certid�o est� ligado, \n Se voc� est� na rede, \n Tente novamente.");
			}  
			}).start();
	}	
}
