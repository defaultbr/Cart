import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import conexaoBanco.conexao;


public class centralizador extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ClientGUI cGui;
;
	private JPanel contentPane;
	private JTextField txtObsAntesDe;
	private static JButton abrirChat = new JButton("Abrir CHAT");

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());

//		            System.out.println();
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				JButton fechar = new JButton("Fechar");
			 
				try {
					ImageIcon iconCertidoes = new ImageIcon(centralizador.class.getResource("/Imagens/certidoes.png"));
					final JLabel lblCertidoes = new JLabel("Fazer Certidão");
					lblCertidoes.setBounds(0, 0, 200, 100);
					 Image imageCert = iconCertidoes.getImage(); // transform it 
					 final Image newIconCertidoes = imageCert.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
					 final Image newIconCertidoesMouseOver = imageCert.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

					
					lblCertidoes.addMouseListener(new MouseAdapter()  
					{  

			            public void mouseEntered(MouseEvent e) {
			                super.mouseEntered(e);
			                
			                lblCertidoes.setForeground(Color.red);
			                lblCertidoes.setIcon(new ImageIcon(newIconCertidoesMouseOver));
			                lblCertidoes.repaint();
			                
			            }

			            @Override
			            public void mouseExited(MouseEvent e) {
			                super.mouseExited(e); 
			                lblCertidoes.setForeground(Color.BLACK);
			                lblCertidoes.setIcon(new ImageIcon(newIconCertidoes));
			                lblCertidoes.repaint();
			            }

					    public void mouseClicked(MouseEvent e)  
					    {  
							try {
								java.awt.Desktop.getDesktop().open( new File("F:\\GeradorCertidoesNegativas\\Programas\\Programa de Certidoes.jar") );
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}


					    }  
					}); 					
					
					
					ImageIcon iconDistribuidor = new ImageIcon(centralizador.class.getResource("/Imagens/distribuidor.png"));
					final JLabel lblDistribuidor = new JLabel("Distribuidor");
					lblDistribuidor.setBounds(0, 0, 200, 100);
					Image imageDist = iconDistribuidor.getImage(); // transform it 
					final Image newIconDistribuidor = imageDist.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
					 final Image newIconDistribuidorMouseOver = imageDist.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

					
					lblDistribuidor.addMouseListener(new MouseAdapter()  
					{  
						 public void mouseEntered(MouseEvent e) {
				                super.mouseEntered(e);
				                
				                lblDistribuidor.setForeground(Color.red);
				                lblDistribuidor.setIcon(new ImageIcon(newIconDistribuidorMouseOver));
				                lblDistribuidor.repaint();
				                
				            }

				            @Override
				            public void mouseExited(MouseEvent e) {
				                super.mouseExited(e); 
				                lblDistribuidor.setForeground(Color.BLACK);
				                lblDistribuidor.setIcon(new ImageIcon(newIconDistribuidor));
				                lblDistribuidor.repaint();
				            }
					    public void mouseClicked(MouseEvent e)  
					    {  
							try {
								java.awt.Desktop.getDesktop().open( new File("F:\\BAIXA\\ATOS DOS GRUPOS\\Programas-NAOMEXER\\distribuidor.jar") );
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}


					    }  
					}); 					
					
					
					
					ImageIcon iconIndicador = new ImageIcon(centralizador.class.getResource("/Imagens/indicador.png"));
					final JLabel lblIndicador = new JLabel("Consultar Indicador");
					lblIndicador.setBounds(0, 0, 200, 100);
					Image imageInd = iconIndicador.getImage(); // transform it 
					final Image newIconIndicador = imageInd.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
					 final Image newIconIndicadorMouseOver = imageInd.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

					lblIndicador.addMouseListener(new MouseAdapter()  
					{  
						 public void mouseEntered(MouseEvent e) {
				                super.mouseEntered(e);
				                
				                lblIndicador.setForeground(Color.red);
				                lblIndicador.setIcon(new ImageIcon(newIconIndicadorMouseOver));
				                lblIndicador.repaint();
				                
				            }

				            @Override
				            public void mouseExited(MouseEvent e) {
				                super.mouseExited(e); 
				                lblIndicador.setForeground(Color.BLACK);
				                lblIndicador.setIcon(new ImageIcon(newIconIndicador));
				                lblIndicador.repaint();
				            }
					    public void mouseClicked(MouseEvent e)  
					    {  
							try {
								java.awt.Desktop.getDesktop().open( new File("F:\\BAIXA\\ATOS DOS GRUPOS\\Programas-NAOMEXER\\Indicadores.jar") );
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}


					    }  
					}); 					
					
				
					final JDialog jdigCentral = new JDialog();
					JPanel panelCentral = new JPanel(new MigLayout("fillx"));
					jdigCentral.setContentPane(panelCentral);
					//jdigCentral.getContentPane().setLayout(null);
					centralizador frame = new centralizador();
					 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
				        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
				       
				        JSeparator jSep1 = new JSeparator(SwingConstants.HORIZONTAL);
				        JSeparator jSep2 = new JSeparator(SwingConstants.HORIZONTAL);
				        JSeparator jSep3 = new JSeparator(SwingConstants.HORIZONTAL);
				        
				        
		                jSep1.setPreferredSize(new Dimension(200,1));  
		                jSep2.setPreferredSize(new Dimension(200,1));  
		                jSep3.setPreferredSize(new Dimension(200,1));  
				        
				      
				        
						jdigCentral.setUndecorated(true);
						

						jdigCentral.setSize(220, (int) rect.getMaxX());
						
						JLabel espacamentoincial = new JLabel("");
						panelCentral.add(espacamentoincial, "wrap 10");
						panelCentral.add(lblCertidoes, "wrap 15, gap 10, wrap");
						panelCentral.add(jSep1, "wrap 15");

						panelCentral.add(lblDistribuidor, "wrap 15, gap 10");
						panelCentral.add(jSep2, "wrap 15");

						panelCentral.add(lblIndicador, "wrap 15, gap 10");
						System.out.println(jdigCentral.getHeight());
						panelCentral.add(jSep3, "wrap 300");
						panelCentral.add(fechar, "align right,wrap 50");
						panelCentral.add(abrirChat, "align right");
						
						   fechar.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									jdigCentral.dispose();
									if(cGui != null) {
									cGui.dispose();
									}
								}
							});

						   abrirChat.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									abrirChat();
								}
							});

						
						lblCertidoes.setIcon(new ImageIcon(newIconCertidoes));
						lblDistribuidor.setIcon(new ImageIcon(newIconDistribuidor));
						lblIndicador.setIcon(new ImageIcon(newIconIndicador));


				        
				        int x = (int) rect.getMaxX() - jdigCentral.getWidth();
				        int y = (int) rect.getMaxY() - jdigCentral.getHeight();
				        
				        
				        jdigCentral.setLocation(x, 0);

				        jdigCentral.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						
				        
				        
					//frame.setVisible(true);
				        
				       
				      //  jdigCentral.setOpacity(0.10f);
					jdigCentral.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public centralizador() {
		setTitle("Central de Programas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDistribuidor = new JButton("Distribuidor");
		btnDistribuidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					java.awt.Desktop.getDesktop().open( new File("F:\\BAIXA\\ATOS DOS GRUPOS\\Programas-NAOMEXER\\distribuidor.jar") );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDistribuidor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDistribuidor.setBounds(21, 26, 172, 132);
		contentPane.add(btnDistribuidor);
		
		JButton btnIndicadorPessoal = new JButton("Indicador Pessoal/Real");
		btnIndicadorPessoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					java.awt.Desktop.getDesktop().open( new File("F:\\BAIXA\\ATOS DOS GRUPOS\\Programas-NAOMEXER\\Indicadores.jar") );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnIndicadorPessoal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnIndicadorPessoal.setBounds(282, 26, 263, 132);
		contentPane.add(btnIndicadorPessoal);
		
		JButton btnDuvidas = new JButton("Duvidas");
		btnDuvidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					java.awt.Desktop.getDesktop().open( new File("F:\\BAIXA\\ATOS DOS GRUPOS\\Programas-NAOMEXER\\Duvidas.jar") );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				
				
			}
		});
		btnDuvidas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDuvidas.setBounds(617, 26, 172, 132);
		contentPane.add(btnDuvidas);
		
		JButton btnCorretorDeMatriculas = new JButton("Corretor de Matriculas");
		btnCorretorDeMatriculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					java.awt.Desktop.getDesktop().open( new File("F:\\BAIXA\\ATOS DOS GRUPOS\\Programas-NAOMEXER\\CorretordeMatriculas.jar") );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			
			}
		});
		btnCorretorDeMatriculas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCorretorDeMatriculas.setBounds(113, 169, 257, 132);
		contentPane.add(btnCorretorDeMatriculas);
		
		JButton btnCertidesGeradas = new JButton("Lista Certid\u00F5es Geradas");
		btnCertidesGeradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					java.awt.Desktop.getDesktop().open( new File("F:\\BAIXA\\ATOS DOS GRUPOS\\Programas-NAOMEXER\\ListaCertidoes.jar") );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
		btnCertidesGeradas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCertidesGeradas.setBounds(437, 169, 257, 132);
		contentPane.add(btnCertidesGeradas);
		
		JTextPane txtpnParaCertidesDeve = new JTextPane();
		txtpnParaCertidesDeve.setBackground(Color.LIGHT_GRAY);
		txtpnParaCertidesDeve.setDisabledTextColor(Color.LIGHT_GRAY);
		txtpnParaCertidesDeve.setForeground(Color.RED);
		txtpnParaCertidesDeve.setEditable(false);
		txtpnParaCertidesDeve.setOpaque(false);
		txtpnParaCertidesDeve.setText("Para certid\u00F5es, deve ser acessada a pasta F:\\ATALHOS\\\r\n-> L\u00E1 possui 2 atalhos, um para o programa que envia certid\u00F5es para Balc\u00E3o e um que envia certid\u00F5es para o computador de certid\u00E3o dos ESCREVENTES...\r\n\r\nclique com o bot\u00E3o direito sobre o que possui o nome que voc\u00EA quer, clique em enviar e AREA DE TRABALHO, isto vai gerar um atalho do programa na sua tela inicial, o nome do atalho est\u00E1 claro para qual ele ir\u00E1 enviar o pedido de certid\u00E3o.");
		txtpnParaCertidesDeve.setBounds(23, 343, 818, 107);
		contentPane.add(txtpnParaCertidesDeve);
		
		txtObsAntesDe = new JTextField();
		txtObsAntesDe.setForeground(Color.WHITE);
		txtObsAntesDe.setBackground(Color.RED);
		txtObsAntesDe.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtObsAntesDe.setText("OBS: antes de criar o atalho verifique, se j\u00E1 n\u00E3o possui na sua area de trabalho um programa chamado GERAR - CERTID\u00C3O - ESCREVENTES ou ... - BALC\u00C3O");
		txtObsAntesDe.setBounds(21, 302, 818, 40);
		contentPane.add(txtObsAntesDe);
		txtObsAntesDe.setColumns(10);
	}
	
	
	
	      /*
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
				//aquivaiabrirochat
				
				
			} else {
				//lblErro.setText("Usuário ou Senha incorretos.");
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
	*/
	public static void abrirChat() {
		JTextField xField = new JTextField(10);
	      JPasswordField yField = new JPasswordField();
	      yField.setColumns(10);
	      JPanel myPanel = new JPanel(new MigLayout("fillx"));
	      myPanel.add(new JLabel("Login:"));
	      myPanel.add(xField, "wrap");
	     // myPanel.add(Box.createHorizontalStrut(2)); // a spacer
	      myPanel.add(new JLabel("Senha:"));
	      myPanel.add(yField);

	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Digite sua Senha e Login", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  String nomeUsuario = xField.getText().toLowerCase();
	  		String senhaUsuario = new String(yField.getPassword()).toLowerCase();
	  		
	        pegaUsuario(nomeUsuario, senhaUsuario);
	      }
	}
	
	public static void pegaUsuario(String nomeUsuario, String senhaUsuario) {
		
		boolean encontrado = false;
		String nomeUser;
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
				nomeUser = results.getString("usuario");
				encontrado = true;
				
			}
			
			if(encontrado) {
				
				cGui = new ClientGUI("192.168.0.5", 1500, nomeUsuario);
				abrirChat.setEnabled(false);
				System.out.println("Deu certo");
				//aquivaiabrirochat
				
				
			} else {
				JOptionPane.showMessageDialog(null, "usuario ou senha incorretos");
				//lblErro.setText("Usuário ou Senha incorretos.");
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
