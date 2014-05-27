package cards_servicos_consultas;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import cards_certidao.SimpleTableModel;
import bancodedados.conexao;
import net.miginfocom.swing.MigLayout;

public class cardmenuitem23 {

	String query = "SELECT * FROM chamados";
	String usuario;
	JPanel menuitem23 = new JPanel(new MigLayout("fillx"));
	JTable listaDeChamados = new JTable();
	JButton btnListar = new JButton("Listar Chamados");
	JScrollPane scrolllista = new JScrollPane(listaDeChamados);
	JTextArea textArea = new JTextArea();
	JScrollPane scrolltexto = new JScrollPane(textArea);
	public void montaPanel() {
		
		textArea.setLineWrap(true);
		listaDeChamados.addMouseListener( new MouseAdapter()
	    {
	    	public void mousePressed( MouseEvent e )
	    	{
	    		if ( SwingUtilities.isLeftMouseButton( e ) )
	    		{
	    			 if (e.getClickCount() == 1) {  
	    				 Point p = e.getPoint();
	 	    			final int rowNumber = listaDeChamados.rowAtPoint( p );
	 	     
	 	    			// Get the ListSelectionModel of the JTable
	 	    			ListSelectionModel model = listaDeChamados.getSelectionModel();
	 	     
	 	    			// set the selected interval of rows. Using the "rowNumber"
	 	    			// variable for the beginning and end selects only that one row.
	 	    			model.setSelectionInterval( rowNumber, rowNumber );
	    					String texto = String.valueOf(listaDeChamados.getValueAt(rowNumber,1));
	    					textArea.setText("");
	    					textArea.setText(texto);

	              

	    			 }
	    			
	    		}
	    		else if ( SwingUtilities.isRightMouseButton( e ))
	    		{
	    			
	    			System.out.println("Botão Esquerdo");
	    			JPopupMenu popupMenu = new JPopupMenu();

	    			 Point p = e.getPoint();
	 	    			final int rowNumber = listaDeChamados.rowAtPoint( p );
	 	     
	 	    			// Get the ListSelectionModel of the JTable
	 	    			ListSelectionModel model = listaDeChamados.getSelectionModel();
	 	     
	 	    			// set the selected interval of rows. Using the "rowNumber"
	 	    			// variable for the beginning and end selects only that one row.
	 	    			model.setSelectionInterval( rowNumber, rowNumber );
	    			
	 	    			JMenuItem i1 = new JMenuItem("Alterar para Aberto");
	 	    			JMenuItem i2 = new JMenuItem("Alterar para Fechado");
		    			i1.addActionListener(new ActionListener() {
		    				public void actionPerformed(ActionEvent arg0) {
		    					String id = String.valueOf(listaDeChamados.getValueAt(rowNumber,5));
		    					abrirChamado(id);
	
		    					}

							public void abrirChamado(String id) {
								String situacao = "Aberto";
								conexao metodosBD = new conexao();
								Connection conexao = null;
								try {
									conexao = metodosBD.abreconexao();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String updateTableSQL = "UPDATE chamados SET situacao = ? WHERE id = ?";
								PreparedStatement insere;
								try {
									insere = conexao.prepareStatement(updateTableSQL);
									insere.setString(1, situacao);
									insere.setString(2, id);
									insere.executeUpdate();
									conexao.close();
								    btnListar.doClick();

									

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
		    			});
		    			
		    			
		    			
		    			i2.addActionListener(new ActionListener() {
		    				public void actionPerformed(ActionEvent arg0) {
								

		    					String id = String.valueOf(listaDeChamados.getValueAt(rowNumber,5));
		    					String texto = String.valueOf(listaDeChamados.getValueAt(rowNumber,1));

		    					fecharChamado(id, texto);
	
		    					}

							public void fecharChamado(String id, String texto) {
								SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
								Date date = new Date(System.currentTimeMillis());    
								String data = formatarDate.format(date);
								
								
								String motivo = JOptionPane.showInputDialog("Qual foram os problemas resolvidos?");
								
								String novoTexto = "------ Adicionado em " + data + " por: " + usuario + " ------ \n"
										+ "" + motivo + "\n"
										+ "-------------------------------------------- \n"
										+ "\n" + texto;
								
								 
								
								
								
								
								
								
								String situacao = "FECHADO";

								conexao metodosBD = new conexao();
								Connection conexao = null;
								try {
									conexao = metodosBD.abreconexao();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String updateTableSQL = "UPDATE chamados SET situacao = ?, texto = ? WHERE id = ?";
								PreparedStatement insere;
								try {
									insere = conexao.prepareStatement(updateTableSQL);
									insere.setString(1, situacao);
									insere.setString(2, novoTexto);
									insere.setString(3, id);
									insere.executeUpdate();
									conexao.close();
									
									textArea.setText("");
								    btnListar.doClick();

									

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
		    			});
	    		
		    			popupMenu.add(i1);
		    			popupMenu.add(i2);
		    			popupMenu.show(e.getComponent(), e.getX(), e.getY());
	    			
	    			
	    		}
	    	}
	    });
		menuitem23.add(btnListar, "span, center, wrap");
		menuitem23.add(scrolllista, "span, push, growy, growx, wrap");
		menuitem23.add(scrolltexto, "growx, h 400");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				listarChamados();

			}
		});
	}


	
	public void listarChamados() {
		
		Connection conn = null;
		ResultSet results = null;
		Statement stm;
		conexao conecta = new conexao();
		try {
			conn = conecta.abreconexao();
			stm = conn.createStatement ();
			results = stm.executeQuery (query + " order by situacao asc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   ArrayList dados = new ArrayList();

		 String[] colunas = new String[] { 
				   "#",
				   "texto",
				   "usuario",
				   "ip",
				   "situacao",
				   "id"
				
				   
		    };  
		 
		 int total = 1;
		 try {
			while(results.next()) {
				
				 dados.add(new String[] { total + "", 
				    		results.getString("texto"),
				    		results.getString("usuario"),
				    		results.getString("ip"),
				    		results.getString("situacao"),
				    		results.getString("id")
				    
				    });  
				 
				 total++;
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 SimpleTableModel model = new SimpleTableModel(dados, colunas);
		 
		 listaDeChamados.setModel(model);
		     
		

	    // listaDeCertidoes.setModel(new SimpleTableModel(dados, colunas));
		 listaDeChamados.getColumnModel().getColumn(0).setMaxWidth(42);
		 listaDeChamados.getColumnModel().getColumn(5).setMaxWidth(40);

		
	     try {
			conn.close();
		} catch (SQLException e) {
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
		return menuitem23;
	}

}
