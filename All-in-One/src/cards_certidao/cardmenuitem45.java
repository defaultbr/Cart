package cards_certidao;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import bancodedados.conexao;
import net.miginfocom.swing.MigLayout;

public class cardmenuitem45 {
	String procurarpor = "";
	String orderby = "";
	String query = "SELECT * FROM log_certidoes";

	JButton btnProcurar = new JButton("Procurar");
	JComboBox cboxProcurarPor = new JComboBox();
	JTextField tfProcurarTexto = new JTextField(10);
	JPanel menuitem45 = new JPanel(new MigLayout("fillx"));
	JButton btnListar = new JButton("Listar Certidões");
	JTable listaDeCertidoes = new JTable();
	JScrollPane scrolllista = new JScrollPane(listaDeCertidoes);
	public void montaPanel() {
		
		
		
		listaDeCertidoes.addMouseListener( new MouseAdapter()
	    {
	    	public void mousePressed( MouseEvent e )
	    	{
	    		if ( SwingUtilities.isLeftMouseButton( e ) )
	    		{
	    			 if (e.getClickCount() == 2) {  
	                 	
	              

	    			 }
	    			
	    			// Do something
	    		}
	    		else if ( SwingUtilities.isRightMouseButton( e ))
	    		{
	    			
	    		
	    			Point p = e.getPoint();
	    			final int rowNumber = listaDeCertidoes.rowAtPoint( p );
	     
	    			// Get the ListSelectionModel of the JTable
	    			ListSelectionModel model = listaDeCertidoes.getSelectionModel();
	     
	    			// set the selected interval of rows. Using the "rowNumber"
	    			// variable for the beginning and end selects only that one row.
	    			model.setSelectionInterval( rowNumber, rowNumber );
	    			
	    			JPopupMenu popupMenu = new JPopupMenu();
	    			
	    			
	    			///////RELACIONADOS AO EDITAR FOLHA INICIO
	    			
	    			JMenuItem i1 = new JMenuItem("Editar Folha Inicio");
	    			i1.addActionListener(new ActionListener() {
	    				public void actionPerformed(ActionEvent arg0) {
	    					String data = String.valueOf(listaDeCertidoes.getValueAt(rowNumber,4));
	    					
	    					alterafolhainicio(data);
	    					
	    					
	    					}

						private void alterafolhainicio(String data) {
							
							conexao metodosBD = new conexao();
							Connection conexao = null;
							try {
								conexao = metodosBD.abreconexao();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							String folhainicio = null;
							String[] options = {"Salvar"};
							JPanel panel = new JPanel();
							JLabel lbl = new JLabel("Digite o número de inicio: ");
							JTextField txt = new JTextField(10);
							txt.addKeyListener(new KeyAdapter() {
								@Override
								public void keyTyped(KeyEvent evt) {
									String caracteres="0987654321";
									if(!caracteres.contains(evt.getKeyChar()+"")){
									evt.consume();
									}
								}
							});
							panel.add(lbl);
							panel.add(txt);
							int selectedOption = JOptionPane.showOptionDialog(null, panel, "Editar folha", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

							if(selectedOption == 0)
							{
							    folhainicio = txt.getText();
							    String updateTableSQL = "UPDATE log_certidoes SET folhainicial = ? WHERE data = ?";
								PreparedStatement insere;
								try {
									insere = conexao.prepareStatement(updateTableSQL);
									insere.setString(1, folhainicio);
									insere.setString(2, data);
									insere.executeUpdate();
								
									
									conexao.close();
								    btnListar.doClick();

									

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							}
							}
							
							
							
	    			});
	    			
	    			
	    			////////////////Relacionados ao EDITAR folha final
	    			
	    			JMenuItem i2 = new JMenuItem("Editar Folha Final");
	    			i2.addActionListener(new ActionListener() {
	    				public void actionPerformed(ActionEvent arg0) {
	    					String data = String.valueOf(listaDeCertidoes.getValueAt(rowNumber,4));
	    					System.out.println(data);
	    					alterafolhafinal(data);
	    					}

						private void alterafolhafinal(String data) {
							
							conexao metodosBD = new conexao();
							Connection conexao = null;
							
							try {
								conexao = metodosBD.abreconexao();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							String folhainicio = null;
							String[] options = {"Salvar"};
							JPanel panel = new JPanel();
							JLabel lbl = new JLabel("Digite o número Final: ");
							JTextField txt = new JTextField(10);
							txt.addKeyListener(new KeyAdapter() {
								@Override
								public void keyTyped(KeyEvent evt) {
									String caracteres="0987654321";
									if(!caracteres.contains(evt.getKeyChar()+"")){
									evt.consume();
									}
								}
							});
							panel.add(lbl);
							panel.add(txt);
							int selectedOption = JOptionPane.showOptionDialog(null, panel, "Editar folha", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

							if(selectedOption == 0)
							{
							    folhainicio = txt.getText();
							    String updateTableSQL = "UPDATE log_certidoes SET folhafinal = ? WHERE data = ?";
								PreparedStatement insere;
								try {
									insere = conexao.prepareStatement(updateTableSQL);
									insere.setString(1, folhainicio);
									insere.setString(2, data);
									insere.executeUpdate();

									conexao.close();
								    btnListar.doClick();

									
									

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							}
							}
							
							
							
							
	    			});
	    			
	    			
	    			////////RELACIONADOS AO DELETAR
	    			
	    			
	    			JMenuItem i3 = new JMenuItem("Deletar Registro");
	    			i3.addActionListener(new ActionListener() {
	    				public void actionPerformed(ActionEvent arg0) {
	    					String[] options = {"DELETAR"};
							JPanel panel = new JPanel();
							JLabel lbl = new JLabel("Digite a senha para deletar o registro de número #" + String.valueOf(listaDeCertidoes.getValueAt(rowNumber,1)) + "\n");
							JTextField txt = new JTextField(10);				
							panel.add(lbl);
							panel.add(txt);	
							
						
							
							int selectedOption = JOptionPane.showOptionDialog(null, panel, "Deletar Registro (#" + String.valueOf(listaDeCertidoes.getValueAt(rowNumber,1)) + ")", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
							String Senha = txt.getText();
	    					String caminhoarquivo = String.valueOf(listaDeCertidoes.getValueAt(rowNumber,4));

							if(Senha.equals("ir465718")) {
								deletarregistro(caminhoarquivo);					
								
							} else {
								JOptionPane.showMessageDialog(null, "Senha incorreta.");
							}
							
							
							
							
							
							
							

	    					
	    					
	    					}

	    				
	    				
	    				
						private void deletarregistro(String data) {
							conexao metodosBD = new conexao();
							Connection conexao = null;
							try {
								conexao = metodosBD.abreconexao();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							try {
								//File arquivo = new File(caminhoarquivo);
								//arquivo.delete();
								String sql = "DELETE FROM log_certidoes WHERE data = ?";
					            PreparedStatement deleta = conexao.prepareStatement(sql);
					            deleta.setString(1, data);
					            deleta.executeUpdate();
					            conexao.close();
								btnListar.doClick();
								JOptionPane.showMessageDialog(null, "Registro de Certidão deletado.");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
							
							
							
							
	    			});
	    			
	    			
	    			
	    			
	    			
	    			

	    			popupMenu.add(i1);
	    			popupMenu.add(i2);
	    			popupMenu.add(i3);
	    			popupMenu.show(e.getComponent(), e.getX(), e.getY());
	    			
	    		}
	    	}
	    	

		
	    });
		
		cboxProcurarPor.addItem("Todos");
		cboxProcurarPor.addItem("Protocolo");
		cboxProcurarPor.addItem("Tipo");
		cboxProcurarPor.addItem("Autor");
		cboxProcurarPor.addItem("Data");
		
		
		

		
		menuitem45.add(cboxProcurarPor, "split 2, span, center");
		menuitem45.add(tfProcurarTexto,"wrap");
		menuitem45.add(btnListar, "span, center, wrap");
		menuitem45.add(scrolllista, "span, push, growy, growx, wrap");
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				listarcertidoes();

			}
		});
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void listarcertidoes() {
		
		
		 if(cboxProcurarPor.getSelectedItem().toString().matches("Todos") == true) {

				query = "SELECT * FROM log_certidoes";
 
		  }
		  if(cboxProcurarPor.getSelectedItem().toString().matches("Autor") == true) {
				query = "SELECT * FROM log_certidoes WHERE autor LIKE '%" + tfProcurarTexto.getText() +   "%'";
				
 
		  }
		  if(cboxProcurarPor.getSelectedItem().toString().matches("Protocolo") == true) {
				query = "SELECT * FROM log_certidoes WHERE protocolo LIKE '%" + tfProcurarTexto.getText() +   "%'";
 
		  }

		  if(cboxProcurarPor.getSelectedItem().toString().matches("Tipo") == true) {
				query = "SELECT * FROM log_certidoes WHERE tipo LIKE '%" + tfProcurarTexto.getText() +   "%'";
 
		  }
		  if(cboxProcurarPor.getSelectedItem().toString().matches("Matricula") == true) {
				query = "SELECT * FROM log_certidoes WHERE numero LIKE '%" + tfProcurarTexto.getText() +   "%'";

		  }
		  if(cboxProcurarPor.getSelectedItem().toString().matches("Data") == true) {
				query = "SELECT * FROM log_certidoes WHERE data LIKE '%" + tfProcurarTexto.getText() +   "%'";

		  }
		
		
		Connection conn = null;
		ResultSet results = null;
		Statement stm;
		conexao conecta = new conexao();
		try {
			conn = conecta.abreconexao();
			stm = conn.createStatement ();
			results = stm.executeQuery (query + " order by data desc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   ArrayList dados = new ArrayList();

		 String[] colunas = new String[] { 
				   "#",
				   "protocolo",
				   "Tipo",
				   "Autor",
				   "Data",
				   "Folha inicial",
				   "Folha final"
				   
		    };  
		 
		 int total = 1;
		 try {
			while(results.next()) {
				
				 dados.add(new String[] { total + "", 
				    		results.getString("protocolo"),
				    		results.getString("tipo"),
				    		results.getString("autor"),
				    		results.getString("data"),
				    		results.getString("folhainicial"),
				    		results.getString("folhafinal"),
				    
				    });  
				 
				 total++;
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 SimpleTableModel model = new SimpleTableModel(dados, colunas);
		 
		     listaDeCertidoes.setModel(model);
		     
		

	    // listaDeCertidoes.setModel(new SimpleTableModel(dados, colunas));
		 listaDeCertidoes.getColumnModel().getColumn(0).setMaxWidth(42);

		
	     try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public JPanel getCard() {
		montaPanel();
		return menuitem45;
	}
	
}
