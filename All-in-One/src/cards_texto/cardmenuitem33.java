package cards_texto;

import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import javax.swing.JTable;

import cards_administracao.SimpleTableModel;
import net.miginfocom.swing.MigLayout;
import bancodedados.conexao;


public class cardmenuitem33 {
	
	JPanel menuitem33 = new JPanel(new MigLayout("fillx"));
	JPanel titledCheckBox = new JPanel(new MigLayout("fillx"));
	JButton btnGerar = new JButton("Gerar Texto");
	JTable tableListar = new JTable();
	JScrollPane scrollListar = new JScrollPane(tableListar);
	ArrayList dados = new ArrayList();
	public void montaPanel() {
		
		titledCheckBox.setBorder(BorderFactory.createTitledBorder("Lista de Itens de Exigência de PJ"));
		menuitem33.add(scrollListar, "growx, growy ,center, wrap");
		
		
		
		

	}
	public void listaTexto() {
		
		
		
		Connection conn = null;
		ResultSet results = null;
		PreparedStatement statement = null;
		conexao atualiza = new conexao();
		String sql = "SELECT * FROM exigencia_pj";
		try {
			conn = atualiza.abreconexao();
			statement = conn.prepareStatement(sql);
			results = statement.executeQuery();
			
			while(results.next()) {
			
				
			}
			
			
			conn.close();
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
		
		
		
	}
	
	public void deletaTexto() {
		Connection conn = null;
		ResultSet results = null;
		PreparedStatement statement = null;
		conexao atualiza = new conexao();
		String sql = "SELECT * FROM exigencia_pj";
		try {
			conn = atualiza.abreconexao();
			statement = conn.prepareStatement(sql);
			results = statement.executeQuery();
			
			while(results.next()) {
			
				
			}
			
			
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionarCheck(int id, String titulo, String texto) {
		
	}
	

	public JPanel getCard() {

		montaPanel();

		return menuitem33;
	}

}
