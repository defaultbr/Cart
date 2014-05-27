import java.awt.Component;
import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
public class receptor implements Runnable{
	static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	static int r;
	static String  arquivonomecompleto;
	static String datacriada;
	 static SimpleTableModel modelo;
     static JDialog jdig = new JDialog();
	static ArrayList dados = new ArrayList();
	
	public static ArrayList copiaDados = new ArrayList();
    public static JTable lista = new JTable();
    static Calendar cal;

    JScrollPane scrolllist = new JScrollPane(lista);
	public static void main(String[] arg){
		
		jdig.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
	        
	
		
		
	    receptor server = new receptor();
	    
	    
	    
	    lista.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            r = lista.rowAtPoint(e.getPoint());
	            if (r >= 0 && r < lista.getRowCount()) {
	                lista.setRowSelectionInterval(r, r);
	            } else {
	                lista.clearSelection();
	            }

	            int rowindex = lista.getSelectedRow();
	            if (rowindex < 0)
	                return;
	            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
	                JPopupMenu popup = new JPopupMenu();
	                JMenuItem jitem1 = new JMenuItem("Finalizar Certidão");
	                jitem1.addActionListener(new ActionListener() {
	    				public void actionPerformed(ActionEvent arg0) {
	    					escondetela();
	    					String message;
	    					if(lista.getModel().getValueAt(r, 0).toString().contains("negativa")) {
		    					 message = "Deseja realmente finalizar a certidão negativa";

	    					} else { 
		    					 message = "[CERTIDÃO] deseja realmente finalizar? \n Protocolo: " + lista.getModel().getValueAt(r, 0).toString();

	    					}
	    					String title = "Confirmação de Certidão";
	    					
	    					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	    			        if (reply == JOptionPane.YES_OPTION) {
	    			    		cal = Calendar.getInstance();

	    			          pegaarquivos(lista.getModel().getValueAt(r, 2).toString(), lista.getModel().getValueAt(r, 1).toString(), lista.getModel().getValueAt(r, 5).toString() );
	    			          int protocolo = Integer.parseInt(lista.getModel().getValueAt(r, 0).toString().trim());
	    			          String tipo = lista.getModel().getValueAt(r, 2).toString();
	    			         long numero = Long.parseLong(lista.getModel().getValueAt(r, 1).toString());
	    			         String autor = lista.getModel().getValueAt(r, 3).toString();
	    			         String ipOrigem = lista.getModel().getValueAt(r, 4).toString();
	    			         datacriada = dateFormat.format(cal.getTime()).toString(); 
	    			          gravarLog(protocolo, tipo, numero, autor, ipOrigem);
	    			          pegapaginas();
		    					apagarCertidao(r);

	    			          
	    			        }
	    			        if (reply == JOptionPane.NO_OPTION) {
	    			        	mostratela();
	    			        }
	    					
	    					
	    					
	    					
	    				
	    					
	    					
	    				}
	    				});
	                
	                          
	             
	                
	                
	                
	                JMenuItem jitem2 = new JMenuItem("Cancelar Certidão");
	                jitem2.addActionListener(new ActionListener() {
	    				public void actionPerformed(ActionEvent arg0) {
	    					apagarCertidao(r);
	    				}
	    				});
	                
	                
	                
	                
	                popup.add(jitem1);
	                popup.add(jitem2);
	                popup.show(e.getComponent(), e.getX(), e.getY());
	            }
	        }
	    });
	    
	   
	
	    
	    
	    
	    
	    
        System.out.println("---------------Aguardando por certidões----------------");   
       
        ArrayList soteste = new ArrayList();
   	    soteste.add("1135001");
   	    soteste.add("20140101010101010101");
   	    soteste.add("NEGATIVA DE REGISTRO DE PROPRIEDADE123456789101112131415");
   	    soteste.add("UsuarioComNomeGrande");
   	    soteste.add("192.168.255.255");
         //  lista(soteste);
        
        
	    new Thread(server).start();
	   
	  }
	
	public static void pegaarquivos(String tipo, String matricula, String livro) {
		String subdir = "0000";
		//PEGA ARQUIVOS DAS MATRICULAS
	
		if(tipo.contains("Matricula")) {
			String arquivo = String.format("%06d", Integer.parseInt(matricula));
			int confere = Integer.parseInt(matricula);
			if(confere >= 1 && confere <= 999) {
				subdir = "0000";
			}
			if(confere > 999 && confere <= 9999) {
				subdir = "000" + matricula.substring(0,1);
			}
			if(confere > 9999 && confere <= 99999) {
				subdir = "00" + matricula.substring(0,2);
			}

		File file = new File("F:\\registro_sql\\Imagens\\" + livro + "\\" + subdir);
		System.out.println("F:\\registro_sql\\Imagens\\" + livro + "\\" + subdir);
			File[] arquivos = file.listFiles();
			
			
			
			int i = 0;
			int achado = 0;
			
			ArrayList listadosarquivos = new ArrayList();
			while(i < arquivos.length) { 
				if(arquivos[i].getName().startsWith(arquivo)) {
					
					
				listadosarquivos.add(arquivos[i].getPath());

				

				} 
				i++;
		}
			Collections.sort(listadosarquivos);
		
			geraopdf(listadosarquivos);
			try {
				Desktop.getDesktop().print(new File(arquivonomecompleto));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
			
		if(tipo.contains("Transcrição")) {
			String arquivo = String.format("%08d", Integer.parseInt(matricula));

			int confere = Integer.parseInt(matricula.substring(3,matricula.length()));
		
			if(confere >= 1 && confere <= 99) {
				subdir = "0000";

			}
			if(confere > 99 && confere <= 999) {
				subdir = "000" + confere/100;


			}
			if(confere > 999 && confere <= 9999) {
				subdir = "00" + confere/100;


			}
			if(confere > 9999 && confere <= 99999) {
				subdir = "0" + confere/100;
			}					
			File file = new File("F:\\registro_sql\\Livro02\\" + subdir);
			File[] arquivos = file.listFiles();		
			int i = 0;
			String caminho = null;
			while(i < arquivos.length) { 
				if(arquivos[i].getName().startsWith(matricula)) {					
					caminho = arquivos[i].getPath();
				} 
				i++;
		}	
			try {
				Desktop.getDesktop().print(new File(caminho));
			} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		
		
		if(tipo.contains("Transcrição") == false && tipo.contains("Matricula") == false) {
			File file = new File("F:\\GeradorCertidoesNegativas\\feitas\\");
			File[] arquivos = file.listFiles();	
			int i = 0;
			String caminho = null;
			while(i < arquivos.length) { 
				if(arquivos[i].getName().startsWith("[" + matricula)) {					
					caminho = arquivos[i].getPath();
				
					try {
						Desktop.getDesktop().print(new File(caminho));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} 
				i++;
		}	
		
		
		
		}
			
		
		}

			

	
			
			
				
			
			
			
			
		
		
		
		
		
			
			

			

		
	
		public static void pegapaginas() {
			final JTextField tfpInicial;
			  final JTextField tfpFinal;
				final JDialog jdigpaginas = new JDialog();
				jdigpaginas.setSize(252,183);
				jdigpaginas.getContentPane().setLayout(null);
				
				JLabel lblpInicial = new JLabel("Pagina Inicial:");
				lblpInicial.setBounds(10, 21, 90, 14);
				jdigpaginas.getContentPane().add(lblpInicial);
				
				JLabel lblpFinal = new JLabel("Pagina Final:");
				lblpFinal.setBounds(10, 64, 90, 14);
				jdigpaginas.getContentPane().add(lblpFinal);
				
				tfpInicial = new JTextField();
				tfpInicial.setBounds(95, 18, 111, 25);
				jdigpaginas.getContentPane().add(tfpInicial);
				tfpInicial.setColumns(10);
				
				tfpFinal = new JTextField();
				tfpFinal.setColumns(10);
				tfpFinal.setBounds(95, 61, 111, 25);
				jdigpaginas.getContentPane().add(tfpFinal);
				
				JButton btnSalvar = new JButton("SALVAR");
				btnSalvar.setBounds(71, 108, 89, 23);
				jdigpaginas.getContentPane().add(btnSalvar);
				jdigpaginas.setUndecorated(true);
				jdigpaginas.setLocationRelativeTo(null);
				jdigpaginas.setDefaultCloseOperation(jdigpaginas.DISPOSE_ON_CLOSE);
				  
				  
				  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
			        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
			        int x = (int) rect.getMaxX() - jdigpaginas.getWidth();
			        int y = (int) rect.getMaxY() - jdigpaginas.getHeight();
			        jdigpaginas.setLocation(x, 0);
				
			        escondetela();
				
				
				
				jdigpaginas.setVisible(true);
				
				tfpInicial.addKeyListener(new KeyAdapter() {
  					@Override
  					public void keyTyped(KeyEvent evt) {
  						String caracteres="0987654321";
  						if(!caracteres.contains(evt.getKeyChar()+"")){
  						evt.consume();
  						}
  					}
  				});
				
				tfpFinal.addKeyListener(new KeyAdapter() {
  					@Override
  					public void keyTyped(KeyEvent evt) {
  						String caracteres="0987654321";
  						if(!caracteres.contains(evt.getKeyChar()+"")){
  						evt.consume();
  						}
  					}
  				});
				
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(tfpFinal.getText().isEmpty() || tfpInicial.getText().isEmpty() || tfpFinal.getText().length() > 6 || tfpInicial.getText().length() > 6) {
							JOptionPane.showMessageDialog(null, "Deve ser preenchidos pagina inicial e pagina final");
						} else { 
							jdigpaginas.dispose();
							alteraPaginas(tfpInicial.getText(), tfpFinal.getText());
							mostratela();
						}
						
						

						
					}
				});
			
			
			
		}
		
		
		public static void alteraPaginas(String pinicial, String pfinal) {
			Connection conn = null;
			PreparedStatement statement = null;
			int folhainicial = Integer.parseInt(pinicial);
			int folhafinal = Integer.parseInt(pfinal);
			conexao atualiza = new conexao();
			String updateTableSQL = "UPDATE log_certidoes SET folhainicial = ?, folhafinal = ? WHERE data = ?";

			try {
				conn = atualiza.abreconexao();
				statement = conn.prepareStatement(updateTableSQL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.setInt(1, folhainicial);
				statement.setInt(2, folhafinal);
				statement.setString(3, datacriada);
				statement.executeUpdate();
				conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		

			

			
			
		}
	
	
	 public static void geraopdf(ArrayList listadosarquivos) {
		 
			try {
				arquivonomecompleto = "F:\\Certidoes Geradas\\Impressoes de Certidoes\\Matricula_" + InetAddress.getLocalHost().getHostName() + "_.pdf";
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        Document doc = null;
	        OutputStream os = null;


	     
					
	    try {
	        //cria o documento tamanho A4, margens de 2,54cm
	        // doc = new Document(PageSize.A4, 152, 152, 152, 152);

	        doc = new Document(PageSize.A4);
	        //(ESQUERDA, DIREITA, TOPO, BAIXO)
	        doc.setMargins(0, 0, 0, 0);

	        //cria a stream de saída
	      
	        
	        
			
			
			
	        os = new FileOutputStream(arquivonomecompleto);
	        //associa a stream de saída ao
	     ///   PdfWriter.getInstance(doc, os);
	        PdfWriter.getInstance(doc, new FileOutputStream(arquivonomecompleto));

	        //abre o documento
	        doc.open();

	        //adiciona o texto ao PDF
	        
	        int i = 0;
	        int pagina = 1;
	        while(i < listadosarquivos.size()) {
	        	doc.newPage();

	       Image img = Image.getInstance(listadosarquivos.get(i).toString());
	     //  Image preco = Image.getInstance("F:\\precocertidoes\\preco37.jpg");
	       
		   int indentation = 99;

	       float scaler = ((doc.getPageSize().getWidth() - doc.leftMargin()
	               - doc.rightMargin() - indentation) / img.getWidth()) * 100;

	img.scalePercent(scaler);
	img.setAlignment(Element.ALIGN_JUSTIFIED);
	doc.add(img);
	i++;
	/* if(i < listadosarquivos.size()) { 
	           doc.newPage();
	           pagina++;
	           i++; 
	       } else {
	       doc.add(img);
	      // doc.add(new Paragraph("Folha número: " + pagina + " de um total de " + totalpaginas));
	       doc.newPage();
	       pagina++;
	       i++;     
	      }*/
	        }
	        
	       
	        
	        
	        //Print jobimprimir = new Print();
	        //jobimprimir.printPDF(arquivonomecompleto);
	        //jobimprimir.Imprimir(arquivonomecompleto);
	        //Desktop.getDesktop().print(new File(arquivonomecompleto)); 
	        
	        
	        

	        } catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            if (doc != null) {
	                //fechamento do documento
	       doc.close();
	            }
	            if (os != null) {
	            	try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    
      doc.close();

	    
	    
	    }
	    
	    
	public static void gravarLog(int protocolo, String tipo, long numero, String autor, String ipOrigem) {
		String impressoonde = null;

		try {
			impressoonde = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "INSERT INTO log_certidoes (tipo,numero,data,autor,iporigem,codigodebarras,folhainicial,folhafinal, impressoonde, protocolo) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement statement = null;
		String data = "0";
		int codigodebarras = 0;
		int folhainicial = 0;
		int folhafinal = 0;
		
		conexao gravalog = new conexao();
		try {
			conn = gravalog.abreconexao();
	        statement = conn.prepareStatement(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.setString(1,tipo);
			statement.setLong(2, numero);
			statement.setString(3,datacriada);
			statement.setString(4,autor);
			statement.setString(5,ipOrigem);
			statement.setInt(6,codigodebarras);
			statement.setInt(7,folhainicial);
			statement.setInt(8,folhafinal);
			statement.setString(9,impressoonde);
			statement.setInt(10,protocolo);
	        statement.executeUpdate();
	        conn.close();
	        


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		

		
		
		
		
		
	}
	
	
	
	public static void lista(ArrayList pedido) {
		copiaDados = pedido;
		lista.addMouseMotionListener(new MouseMotionAdapter(){  
			   public void mouseMoved(MouseEvent e){  
			        Point p = e.getPoint();   
			        int row = lista.rowAtPoint(p);  
			        int column = lista.columnAtPoint(p);  
			        lista.setToolTipText("<html><b><u>TIPO</b></u>: " + String.valueOf(lista.getValueAt(row,2))  + "</font></html>");  
			    }//end MouseMoved  
			}); // end MouseMotionAdapter 

		
		   String[] colunas = new String[] {
				   "Protocolo",
				   "Mat/Trans/Controle",
				   "Tipo",
				   "Usuario",
				   "IP Origem",
				   "Livro"
			


		    };  
		   
		   dados.add(new String[] { 
				   (String) pedido.get(0),
		   		(String) pedido.get(1),
		   		(String) pedido.get(2),
		   		(String) pedido.get(3),
		   		(String) pedido.get(4),
		   		(String) pedido.get(5)
	
		   });  
		
		   modelo = new SimpleTableModel(dados, colunas);
		   
		   
	
		   

       // lista.setDefaultRenderer(Object.class, new LineSelectionTableCellRenderer());
		   
		
		     TableCellRenderer renderer = new LineSelectionTableCellRenderer();
			   lista.setModel(modelo);
			   lista.getColumnModel().getColumn(0).setMinWidth(80);
			   lista.getColumnModel().getColumn(0).setMaxWidth(80);
			   
			   lista.getColumnModel().getColumn(4).setMinWidth(115);
			   lista.getColumnModel().getColumn(4).setMaxWidth(115);
			   
			   
			   lista.getColumnModel().getColumn(5).setMinWidth(1);
			   lista.getColumnModel().getColumn(5).setMaxWidth(1);

			   
			   
	    	 lista.setDefaultRenderer(lista.getColumnClass(3), renderer);
	    	 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			   lista.setFont(new Font("Serif", Font.BOLD, 35));

			 
		  

		   
		//lista.setModel(new SimpleTableModel(dados, colunas));
		   
		
	}



	public final String serverBusyMessage="Error -> Server is busy";
	  public ServerSocket ss;
	  public  volatile boolean busy=false;
	  public  void setFree(){busy=false;}
	  public void run(){
		  
		  jdig.getContentPane().add(scrolllist);
		  jdig.setUndecorated(true);
		  jdig.setDefaultCloseOperation(jdig.DISPOSE_ON_CLOSE);

		  jdig.pack();
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	       GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
	      Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
	       int x = (int) rect.getMaxX() - 700;
	      int y = (int) rect.getMaxY() - 600;
			 jdig.setSize(700,600);     
	      jdig.setLocation(x, 0);
	      
	      

	      jdig.setVisible(true);
		  
		  
		  
	    while(true){

	       try{Socket s=ss.accept();
	          /* if(busy){//if there is one connection processing.
	              ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
	              out.writeObject(serverBusyMessage);
	              out.flush();
	             continue;
	           } */
	           new Thread(new Provider(s, this)).start();
	           busy=false;
	       }catch(Exception e){}
	    }
	  }
	  public receptor(){try{ss=new ServerSocket(2014);}catch(Exception ex){}}

	  
	  public static  void escondetela() {
		  
		  jdig.setVisible(false);
		  
	  }
	  
	  public static  void mostratela() {
		  jdig.setVisible(true);
	  }
	  
	  public static  void apagarCertidao(int linha) {
		  

		   modelo.deleteX(linha);
		  
		//  lista.repaint();
	  
	  
	  }
	  
		
	  
	  
	  
	  
	}



