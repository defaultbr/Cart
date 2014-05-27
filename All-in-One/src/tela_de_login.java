import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import bancodedados.conexao;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class tela_de_login {
	JFrame frmLogarNoSistema = new JFrame();

	ImageIcon icon = new ImageIcon(tela_de_login.class.getResource("/Imagens/user_login.png"));
	private JTextField tfUsuario;
	private JPasswordField tfSenha;
	JLabel lblErro = new JLabel("");
	JButton btnLogar = new JButton("Entrar");

	String nomeCompleto = "";
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void abrirTela() {
		frmLogarNoSistema.setTitle("Logar no Sistema");
		frmLogarNoSistema.setSize(554, 295);
		frmLogarNoSistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogarNoSistema.getContentPane().setLayout(null);
		frmLogarNoSistema.setLocationRelativeTo(null);
		frmLogarNoSistema.getRootPane().setDefaultButton(btnLogar);
		
		JLabel iconLogin = new JLabel("");
		iconLogin.setBounds(0, 0, 276, 263);
		frmLogarNoSistema.getContentPane().add(iconLogin);
		iconLogin.setIcon(icon);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setBounds(286, 68, 46, 14);
		frmLogarNoSistema.getContentPane().add(lblUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				tfUsuario.setBackground(Color.YELLOW);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				tfUsuario.setBackground(Color.WHITE);

			}
		});
		tfUsuario.setBounds(343, 65, 169, 20);
		frmLogarNoSistema.getContentPane().add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(286, 116, 46, 14);
		frmLogarNoSistema.getContentPane().add(lblSenha);
		
		tfSenha = new JPasswordField();
		tfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				tfSenha.setBackground(Color.YELLOW);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				tfSenha.setBackground(Color.WHITE);

			}
		});
		tfSenha.setColumns(10);
		tfSenha.setBounds(343, 113, 169, 20);
		frmLogarNoSistema.getContentPane().add(tfSenha);
		
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificaCadastro();
			}
		});
		btnLogar.setBounds(286, 186, 89, 23);
		frmLogarNoSistema.getContentPane().add(btnLogar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(419, 186, 89, 23);
		frmLogarNoSistema.getContentPane().add(btnCancelar);
		
		lblErro.setForeground(Color.RED);
		lblErro.setBounds(308, 155, 179, 14);
		frmLogarNoSistema.getContentPane().add(lblErro);
		frmLogarNoSistema.setVisible(true);

	}
	public void verificaCadastro() {
		boolean encontrado = false;
		String nomeUsuario = tfUsuario.getText().toLowerCase();
		String senhaUsuario = new String(tfSenha.getPassword()).toLowerCase();
		int nivelUsuario = 0;
		conexao verificar = new conexao();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet results = null;
		try {
			conn = verificar.abreconexao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try {
			String sql = "SELECT * FROM usuarios WHERE usuario=? AND senha=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, nomeUsuario);
			ps.setString(2, senhaUsuario);
			results = ps.executeQuery();
			while(results.next()) {
				nivelUsuario = results.getInt("nivel");
				nomeCompleto = results.getString("nomecompleto");
				encontrado = true;
				
			}
			
			if(encontrado) {
				//Aqui se der certo vai abrir proxima tela enviando os dados
				
				
				frmLogarNoSistema.dispose();
				tela_do_programa telaprograma = new tela_do_programa();
				telaprograma.abrir(nomeUsuario, nivelUsuario, nomeCompleto);
				
				
			} else {
				lblErro.setText("Usuário ou Senha incorretos.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
		
	}
}
