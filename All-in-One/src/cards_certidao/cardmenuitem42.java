package cards_certidao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.rtf.RTFEditorKit;

import com.sun.star.comp.helper.BootstrapException;

import ooo.connector.BootstrapSocketConnector;







import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XStorable;
import com.sun.star.uno.UnoRuntime;

import net.miginfocom.swing.MigLayout;

public class cardmenuitem42 {
	String caminho;
	String ipOrigem;
	String nomeUsuario;
	JPanel menuitem42 = new JPanel(new MigLayout("fillx"));
	public int totalpaginas = 0;
	String[] arraypath = new String[200];
	public int paginaatual = 1;
	JEditorPane rtfDisplay = new JEditorPane("text/rtf",null);
	int matricula = 0;
	String subdir = "0000";
	public File[] listOfFiles;
	public String[] caminhos = new String[500];
	RTFEditorKit rtf = new RTFEditorKit();  

	com.sun.star.text.XTextDocument aTextDocument = null;
    com.sun.star.lang.XComponent xComp = null;
    com.sun.star.util.XReplaceDescriptor xReplaceDescr = null;
    com.sun.star.util.XSearchDescriptor xSearchDescriptor = null;
    com.sun.star.util.XReplaceable xReplaceable = null;
    com.sun.star.uno.XComponentContext xContext = null;
    com.sun.star.lang.XMultiComponentFactory xMCF = null;
    Object oDesktop = null;
    com.sun.star.frame.XComponentLoader xCompLoader = null;
	XStorable xStorable = null;




	JLabel lblMatricula = new JLabel("Transcrição:");
	JTextField tfMatriula = new JTextField(10);
	JButton btnVisualizar = new JButton("Visualizar");
	JButton btnImprimir = new JButton("Imprimir Certidão");
	
	JButton btnAnterior = new JButton("");
	JButton btnProxima = new JButton("");

	JLabel folhaatual = new JLabel("0 /");
	JLabel folhafinal = new JLabel(" 0");
	JLabel aviso = new JLabel("  ");
	
    JScrollPane scrollImagem = new JScrollPane(rtfDisplay);
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

		btnImprimir.setEnabled(false);
		
		
		
		
		
		imgpanel.setLayout(new BorderLayout(0, 0));
		imgpanel.add(lblImageIcon);
		ImageIcon imageIcon = new ImageIcon(img);
		lblImageIcon.setIcon(imageIcon);
		scrollImagem.setSize(650,865);
		rtfDisplay.setSize(650,865);
		
		rtfDisplay.setContentType("text/rtf");
		
	    try {
			Image iconbtnanterior = ImageIO.read(getClass().getResource("/Imagens/pagina_anterior.png"));
			Image iconbtnproximo = ImageIO.read(getClass().getResource("/Imagens/pagina_proxima.png"));
			Image iconbtnimprimir = ImageIO.read(getClass().getResource("/Imagens/btn_imprimir.png"));
		   
			
			
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

		menuitem42.add(lblMatricula, "span, split 3, center");
		menuitem42.add(tfMatriula);
		tfMatriula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){    
					btnVisualizar.doClick();  
					}  
			}
		});
		
		
		
		menuitem42.add(btnVisualizar, "wrap");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(tfMatriula.getText().length() < 8) {
					JOptionPane.showMessageDialog(null, "Deve ser digitada o número completo da transcrição, ex: 31015000");
				} else { 
					pegatranscricao();

				}
				
				
			
			}
		});
		
		
		
		menuitem42.add(btnAnterior, "span, split 4, center");
		 btnAnterior.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    	//	paginaanterior();
		    	}
		    });
		menuitem42.add(folhaatual);
		menuitem42.add(folhafinal);
		menuitem42.add(btnProxima, "wrap");
		 btnProxima.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		//proximapagina();
		    	}
		    });
		
		
		menuitem42.add(aviso, "span, center, wrap");
		
		menuitem42.add(scrollImagem, "span, push, w 650, growy, center, wrap");
		menuitem42.add(lblProtocolo, "span, split 2, center");
		menuitem42.add(tfProtocolo);
		menuitem42.add(btnImprimir, "split 2, center");
		 menuitem42.add(cboxDestino);
		 btnImprimir.addActionListener(new ActionListener() {
			 
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(cboxDestino.getSelectedIndex() == 0) { 
			    		JOptionPane.showMessageDialog(null, "Selecione um Destino");
			    	} else {
		    		if(tfProtocolo.getText().isEmpty()) {
		    			JOptionPane.showMessageDialog(null, "Certidão deve ter um protocolo");
		    			tfProtocolo.setBackground(Color.yellow);
		    		} else {
		    			matricula = Integer.parseInt(tfMatriula.getText());
			    		EnviarCertidao();
			    		btnImprimir.setEnabled(false);
		    		}
		    		

		    	}
		    	}
		    });
		

		
		
		
		
		
		
	}
	
	public void pegatranscricao() {
		String matricula = String.format("%08d", Integer.parseInt(tfMatriula.getText()));
		System.out.println(matricula);
		int confere = Integer.parseInt(matricula.substring(3,matricula.length()));
		
		System.out.println(confere);
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
		
		boolean achou = false;
		File file = new File("F:\\registro_sql\\Livro02\\" + subdir);
		File[] arquivos = file.listFiles();
		
		int i = 0;
		
		caminho = null;
		while(i < arquivos.length) { 
			if(arquivos[i].getName().startsWith(matricula)) {
				
				caminho = arquivos[i].getPath();
				achou = true;
			
			

			}
			i++;
		}
		
		if(achou) {
			btnImprimir.setEnabled(true);
			try {
				Desktop.getDesktop().open(new File(caminho));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//ProcessBuilder pb = new ProcessBuilder("F://write.exe", caminho);
			//try {
			//	pb.start();
			//} catch (IOException e) {
			//	e.printStackTrace();
			//}
		} else { 
			JOptionPane.showMessageDialog(null, "Transcrição não encontrada.");
			btnImprimir.setEnabled(false);
		}
		
	}
	
 public void EnviarCertidao() {
	 /*
		String oooExeFolder = "F:/OpenOffice 4/program/";

	 String saidaPDF = "F:\\GeradorCertidoesNegativas\\feitas\\teste.pdf";

	 
	 
	  if ( caminho.indexOf("private:") != 0) {
          java.io.File sourceFile = new java.io.File(caminho);
          StringBuffer sbTmp = new StringBuffer("file:///");
          try {
			sbTmp.append(sourceFile.getCanonicalPath().replace('\\', '/').replace('\\', '/'));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          caminho = sbTmp.toString();
       }
   if ( saidaPDF.indexOf("private:") != 0) {
       java.io.File sourceFile = new java.io.File(saidaPDF);
       StringBuffer sbTmp = new StringBuffer("file:///");
       try {
			sbTmp.append(sourceFile.getCanonicalPath().replace('\\', '/'));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       saidaPDF = sbTmp.toString();
   }
	 try {
		xContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
	} catch (BootstrapException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}

	 xMCF =
          xContext.getServiceManager();

      try {
			oDesktop = xMCF.createInstanceWithContext(
			     "com.sun.star.frame.Desktop", xContext);
		} catch (com.sun.star.uno.Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

      xCompLoader =
          UnoRuntime.queryInterface(
           com.sun.star.frame.XComponentLoader.class, oDesktop);
      
	  try {
		try {
			xComp = xCompLoader.loadComponentFromURL(
			          caminho, "_blank", 0, new com.sun.star.beans.PropertyValue[0]);
		} catch (com.sun.star.lang.IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (com.sun.star.io.IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalArgumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  
	  
	  aTextDocument = UnoRuntime.queryInterface(
         com.sun.star.text.XTextDocument.class, xComp);
	 xStorable = 
          UnoRuntime.queryInterface(
          com.sun.star.frame.XStorable.class, aTextDocument );
 PropertyValue[] storeProps = new PropertyValue[0];
 storeProps = new PropertyValue[1];
 storeProps[0] = new PropertyValue();
 storeProps[0].Name = "FilterName";
 storeProps[0].Value = "writer_pdf_Export";

 PropertyValue[] aMediaDescriptor = new PropertyValue[1];
 aMediaDescriptor[0] = new PropertyValue();
 aMediaDescriptor[0].Name = "FilterName";
 aMediaDescriptor[0].Value = "writer_pdf_Export";
 

 
 try {
		xStorable.storeToURL(saidaPDF, aMediaDescriptor);
	} catch (com.sun.star.io.IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 aTextDocument.dispose();
	
	
	
	
	 */
	 
		 enviaCertidao enviar = new enviaCertidao();
		 enviar.setDestino(cboxDestino.getSelectedItem().toString());


		enviar.enviacaminho(tfProtocolo.getText(), matricula + "", "Transcrição", nomeUsuario, "");
		 
	 }
	 
	 
	 public void setUsuario(String nomeUsuario) {
		 this.nomeUsuario = nomeUsuario;
		 
	 }
	
	
	
	
	
	
	public JPanel getCard() {
		montaPanel();
		return menuitem42;
	}
	
}
