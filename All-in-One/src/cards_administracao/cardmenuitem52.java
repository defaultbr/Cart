package cards_administracao;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import bancodedados.conexao;
import net.miginfocom.swing.MigLayout;

public class cardmenuitem52 {
	static int r;

JPanel menuitem52 = new JPanel(new MigLayout("fillx"));
JPanel titledConfiguracoesIP = new JPanel(new MigLayout("fillx"));
JLabel lblbalcaoip = new JLabel("Balcão:");
JTextField tfbalcaoip = new JTextField(15);
JLabel lblescreventesip = new JLabel("Escreventes:");
JTextField tfescreventesip = new JTextField(15);

JLabel ipbalcaoinvalido = new JLabel("Ip Inválido");
JLabel ipescreventesinvalido = new JLabel("Ip Inválido");

JButton btnSalvarIPs = new JButton("Salvar");
boolean inputOK;


public void montaPanel() {
	
	titledConfiguracoesIP.setBorder(BorderFactory.createTitledBorder("Configurações de IP"));
	menuitem52.add(titledConfiguracoesIP, "center");
	titledConfiguracoesIP.add(lblbalcaoip,"");
	titledConfiguracoesIP.add(tfbalcaoip, "");
	titledConfiguracoesIP.add(ipbalcaoinvalido, "wrap");
	
	ipbalcaoinvalido.setVisible(false);
	ipescreventesinvalido.setVisible(false);
	
	titledConfiguracoesIP.add(lblescreventesip);
	titledConfiguracoesIP.add(tfescreventesip);
	titledConfiguracoesIP.add(ipescreventesinvalido, "wrap");

	titledConfiguracoesIP.add(btnSalvarIPs, "span, center");
	
	
	
	
    

	
	btnSalvarIPs.addActionListener(new ActionListener() {
			@Override
		public void actionPerformed(ActionEvent arg0) {
				String ipbalcao = tfbalcaoip.getText();
				String regex = "\\d?\\d?\\d[.]\\d?\\d?\\d[.]\\d?\\d?\\d[.]\\d?\\d?\\d";  
			      Pattern pattern = Pattern.compile( regex );  
			      Matcher matcher = pattern.matcher( ipbalcao );  
			      if( matcher.matches() ) {  
			  		alteraIp("Balcão", tfbalcaoip.getText());
			    	  ipbalcaoinvalido.setText("IP Salvo.");
			    	  ipbalcaoinvalido.setVisible(true);

			      }  else {
			    	  ipbalcaoinvalido.setVisible(true);

			      }
			      
			      String ipescreventes = tfescreventesip.getText();
				      matcher = pattern.matcher( ipescreventes );  
				      if( matcher.matches() ) {  
				    	  alteraIp("Escreventes", tfescreventesip.getText());
				    	  ipescreventesinvalido.setText("IP Salvo.");
				    	  ipescreventesinvalido.setVisible(true);

				      }  else {
				    	  ipescreventesinvalido.setVisible(true);

				      }     

		}
			
	});
	
	
	
	
	
	
	tfbalcaoip.setText(pegaIp("Balcão"));
	tfescreventesip.setText(pegaIp("Escreventes"));
	
}



public void alteraIp(String nome, String ip) {
	Connection conn = null;
	PreparedStatement statement = null;
	conexao atualiza = new conexao();
	String update1 = "UPDATE IPCertidoes SET ip = ? WHERE nome = '" + nome +"'";
	
	try {
		conn = atualiza.abreconexao();
		statement = conn.prepareStatement(update1);
		statement.setString(1, ip);

		statement.executeUpdate();
		conn.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public String pegaIp(String nome) {
	String ip = null;
	Connection conn = null;
	PreparedStatement statement = null;
	conexao atualiza = new conexao();
	ResultSet results;
	String sql = "SELECT * FROM IPCertidoes WHERE nome=?";
	try {
		conn = atualiza.abreconexao();
		statement = conn.prepareStatement(sql);
		statement.setString(1, nome);
		results = statement.executeQuery();
		
		while(results.next()) {
			ip = results.getString("ip");
			
		}
		
		
		conn.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return ip;
}


	




public JPanel getCard() {
	montaPanel();
	return menuitem52;
}
}
