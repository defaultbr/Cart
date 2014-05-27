package cards_servicos_consultas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bancodedados.conexao;
import net.miginfocom.swing.MigLayout;

public class cardmenuitem21 {
	String usuario;
	String senha;
	JLabel lblSenha = new JLabel("Digite a nova Senha:");
	JTextField tfSenha = new JTextField(10);
	JButton btnAlterar = new JButton("Salvar Nova Senha");
	JPanel menuitem21 = new JPanel(new MigLayout("fillx"));

	public void montaPanel() {
		menuitem21.add(lblSenha, "split 3, span");
		menuitem21.add(tfSenha);
		menuitem21.add(btnAlterar);

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfSenha.getText().isEmpty() || tfSenha.getText() == null) { 
					JOptionPane.showMessageDialog(null, "Digite uma senha");
				} else {
					senha = tfSenha.getText();
					tfSenha.setText("");
				AlterarSenha();
				}
			}
		});

	}
	
	public void AlterarSenha() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String update1 = "UPDATE usuarios SET senha = ? WHERE usuario = '" + usuario +"'";

		
		conexao conecta = new conexao();
		try {
			conn = conecta.abreconexao();
			stmt = conn.prepareStatement(update1);
			stmt.setString(1, senha);

			stmt.executeUpdate();
			
			conn.close();
			
			JOptionPane.showMessageDialog(null, "Senha Alterada Com Sucesso");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public JPanel getCard() {
		montaPanel();
		return menuitem21;
	}

}
