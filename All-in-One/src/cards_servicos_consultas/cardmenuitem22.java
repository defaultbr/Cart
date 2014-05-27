package cards_servicos_consultas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bancodedados.conexao;
import net.miginfocom.swing.MigLayout;

public class cardmenuitem22 {
	String usuario;
	String data;
	String ip;
	JLabel chamadoAdicionado = new JLabel(" ");
	JLabel lblChamado = new JLabel("Digite o Problema no seu PC: (seja objetivo(a))");
	JTextArea txtChamado = new JTextArea();
	JScrollPane scrollpane = new JScrollPane(txtChamado);
	JButton btnChamado = new JButton("Adicionar Chamado");
	JPanel menuitem22 = new JPanel(new MigLayout("fillx"));

	/**
	 * @wbp.parser.entryPoint
	 */
	public void montaPanel() {
		menuitem22.add(lblChamado, "span, wrap");
		menuitem22.add(scrollpane,"push, growy, growx, wrap");
		menuitem22.add(btnChamado, "center,wrap");
		menuitem22.add(chamadoAdicionado, "center");
		
		btnChamado.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				AdicionaChamado();
			}
		});
		
		chamadoAdicionado.setForeground(Color.red);
		txtChamado.setLineWrap(true);
	}
	
	public void AdicionaChamado() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		Date date = new Date(System.currentTimeMillis());    
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy"); 
		data = formatarDate.format(date);
		String update1 = "INSERT INTO  chamados (data, texto, usuario, ip, situacao) VALUES (?,?,?,?,?)";

		String textoChamado = "--------------------- MOTIVO DO CHAMADO: --------------------- \n "
				+ "" + txtChamado.getText() + "\n"
						+ "-----------------------------------------------------";
		
		
		conexao conecta = new conexao();
		try {
			conn = conecta.abreconexao();
			stmt = conn.prepareStatement(update1);
			stmt.setString(1, data);
			stmt.setString(2, textoChamado);
			stmt.setString(3, usuario);
			stmt.setString(4, InetAddress.getLocalHost().getHostAddress());
			stmt.setString(5, "Aberto");

			stmt.executeUpdate();
			
			conn.close();
			chamadoAdicionado.setText("Para adicionar outro chamado deve ser fechado e aberto o programa novamente.");
			btnChamado.setEnabled(false);
			JOptionPane.showMessageDialog(null, "Chamado adicionado com sucesso.");
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
		return menuitem22;
	}

}
