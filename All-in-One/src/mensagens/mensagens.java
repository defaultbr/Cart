package mensagens;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class mensagens {
	
	String INFO_ENVIADA = "Certidão Recebida com Sucesso.";
	String ERROR_CONEXAO = "ERRO: Verifique se o servidor de certidão está ligado, \n Se você está na rede, \n Tente novamente.";
	

	
	public void info_enviada() {
		new Thread(new Runnable(){  
			@Override  
			public void run(){  
				
				JOptionPane.showMessageDialog(null, "Certidão Recebida.");

			
			}  
			}).start();
	}	
	public void error_conexao() {
		new Thread(new Runnable(){  
			@Override  
			public void run(){  
				JOptionPane.showMessageDialog(null, "ERRO: Verifique se o servidor de certidão está ligado, \n Se você está na rede, \n Tente novamente.");
			}  
			}).start();
	}	
}
