package cards_administracao;

import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class cardmenuitem51 {
	static int r;

JPanel menuitem51 = new JPanel(new MigLayout("fillx"));
JPanel titledAdicionar = new JPanel(new MigLayout("fillx"));
JPanel titledListar = new JPanel(new MigLayout("fillx"));


JLabel lblUsuarioNome = new JLabel("Nome do Usuário:");
JTextField tfUsuarioNome = new JTextField(10);
JLabel lblUsuarioSenha = new JLabel("Senha:");
JPasswordField tfUsuarioSenha = new JPasswordField(10);

JLabel lblUsuarioNomeCompleto = new JLabel("Nome Completo:");
JTextField tfUsuarioNomeCompleto = new JTextField(25);

JButton btnAdicionarUsuario = new JButton("Adicionar");


JLabel lblNivelUsuario = new JLabel("Nível:");

SpinnerModel sm = new SpinnerNumberModel(1, 1, 5, 1); //default value,lower bound,upper bound,increment by
JSpinner spinnerNivelUsuario = new JSpinner(sm);

JLabel lblAvisoAdicionar = new JLabel(" ");
JLabel lblAvisoAlterarDeletar = new JLabel(" ");

JButton btnListarUsuarios = new JButton("Listar Usuários");
JTable tableListar = new JTable();
JScrollPane scrollListar = new JScrollPane(tableListar);


public void montaPanel() {
	
	
	menuitem51.add(titledAdicionar, "span, center, growx");
	titledAdicionar.setBorder(BorderFactory.createTitledBorder("Adicionar Usuário"));

	titledAdicionar.add(lblUsuarioNomeCompleto, "span, split 9, center");
	titledAdicionar.add(tfUsuarioNomeCompleto);
	titledAdicionar.add(lblUsuarioNome);
	titledAdicionar.add(tfUsuarioNome);
	titledAdicionar.add(lblUsuarioSenha);
	titledAdicionar.add(tfUsuarioSenha);
	titledAdicionar.add(lblNivelUsuario);
	titledAdicionar.add(spinnerNivelUsuario);
	titledAdicionar.add(btnAdicionarUsuario,"wrap");
	btnAdicionarUsuario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		adicionarUsuario();
		}
		});
	
	
	
	titledAdicionar.add(lblAvisoAdicionar,"span,center");
	
	
	
	menuitem51.add(titledListar, "span, center, growx");
	titledListar.setBorder(BorderFactory.createTitledBorder("Listagem e outras Opções"));
	titledListar.add(btnListarUsuarios, "span, center, wrap");
	btnListarUsuarios.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		listarUsuarios();
		}
		});

	titledListar.add(scrollListar, "span, center,wrap");
	tableListar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            r = tableListar.rowAtPoint(e.getPoint());
            if (r >= 0 && r < tableListar.getRowCount()) { tableListar.setRowSelectionInterval(r, r); } else { tableListar.clearSelection();  }
            int rowindex = tableListar.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem jitem1 = new JMenuItem("Alterar Senha");
                JMenuItem jitem2 = new JMenuItem("Excluir Usuário");
                jitem1.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent arg0) {
    					Object[] message = {"Nova Senha"};
    			        String NovaSenha = JOptionPane.showInputDialog(null, message, "Alteração de Senha", JOptionPane.OK_CANCEL_OPTION);
    			        if (NovaSenha == null);
    			        else {
    			       alteraSenha(NovaSenha, tableListar.getModel().getValueAt(r, 2).toString());
    			        }    					    					
    				}
    				});
                
                jitem2.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent arg0) {
    					String title = "Confirmação de Certidão";
   					 String message = "Deseja realmente deletar o usuário(a): " + tableListar.getModel().getValueAt(r, 2).toString();

    					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    			        if (reply == JOptionPane.YES_OPTION) {
    					
    					
    						deletaUsuario(tableListar.getModel().getValueAt(r, 2).toString());	
    			        }
    			        if (reply == JOptionPane.NO_OPTION) {
    			        }
    				}
    				});             
                popup.add(jitem1);
                popup.add(jitem2);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    });
	
	titledListar.add(lblAvisoAlterarDeletar, "span, center");
	
	
	
}

public void alteraSenha(String senha, String usuario) {
	Connection conn = null;
	PreparedStatement statement = null;
	conexao atualiza = new conexao();
	String updateTableSQL = "UPDATE usuarios SET senha = ? WHERE usuario = ?";
	
	try {
		conn = atualiza.abreconexao();
		statement = conn.prepareStatement(updateTableSQL);
		statement.setString(1, senha);
		statement.setString(2, usuario);
		statement.executeUpdate();
		conn.close();
		lblAvisoAlterarDeletar.setText("Senha Alterada.");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
}

public void deletaUsuario(String usuario) {
	Connection conn = null;
	PreparedStatement statement = null;
	conexao deleta = new conexao();
	
	String sql = "DELETE FROM usuarios WHERE usuario = ?";
    try {
    	conn = deleta.abreconexao();
    	statement = conn.prepareStatement(sql);

        
		statement.setString(1, usuario);
        statement.executeUpdate();

		conn.close();
        btnListarUsuarios.doClick();
		lblAvisoAlterarDeletar.setText("Usuário [" + usuario + "] Deletado.");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
	
	
}

public void listarUsuarios() {
	conexao conexao = new conexao();
	Connection conn = null;
	Statement statement = null;
	ArrayList dados = new ArrayList();
	ResultSet results = null;
	String query = "SELECT * FROM usuarios";

	
	try {
		conn = conexao.abreconexao();
		statement = conn.createStatement ();
		results = statement.executeQuery (query + " order by usuario asc");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    String[] colunas = new String[] { 
		   "#",
		   "Nome",
		   "usuario",
		   "nivel"
		
    };  
    	 
		
		
			
			
    int i = 1;
	try {
		while(results.next()) {
			
				    dados.add(new String[] { i + "",
				    		results.getString("nomecompleto"),
				    		results.getString("usuario"),
				    		results.getString("nivel")
				    		
				    
				    });
				    i++;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			    
			    tableListar.setModel(new SimpleTableModel(dados, colunas));
			    try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
	

public void adicionarUsuario() {
	
	if(tfUsuarioNome.getText().isEmpty() || tfUsuarioSenha.getPassword().length < 1) { 
		lblAvisoAdicionar.setForeground(Color.red);

		lblAvisoAdicionar.setText("Usuário ou Senha não podem estar em branco.");
	} else {
	String nomeCompleto = tfUsuarioNomeCompleto.getText().toUpperCase();
	String nomeUsuario = tfUsuarioNome.getText().toLowerCase();
	String senhaUsuario = new String(tfUsuarioSenha.getPassword()).toLowerCase();
	
	conexao conexao = new conexao();
	String sql = "INSERT INTO usuarios (nomecompleto,usuario,senha,nivel) VALUES(?,?,?,?)";
	Connection conn = null;
	PreparedStatement statement = null;
	try {
		conn = conexao.abreconexao();
        statement = conn.prepareStatement(sql);

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		statement.setString(1,nomeCompleto);
		statement.setString(2,nomeUsuario);
		statement.setString(3,senhaUsuario);
		statement.setString(4,spinnerNivelUsuario.getValue().toString());
        statement.executeUpdate();
        lblAvisoAdicionar.setText("Usuário adicionado com sucesso.");
        tfUsuarioNome.setText("");
        tfUsuarioSenha.setText("");
        tfUsuarioNomeCompleto.setText("");
        btnListarUsuarios.doClick();
        lblAvisoAdicionar.setForeground(Color.blue);

		conn.close();

	} catch (SQLException e) {
		if(e.getMessage().contains("Abort due to constraint violation")) {
			lblAvisoAdicionar.setText("Já existe um usuário com essse nome");
			lblAvisoAdicionar.setForeground(Color.red);

		}
		//e.printStackTrace();
	}
	}
	
	
}


public JPanel getCard() {
	montaPanel();
	return menuitem51;
}
}
