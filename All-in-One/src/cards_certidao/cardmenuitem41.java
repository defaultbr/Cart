package cards_certidao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.joda.time.DateTime;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cardmenuitem41 {
	String ipOrigem;
	String nomeUsuario;
	public int totalpaginas = 0;
	String[] arraypath = new String[200];
	public int paginaatual = 1;

	JPanel menuitem41 = new JPanel(new MigLayout("fillx"));
	int matricula = 0;
	String subdir = "0000";
	public File[] listOfFiles;
	public String[] caminhos = new String[500];


	JComboBox cboxLivro = new JComboBox();
	JLabel lblMatricula = new JLabel("Matricula:");
	JTextField tfMatriula = new JTextField(10);
	JButton btnVisualizar = new JButton("Visualizar");
	JButton btnImprimir = new JButton("Imprimir Certidão");
	
	JButton btnAnterior = new JButton("");
	JButton btnProxima = new JButton("");

	JLabel folhaatual = new JLabel("0 /");
	JLabel folhafinal = new JLabel(" 0");
	JLabel aviso = new JLabel("  ");
	
    JScrollPane scrollImagem = new JScrollPane();
    JPanel imgpanel = new JPanel();
	public JLabel lblImageIcon = new JLabel("");
	String img = "F:\\primeira.jpg";
	JLabel lblProtocolo = new JLabel("Protocolo:");
	JTextField tfProtocolo = new JTextField(10);
	
	
	JComboBox cboxDestino = new JComboBox();
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void montaPanel() {
		cboxDestino.addItem("Selecione o Destino para Impressão.");
		cboxDestino.addItem("Balcão");
		cboxDestino.addItem("Escreventes");
		
		
		imgpanel.setLayout(new BorderLayout(0, 0));
		imgpanel.add(lblImageIcon);
		ImageIcon imageIcon = new ImageIcon(img);
		lblImageIcon.setIcon(imageIcon);
		scrollImagem.setViewportView(imgpanel);
		//scrollImagem.setSize(650,865);
		
		
	    try {
			Image iconbtnanterior = ImageIO.read(getClass().getResource("/Imagens/pagina_anterior.png"));
			Image iconbtnproximo = ImageIO.read(getClass().getResource("/Imagens/pagina_proxima.png"));
			Image iconbtnimprimir = ImageIO.read(getClass().getResource("/Imagens/btn_imprimir.png"));
			btnImprimir.setEnabled(false);
		   
			
			
		    btnAnterior.setIcon(new ImageIcon(iconbtnanterior));
		    btnAnterior.setOpaque(false);
		    btnAnterior.setContentAreaFilled(false);
		    btnAnterior.setBorderPainted(false);
		    
		    
		    btnProxima.setIcon(new ImageIcon(iconbtnproximo));
		    btnProxima.setOpaque(false);
		    btnProxima.setContentAreaFilled(false);
		    btnProxima.setBorderPainted(false);
		    
		    
		   btnImprimir.setIcon(new ImageIcon(iconbtnimprimir));


		    

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    menuitem41.add(cboxLivro, "span, split 4, center");
	    cboxLivro.addItem("Livro02");
	    cboxLivro.addItem("Livro03");
		menuitem41.add(lblMatricula);
		menuitem41.add(tfMatriula);
		tfMatriula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){    
					btnVisualizar.doClick();  
					}  
			}
		});
		
		
		
		menuitem41.add(btnVisualizar, "wrap");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				aviso.setText(" ");
				totalpaginas = 0;
				paginaatual = 1;
				confereCaracteres(tfMatriula.getText());
			}
		});
		
		
		
		menuitem41.add(btnAnterior, "span, split 4, center");
		 btnAnterior.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		paginaanterior();
		    	}
		    });
		menuitem41.add(folhaatual);
		menuitem41.add(folhafinal);
		menuitem41.add(btnProxima, "wrap");
		 btnProxima.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		proximapagina();
		    	}
		    });
		
		
		menuitem41.add(aviso, "span, center, wrap");
		
		menuitem41.add(scrollImagem, "span, w 670, h 865, center, wrap");
		menuitem41.add(lblProtocolo, "span, split 2, center");
		menuitem41.add(tfProtocolo);
		menuitem41.add(btnImprimir, "split 2, center");
		 menuitem41.add(cboxDestino);

		 btnImprimir.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    	if(cboxDestino.getSelectedIndex() == 0) { 
		    		JOptionPane.showMessageDialog(null, "Selecione um Destino");
		    	} else {
		    		if(tfProtocolo.getText().isEmpty()) {
		    			JOptionPane.showMessageDialog(null, "Certidão deve ter um protocolo");
		    			tfProtocolo.setBackground(Color.yellow);
		    		} else {
		    			tfProtocolo.setBackground(Color.white);
		    		btnImprimir.setEnabled(false);
		    		EnviarCertidao();
		    		}
		    		
		    	}

		    	}
		    });
		 
		
		
		
		
		
	}
	
	public void  confereCaracteres(String matriculacheca) {

		if (matriculacheca.matches("[0-9]+")) {
//			JOptionPane.showMessageDialog(null, "Digitou certo capeão");
			matricula = Integer.parseInt(matriculacheca);
			System.out.println(matricula);
			limpaCaracteres(matricula + "");
			
		} else {
			JOptionPane.showMessageDialog(null, "Tem que digitar somente números");
		}

	}
	
	
	public void limpaCaracteres(String matricula) {
		int confere = Integer.parseInt(matricula);
		if(confere >= 0 && confere < 10) {
			 
			matricula = "00000" + confere;
			subdir = "0000";

		}
		if(confere > 9 && confere < 100) {
			matricula = "0000" + confere;
			subdir = "0000";

		}
		if(confere > 99 && confere < 1000) {
			matricula = "000" + confere;
			subdir = "0000";


		}
		if(confere > 999 && confere < 10000) {
			subdir = "000" + matricula.substring(0,1);
			matricula = "00" + confere;

		}
		if(confere > 9999 && confere < 100000) {
			subdir = "00" + matricula.substring(0,2);
			matricula = "0" + confere;

		}
		if(confere > 99999 && confere < 1000000) {

			subdir = "0" + matricula.substring(0,3);
		}
		
//		ConfereSeExiste(matricula);
//		JOptionPane.showMessageDialog(null, matricula);
		
		geraListaArquivos(subdir,matricula);
	}
	
	public void geraListaArquivos(String subdir, String matricula) {

		int naoachou = 0;
		String diretorio = "F:\\registro_sql\\Imagens\\" + cboxLivro.getSelectedItem().toString() +"\\" + subdir + "\\";
		String novaimagem = null;
		File folder = new File(diretorio);
		listOfFiles = folder.listFiles();
		if (!folder.exists()) { 
			JOptionPane.showMessageDialog(null,"Não existe o diretório ou matricula.");
			btnImprimir.setEnabled(false);
		} else {
//		JOptionPane.showMessageDialog(null, diretorio);
		for (int i = 0; i < listOfFiles.length; i++) {	
			
			String matsonome[] = listOfFiles[i].getName().split("\\.");
			
			for (int i2 = 0; i2 < matsonome.length; i2 = i2 +2) {
		
				
				
				
				if(matsonome[i2].matches(matricula)) {
					novaimagem = diretorio + matricula + ".001";
					
					
					
					caminhos[totalpaginas] = diretorio + matsonome[i2] + "." + matsonome[i2 + 1];
					totalpaginas++;
				
					
					
			//		System.out.println(matsonome[i2]);
			//		System.out.println(matsonome[i2+1]);
					//System.out.println(caminhos[0]);
				//	System.out.println(caminhos[posicao]);
				//	System.out.println(listOfFiles[i].getPath());
					naoachou++;

					
						
				
				}
				}
			
				
				
			}

		int geraarray = 0;
		int pagina = 1;
		while(geraarray < totalpaginas) {
			if(pagina < 10) {
			arraypath[geraarray] = diretorio + matricula + ".00" + pagina; 
			//System.out.println(arraypath[geraarray]);
			geraarray++;
			pagina++;
			}		
			if(pagina >= 10) {
			arraypath[geraarray] = diretorio + matricula + ".0" + pagina; 
			System.out.println(arraypath[geraarray]);
			geraarray++;
			pagina++;
			}


			
			}
		if (naoachou == 0 && folder.exists() == true) {
				UIManager.getDefaults().put("OptionPane.background",new Color(217,232,238));  
				UIManager.put ("Panel.background", new Color(217,232,238));
				JOptionPane.showMessageDialog(null,"Não existe a matrícula ");
				btnImprimir.setEnabled(false);
				
				
		
				


		}
		
		if(naoachou != 0) {
		//	System.out.println(caminhos[0]);
		EnviaLink(novaimagem);
		btnImprimir.setEnabled(true);
		}


			folhaatual.setText(Integer.toString(paginaatual) + " /");
			folhafinal.setText(Integer.toString(totalpaginas));

		}




	}
	public void EnviaLink(String link) {  
	  //  JOptionPane.showMessageDialog(null, "enviando o link " + link);  
		
		GeraImagem gi =  new GeraImagem(link, this);

	         
	 }  

	 public void alteraLabel(Image resizedImage) {  
		      if (lblImageIcon== null) {  
		         lblImageIcon = new JLabel(new ImageIcon(resizedImage));  
		      } else {  
		         lblImageIcon.setIcon(new ImageIcon(resizedImage));  
		      }  
		//Revalidação e repaint do componente  
		      this.lblImageIcon.revalidate();  
		      this.lblImageIcon.repaint();  
		   }  
	 //		 

	 public void proximapagina() {
			
		if(paginaatual == totalpaginas) {
			aviso.setText("Já está na Última Folha");
		} else {
			paginaatual++;
			EnviaLink(arraypath[paginaatual -1]);
			aviso.setText(" ");
		
			folhaatual.setText(Integer.toString(paginaatual) + " /");
			}
		}

	// }
	 public void paginaanterior() {
		if(paginaatual == 1) { 
			
			aviso.setText("Já está na Primeira Folha");

			
		} else {
			paginaatual--;
			EnviaLink(arraypath[paginaatual -1]);
			aviso.setText(" ");


			folhaatual.setText(Integer.toString(paginaatual) + " /");
		}

	 }
	
	
	
	 public void EnviarCertidao() {
		 
		 enviaCertidao enviar = new enviaCertidao();
		 enviar.setDestino(cboxDestino.getSelectedItem().toString());
		enviar.enviacaminho(tfProtocolo.getText(), matricula + "", "Matricula", nomeUsuario, cboxLivro.getSelectedItem().toString());
		 
	 }
	 
	 
	 public void setUsuario(String nomeUsuario) {
		 this.nomeUsuario = nomeUsuario;
		 
	 }
	
	
	
	
	
	
	
	public JPanel getCard() {
		montaPanel();
		
		return menuitem41;
	}
	
	
	
}
